package com.zjjzfy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.annotation.PagehelpService;
import com.zjjzfy.common.config.*;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.DateUtil;
import com.zjjzfy.common.utils.IDUtils;
import com.zjjzfy.interfaces.*;
import com.zjjzfy.order.service.AddressService;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.OrgBalanceService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pojo.dto.PurchaseFormDto;
import com.zjjzfy.pojo.dto.PurchaseFormExampleDto;
import com.zjjzfy.repository.service.RepositoryService;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Saintyun on 2019/3/13.
 */
@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private TbPurchaseFormMapper tbPurchaseFormMapper;
    @Autowired
    private TbPurchaseDetailMapper tbPurchaseDetailMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private PublicTableMapper publicTableMapper;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private OrgBalanceService orgBalanceService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TbCartMapper tbCartMapper;
    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private UserOrgService userOrgService;
    @Autowired
    private  AddressService addressService;



    /*
     * 一般条件 查询订货单列表
     * */
    @Override
    public List<TbPurchaseForm> findPurchaseOrderList(TbPurchaseFormExample example) {
        return tbPurchaseFormMapper.selectByExample(example);
    }

    @Override
    public List<TbPurchaseForm> selectAll() {
        return tbPurchaseFormMapper.selectAll();
    }


    /*
     * 根据 订货单编号 查询 订货单详情（list-productid）
     * */
    @Override
    public List<TbPurchaseDetail> findPurchaseDetailListById(Integer p_id) {
        TbPurchaseDetailExample example = new TbPurchaseDetailExample();
        example.createCriteria().andPurchaseFormIdEqualTo(p_id);
        return tbPurchaseDetailMapper.selectByExample(example);
    }


    /**
     * 购物车 生成 订货单和 订货单详情
     *
     * @param ids          购物车表里面的id
     * @param purchaser_id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult generPurchaseForm(List<Integer> ids, Integer purchaser_id,Integer adsId, Boolean orderCheck) throws RuntimeException {
        //先更新下价格 进入界面去购物车界面去更新 价格
        tbCartMapper.updateByPurchaseId(purchaser_id);

        TbCartExample example = new TbCartExample();
        example.createCriteria().andIdIn(ids);
        List<TbCart> carts = cartService.selectCartByExample(example);

        SupplyResult result = repositoryService.checkStock(carts);
        if (result.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
            return result;
        }
        //=========================================
        TbPurchaseForm form = new TbPurchaseForm();
        //=========================================
        BigDecimal count = BigDecimal.ZERO;
        Integer totalquantity = 0;
        String billno = IDUtils.genOrderId();

        for (TbCart cart : carts) {
            count = count.add(cart.getProductPrice().multiply(new BigDecimal(cart.getProductQuality())));
            totalquantity += cart.getProductQuality();
        }
        form.setPurchaserId(purchaser_id);
        TbUserInfo user = userService.findUserByUserId(purchaser_id);
        if (user != null) {
            form.setPurchaseOrgId(user.getOrgid());
        }
        form.setPurchaseBillno(billno);
        form.setCreateDate(new Date());
        form.setTotalPrice(count);
        form.setStatus(PurchaseStatus.UNPAID.getStatus());
        //自动审核
        //TODO 如果是永康农商行则默认是未确认 如果是台州供货则默认是已确认
        if(orderCheck){
            form.setCheckStatus(PurchaseCheckStatus.checked.getStatus());
        }else {
            form.setCheckStatus(PurchaseCheckStatus.uncheck.getStatus());
        }

        form.setTotalQuantity(totalquantity);
        //=========================================
            form = formSetAds(adsId, form);
        //=========================================
        tbPurchaseFormMapper.insertUseGeneratedKeys(form);
        int id = form.getId();


        List<TbPurchaseDetail> details = new ArrayList<>();
        List<Integer> productIds = new ArrayList<>();
        for (TbCart cart : carts) {
            TbPurchaseDetail detail = new TbPurchaseDetail();
            detail.setCreateDate(new Date());
            detail.setStatus(PurchaseDetailStatus.unfinished.getStatus());
            detail.setProductId(cart.getProductId());
            detail.setPurchaseBillno(billno);
            detail.setPurchaseFormId(id);
            detail.setPurchasePrice(cart.getProductPrice());
            detail.setSurplusQuantity(cart.getProductQuality());
            detail.setPurchaseQuantity(cart.getProductQuality());
            detail.setStorageQuantity(0);
            detail.setOnQuality(0);
            detail.setProductColor(cart.getProductColor());
            details.add(detail);
            productIds.add(cart.getProductId());
        }
        tbPurchaseDetailMapper.insertList(details);
        cartService.clearCart(purchaser_id, productIds, CartStatus.ORDER_GENERRATED.getStatus());
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg(), id);
        //return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg(), -1);
    }
    /**
     *mask
     */
    public TbPurchaseForm formSetAds(Integer adsId, TbPurchaseForm form) {
        TbUserInfo userInfo = userService.getCurrentTbUserInfo();
        return addressService.formSetAds(userInfo.getUid(),adsId,form,false);
    }
