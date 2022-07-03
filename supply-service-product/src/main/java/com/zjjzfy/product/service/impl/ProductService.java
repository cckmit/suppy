package com.zjjzfy.product.service.impl;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrderEnum;
import com.zjjzfy.common.config.ProductLabelStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbProductExample;
import com.zjjzfy.pojo.dto.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hsmz
 */
public interface ProductService {

    SupplyResult insert(TbProduct product, String imageList, String imgs);

    SupplyResult modify(TbProduct product, String imageList, String imgs);

    PageInfo<TbProduct> selectProduct(Integer supplyOrgId, String productName, Byte productStatus, List<Byte> checkStatus, Integer categoryId, boolean flag, Integer page, Integer size);

    int updateProduct(TbProduct product);

    /*查询商品  按分类 名称 分级 等信息筛选 并按价格销量时间排序*/
    List<ProductDto> selectProductAndStock(ProductLabelStatus pls, OrderEnum order, String name, Integer categoryId,Integer prefecture);

    /*统计商品销量 按发货时间 商品ID 供应商机构ID 筛选*/
    List<ProductDto> statisticsCommoditySales(Integer supplierId, Integer pId, Date startTime, Date endTime);

    /*查询商品详情*/
    ProductDto selectProductDetail(Integer proId);

    /*查询供货商的所有已上架商品--分页*/
    PageInfo<ProductDto> selectProductDtoBySupplyOrgId(Integer supplyOrgId, String content, Integer pageNo,
                                                       Integer pageSize);

    /*查询供货商的所有已上架商品--不分页*/
    List<TbProduct> selectProductBySupplyOrgId(Integer supplyOrgId);

    /**
     * 行社通过员工id查看商品列表(包过库存)
     *
     * @param supplierId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<ProductDto> selectProductBySupplierId(Integer supplierId, Integer pageNo, Integer pageSize);

    /*商品入库*/
    SupplyResult addProductRepository(Integer supplyOrgId, ArrayList<Integer> ids, ArrayList<Integer> nums);

    SupplyResult selectByExampleOrId(TbProductExample example, Integer id);

    SupplyResult updateProductStatus(Integer id);

    SupplyResult uploadImg(String base64Img, String fileName);
    SupplyResult uploadImgPc(String imgurl,String base64Img, String fileName);

    SupplyResult checkProduct(Integer id, BigDecimal purchasePrice, Integer checkValue,
                              String isDiscount, String newProduct, Integer sales, BigDecimal exchangePrice,Integer exchangeType,BigDecimal exchangeCash,String exchangeRemark,String productStatus,Integer purchaseOrnot,String model,Integer productPrefecture);

    SupplyResult checkedProduct(Integer id, BigDecimal purchasePrice,
                              String isDiscount, String newProduct, Integer sales, BigDecimal exchangePrice,Integer exchangeType,BigDecimal exchangeCash,String exchangeRemark,String productStatus,Integer purchaseOrnot,String model,Integer productPrefecture);

    SupplyResult selectOnlineProducts();

    /**
     * 热卖商品
     *
     * @param pageIndex
     * @param pageSize
     * @param type      123 新品上架 124 热卖商品 3 特惠快购
     * @return
     */
    PageInfo<ProductDto> selectProduct(Integer pageIndex, Integer pageSize, Integer type);

    PageInfo<ProductDto> selectProductSp(Integer pageIndex, Integer pageSize, Integer type,Integer id);
    /**
     * 平台方输入排序编号
     *
     * @param id
     * @param sortId
     */
    void setSortId(Integer id, Integer sortId);

    /**
     * 商品审核 可编辑表格 保存数据
     * @param name
     * @param value
     */
    public void addEditProduct(Integer id,String name,String value);

    /**
     * 修改该商品的专题
     * @param pid 商品id
     * @param cid 专题id
     */
    SupplyResult doProductAdd(Integer pid,Integer cid);

    void doAllProductEdit(List<TbProduct> list);

    SupplyResult doProductDlt(Integer id);

    /**
     * 统计商品点击流量展示
     */
    void countShopClick();

    void addClickRate(Integer id);

    SupplyResult countShopClickRate();
    
    PageInfo<ProductDto> selectProductByEv(Integer pageIndex, Integer pageSize, Integer type);
}