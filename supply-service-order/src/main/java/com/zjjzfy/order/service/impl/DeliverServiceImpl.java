package com.zjjzfy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.annotation.PagehelpService;
import com.zjjzfy.common.config.*;
import com.zjjzfy.common.entity.StockInResult;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.DateUtil;
import com.zjjzfy.common.utils.IDUtils;
import com.zjjzfy.interfaces.*;
import com.zjjzfy.order.asynctask.StockInAsyncTask;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.DeliverFormDto;
import com.zjjzfy.pojo.dto.DeliverFormExampleDto;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pojo.dto.ProductFormDto;
import com.zjjzfy.repository.service.RepositoryService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: hsmz
 * @date: 2019/3/13 下午2:36
 */
@Service
@Slf4j
public class DeliverServiceImpl implements DeliverService {


    @Autowired
    private TbDeliverFormMapper tbDeliverFormMapper;
    @Autowired
    private TbDeliverDetailMapper tbDeliverDetailMapper;
    @Autowired
    private PublicTableMapper publicTableMapper;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private StockInAsyncTask stockInAsyncTask;
    @Autowired
    private UserService userService;
    @Autowired
    private JfDeliverOrderMapper jfDeliverOrderMapper;

    /**
     * 生成发货单
     * 1.插入发货单数据
     * 2.插入发货单详情数据
     * 3.扣库存
     *
     * @param purchaserId     订货发人员Id
     * @param purchaserOrgId  订货方机构号Id
     * @param supplierId      供货方人员Id
     * @param supplyOrgId     供货方机构Id
     * @param purchaseBillno  相应订货单订单号
     * @param purchaseFormId  相应订货单id
     * @param deliverPersonId 发货人员 默认供货方人员Id
     * @param productFormDto  商品发货单列表
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult generateDeliverForm(Integer purchaserId, Integer purchaserOrgId, Integer supplierId, Integer supplyOrgId, String purchaseBillno, Integer purchaseFormId, Integer deliverPersonId, ProductFormDto productFormDto) {

        final TbDeliverForm deliverForm = new TbDeliverForm();

        String deliverBillno = IDUtils.genOrderId();
        Date nowDate = new Date();

        deliverForm.setDeliverBillno(deliverBillno);
        deliverForm.setPurchaserId(purchaserId);
        deliverForm.setSupplierId(supplierId);
        deliverForm.setPurchaseBillno(purchaseBillno);
        deliverForm.setPurchaseFormId(purchaseFormId);
        deliverForm.setStatus(DeliverStatus.UNCONFIRMED.getStatus());
        deliverForm.setSettleStatus(DeliverSettleStatus.UNCLEARED.getStatus());
        deliverForm.setDeliverPersonId(deliverPersonId);
        deliverForm.setDeliverDate(nowDate);
        deliverForm.setConfirmPersonId(null);
        deliverForm.setConfirmDate(null);
        deliverForm.setCreateDate(nowDate);
        deliverForm.setUpdateDate(nowDate);
        deliverForm.setSupplyOrgId(supplyOrgId);
        deliverForm.setPurchaseOrgId(purchaserOrgId);
        deliverForm.setTotalPrice(productFormDto.getTotalProductPrice());
        deliverForm.setTotalPriceBank(productFormDto.getTotalProductPurchasePrice());
        deliverForm.setTotalQuantity(productFormDto.getTotalProductNumber());
        deliverForm.setSettleStatusBank(DeliverSettleStatus.UNCLEARED.getStatus());
        tbDeliverFormMapper.insertUseGeneratedKeys(deliverForm);
        int deliverFormId = deliverForm.getId();
        if (productFormDto.getCartList().size() > 0) {
            for (TbCart tbCart : productFormDto.getCartList()) {
                final TbDeliverDetail deliverDetail = new TbDeliverDetail();

                deliverDetail.setProductId(tbCart.getProductId());
                deliverDetail.setDeliverBillno(deliverBillno);
                deliverDetail.setDeliverFormId(deliverFormId);
                deliverDetail.setDeliverQuantity(tbCart.getProductQuality());
                deliverDetail.setPurchasePrice(tbCart.getProductPrice());
                deliverDetail.setCreateDate(nowDate);
                deliverDetail.setUpdateDate(nowDate);
                tbDeliverDetailMapper.insert(deliverDetail);
                log.info(deliverDetail.toString());
            }
        }

        repositoryService.stockOutput(productFormDto.getCartList());

        Boolean result = repositoryService.checkFakeStock(productFormDto.getCartList());

        if (result) {
            throw new RuntimeException(ReturnMsg.FAKE_QUANTITY.getMsg());
        }

        return SupplyResult.ok();
    }

    /**
     * 订货方确认发货单
     * 1.先判断这笔发货单有没有接受过  先判断发货单对应订货单有没有完成
     * 2.确认收货单的时候判断货品有没有超过订货单详情里面商品的值
     * 3.更改发货单状态由未确认改成已确认
     * 4.确认收货后判断商品订货单有没有完成
     * 5.发货单中的商品进入订货方库存
     *
     * @param purchaseFormId 相应订货单id
     * @param deliverFormId  发货单id
     * @param openId         微信公众号登录openId
     * @param orderConfirm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult confirmDeliverForm(Integer purchaseFormId, Integer deliverFormId, String openId, Boolean orderConfirm) {

        TbDeliverForm tbDeliverForm = selectDeliverFormById(deliverFormId);
        if (tbDeliverForm.getStatus().equals(DeliverStatus.CONFIRMED.getStatus())) {
            return SupplyResult.build(300, "该发货单已经接收");
        }
        //1.判断订货单有没有完成
        Boolean result = purchaseService.checkPurchaseFormFinishByStatus(purchaseFormId);
        if (result) {
            return SupplyResult.build(ReturnStatus.PURCHASE_FORM_FINISHED.getStatus(), ReturnMsg.PURCHASE_FORM_FINISHED.getMsg());
        }

        publicTableMapper.updatePurchaseFormQuantityByDeliverFormId(deliverFormId);

        updateDeliverFormStatusByDeliverFormId(deliverFormId, DeliverStatus.CONFIRMED.getStatus());

        //这个地方需要改成异步
        purchaseService.checkPurchaseFormFinishByQuantity(purchaseFormId);

        repositoryService.purchaserStockInputByDeliverFormId(deliverFormId);

        if (orderConfirm) {
            Future<StockInResult> future = stockInAsyncTask.acceptStock(openId, deliverFormId);
            try {
                StockInResult stockInResult = future.get();
                if (!stockInResult.getStockInResult()) {
                    log.info("接收库存失败[{}]", stockInResult.getMsg());
                    throw new RuntimeException(stockInResult.getMsg());
                } else {
                    log.info("积分商城接口返回billNo[{}]", stockInResult.getBillNo());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("网络异常");
            } catch (ExecutionException e) {
                e.printStackTrace();
                throw new RuntimeException("网络异常");
            }
        }

        return SupplyResult.ok();
    }

    /**
     * 订货方拒绝发货单
     * 1.更改发货单状态为已拒绝
     * 2.退还相应库存
     *
     * @param deliverFormId 相应订货单id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult rejectDeliverForm(Integer deliverFormId) {

        publicTableMapper.rejectPurchaseFormByDeliverFormId(deliverFormId);

        updateDeliverFormStatusByDeliverFormId(deliverFormId, DeliverStatus.REJECTED.getStatus());

        return SupplyResult.ok();
    }

    /**
     * 发货单查询
     *
     * @param
     * @return
     */
    @Override
    public List<TbDeliverForm> selectDeliverFormNotAccept(Integer PurchaserId) {
        TbDeliverFormExample example = new TbDeliverFormExample();
        example.createCriteria().andPurchaserIdEqualTo(PurchaserId).andStatusEqualTo(DeliverStatus.UNCONFIRMED.getStatus());
        List<TbDeliverForm> TbDeliverForm = tbDeliverFormMapper.selectByExample(example);
        return TbDeliverForm;
    }

