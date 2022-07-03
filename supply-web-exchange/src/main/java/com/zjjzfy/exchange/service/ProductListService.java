package com.zjjzfy.exchange.service;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.pojo.ProductModel;
import com.zjjzfy.pojo.TbProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 17:25
 */
public interface ProductListService {

    /**
     * 根据请求Ip,获取指定网点的库存产品
     * @param request
     * @return
     */
    PageInfo<TbProduct> selectProduct(HttpServletRequest request, int pageNo, int pageSize, int categoryId, String priceOrder);
    PageInfo<TbProduct> selectProduct(String branchno, int pageNo, int pageSize);
    PageInfo<TbProduct> selectProduct(String branchno, int pageNo, int pageSize,int categoryId, String priceOrder);

    /**
     * 带礼品名称模糊查询的礼品列表获取
     * @param branchno
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @param priceOrder
     * @param prodNameLike
     * @return
     */
    PageInfo<TbProduct> selectProduct(String branchno, int pageNo, int pageSize,int categoryId, String priceOrder ,String prodNameLike);

    /**
     * 获取指定类型的所有礼品
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @param priceOrder
     * @return
     */
    PageInfo<TbProduct> selectProduct(int pageNo, int pageSize, int categoryId, String priceOrder);

    /**
     * 带礼品名称模糊查询,获取指定类型的所有礼品.
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @param priceOrder
     * @param prodNameLike
     * @return
     */
    PageInfo<TbProduct> selectProduct(int pageNo, int pageSize, int categoryId, String priceOrder, String prodNameLike);

    /**
     * 根据请求Ip 获取某个产品的详情和对应网点的库存。
     * @param productid
     * @param request
     * @return
     */
    ProductModel selectProductModel(int productid ,HttpServletRequest request);

    ProductModel selectProductModel(int productid ,String branchno);

    /**
     * 设置ip和网点的对应关系。
     * @param request
     * @param branchno
     */
    SupplyResult setIp2branchno(HttpServletRequest request, String branchno);
    SupplyResult setIp2branchno(String ip,String branchno);


    /**
     * 给客户端设置cookie,以识别网点机构
     * @param response
     * @param branchno
     * @return
     */
    SupplyResult setCookie(HttpServletResponse response, String branchno);

    /**
     * 自定义缓存 手动刷新
     * @return
     */
    SupplyResult cacheRefresh();

    /**
     * 选出指定分类，及其子分类的分类编号；
     * @param categoryId
     * @return
     */
    List<Integer> findAndChildCategory(Integer categoryId);
}
