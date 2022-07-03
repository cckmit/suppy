package com.zjjzfy.mgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 兑换出库
 * @author zyx
 * @date 2020/5/18 上午11:19
 */
@RequestMapping("/mgt")
@Controller
public class ExchangeController {

    @RequestMapping("toProductExchange")
    public String toProductExchange(){
       return "exchange/productExchange";
    }



}
