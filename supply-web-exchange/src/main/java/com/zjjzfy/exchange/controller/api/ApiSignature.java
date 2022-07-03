package com.zjjzfy.exchange.controller.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.service.SignatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author      jackshenonly
 * @description 自助兑换签名上传
 * @date        2020-03-30 09:15
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@Api(tags = "自助兑换签名上传")
public class ApiSignature {
    @Autowired
    private SignatureService signatureService;

    @PostMapping("/uploadSignature")
    @ApiOperation(value ="自助兑换签名上传",notes = "~")
    public SupplyResult uploadSignature(@RequestParam("imgStr")@ApiParam(value = "Base64编码过后的签名") String imgStr,
                                        @RequestParam("billno")@ApiParam(value = "签名对应的订单号") String billno){
        SupplyResult rt;
        try {
            rt = signatureService.uploadSignature(imgStr,billno);
        }catch (Exception e){
            log.error("签名上传失败",e);
            rt = SupplyResult.build(901,"签名上传异常:" + e.getMessage());
        }
        return rt;
    }

}
