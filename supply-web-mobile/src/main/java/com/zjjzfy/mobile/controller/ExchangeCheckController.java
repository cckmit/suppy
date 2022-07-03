package com.zjjzfy.mobile.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.ExchangeOrderService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/8
 * Time: 15:22
 */
@Slf4j
@Controller
public class ExchangeCheckController {

    @Autowired
    private ExchangeOrderService exchangeOrderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/purchase/exchangeCheck")
    public String exchangeCheckInit(){
        return "/purchase/exchange/exchangeCheck";
    }

    /**
     * 查询 自助兑换单详情
     * @param billno
     * @return
     */
    @RequestMapping("/purchase/exchangeCheck/{billno}")
    @ResponseBody
    public SupplyResult exchangeCheck(@PathVariable("billno") String billno){
        SupplyResult result = exchangeOrderService.orderInfo(billno,userService.getCurrentTbUserInfo());
        return result;
    }

    /**
     * 查询 当前用户所在网点的自助兑换；最近数条记录 （非详情）。
     * @return
     */
    @RequestMapping("/purchase/exchangeCheck/recent")
    @ResponseBody
    public SupplyResult exchangeCheckRecent(@RequestParam("pageNo") int pageNo,
                                            @RequestParam("pageSize") int pageSize){

        SupplyResult result = exchangeOrderService.orderRecent(pageNo,pageSize,userService.getCurrentTbUserInfo().getOrgid());
        return result;
    }

    /**
     * 查询 最近自助兑换单详情
     * @param recentNo 最近第 recentNo 条记录
     * @return
     */
    @RequestMapping("/purchase/exchangeCheck/recent/{recentNo}")
    @ResponseBody
    public SupplyResult exchangeCheckRecentOne(@PathVariable("recentNo") Integer recentNo){
        SupplyResult result = exchangeOrderService.orderInfoRecent(recentNo,userService.getCurrentTbUserInfo());
        return result;
    }

    @PostMapping("/purchase/exchangeOut/{billno}")
    @ResponseBody
    public SupplyResult exchangeOut(@PathVariable("billno")String billno){
        SupplyResult result;
        try {
            result = exchangeOrderService.orderOut(billno,userService.getCurrentTbUserInfo());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            log.error("自助兑换单发放失败",e);
            result = SupplyResult.build(300,"自助兑换单发放失败"+e.getMessage());
        }
        return result;
    }

}
