package com.zjjzfy.exchange.controller.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.service.PayService;
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
@Api(tags = "支付返回码检查")
public class ApiPayCodeCheckRestController {

    @Autowired
    private PayService payService;

    @PostMapping("/order/payCodeCheck")
    @ApiOperation(value = "支付返回码检查", notes = "支付返回码是否已经存在，如果已经存在表示该支付结果已经使用。data=true表示存在，data=false 表示不存在")
    public SupplyResult pay(@RequestParam @ApiParam(value = "按约定进行传递,上传通知信息时传递的啥，此处就传递啥") String rtCode) {
        log.info("支付返回码检查：{}", rtCode);
        SupplyResult result = null;
        try {
            Boolean exist = payService.payCodeCheck(rtCode);
            if (exist) {
                result = SupplyResult.build(200, "支付返回码存在", exist);
            } else {
                result = SupplyResult.build(200, "支付返回码不存在", exist);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付返回码检查异常：{}", e.getMessage());
            log.error("支付返回码检查异常", e);
            result = SupplyResult.build(901, e.getMessage());
        }

        return result;
    }

}
