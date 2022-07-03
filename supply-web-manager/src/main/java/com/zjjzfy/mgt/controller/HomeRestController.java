package com.zjjzfy.mgt.controller;

import com.zjjzfy.order.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2019/11/20 上午9:05
 */
@RestController
@RequestMapping("/mgt")
public class HomeRestController {
    @Autowired
    private HomeService homeService;

    /**
     * 统计待发货订单数
     * @return
     */
    @GetMapping("/countWaitOrder")
    public Map countWaitOrder(){
        return homeService.countWaitOrder();
    };

    /**
     * 统计昨日订单数量 总价
     * @return
     */
    @GetMapping("/countYesterOrder")
    public Map countYesterOrder(){
        return homeService.countYesterOrder();
    }

    /**
     * 统计昨日完成 订单金额  和订单数
     * @return
     */
    @GetMapping("/countYesterOrdered")
    public Map countYesterOrdered(){
        return homeService.countYesterOrdered();
    }

    /**
     * 统计等待审核发票信息
     * @return
     */
    @GetMapping("/countWaitInvoice")
    public Map countWaitInvoice(){
        return homeService.countWaitInvoice();
    }

    @GetMapping("/countOrderData")
    public Object[][] countOrderData(){
        return homeService.countOrderDate();
    }

    @GetMapping("/homeMapAll")
    public Map homeMapAll(){
        return homeService.homeMapAll();
    }

}
