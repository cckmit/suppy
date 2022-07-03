package com.zjjzfy.exchange.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.service.PayService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 13:59
 */
@Slf4j
@RestController
public class DeliverOrderRestController {

    @Autowired
    private PayService payService;

    @Value("${myconfig.web-exchange}")
    private Boolean webExchange;

    @PostMapping("/order/pay")
    public SupplyResult pay(@RequestParam("billno") String billno,
                            @RequestParam("paycode")String paycode,
                            @RequestParam("remark")String remark,
                            HttpServletRequest request){
        if(!webExchange){
            return SupplyResult.build(404,"网页自助兑换已停止提供服务！请使用APP进行兑换！","");
        }

        SupplyResult result  = null;
        try {
            result = payService.payByOcx(billno,paycode,remark,request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付失败：{}",e.getMessage());
            log.error("支付失败",e);
            result = SupplyResult.build(300,e.getMessage());
        }
        return result;
    }
}
