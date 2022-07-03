package com.zjjzfy.repository.service;

import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbRepository;

import java.util.List;
import java.util.Map;

/**
 * 货品库存服务
 * @author: hsmz
 * @date: 2019/3/14 下午4:43
 */
public interface RepositoryService {


    /**
     * 采购方确认收货商品入库
     * @param deliverFormId 发货单id
     * @return
     */
    SupplyResult purchaserStockInputByDeliverFormId(Integer deliverFormId);

    /**
     * 采购方入库
     * @param productId
     * @param quantity
     * @param purchaserId
     * @return
     */
    SupplyResult purchaserStockInput(Integer productId,Integer quantity,Integer purchaserId);

    /**
     * 采购方入库
     * @param productIdList
     * @param quantityList
     * @param purchaserId
     * @return
     */
    SupplyResult purchaserStockInputList(List<Integer> productIdList,List<Integer> quantityList,Integer purchaserId);

    /**
     * 采购方出库
     * @param productId
     * @param quantity
     * @param purchaserId
     * @return
     */
    SupplyResult purchaserStockOutput(Integer productId,Integer quantity,Integer purchaserId);

    /**
     * 采购方批量出库
     * 采购方库存单位为人员
     * @param cartList
     * @return
     */
    SupplyResult purchaserStockOutputBatch(List<TbCart> cartList,Integer supplierId);


    /**
     * 供货方增加货品库存
     * @param cartList 购物车列表
     * @return
     */
    SupplyResult stockInput(List<TbCart> cartList);

    /**
     * 供货方减少货品库存
     * @param cartList 购物车列表
     * @return
     */
    SupplyResult stockOutput(List<TbCart> cartList);

    /**
     * 判断货品库存足不足
     * @param cartList
     * @return
     */
    SupplyResult checkStock(List<TbCart> cartList);

    /**
     * 判断供货方(商户)是否有负库存
     * 供货方库存单位是机构
     * @param cartList
     * @return
     */
    Boolean checkFakeStock(List<TbCart> cartList);

    /**
     * 判断采购方是否有负库存
     * 采购方库存单位是员工
     * @param cartList
     * @param supplierId
     * @return
     */
    Boolean checkPurchaserFakeStock(List<TbCart> cartList, Integer supplierId);

    /**
     * pc端查询商品库存  用于修改库存
     * @param supplyOrgId
     * @param brand
     * @param page
     * @param limit
     * @return
     */
    LayuiData queryRepository(Integer supplyOrgId, String brand,Integer page,Integer limit,String goodsName);

    /**
     * 查询所有供货商跟商品品牌用于库存条件筛选
     * @return
     */
    SupplyResult selectRepositry();

    Boolean changeRepository(Integer supplyOrgId,Integer proId,Integer num);

    void resetPurchaseRepository(List<TbRepository> repositoryList);

    void resetDeliverRepository(List<TbRepository> repositoryList);

}