    /**
     * 通过订货方id查看订货单
     *
     * @param exampleDto
     * @return
     */
    @Override
    public PageInfo<TbDeliverForm> selectDeliverFormByPurchaseId(DeliverFormExampleDto exampleDto) {
        if (exampleDto.getPageNo() == null || exampleDto.getPageNo() < 1) {
            exampleDto.setPageNo(1);
        }
        if (exampleDto.getPageSize() == null || exampleDto.getPageSize() <= 0) {
            exampleDto.setPageSize(10);
        }
        PageHelper.startPage(exampleDto.getPageNo(), exampleDto.getPageSize());


        TbDeliverFormExample example = new TbDeliverFormExample();
        TbDeliverFormExample.Criteria c = example.createCriteria();
        if (exampleDto.getPurchaserId() != null) {
            c.andPurchaserIdEqualTo(exampleDto.getPurchaserId());
        }
        if (exampleDto.getSupplyOrgId() != null) {
            c.andSupplyOrgIdEqualTo(exampleDto.getSupplyOrgId());
        }
        Boolean dateSwitch = exampleDto.getStartDate() != null && exampleDto.getStartDate() != "" && exampleDto.getEndDate() != null && exampleDto.getEndDate() != "";
        if (dateSwitch) {
            c.andCreateDateBetween(DateUtil.stringToDateNoTime(exampleDto.getStartDate()), DateUtil.stringToDateNoTime(exampleDto.getEndDate()));
        }
        if (exampleDto.getStatus() != null) {
            c.andStatusEqualTo(exampleDto.getStatus());
        }
        example.setOrderByClause("ID DESC");
        PageInfo<TbDeliverForm> pageInfo = new PageInfo<>(tbDeliverFormMapper.selectByExample(example));

        return pageInfo;
    }

