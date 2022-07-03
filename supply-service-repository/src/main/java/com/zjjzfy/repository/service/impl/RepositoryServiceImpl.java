package com.zjjzfy.repository.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.CartStatus;
import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.IDUtils;
import com.zjjzfy.interfaces.*;
import com.zjjzfy.pojo.*;
import com.zjjzfy.repository.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: hsmz
 * @date: 2019/3/15 上午10:38
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private TbRepositoryMapper repositoryMapper;
    @Autowired
    private TbDeliverFormMapper deliverFormMapper;
    @Autowired
    private TbDeliverDetailMapper deliverDetailMapper;
    @Autowired
    private TbRepositoryRecordDetailMapper tbRepositoryRecordDetailMapper;
    @Autowired
    private TbRepositoryRecordMapper tbRepositoryRecordMapper;
    @Autowired
    private TbRepositoryMapper tbRepositoryMapper;
    @Autowired
    private TbCartMapper tbCartMapper;

    /**
     * 采购方确认收货商品入库
     *
     * @param deliverFormId 发货单id
     * @return
     */
    @Override
    public SupplyResult purchaserStockInputByDeliverFormId(Integer deliverFormId) {

        TbDeliverDetail deliverDetail = new TbDeliverDetail();
        deliverDetail.setDeliverFormId(deliverFormId);
        List<TbDeliverDetail> deliverDetailList = deliverDetailMapper.select(deliverDetail);
        TbDeliverForm deliverForm = deliverFormMapper.selectByPrimaryKey(deliverFormId);
        for (TbDeliverDetail detail : deliverDetailList) {
            TbRepository repository = new TbRepository();
            repository.setProductId(detail.getProductId());
            repository.setSupplierId(deliverForm.getPurchaserId());

            TbRepository inRepository = repositoryMapper.selectOne(repository);
            if(inRepository==null){
                //如果没有找到库存则插入一条
                repository.setQuantity(detail.getDeliverQuantity());
                repository.setQuantityTotal(detail.getDeliverQuantity());
                repository.setSupplyOrgId(deliverForm.getPurchaseOrgId());
                repository.setSupplierId(deliverForm.getPurchaserId());
                repository.setProductId(detail.getProductId());
                repositoryMapper.insertSelective(repository);
            }else {
                purchaserStockInput(detail.getProductId(),detail.getDeliverQuantity(),deliverForm.getPurchaserId());
            }
        }
        return SupplyResult.ok();
    }

    /**
     * 采购方入库
     *
     * @param productId
     * @param quantity
     * @param purchaserId
     * @return
     */
    @Override
    public SupplyResult purchaserStockInput(Integer productId, Integer quantity, Integer purchaserId) {
        repositoryMapper.updateInput(productId,quantity,purchaserId);
        return SupplyResult.ok();
    }

    /**
     * 采购方入库
     *
     * @param productIdList
     * @param quantityList
     * @param purchaserId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult purchaserStockInputList(List<Integer> productIdList, List<Integer> quantityList, Integer purchaserId) {
        for (int i=0; i<productIdList.size(); i++) {
            repositoryMapper.updateInput(productIdList.get(i),quantityList.get(i),purchaserId);
        }
        return SupplyResult.ok();
    }


    /**
     * 采购方出库
     *
     * @param productId
     * @param quantity
     * @param supplierId
     * @return
     */
    @Override
    public SupplyResult purchaserStockOutput(Integer productId, Integer quantity, Integer supplierId) {
        repositoryMapper.updateOutput(productId,quantity,supplierId);
        return SupplyResult.ok();
    }

    /**
     * 采购方批量出库
     *
     * @param cartList
     * @return
     */
    @Override
    public SupplyResult purchaserStockOutputBatch(List<TbCart> cartList, Integer supplierId) {
        repositoryMapper.purchaserStockOutputBatch(cartList,supplierId);
        return SupplyResult.ok();
    }


    /**
     * 供货方新增货品库存
     *
     * @param cartList   购物车列表
     * @return
     */
    @Override
    public SupplyResult stockInput(List<TbCart> cartList) {

        repositoryMapper.updateInputBatch(cartList);
        return SupplyResult.ok();
    }

    /**
     * 供货方减少货品库存
     *
     * @param cartList 购物车列表
     * @return
     */
    @Override
    public SupplyResult stockOutput(List<TbCart> cartList) {

        repositoryMapper.updateOutputBatch(cartList);
        return SupplyResult.ok();
    }

    /**
     * 判断货品库存足不足
     *
     * @param cartList
     * @return
     */
    @Override
    public SupplyResult checkStock(List<TbCart> cartList) {

        List<TbCart> list = null;

        List<TbCart> list2 = new ArrayList<>();
        HashMap map = new HashMap();

        if(null != cartList && cartList.size() > 0){

            for (int i = 0; i < cartList.size(); i++) {
                TbCartExample example = new TbCartExample();
                example.createCriteria().andSupplyOrgIdEqualTo(cartList.get(i).getSupplyOrgId()).andProductIdEqualTo(cartList.get(i).getProductId()).andStatusEqualTo(CartStatus.NORMAL.getStatus());
                list = tbCartMapper.selectByExample(example);
            }

           /* list.parallelStream().collect(Collectors.groupingBy(o->(o.getProductId()+o.getSupplyOrgId()), Collectors.toList())).forEach(
                    (id,transfer)->{
                        transfer.stream().reduce((a,b)->new TbCart(a.getProductId(),a.getSupplyOrgId(),(a.getProductQuality()+b.getProductQuality()))).ifPresent(list2::add);
                    }
            );*/

           if(list != null && list.size() > 0){
               map.put(list.get(0).getProductId() +"-"+ list.get(0).getSupplyOrgId(),list.get(0).getProductQuality());
               for (int i = 1; i < list.size(); i++) {
                   String k = ""+list.get(i).getProductId() + "-" +list.get(i).getSupplyOrgId();
                   if (map.containsKey(k)){
                       map.put(k,(int)map.get(k)+list.get(i).getProductQuality());
                   }
               }

               Iterator iterator = map.keySet().iterator();
               while (iterator.hasNext()){
                   String key = (String) iterator.next();
                   String[] str = key.split("-");
                   TbRepository repository = new TbRepository();
                   repository.setProductId(Integer.valueOf(str[0]));
                   repository.setSupplyOrgId(Integer.valueOf(str[1]));

                   if (tbRepositoryMapper.selectOne(repository).getQuantity() <= (Integer) map.get(key) ) {
                       return SupplyResult.build(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus(), ReturnMsg.NOT_ENOUGH_QUANTITY.getMsg());
                   }
               }

           }

        }



      /*  for (TbCart tbCart : cartList) {
            TbRepository repository = new TbRepository();
            repository.setProductId(tbCart.getProductId());
            repository.setSupplyOrgId(tbCart.getSupplyOrgId());

            if (tbRepositoryMapper.selectOne(repository).getQuantity() < tbCart.getProductQuality()) {
                return SupplyResult.build(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus(), ReturnMsg.NOT_ENOUGH_QUANTITY.getMsg());
            }
        }*/
        return SupplyResult.ok();
    }

    /**
     * 判断供货方(商户)是否有负库存
     * 供货方库存单位是机构
     * @param cartList
     * @return
     */
    @Override
    public Boolean checkFakeStock(List<TbCart> cartList) {

        for (TbCart tbCart : cartList) {
            TbRepository repository = new TbRepository();
            repository.setSupplyOrgId(tbCart.getSupplyOrgId());
            repository.setProductId(tbCart.getProductId());
            if (repositoryMapper.selectOne(repository).getQuantity() < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断采购方是否有负库存
     * 采购方库存单位是员工
     * @param cartList
     * @param supplierId
     * @return
     */
    @Override
    public Boolean checkPurchaserFakeStock(List<TbCart> cartList, Integer supplierId) {

        for (TbCart tbCart : cartList) {
            TbRepository repository = new TbRepository();
            repository.setSupplierId(supplierId);
            repository.setProductId(tbCart.getProductId());
            if (repositoryMapper.selectOne(repository).getQuantity() < 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LayuiData queryRepository(Integer supplyOrgId,String brand,Integer page,Integer limit,String goodsName) {
        if(supplyOrgId==-1){
            supplyOrgId = null;
        }

        if(goodsName != null && goodsName != ""){
            goodsName = "%"+goodsName+"%";
        }
        PageHelper.startPage(page, limit);
        SupplyResult result = SupplyResult.ok(new PageInfo(repositoryMapper.queryRepository(supplyOrgId,brand,goodsName)));
        LayuiData data = new LayuiData();
        if (result.isOK()) {
            PageInfo pageInfo = (PageInfo) result.getData();
            data.setCode(200);
            data.setCount(pageInfo.getTotal());
            data.setData(pageInfo.getList());
        } else {
            data.setCode(0);
        }
        return data;
    }

    /**
     * 查询所有供货商跟商品品牌用于库存条件筛选
     *
     * @return
     */
    @Override
    public SupplyResult selectRepositry() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("supplyOrg",repositoryMapper.selectSuppkyOrg());
        map.put("brand",repositoryMapper.selectBrand());
        return SupplyResult.ok(map);
    }

    @Transactional
    @Override
    public Boolean changeRepository(Integer supplyOrgId,Integer proId,Integer num) {
        Date date = new Date();
        String billno = IDUtils.genOrderId();

        Integer re = tbRepositoryMapper.ChangeOneProductRepository(supplyOrgId, proId, num,(num<0)?0:num);

        //供货商库存修改表
        TbRepositoryRecord rr = new TbRepositoryRecord();
        rr.setStatus((byte) 0);
        rr.setCreateDate(date);
        rr.setSupplyOrgId(supplyOrgId);
        rr.setBillno(billno);
        int i = tbRepositoryRecordMapper.insertUseGeneratedKeys(rr);

        //详情表
        TbRepositoryRecordDetail detail = new TbRepositoryRecordDetail();
        detail.setCreateDate(date);
        detail.setProductId(proId);
        //加库存为正 减库存为负 前端处理
        detail.setProductQuantity(num);
        detail.setRecordBillno(billno);
        detail.setRecordId(rr.getId());
        int insert = tbRepositoryRecordDetailMapper.insert(detail);

        if(i == 0 || insert ==0 ||re == 0){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void resetPurchaseRepository(List<TbRepository> repositoryList) {

        for (TbRepository repository : repositoryList) {
            TbRepositoryExample example = new TbRepositoryExample();
            example.createCriteria().andSupplierIdEqualTo(repository.getSupplierId()).andProductIdEqualTo(repository.getProductId());
            List<TbRepository> list = tbRepositoryMapper.selectByExample(example);
            if(list.size() == 0){
                tbRepositoryMapper.insertSelective(repository);
            }else {
                tbRepositoryMapper.updateByExampleSelective(repository, example);
            }
        }

    }

    @Override
    public void resetDeliverRepository(List<TbRepository> repositoryList) {

        for (TbRepository repository : repositoryList) {
            TbRepositoryExample example = new TbRepositoryExample();
            example.createCriteria().andSupplyOrgIdEqualTo(repository.getSupplyOrgId()).andProductIdEqualTo(repository.getProductId());
            List<TbRepository> list = tbRepositoryMapper.selectByExample(example);
            if(list.size() == 0){
                tbRepositoryMapper.insertSelective(repository);
            }else {
                tbRepositoryMapper.updateByExampleSelective(repository, example);
            }
        }
    }


}
