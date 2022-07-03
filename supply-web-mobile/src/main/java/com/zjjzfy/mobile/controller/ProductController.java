package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ProductCheckStatus;
import com.zjjzfy.common.config.ProductImgType;
import com.zjjzfy.common.config.ProductStatus;
import com.zjjzfy.pojo.*;
import com.zjjzfy.product.service.impl.CategoryService;
import com.zjjzfy.product.service.impl.ProductImgService;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hsmz
 * @date: 2019/3/26 下午2:20
 */
@RequestMapping("/deliver")
@Controller
public class ProductController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductImgService productImgService;

    @RequestMapping("/productManager")
    public String queryProductList(Model model, Integer page, Integer size) {
        TbOrgInfo org = userService.getOrg();
        List<Byte> statusList = new ArrayList<>();
        statusList.add(ProductCheckStatus.CHECK_PASS.getStatus());
        PageInfo<TbProduct> products = productService.selectProduct(org.getId(), null, ProductStatus.ON.getStatus(), statusList,
                null, false, page, size);
        List<TbCategory> categoryList = (List<TbCategory>) categoryService.selectCategory(new TbCategoryExample(), null).getData();

        model.addAttribute("products", products.getList());
        model.addAttribute("categoryList", categoryList);
        return "/deliver/product/commodityList";
    }

    @RequestMapping("/commodityDetail")
    public String commodityDetail(Model model, Integer id) {
        TbProduct product = (TbProduct) productService.selectByExampleOrId(null, id).getData();
        List<TbProductImg> imgs = (List<TbProductImg>) productImgService.selectProductImg(id, ProductImgType.REVEAL.getType()).getData();
        List<TbProductImg> imgs2 = (List<TbProductImg>) productImgService.selectProductImg(id, ProductImgType.DETAIL.getType()).getData();

        model.addAttribute("imgs", imgs);
        model.addAttribute("imgs2", imgs2);
        model.addAttribute("product", product);
        return "/deliver/product/commodityDetail";
    }

    @RequestMapping("/commodityEdit")
    public String commodityEdit(Model model, Integer id) {
        TbProduct product = (TbProduct) productService.selectByExampleOrId(null, id).getData();
        List<TbProductImg> imgs = (List<TbProductImg>) productImgService.selectProductImg(id, ProductImgType.REVEAL.getType()).getData();
        List<TbProductImg> imgs2 = (List<TbProductImg>) productImgService.selectProductImg(id, ProductImgType.DETAIL.getType()).getData();
        TbCategory category = (TbCategory) categoryService.selectCategory(null, product.getCategoryId()).getData();
        List<TbCategory> categoryList = categoryService.getCategoryList(0,0);
        List<TbCategory> categoryList2 = categoryService.getCategoryList(category.getParentId(),0);

        model.addAttribute("imgs", imgs);
        model.addAttribute("imgs2", imgs2);
        model.addAttribute("product", product);
        model.addAttribute("category", category);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categoryList2", categoryList2);
        return "/deliver/product/commodityEdit";
    }

    @RequestMapping("/commodityAddPage")
    public String commodityAddPage(Model model) {
        //获取所有一级分类
        List<TbCategory> categoryList = categoryService.getCategoryList(0,0);
        List<TbCategory> categoryList2 = categoryService.getCategoryList(categoryList.get(0).getId(),0);
//        TbCategory category = null;
//        if (categoryList != null && categoryList.size() > 0) {
//            category = categoryList.get(0);
//        }
//        model.addAttribute("category", category);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categoryList2", categoryList2);
        return "/deliver/product/commodityAdd";
    }

    @RequestMapping("/getCategory")
    public String getCategory(Model model) {
        //获取所有一级分类
        List<TbCategory> categoryList = categoryService.getCategoryList(0,0);
        model.addAttribute("categoryList", categoryList);
        return "/deliver/product/type";
    }


}