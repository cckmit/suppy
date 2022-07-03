package com.zjjzfy.interfaces;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbRepository;
import com.zjjzfy.pojo.TbRepositoryExample;
import java.util.List;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hsmz
 */
public interface TbRepositoryMapper extends PublicMapper<TbRepository> {

    /**
     * 批量入库
     *
     * @param cartList
     */
    void updateInputBatch(@Param(value = "cartList") List<TbCart> cartList);

    /**
     * 批量出库
     *
     * @param cartList
     */
    void updateOutputBatch(@Param(value = "cartList") List<TbCart> cartList);

    /**
     * 行社方确认发货单入库
     * @param productId
     * @param quantity
     * @param supplierId
     * @return
     */
    void updateInput(@Param(value = "productId") Integer productId, @Param(value = "quantity") Integer quantity, @Param(value = "supplierId") Integer supplierId);

    /**
     * 行社方兑换出库
     * @param productId
     * @param quantity
     * @param supplierId
     * @return
     */
    void updateOutput(@Param(value = "productId") Integer productId, @Param(value = "quantity") Integer quantity, @Param(value = "supplierId") Integer supplierId);

    /**
     * 行社方兑换批量出库
     * @param cartList
     */
    void purchaserStockOutputBatch(@Param(value = "cartList") List<TbCart> cartList, @Param(value = "supplierId") Integer supplierId);

    /**
     * 判断是否有负库存
     *
     * @param cartList
     * @return 对应库存list
     */
    List<Integer> checkFakeStock(@Param(value = "cartList") List<TbCart> cartList);


    /**
     * 单个商品入库
     * @param supplyOrgId
     * @param pId
     * @param num
     * @return
     */
    Integer addOneProductRepository(@Param("supplyOrgId") Integer supplyOrgId, @Param("pId") Integer pId, @Param("num") Integer num);

    /**
     * 查询库存 用于后管修改
     * @return
     */
    List<Map<String,Object>> queryRepository(@Param("supplyOrgId") Integer supplyOrgId,@Param("brand") String brand,String goodsName);

    List<Map<String,Object>> selectBrand();

    List<Map<String,Object>> selectSuppkyOrg();

    Integer ChangeOneProductRepository(@Param("supplyOrgId") Integer supplyOrgId, @Param("pId") Integer pId, @Param("num") Integer num,@Param("total") Integer total);
}