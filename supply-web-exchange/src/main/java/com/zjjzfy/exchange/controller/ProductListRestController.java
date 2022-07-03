package com.zjjzfy.exchange.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.pojo.ProductModel;
import com.zjjzfy.exchange.service.ProductListService;
import com.zjjzfy.pojo.TbProduct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 16:25
 */
@RestController
public class ProductListRestController {

    @Autowired
    private ProductListService productListService;


    @RequestMapping("/product")
    public SupplyResult productList(HttpServletRequest request,
                                    @RequestParam("pageNo") int pageNo,
                                    @RequestParam("pageSize") int pageSize,
                                    @RequestParam("categoryId") int categoryId,
                                    @RequestParam("priceOrder") String priceOrder,
                                    @RequestParam("onlyStock") boolean onlyStock){

        PageInfo<TbProduct> pageInfo ;
        if(onlyStock){
            pageInfo = productListService.selectProduct(request,pageNo, pageSize, categoryId, priceOrder);
        }else {
            pageInfo = productListService.selectProduct(pageNo, pageSize, categoryId, priceOrder);
        }

        return SupplyResult.build(200,"获取成功！",pageInfo);
    }

    @RequestMapping("/product/{pid}")
    public SupplyResult productInfo(@PathVariable int pid,
                                    HttpServletRequest request){
        ProductModel data = productListService.selectProductModel(pid,request);
        return SupplyResult.build(200,"获取成功！",data);
    }

    @PostMapping("/ip2branchno")
    public SupplyResult ip2org(HttpServletRequest request,
                               @RequestParam("branchno") String branchno,
                               HttpServletResponse response){
        SupplyResult result = productListService.setCookie(response,branchno);
        return result;
    }

    @RequestMapping("/cacheRefresh")
    public SupplyResult cacheRefresh(){
        return productListService.cacheRefresh();
    }

}