    /**
     * 发货单更新
     *
     * @param example
     * @param tbDeliverForm
     * @return
     */
    @Override
    public SupplyResult updateDeliverFormByExample(TbDeliverFormExample example, TbDeliverForm tbDeliverForm) {
        return null;
    }

    @Override
    /**
     * 通过发货单id更新发货单状态
     * @param deliverFormId 发货单id
     * @param Status 状态
     */
    public void updateDeliverFormStatusByDeliverFormId(Integer deliverFormId, Byte status) {
        TbDeliverForm deliverForm = new TbDeliverForm();
        deliverForm.setId(deliverFormId);
        deliverForm.setStatus(status);
        deliverForm.setConfirmDate(new Date());
        tbDeliverFormMapper.updateByPrimaryKeySelective(deliverForm);
    }

    /**
     * 通过订货单id查找订货单
     *
     * @param deliverFormId
     * @return
     */
    @Override
    public TbDeliverForm selectDeliverFormById(Integer deliverFormId) {
        return tbDeliverFormMapper.selectByPrimaryKey(deliverFormId);
    }

    /**
     * 通过供货表id查看供货单商品列表
     *
     * @param deliverFormId
     * @return
     */
    @Override
    public List<ProductDto> queryDeliverFormProductList(Integer deliverFormId) {

        return publicTableMapper.queryDeliverFormProductList(deliverFormId);
    }

    /**
     * 通过自定义查询对象查询订货单
     *
     * @param deliverFormExampleDto
     * @return
     */
    @Override
    public List<DeliverFormDto> selectByDeliverFormExampleDto(DeliverFormExampleDto deliverFormExampleDto) {
        return tbDeliverFormMapper.selectByDeliverFormExampleDto();
    }

    @Override
    public SupplyResult selectSellerDelivers(DeliverStatus ds, Integer sellerId, DeliverSettleStatus dss, Date startTime, Date endTime, Integer pageNo, Integer pageSize) {
        if ((startTime == null) ^ (endTime == null)) {
            return SupplyResult.build(2, "请同时选择开始时间和结束时间，或都不选择");
        }

        Byte status = null;
        if (ds != null) {
            status = ds.getStatus();
        }

        Byte settleStatus = null;
        if (dss != null) {
            settleStatus = dss.getStatus();
        }
        
        
        if (pageNo == null) {
            pageNo = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNo, pageSize);
        List<TbDeliverForm> delivers = tbDeliverFormMapper.selectSellerDelivers(status, sellerId, settleStatus, startTime, endTime);
        return SupplyResult.ok(new PageInfo(delivers));
    }

    @Override
    public SupplyResult selectPurchaseDelivers(DeliverStatus ds, Integer purchaseId, DeliverSettleStatus dss, Date startTime, Date endTime, Integer pageNo, Integer pageSize) {
        if ((startTime == null) ^ (endTime == null)) {
            return SupplyResult.build(2, "请同时选择开始时间和结束时间，或都不选择");
        }

        Byte status = null;
        if (ds != null) {
            status = ds.getStatus();
        }

        Byte settleStatus = null;
        if (dss != null) {
            settleStatus = dss.getStatus();
        }

        if (pageNo == null) {
            pageNo = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNo, pageSize);
        List<TbDeliverForm> delivers = tbDeliverFormMapper.selectPurchaseDelivers(status, purchaseId, settleStatus, startTime, endTime);
        return SupplyResult.ok(new PageInfo(delivers));
    }

    @Override
    public SupplyResult statisticsPriceGroupBySettleStatus(Integer sellerId, Date startTime, Date endTime) {
        if ((startTime == null) ^ (endTime == null)) {
            return SupplyResult.build(2, "请同时选择开始时间和结束时间，或都不选择");
        }

        List<Map<String, Object>> maps = tbDeliverFormMapper.statisticsPriceGroupBySettleStatus(sellerId, startTime, endTime);
        return SupplyResult.ok(maps);
    }

    @Override
    public SupplyResult statisticsPriceGroupBySettleStatusBank(Integer purchaseId, Date startTime, Date endTime) {
        if ((startTime == null) ^ (endTime == null)) {
            return SupplyResult.build(2, "请同时选择开始时间和结束时间，或都不选择");
        }

        List<Map<String, Object>> maps = tbDeliverFormMapper.statisticsPriceGroupBySettleStatusBank(purchaseId, startTime, endTime);
        return SupplyResult.ok(maps);
    }

