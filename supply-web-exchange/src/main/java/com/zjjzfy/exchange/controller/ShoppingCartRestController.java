package com.zjjzfy.exchange.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.service.ShoppingCartService;
import com.zjjzfy.order.service.ExchangeOrderService;
import com.zjjzfy.pojo.TbOrgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/30
 * Time: 10:50
 */
@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;

    @Value("${myconfig.web-exchange}")
    private Boolean webExchange;


    @PostMapping("/cart/add")
    public SupplyResult cartAdd(@RequestParam("productId") int productId,
                                @RequestParam("count") int count,
                                HttpServletRequest request){
        SupplyResult result = shoppingCartService.cartAdd(productId,count,request);
        return result;
    }

    @PostMapping("/cart/delete/{productId}")
    public SupplyResult cartDel(@PathVariable("productId") int productId,
                                HttpServletRequest request){
        SupplyResult result = shoppingCartService.cartDel(productId,request);
        return result;
    }

    @GetMapping("/cart/amount")
    public SupplyResult cartAmount(HttpServletRequest request){
        SupplyResult result = shoppingCartService.amountGet(request);
        return result;
    }

    @PostMapping("/cart/submit")
    public SupplyResult cartSubmit(HttpServletRequest request,
                                   @RequestParam("productIds")List<Integer> productIds,
                                   @RequestParam("counts")List<Integer> counts,
                                   @RequestParam("token")String token){
        if(!webExchange){
            return SupplyResult.build(404,"网页自助兑换已停止提供服务！请使用APP进行兑换！","");
        }
        SupplyResult result =  shoppingCartService.submit(request,token,productIds,counts);
        return result;
    }

    @GetMapping("/cart/recent/{recentNo}")
    public SupplyResult exchangeCheckRecent(@PathVariable("recentNo") Integer recentNo,
                                            HttpServletRequest request){
        TbOrgInfo orgInfo  = OrgCache.getInstance().getOrgInfobyRequest(request);
        if(orgInfo != null){
            SupplyResult result = exchangeOrderService.orderInfoRecent(recentNo,orgInfo.getId());
            return result;
        }else {
            return SupplyResult.build(404,"获取最近订单失败：未知的请求地址！");
        }
    }
}
