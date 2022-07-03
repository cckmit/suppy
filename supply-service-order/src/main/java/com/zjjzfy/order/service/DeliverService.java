package com.zjjzfy.order.service;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.DeliverSettleStatus;
import com.zjjzfy.common.config.DeliverStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.*;
import com.zjjzfy.pojo.dto.DeliverFormDto;
import com.zjjzfy.pojo.dto.DeliverFormExampleDto;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pojo.dto.ProductFormDto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发货服务
 *
 * @author: hsmz
 * @date: 2019/3/13 上午11:08
 */
public interface DeliverService {

    /**
     * 生成发货单
     *
     * @param purchaserId     订货方人员Id
     * @param purchaserOrgId  订货方机构Id
     * @param supplierId      供货方人员Id
     * @param supplyOrgId     供货方机构号ID
     * @param purchaseBillno  相应订货单订单号
     * @param purchaseFormId  相应订货单id
     * @param deliverPersonId 发货人员 默认供货方人员Id
     * @param productFormDto  商品发货单列表
     * @return
     */
    SupplyResult generateDeliverForm(Integer purchaserId, Integer purchaserOrgId, Integer supplierId, Integer supplyOrgId, String purchaseBillno, Integer purchaseFormId, Integer deliverPersonId, ProductFormDto productFormDto);

    /**
     * 订货方确认发货单
     *
     * @param purchaseFormId 相应订货单id
     * @param deliverFormId  相应发货单id
     * @param openId 微信公众号登录openId
     * @param orderConfirm 永康确认收货后同步到老的积分系统
     * @return
     */
    SupplyResult confirmDeliverForm(Integer purchaseFormId, Integer deliverFormId, String openId, Boolean orderConfirm);

    /**
     * 订货方拒绝发货单
     *
     * @param deliverFormId 相应订货单id
     * @return
     */
    SupplyResult rejectDeliverForm(Integer deliverFormId);

    /**
     * 发货单数量查询  未确定的
     *
     * @param
     * @return
     */
    List<TbDeliverForm> selectDeliverFormNotAccept(Integer purchaserId);

    /**
     * 通过下面参数查看发货单
     *
     * @param deliverFormExampleDto 自定义发货单条件
     * @return
     */
    PageInfo<TbDeliverForm> selectDeliverFormByPurchaseId(DeliverFormExampleDto deliverFormExampleDto);


    /**
     * 发货单更新
     *
     * @param example
     * @param tbDeliverForm
     * @return
     */
    SupplyResult updateDeliverFormByExample(TbDeliverFormExample example, TbDeliverForm tbDeliverForm);

    /**
     * 通过发货单id更新发货单状态
     *
     * @param deliverFormId 发货单id
     * @param status        状态
     */
    void updateDeliverFormStatusByDeliverFormId(Integer deliverFormId, Byte status);

    /**
     * 通过订货单id查找订货单
     *
     * @param deliverFormId
     * @return
     */
    TbDeliverForm selectDeliverFormById(Integer deliverFormId);

    /**
     * 通过供货表id查看供货单商品列表
     *
     * @param deliverFormId
     * @return
     */
    List<ProductDto> queryDeliverFormProductList(Integer deliverFormId);

    /**
     * 通过自定义查询对象查询订货单
     *
     * @param deliverFormExampleDto
     * @return
     */
    List<DeliverFormDto> selectByDeliverFormExampleDto(DeliverFormExampleDto deliverFormExampleDto);


    /*查询商户发货单--已被确认的
     * sellerId 商户ID 即org_info
     * dss  发货单清算状态
     * startTime  发货单确认时间
     * endTime  发货单确认时间
     * */
    SupplyResult selectSellerDelivers(DeliverStatus ds, Integer sellerId, DeliverSettleStatus dss, Date startTime, Date endTime, Integer pageNo, Integer pageSize);

    SupplyResult selectPurchaseDelivers(DeliverStatus ds, Integer purchaseId, DeliverSettleStatus dss, Date startTime, Date endTime, Integer pageNo, Integer pageSize);

    /*统计商户发货单--已被确认的*/
    SupplyResult statisticsPriceGroupBySettleStatus(Integer sellerId, Date startTime, Date endTime);

    SupplyResult statisticsPriceGroupBySettleStatusBank(Integer purchaseId, Date startTime, Date endTime);

    /*统计发货单--按确认状态统计*/
    SupplyResult statisticsPriceGroupByStatus(Integer sellerId, Date startTime, Date endTime);


    SupplyResult statisticsPriceGroupByStatusBank(Integer purchaseId, Date startTime, Date endTime);

    /*批量设置发货单清算进度为已发起--行社发起清算时使用  如果要用，千万注意判断返回数量*/
    Integer branchLiquidationOrders(List<Integer> ids, Integer sellerId);

    Integer branchLiquidationOrdersBank(List<Integer> ids, Integer purchaseId);

    /*批量设置发货单清算进度为清算完成--行社完成清算时使用  如果要用，千万注意判断返回数量*/
    Integer trueLiquidationOrders(List<Integer> ids, Integer sellerId, Integer managerId);

    Integer trueLiquidationOrdersBank(List<Integer> ids, Integer purchaseId, Integer managerId);

    /*统计发货单总价*/
    BigDecimal liquidationAmount(List<Integer> ids);

    List<TbDeliverForm> queryOrderByDeliverNo(String id);

    /**
     * 查询发货单 和 供货商名称
     * @param deliverFormExampleDto
     * @return
     */
    PageInfo<Map<String,Object>> selectTbDeliverForm(DeliverFormExampleDto deliverFormExampleDto);


    SupplyResult updateExportMark(TbDeliverForm tbDeliverForm);


    /**
     * 重置供货方库存
     * @return
     */
    List<TbRepository> queryDeliverRepository();

    /**
     * 重置采购方库存
     * @return
     */
    List<TbRepository> queryPurchaseRepository();

    /**
     * 增加发货单商品数量统计页面，已发货数量、确认数量、未确认数量
     * @return
     */
    SupplyResult queryDeliverOrderCount();

    /**
     * 统计自助兑换 网点 商品 价格
     * @return
     */
    PageInfo selectExchangeOrderCount(Date startTime,Date endTime,Integer pageNo,Integer pageSize,Integer type,Integer orgId);

    /**
     * 统计自助兑换网点 详细商品数据
     * @param startTime
     * @param endTime

     * @param orgId
     * @return
     */
    SupplyResult selectExchageOrderCountDataByOrgId(Date startTime,Date endTime,Integer orgId,Integer type);
}
