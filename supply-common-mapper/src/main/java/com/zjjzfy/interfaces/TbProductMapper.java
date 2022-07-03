package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbProductExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pojo.TbPurchaseDetail;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbProductMapper extends PublicMapper<TbProduct> {
    /*查询商品  按分类 名称 分级 等信息筛选 并按价格销量时间排序*/
    List<ProductDto> selectProductAndStock(@Param("label") Byte label, @Param("order") String order, @Param("name") String name, @Param("categoryId") Integer categoryId,@Param("prefecture") Integer prefecture);

    /*查询商品详情信息包括当前库存和销量*/
    ProductDto selectProductDetail(@Param("proId") Integer proId);

    /*查询供货商所有已上架商品*/
    List<ProductDto> selectProductBySupplyOrgId(@Param("supplyOrgId") Integer supplyOrgId,
                                                @Param("content") String content);

    /**
     * 行社通过员工id查看商品列表(包过库存)
     *
     * @param supplierId
     * @return
     */
    List<ProductDto> selectProductBySupplierId(@Param("supplierId") Integer supplierId);

    /*统计商品销量 按发货时间 商品ID 供应商机构ID 筛选*/
    List<ProductDto> statisticsCommoditySales(@Param("supplierId") Integer supplierId, @Param("pId") Integer pId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 查询除在售外的所有商品
     *
     * @param supplierId
     * @return
     */
    List<TbProduct> selectNotONProduct(@Param("supplierId") Integer supplierId,
                                       @Param("name") String name,
                                       @Param("categoryId") Integer categoryId);

    /**
     * 热卖商品
     *
     * @return
     */
    List<ProductDto> selectBestSeller();

    /**
     * 查询所有的特惠商品
     *
     * @return
     */
    List<ProductDto> selectIsDiscountProduct();

    /**
     * 查询所有的新品上架
     *
     * @return
     */
    List<ProductDto> selectNewProduct();

    List<ProductDto> selectProductByCategory(@Param("category")Integer category);

    void addEditProduct(@Param("id") Integer id,@Param("name") String name, @Param("value") String value);

    List<Map<String,Object>> countShopClickRate();

    List<ProductDto> selectProductByEv(@Param("type") Integer type);

}