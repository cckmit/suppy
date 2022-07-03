package com.zjjzfy.exchange.controller.api;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.Param;
import com.zjjzfy.exchange.service.OrderExtraInfoService;
import com.zjjzfy.exchange.service.PayService;
import com.zjjzfy.order.service.ExchangeOrderService;
import com.zjjzfy.pojo.JfDeliverOrderExtra;
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
 * Date: 2019/5/7
 * Time: 13:59
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@Api(tags = "支付通知")
public class ApiDeliverOrderRestController {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderExtraInfoService orderExtraInfoService;

    @PostMapping("/order/pay")
    @ApiOperation(value ="支付成功通知",notes = "务必在支付成功后才请求，否则订单将变成支付状态")
    public SupplyResult pay(@RequestBody@ApiParam(value = "按约定进行传递json;注意contentType:application/json") Param param){
        log.info("客户端发起订单支付成功请求：{}", JSON.toJSONString(param));
        SupplyResult result  = null;
        try {
            result = payService.payByAndroidPos(param);

        }catch (RsaCheckException e){
            result = SupplyResult.build(900,"小伙子不诚实哈",e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            log.error("支付通知失败：{}",e.getMessage());
            log.error("支付通知失败",e);
            result = SupplyResult.build(901,e.getMessage());
        }
        return result;
    }

    @PostMapping("/order/payByNothing")
    @ApiOperation(value ="非消费兑换通知",notes = "请在签名上传成功后请求！！！")
    public SupplyResult payByNothing(@RequestBody@ApiParam(value = "按约定进行传递json;注意contentType:application/json") Param param){
        log.info("客户端发起订单支付成功请求：{}", JSON.toJSONString(param));
        SupplyResult result  = null;
        try {
            result = payService.payByNothing(param);

        }catch (RsaCheckException e){
            result = SupplyResult.build(900,"小伙子不诚实哈",e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            log.error("无消费通知失败：{}",e.getMessage());
            log.error("无消费通知失败",e);
            result = SupplyResult.build(901,e.getMessage());
        }
        return result;
    }


    @PostMapping("/order/extraInfoSubmit")
    @ApiOperation(value = "兑换订单额外信息上传", notes = "当订单为预购单或其他需要额外信息记录时，需要记录客户的联系方式等信息，再产品到货后联系客户自取/邮递等，进行发放。")
    public SupplyResult orderExtraInfoSubmit(@RequestBody@ApiParam(value = "兑换订单额外信息对象体：预购单时必填项：订单号、客户名称（也可以是昵称）、电话；上传中间过程时：process_status必填。")JfDeliverOrderExtra extraBody){
        SupplyResult result  = null;
        try {
            result = orderExtraInfoService.orderExtraInfoSubmit(extraBody);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("上传额外信息失败：{}",e.getMessage());
            log.error("上传额外信息失败：",e);
            result = SupplyResult.build(500,"订单额外信息上传失败！");
        }

        return result;
    }
}
