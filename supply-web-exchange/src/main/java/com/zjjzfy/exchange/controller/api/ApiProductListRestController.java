package com.zjjzfy.exchange.controller.api;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.ProductCache;
import com.zjjzfy.exchange.pojo.CategoryTree;
import com.zjjzfy.exchange.pojo.ProductModel;
import com.zjjzfy.exchange.pojo.ProductsInfo;
import com.zjjzfy.exchange.service.ProductListService;
import com.zjjzfy.exchange.utils.CategoryHandler;
import com.zjjzfy.pojo.TbProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 16:25
 */
@RestController
@RequestMapping("/rest")
@Api(tags = "礼品接口相关")
public class ApiProductListRestController {

    @Autowired
    private ProductListService productListService;


    @GetMapping("/product/category")
    @ApiOperation(value ="礼品分类查询",notes = "")
    public SupplyResult categoryInfo(){
        CategoryTree categoryTree = CategoryHandler.handle(ProductCache.getInstance().getCatesAll());
        return SupplyResult.build(200,"获取成功！", categoryTree);
    }


    @GetMapping("/product")
    @ApiOperation(value ="礼品列表查询",notes = "")
    public SupplyResult productList(@RequestParam("branchno")@ApiParam(value = "机构编号") String branchno ,
                                    @RequestParam("pageNo")@ApiParam(value = "分页页码") int pageNo ,
                                    @RequestParam("pageSize")@ApiParam(value = "分页大小") int pageSize,
                                    @RequestParam("categoryId")@ApiParam(value = "礼品分类ID，-1表示全部。") int categoryId,
                                    @RequestParam("priceOrder")@ApiParam(value = "价格排序,可能的值[ASC,DESC]") String priceOrder,
                                    @RequestParam("onlyStock")@ApiParam(value = "只展示有库存的礼品") boolean onlyStock,
                                    @RequestParam(value="prodNameLike",required = false)@ApiParam(value = "产品名称，模糊查询；没有时请不要传递该参数或者传空串") String prodNameLike){

        ProductsInfo productsInfo = new ProductsInfo();
        PageInfo<TbProduct> pageInfo ;
        if(onlyStock){
            pageInfo = productListService.selectProduct(branchno,pageNo, pageSize, categoryId, priceOrder,prodNameLike);
        }else {
            pageInfo = productListService.selectProduct(pageNo, pageSize, categoryId, priceOrder, prodNameLike);
        }
        productsInfo.setPageInfo(pageInfo);
        return SupplyResult.build(200,"获取成功！",productsInfo);
    }

    @GetMapping("/product/detail")
    @ApiOperation(value ="礼品详情查询",notes = "")
    public SupplyResult productInfo(@RequestParam("pid")@ApiParam(value = "礼品编号") int pid ,
                                    @RequestParam("branchno")@ApiParam(value = "机构编号") String branchno){
        ProductModel data = productListService.selectProductModel(pid,branchno);
        return SupplyResult.build(200,"获取成功！",data);
    }


    @PostMapping("/cacheRefresh")
    @ApiOperation(value ="自定义缓存（机构信息和礼品信息）手动刷新",notes = "")
    public SupplyResult cacheRefresh(){
        return productListService.cacheRefresh();
    }

}