    @Override
    public SupplyResult statisticsPriceGroupByStatus(Integer sellerId, Date startTime, Date endTime) {
        if ((startTime == null) ^ (endTime == null)) {
            return SupplyResult.build(2, "请同时选择开始时间和结束时间，或都不选择");
        }

        List<Map<String, Object>> maps = tbDeliverFormMapper.statisticsPriceGroupByStatus(sellerId, startTime, endTime);
        return SupplyResult.ok(maps);
    }

    @Override
    public SupplyResult statisticsPriceGroupByStatusBank(Integer purchaseId, Date startTime, Date endTime) {
        if ((startTime == null) ^ (endTime == null)) {
            return SupplyResult.build(2, "请同时选择开始时间和结束时间，或都不选择");
        }

        List<Map<String, Object>> maps = tbDeliverFormMapper.statisticsPriceGroupByStatusBank(purchaseId, startTime, endTime);
        return SupplyResult.ok(maps);
    }

    @Override
    public Integer branchLiquidationOrders(List<Integer> ids, Integer sellerId) {
        return tbDeliverFormMapper.branchLiquidationOrders(ids, sellerId);
    }

    @Override
    public Integer branchLiquidationOrdersBank(List<Integer> ids, Integer purchaseId) {
        return tbDeliverFormMapper.branchLiquidationOrdersBank(ids, purchaseId);
    }

    @Override
    public Integer trueLiquidationOrders(List<Integer> ids, Integer sellerId, Integer managerId) {
        return tbDeliverFormMapper.trueLiquidationOrders(ids, sellerId, managerId);
    }

    @Override
    public Integer trueLiquidationOrdersBank(List<Integer> ids, Integer purchaseId, Integer managerId) {
        return tbDeliverFormMapper.trueLiquidationOrdersBank(ids, purchaseId, managerId);
    }

    @Override
    public BigDecimal liquidationAmount(List<Integer> ids) {
        return tbDeliverFormMapper.liquidationAmount(ids);
    }

    @Override
    public List<TbDeliverForm> queryOrderByDeliverNo(String id) {
        TbDeliverFormExample example = new TbDeliverFormExample();
        example.createCriteria().andDeliverBillnoEqualTo(id);
        return tbDeliverFormMapper.selectByExample(example);
    }

    /**
     * 查询发货单 和 供货商名称
     *
     * @param
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> selectTbDeliverForm(DeliverFormExampleDto exampleDto) {
        if (exampleDto.getPageNo() == null || exampleDto.getPageNo() < 1) {
            exampleDto.setPageNo(1);
        }
        if (exampleDto.getPageSize() == null || exampleDto.getPageSize() <= 0) {
            exampleDto.setPageSize(10);
        }
        PageHelper.startPage(exampleDto.getPageNo(), exampleDto.getPageSize());

        List<Map<String, Object>> maps = tbDeliverFormMapper.selectDeliveFormById(exampleDto.getPurchaserId(), exampleDto.getSupplyOrgId()
                , exampleDto.getStatus());
        return new PageInfo<>(maps);
    }

    @Override
    public SupplyResult updateExportMark(TbDeliverForm tbDeliverForm) {
        tbDeliverFormMapper.updateByPrimaryKeySelective(tbDeliverForm);
        return SupplyResult.ok();
    }

    @Override
    public List<TbRepository> queryPurchaseRepository() {

        return tbDeliverFormMapper.queryPurchaseRepository();
    }

    /**
     * 增加发货单商品数量统计页面，已发货数量、确认数量、未确认数量
     *
     * @param
     * @return
     */
    @Override
    public SupplyResult queryDeliverOrderCount() {
        TbOrgInfo org = userService.getOrg();
        List<Map<String, Object>> maps = tbDeliverFormMapper.selectDeliverCount(org.getId());
        return SupplyResult.ok(maps);
    }

    /**
     * 统计自助兑换 网点 商品 价格
     *
     * @param startTime
     * @param endTime
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PagehelpService
    @Override
    public PageInfo selectExchangeOrderCount(Date startTime, Date endTime, Integer pageNo, Integer pageSize,Integer type,Integer orgId) {
        List<Map<String, Object>> maps = jfDeliverOrderMapper.selectExchangeOrderCount(startTime, endTime,type,orgId);
        return new PageInfo<>(maps);
    }

    /**
     * 统计自助兑换网点 详细商品数据
     *
     * @param startTime
     * @param endTime
     * @param orgId
     * @return
     */
    @Override
    public SupplyResult selectExchageOrderCountDataByOrgId(Date startTime, Date endTime,Integer orgId,Integer type) {
        List<Map<String, Object>> maps = jfDeliverOrderMapper.selectExchangeOrderDataByOrgId(startTime, endTime, orgId,type);
        return  SupplyResult.ok(new PageInfo<>(maps)) ;
    }

    @Override
    public List<TbRepository> queryDeliverRepository() {
        return tbDeliverFormMapper.queryDeliverRepository();
    }

}
