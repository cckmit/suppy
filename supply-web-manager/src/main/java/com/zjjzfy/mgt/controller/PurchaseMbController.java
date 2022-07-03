package com.zjjzfy.mgt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgtBack")
@Slf4j
public class PurchaseMbController {
    //去首页
    @RequestMapping("/toIndexEdit")
    public String toIndexEdit() {
        return "mbHomePage/index";
    }
}
