package com.zjjzfy.order.service;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbPurchaseDetail;
import com.zjjzfy.pojo.TbPurchaseForm;
import com.zjjzfy.pojo.TbPurchaseFormExample;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pojo.dto.PurchaseFormDto;
import com.zjjzfy.pojo.dto.PurchaseFormExampleDto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Saintyun on 2019/3/13.
 */
public interface PurchaseService {

    List<TbPurchaseForm> findPurchaseOrderList(TbPurchaseFormExample example);

    List<TbPurchaseForm> selectAll();

    List<TbPurchaseDetail> findPurchaseDetailListById(Integer p_id);

    SupplyResult generPurchaseForm(List<Integer> ids, Integer purchaser_id,Integer adsId, Boolean orderCheck);

    SupplyResult generWillPurchaseForm(List<Integer> ids,List<Integer> ammounts,Integer purchaser_id);

    void updatePurchaseOrder(Integer id, TbPurchaseForm form);

    void updatePurchaseOrderDetail(Integer id, TbPurchaseDetail detail);

    SupplyResult payForPurchaseOrder(Integer id,Double totalPrice,Integer orgid, Boolean overpayment);

    /**
     * 通过订货单号判断订货单是否完成
     * @param purchaseFormId 订货单id
     * @return
     */
    Boolean checkPurchaseFormFinishByStatus(Integer purchaseFormId);

    /**
     * 通过剩余库存判断订货单是否完成,如果剩余库存都为0,则订货单完成,更新订货单状态为已完成
     * @param purchaseFormId
     * @return
     */
    Boolean checkPurchaseFormFinishByQuantity(Integer purchaseFormId);


    /**
     * 根据供货商id 获取 订货单id list
     * @param supplyId
     * @return
     */
    List<PurchaseFormDto> selectPurchaseBySupplyId(Integer supplyId);


    /**
     * 审核 订货单
     * @param id 订货单id
     * @param checkPersonId 审核人id
     * @param checkRemark 审核备注
     * @return
     */
    SupplyResult checkOrder(Integer id,Integer checkPersonId,String checkRemark);


    /**
     * 作废 订货单
     * @param id
     * @return
     */
    SupplyResult invalidOrder(Integer id,String remark, Boolean overpayment);
    /**
     * 通过自定义对象查看订货单
     * @param purchaseFormExampleDto
     * @return
     */
    PageInfo<TbPurchaseForm> selectPurchaseFormByExampleDto(PurchaseFormExampleDto purchaseFormExampleDto);

    /**
     * 通过订货方id查看订货方单
     * @param purchaseFormId
     * @return
     */
    TbPurchaseForm selectByPurchaseFormId(Integer purchaseFormId);

    /**
     * 通过订货单表id查看订货商品列表
     * @param purchaseFormId
     * @return
     */
    List<ProductDto> queryPurchaseFormProductList(Integer purchaseFormId);

    /**
     * 通过供货方id、订货单表id查看订货商品列表
     * @param purchaseFormId
     * @param supplyOrgId
     * @return
     */
    List<ProductDto> queryProductListBySupplyOrgId(Integer purchaseFormId, Integer supplyOrgId);

    /**
     * 通过订货 form id查询所有需要展示的数据
     */
    List<HashMap<String,Object>> queryPurchaseData(Integer pid);

    /**
     * 查询orgname
     * @param purchaseFormId
     * @return
     */
    List<String> queryOrderOrgName(Integer purchaseFormId);

    /**
     * 更改订货单状态
     * @param purchaseFormId
     * @param status
     * @return
     */
    void updatePurchaseFormStatus(Integer purchaseFormId, Byte status);

    /**
     * 更改订货单审核状态
     * @param purchaseFormId
     * @param status
     */
    void updatePurchaseFormCheckStatus(Integer purchaseFormId,Integer uid, Byte status);


    /**
     * 根据柜员id来找 未审核订单
     * @param purchaserIds
     * @return
     */
    PageInfo<PurchaseFormDto> selectUncheckedOrderWithPage(List<Integer> purchaserIds, Integer pageNo, Integer pageSize);

    /**
     * 根据供货方id查订货单
     * @param supplyOrgId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<PurchaseFormDto> selectSupplierPurchaseFormWithPage(Integer supplyOrgId, Integer pageNo, Integer pageSize,Integer status,String purchaseBillno,Boolean check,Integer purchaseFormId);

    List<PurchaseFormDto> selectSupplierPurchaseForm(Integer supplyOrgId,Integer status,String purchaseBillno,Boolean check,Integer purchaseFormId);


    List<HashMap<String,Object>> selectPurchaserInfo(Integer purchaserId);

    SupplyResult purchaseAgain(Integer formId);

    List<Map<String, Object>> getDeliverForm(String pBillno);

    /*选出本网点 所有预购商品单号*/
    PageInfo<Map<String,Object>> selectWillPurchaseListByOrgid(Integer orgid,Date start,Date end,Integer page,Integer size);

    /**
     * 查询订货单
     * @return
     */
    SupplyResult selectPurchaseOrder(Integer pageNo,Integer pageSize,Date starTime,Date endTime,Integer status,Integer orgId);


}
