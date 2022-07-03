package com.zjjzfy.mgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zyx 订单管理
 * @date 2020/5/6 下午3:05
 */
@RequestMapping("/mgt")
@Controller
public class OrderManagerController {


    @RequestMapping("toPurchaseManager")
    public String toPurchaseManager(){
        return "orderManager/purchaseManager";
    }

    @RequestMapping("toWillOrderManager")
    public String toWillOrderManager(){
        return "orderManager/willOrderManager";
    }
}
