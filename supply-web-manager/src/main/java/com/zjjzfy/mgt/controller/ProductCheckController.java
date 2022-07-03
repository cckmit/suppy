package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.product.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgt")

public class ProductCheckController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/productCheckPage")
    public String productCheckPage() {
        return "productCheck/productCheckList";
    }

    @RequestMapping("/productCheckDetail")
    public String productCheckDetail(Model model, Integer id) {
        model.addAttribute("id", id);
        SupplyResult a = categoryService.getCategoryByCtgrClass(1);
        SupplyResult b = categoryService.getCategoryByCtgrClass(2);
        model.addAttribute("ctgr1",a.getData());
        model.addAttribute("ctgr2",b.getData());
        return "productCheck/productCheckDetail";
    }


}