package com.zjjzfy.exchange.controller.api;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.Param;
import com.zjjzfy.exchange.service.ShoppingCartService;
import com.zjjzfy.order.service.ExchangeOrderService;
import com.zjjzfy.pojo.TbOrgInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/30
 * Time: 10:50
 */
@RequestMapping("/rest")
@RestController
@Slf4j
@Api(tags = "订单提交和查询")
public class ApiShoppingCartRestController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;


    @PostMapping("/cart/submit")
    @ApiOperation(value ="兑换订单提交",notes = "~")
    public SupplyResult cartSubmit(@RequestBody@ApiParam(value = "按约定进行传递json；注意contentType:application/json") Param param){
        log.info("客户端发起订单生成请求：{}", JSON.toJSONString(param));
        SupplyResult result = null;
        try {
            result = shoppingCartService.submit(param);
        } catch (RsaCheckException e) {
            e.printStackTrace();
            result = SupplyResult.build(900,"小伙子不诚实哈",e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            result = SupplyResult.build(901,"提示："+e.getMessage(),e.getMessage());
        }
        return result;
    }

    @GetMapping("/order/recent")
    @ApiOperation(value ="获取最近第几个订单",notes = "~")
    public SupplyResult exchangeCheckRecent(@RequestParam("recentNo")@ApiParam(value = "最近第几个") Integer recentNo,
                                            @RequestParam("branchno")@ApiParam(value = "机构编号") String branchno ){
        TbOrgInfo orgInfo  = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if(orgInfo != null){
            SupplyResult result = exchangeOrderService.orderInfoRecent(recentNo,orgInfo.getId());
            return result;
        }else {
            return SupplyResult.build(404,"获取最近订单失败：未知的请求地址！");
        }
    }

    @GetMapping("/order")
    @ApiOperation(value ="订单详情查询",notes = "")
    public SupplyResult orderInfo(@RequestParam("billno")@ApiParam(value = "订单编号") String billno ){
        SupplyResult result = exchangeOrderService.orderInfo(billno);
        return result;
    }
}
