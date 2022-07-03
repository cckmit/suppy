package com.zjjzfy.exchange.controller;

import com.zjjzfy.common.entity.SupplyResult;

import com.zjjzfy.exchange.service.PayService;
import com.zjjzfy.order.service.ExchangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 10:35
 */
@Controller
public class DeliverOrderController {
    @Autowired
    private ExchangeOrderService exchangeOrderService;

    @Autowired
    private PayService payService;

    @RequestMapping("/order/{billno}")
    public String orderInfo(@PathVariable("billno")String billno,
                            Model model,
                            HttpServletRequest request){
        payService.putPaycode(request);
        SupplyResult result = exchangeOrderService.orderInfo(billno);
        model.addAttribute("order",result.getData());
        return "result";
    }
}
