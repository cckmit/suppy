package com.zjjzfy.mgt.controller;

import com.zjjzfy.product.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zyx
 * @date 2019/11/29 下午2:01
 */
@Controller
@RequestMapping("/mgt")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/addProduct")
    public String  productAdd() {
        return "productCheck/productAdd";
    }

    @GetMapping("/productStock")
    public String productStock(){
        return "productCheck/productStock";
    }

}