//    public TbPurchaseDetail dtlSetAds(Integer adsId, TbPurchaseDetail form) {
//        form.setSendAddressId(adsId);
//        form.setRcvAddressId(adsId);
//        form.setRcvAddress(adsId.toString());
//        return form;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult generWillPurchaseForm(List<Integer> ids, List<Integer> ammounts, Integer purchaser_id) throws RuntimeException  {
        TbProductExample example = new TbProductExample();
        example.createCriteria().andIdIn(ids);
        List<TbProduct> products = tbProductMapper.selectByExample(example);
        List<TbCart> carts = new ArrayList<>();
        for(int i=0;i<products.size();i++){
            TbCart cart = new TbCart();
            cart.setProductId(products.get(i).getId());
            cart.setSupplyOrgId(products.get(i).getSupplyOrgId());
            cart.setProductQuality(ammounts.get(i));
            carts.add(cart);
        }
        SupplyResult result = repositoryService.checkStock(carts);
        if (result.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
            return result;
        }
        TbPurchaseForm form = new TbPurchaseForm();
        BigDecimal count = BigDecimal.ZERO;
        Integer totalquantity = 0;
        String billno = IDUtils.genOrderId();

        for (int i=0;i<products.size();i++) {
            count = count.add(products.get(i).getPurchasePrice().multiply(new BigDecimal(ammounts.get(i))));
            totalquantity += ammounts.get(i);
        }
        form.setPurchaserId(purchaser_id);
        TbUserInfo user = userService.findUserByUserId(purchaser_id);
        if (user != null) {
            form.setPurchaseOrgId(user.getOrgid());
        }
        form.setPurchaseBillno(billno);
        form.setCreateDate(new Date());
        form.setTotalPrice(count);
        form.setStatus(PurchaseStatus.UNPAID.getStatus());
        //自动审核
        form.setCheckStatus(PurchaseCheckStatus.checked.getStatus());
        form.setTotalQuantity(totalquantity);
        tbPurchaseFormMapper.insertUseGeneratedKeys(form);
        int id = form.getId();


        List<TbPurchaseDetail> details = new ArrayList<>();
        for (int i=0;i< products.size(); i++) {
            TbPurchaseDetail detail = new TbPurchaseDetail();
            detail.setCreateDate(new Date());
            detail.setStatus(PurchaseDetailStatus.unfinished.getStatus());
            detail.setProductId(products.get(i).getId());
            detail.setPurchaseBillno(billno);
            detail.setPurchaseFormId(id);
            detail.setPurchasePrice(products.get(i).getPurchasePrice());
            detail.setSurplusQuantity(ammounts.get(i));
            detail.setPurchaseQuantity(ammounts.get(i));
            detail.setStorageQuantity(0);
            detail.setOnQuality(0);
            details.add(detail);
        }
        tbPurchaseDetailMapper.insertList(details);
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg(), id);
    }


    /**
     * 更新 订货单 （tb_purchase_form）
     *
     * @param id   订货单id
     * @param form 订货单实体
     */
    @Override
    public void updatePurchaseOrder(Integer id, TbPurchaseForm form) {
        form.setId(id);
        tbPurchaseFormMapper.updateByPrimaryKeySelective(form);
    }


    /*
     * 更新 订货单详情 （tb_purchase_detail）
     * */
    @Override
    public void updatePurchaseOrderDetail(Integer id, TbPurchaseDetail detail) {
        detail.setId(id);
        tbPurchaseDetailMapper.updateByPrimaryKeySelective(detail);
    }

    /**
     * 立即付款
     *
     * @param id
     * @param totalPrice
     * @param orgid
     * @return
     */
    @Override
    @Transactional
    public SupplyResult payForPurchaseOrder(Integer id, Double totalPrice, Integer orgid, Boolean overpayment) {
        SupplyResult result = new SupplyResult();
        //需要判断 购物车的状态
        //判断金额 是否足够
        SupplyResult re = orgBalanceService.usedOrgBalance(orgid, new BigDecimal(totalPrice), overpayment);
        if (re.getStatus() == 300) {
            return re;
        }
        updatePurchaseFormStatus(id, PurchaseStatus.UNFINISHED.getStatus());
        TbUserInfo user = userService.getCurrentTbUserInfo();
        SupplyResult r = userOrgService.getUserOrgUserId(user.getUid());
        //是否有相应审核权限
        if (!userOrgService.getUserOrgUserId(user.getUid()).getStatus().equals(ReturnStatus.NOT_AUTHORIZATION.getStatus())) {
            //有审核权限
            System.out.println("有权限");

            updatePurchaseFormCheckStatus(id, user.getUid(), PurchaseCheckStatus.checked.getStatus());
        }

        return SupplyResult.ok();
    }


    /**
     * 通过订货单号判断订货单是否完成
     * Created by hsmz
     *
     * @param purchaseFormId 订货单id
     * @return
     */
    @Override
    public Boolean checkPurchaseFormFinishByStatus(Integer purchaseFormId) {
        TbPurchaseForm purchaseForm = tbPurchaseFormMapper.selectByPrimaryKey(purchaseFormId);
        Boolean result = purchaseForm.getStatus() == PurchaseStatus.FINISHED.getStatus() || purchaseForm.getStatus() == PurchaseStatus.CLOSED.getStatus();
        return result;
    }

    /**
     * 通过剩余库存判断订货单是否完成,如果剩余库存都为0,则订货单完成,更新订货单状态为已完成
     * Created by hsmz
     *
     * @param purchaseFormId
     * @return
     */
    @Override
    public Boolean checkPurchaseFormFinishByQuantity(Integer purchaseFormId) {
        TbPurchaseDetail purchaseDetail = new TbPurchaseDetail();
        purchaseDetail.setPurchaseFormId(purchaseFormId);
        List<TbPurchaseDetail> purchaseDetailList = tbPurchaseDetailMapper.select(purchaseDetail);
        for (TbPurchaseDetail tbPurchaseDetail : purchaseDetailList) {
            if (tbPurchaseDetail.getSurplusQuantity() != 0) {
                return false;
            }
        }
        TbPurchaseForm purchaseForm = new TbPurchaseForm();
        purchaseForm.setId(purchaseFormId);
        purchaseForm.setStatus(PurchaseStatus.FINISHED.getStatus());
        tbPurchaseFormMapper.updateByPrimaryKeySelective(purchaseForm);
        log.info("订货单:" + purchaseFormId + "完成");
        return true;
    }

    @Override
    public List<PurchaseFormDto> selectPurchaseBySupplyId(Integer supplyOrgId) {
        List<Integer> ids = tbPurchaseFormMapper.selectPurchaseBySupplyOrgId(supplyOrgId);
        return tbPurchaseFormMapper.deliverSelectPurchaseDto(ids, PurchaseCheckStatus.checked.getStatus());
    }

    /**
     * 审核 订货单
     *
     * @param id            订货单id
     * @param checkPersonId 审核人id
     * @param checkRemark   审核备注
     * @return
     */
    @Override
    public SupplyResult checkOrder(Integer id, Integer checkPersonId, String checkRemark) {
        TbPurchaseFormExample e = new TbPurchaseFormExample();
        e.createCriteria().andIdEqualTo(id);
        TbPurchaseForm form = new TbPurchaseForm();
        form.setCheckStatus(PurchaseCheckStatus.checked.getStatus());
        form.setUpdateDate(new Date());
        form.setCheckPersonId(checkPersonId);
        form.setCheckRemark(checkRemark);
        form.setCheckDate(new Date());
        tbPurchaseFormMapper.updateByExampleSelective(form, e);
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }


    /**
     * 作废 订货单
     * 退还 未发货金额
     *
     * @param id 订货单id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult invalidOrder(Integer id, String remark, Boolean overpayment) {
        Integer orgid = userService.getOrg().getId();
        TbPurchaseFormExample e = new TbPurchaseFormExample();
        e.createCriteria().andIdEqualTo(id);
        TbPurchaseForm form = selectByPurchaseFormId(id);
        BigDecimal count = BigDecimal.ZERO;

        log.info("订货单订单作废 作废单号："+id);

        /*if (form.getCheckStatus().equals(PurchaseCheckStatus.uncheck.getStatus())) {*/
            //未审核状态下作废
            TbPurchaseDetail detail = new TbPurchaseDetail();
            detail.setPurchaseFormId(id);
            List<TbPurchaseDetail> details = tbPurchaseDetailMapper.select(detail);
            for (int i = 0; i < details.size(); i++) {
                count = count.add(new BigDecimal(details.get(i).getSurplusQuantity()).multiply(details.get(i).getPurchasePrice()));
            }
       /* }else{
            return SupplyResult.build(ReturnStatus.FAILURE.getStatus(),"已审核订单不能作废!");
        }*/
        SupplyResult result = orgBalanceService.usedOrgBalance(orgid, count.multiply(BigDecimal.valueOf(-1)),overpayment);
        if (!result.getStatus().equals(ReturnStatus.SUCCESS.getStatus())) {
            return result;
        }
        form.setCheckRemark("[作废]" + remark);
        form.setStatus(PurchaseStatus.CLOSED.getStatus());
        tbPurchaseFormMapper.updateByExampleSelective(form, e);
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }


    /**
     * 通过自定义查询对象查看订货单(分页查询)
     *
     * @param exampleDto
     * @return
     */
    @Override
    public PageInfo<TbPurchaseForm> selectPurchaseFormByExampleDto(PurchaseFormExampleDto exampleDto) {

        if (exampleDto.getPageNo() == null || exampleDto.getPageNo() < 1) {
            exampleDto.setPageNo(1);
        }

        if (exampleDto.getPageSize() == null || exampleDto.getPageSize() <= 0) {
            exampleDto.setPageSize(10);
        }
        PageHelper.startPage(exampleDto.getPageNo(), exampleDto.getPageSize());

        TbPurchaseFormExample example = new TbPurchaseFormExample();
        TbPurchaseFormExample.Criteria c = example.createCriteria();
        if (exampleDto.getPurchaserId() != null) {
            c.andPurchaserIdEqualTo(exampleDto.getPurchaserId());
        }

        Boolean dateSwitch = exampleDto.getStartDate() != null && exampleDto.getStartDate() != "" && exampleDto.getEndDate() != null && exampleDto.getEndDate() != "";
        if (dateSwitch) {
            c.andCreateDateBetween(DateUtil.stringToDateNoTime(exampleDto.getStartDate()), DateUtil.stringToDateNoTime(exampleDto.getEndDate()));
        }
        if (exampleDto.getStatus() != null) {
            c.andStatusEqualTo(exampleDto.getStatus());
        }
        example.setOrderByClause("create_date desc");
        PageInfo<TbPurchaseForm> pageInfo = null;
        //代付款显示的原来的逻辑
        pageInfo = new PageInfo<>(tbPurchaseFormMapper.selectByExample(example));
        return pageInfo;
    }

    /**
     * 通过订货方id查看订货方单
     *
     * @param purchaseFormId
     * @return
     */
    @Override
    public TbPurchaseForm selectByPurchaseFormId(Integer purchaseFormId) {

        return tbPurchaseFormMapper.selectByPrimaryKey(purchaseFormId);
    }

    /**
     * 通过订货方id查看订货商品列表
     *
     * @param purchaseFormId
     * @return
     */
    @Override
    public List<ProductDto> queryPurchaseFormProductList(Integer purchaseFormId) {

        return publicTableMapper.queryPurchaseFormProductList(purchaseFormId);
    }

    /**
     * 通过供货方id、订货单表id查看订货商品列表
     *
     * @param purchaseFormId
     * @param supplyOrgId
     * @return
     */
    @Override
    public List<ProductDto> queryProductListBySupplyOrgId(Integer purchaseFormId, Integer supplyOrgId) {
        return publicTableMapper.queryProductListBySupplyOrgId(purchaseFormId, supplyOrgId);
    }

    @Override
    public List<HashMap<String, Object>> queryPurchaseData(Integer pid) {
        return publicTableMapper.queryPurchaseData(pid);
    }

    @Override
    public List<String> queryOrderOrgName(Integer purchaseFormId) {
        return publicTableMapper.queryOrderOrgName(purchaseFormId);
    }

    /**
     * 更改订货单状态
     *
     * @param purchaseFormId
     * @param status
     * @return
     */
    @Override
    public void updatePurchaseFormStatus(Integer purchaseFormId, Byte status) {

        TbPurchaseForm purchaseForm = new TbPurchaseForm();
        purchaseForm.setId(purchaseFormId);
        purchaseForm.setStatus(status);
        tbPurchaseFormMapper.updateByPrimaryKeySelective(purchaseForm);
    }

    /**
     * 更改订货单审核状态
     *
     * @param formId
     * @param status
     */
    @Override
    public void updatePurchaseFormCheckStatus(Integer formId, Integer uid, Byte status) {
        TbPurchaseForm purchaseForm = new TbPurchaseForm();
        purchaseForm.setId(formId);
        purchaseForm.setCheckDate(new Date());
        purchaseForm.setCheckPersonId(uid);
        purchaseForm.setCheckRemark("[有审核权限，自动审核]");
        purchaseForm.setCheckStatus(status);
        tbPurchaseFormMapper.updateByPrimaryKeySelective(purchaseForm);
    }

    @Override
    public PageInfo<PurchaseFormDto> selectUncheckedOrderWithPage(List<Integer> purchaserIds, Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }

        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<PurchaseFormDto> pageInfo = new PageInfo<>(tbPurchaseFormMapper.selectPurchaseDto(purchaserIds, PurchaseStatus.UNFINISHED.getStatus(), PurchaseCheckStatus.uncheck.getStatus()));
        return pageInfo;
    }

    @Override
    public PageInfo<PurchaseFormDto> selectSupplierPurchaseFormWithPage(Integer supplyOrgId, Integer pageNo, Integer pageSize,Integer status,String purchaseBillno,Boolean check,Integer purchaseFormId) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        if(purchaseBillno == ""){
            purchaseBillno =null;
        }
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<PurchaseFormDto> pageInfo = new PageInfo<>(tbPurchaseFormMapper.selectPurchaseFormByStatus(status,supplyOrgId,purchaseBillno,check,purchaseFormId));
        return pageInfo;
    }

    @Override
    public List<PurchaseFormDto> selectSupplierPurchaseForm(Integer supplyOrgId, Integer status, String purchaseBillno, Boolean check, Integer purchaseFormId) {
        return tbPurchaseFormMapper.selectPurchaseFormByStatus(status,supplyOrgId,purchaseBillno,check,purchaseFormId);
    }

    @Override
    public List<HashMap<String, Object>> selectPurchaserInfo(Integer purchaserId) {
        return publicTableMapper.selectPurchaserInfo(purchaserId);
    }

    @Override
    public SupplyResult purchaseAgain(Integer formId) {
        TbUserInfo userInfo = userService.getCurrentTbUserInfo();
        //选出detail
        TbPurchaseDetail detail = new TbPurchaseDetail();
        detail.setPurchaseFormId(formId);
        List<TbPurchaseDetail> details = tbPurchaseDetailMapper.select(detail);
        List<TbCart> newcarts = new ArrayList<>();
        for (TbPurchaseDetail detail1 : details) {
            //1.判断是否下架
            TbProduct p = new TbProduct();
            p.setId(detail1.getProductId());
            List<TbProduct> products = tbProductMapper.select(p);
            if (products.size() > 0 && products.get(0).getProductStatus().equals(ProductStatus.OFF.getStatus())) {
                return SupplyResult.build(ReturnStatus.PRODUCT_OFF.getStatus(), ReturnMsg.PRODUCT_OFF.getMsg());
            }
            TbProduct product = products.get(0);
            //2.根据 purchaseid 判断是否有相同产品
            TbCartExample example = new TbCartExample();
            example.createCriteria()
                    .andPurchaserIdEqualTo(userInfo.getUid())
                    .andProductIdEqualTo(detail1.getProductId())
                    .andStatusEqualTo(CartStatus.NORMAL.getStatus());
            List<TbCart> carts = tbCartMapper.selectByExample(example);
            if (carts.size() == 0) {
                //判断库存是否充足
                TbCart cart = new TbCart();
                cart.setProductId(detail1.getProductId());
                cart.setProductPrice(product.getPrice());
                cart.setProductQuality(detail1.getPurchaseQuantity());
                cart.setStatus(CartStatus.NORMAL.getStatus());
                cart.setSupplyOrgId(product.getSupplyOrgId());
                cart.setCreateDate(new Date());
                cart.setPurchaserId(userInfo.getUid());

                SupplyResult r = repositoryService.checkStock(new ArrayList<TbCart>() {{
                    add(cart);
                }});
                if (r.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
                    return r;
                }
                tbCartMapper.insertSelective(cart);
            } else {
                //判断库存是否充足
                //判断count是不是为
                TbCart cart = new TbCart();
                cart.setId(carts.get(0).getId());
                cart.setProductQuality((carts.get(0).getProductQuality() + detail1.getPurchaseQuantity()));
                cart.setProductId(detail1.getProductId());
                cart.setSupplyOrgId(product.getSupplyOrgId());
                SupplyResult r = repositoryService.checkStock(new ArrayList<TbCart>() {{
                    add(cart);
                }});
                if (r.getStatus().equals(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus())) {
                    return r;
                }
                tbCartMapper.updateByPrimaryKeySelective(cart);
            }
        }
        return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg());
    }

    @Override
    public List<Map<String, Object>> getDeliverForm(String pBillno) {
        List<Map<String, Object>> maps = publicTableMapper.deliverFromDetail(pBillno);
        return maps;
    }

    @Override
    public PageInfo<Map<String,Object>> selectWillPurchaseListByOrgid(Integer orgid,Date start,Date end,Integer pageNo,Integer pageSize) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(publicTableMapper.selectWillPurchaseListByOrgid(orgid,start,end));
        return pageInfo;
    }

    /**
     * 查询订货单
     *
     * @return
     */
    @PagehelpService
    @Override
    public SupplyResult selectPurchaseOrder(Integer pageNo,Integer pageSize,Date starTime,Date endTime,Integer status,Integer orgId) {

        Byte s = null;
        if(null != status){
            s = (byte) status.intValue();
        }
        List<Map<String, Object>> maps = tbPurchaseFormMapper.selectPurchaseOrder(starTime, endTime, s, orgId);
        return SupplyResult.ok(new PageInfo<>(maps));
    }
}
