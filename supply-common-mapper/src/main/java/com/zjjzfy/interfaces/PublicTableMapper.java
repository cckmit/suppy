package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.dto.ProductDto;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Saintyun on 2019/3/13.
 */
public interface PublicTableMapper {

    Double countConfirmMoney(@Param("purchase_form_id")Integer p_id);

    List<HashMap<String,Object>> queryPurchaseConfirmProducts(@Param("purchase_form_id")Integer p_id);

    /**
     * 接收发货单时判断有没有超过订货单剩余库存
     * Create by hsmz
     * @param purchaseFormId 订货单id
     * @return list(剩余数量-发货数量)
     */
    List<Integer> checkPurchaseFormSurplusQuantity(@Param("purchaseFormId") Integer purchaseFormId);

    /**
     * 通过发货单更新订货单中的已确定到货商品数量
     * Create by hsmz
     * @param deliverFormId 发货单id
     * @return
     */
    void updatePurchaseFormQuantityByDeliverFormId(@Param("deliverFormId") Integer deliverFormId);

    /**
     * 拒绝发货单归还库存
     * @param deliverFormId
     */
    void rejectPurchaseFormByDeliverFormId(@Param("deliverFormId") Integer deliverFormId);

    /**
     * 通过订货单单id查看订货单商品列表
     * @param purchaseFormId
     * @return
     */
    List<ProductDto> queryPurchaseFormProductList(@Param("purchaseFormId") Integer purchaseFormId);

    /**
     * 通过订货单单id查看订货单商品列表
     * @param purchaseFormId
     * @param supplyOrgId 供货方机构id
     * @return
     */
    List<ProductDto> queryProductListBySupplyOrgId(@Param("purchaseFormId") Integer purchaseFormId, @Param("supplyOrgId") Integer supplyOrgId);

    /**
     * 通过发货单单id查看发货单商品列表
     * @param deliverFormId
     * @return
     */
    List<ProductDto> queryDeliverFormProductList(@Param("deliverFormId") Integer deliverFormId);


    List<HashMap<String,Object>> queryPurchaseData(@Param("pid")Integer pid);


    List<HashMap<String,Object>> selectPurchaserInfo(@Param("purchaserId")Integer purchaserId);

    List<String> queryOrderOrgName(@Param("purchaseFormId")Integer purchaseFormId);

    List<HashMap<String,Object>> selectGiftList(@Param("employeeid")Integer employeeid,
                                                @Param("productid")Integer productid,
                                                @Param("orderBy")String orderBy,
                                                @Param("orderCase")String orderCase);

    List<HashMap<String,Object>> selectPurchasedProducts(@Param("startdate")Date startdate,
                                                         @Param("enddate")Date enddate,
                                                         @Param("pid")Integer productId);
    List<HashMap<String,Object>> queryProductsTwoDimensionalCharts(@Param("orgid")Integer orgid,
                                                                   @Param("startdate")Date startdate,
                                                                   @Param("enddate")Date enddate);

    List<HashMap<String,Object>> queryBranchPurchase(@Param("orgid")Integer orgid,
                                                                   @Param("startdate")Date startdate,
                                                                   @Param("enddate")Date enddate);

    List<HashMap<String,Object>> queryBranchTotalPurchase(@Param("orgid")Integer orgid,
                                                          @Param("startdate")Date startdate,
                                                          @Param("enddate")Date enddate);

    List<HashMap<String,Object>> selectPurchaseDetail(@Param("orgid")Integer orgid,
                                                      @Param("startdate")Date startdate,
                                                      @Param("enddate")Date enddate);

    List<HashMap<String,Object>> purchaseProductDetail(@Param("orgid")Integer orgid,
                                                       @Param("startdate")Date startdate,
                                                       @Param("enddate")Date enddate);


    List<HashMap<String,Object>> supplierProductDetail(@Param("orgid")Integer orgid,
                                                       @Param("startdate")Date startdate,
                                                       @Param("enddate")Date enddate);

    List<Map<String,Object>> deliverFromDetail(@Param("pBillno") String pBillno);

    List<Map<String,Object>> selectWillPurchaseListByOrgid(@Param("orgid")Integer orgid,
                                                               @Param("start")Date start,
                                                               @Param("end")Date end);
    List<HashMap<String,Object>> supplierProductDetails(@Param("startdate")Date startdate,
                                                       @Param("enddate")Date enddate,@Param("id")Integer id);

    List<HashMap<String,Object>> selectPurchase(@Param("startdate")Date startdate,
                                                @Param("enddate")Date enddate,@Param("id")Integer id);

    List<HashMap<String,Object>> selectProuct(@Param("startdate")Date startdate,
                                              @Param("enddate")Date enddate);





}