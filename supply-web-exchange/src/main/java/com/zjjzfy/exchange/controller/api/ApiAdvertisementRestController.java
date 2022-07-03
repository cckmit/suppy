package com.zjjzfy.exchange.controller.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.TbAdvertisementService;
import com.zjjzfy.pojo.TbAdvertisement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-02-17 14:32
 */
@RequestMapping("/rest")
@RestController
@Slf4j
@Api(tags = "banner广告查询")
public class ApiAdvertisementRestController {

    @Autowired
    private TbAdvertisementService tbAdvertisementService;

    @PostMapping("/banner")
    @ApiOperation(value ="banner广告接口",notes = "请勿使用返回数据中的跳转href，其跳转地址时内采中的链接")
    public SupplyResult banner(){

        try {
            List<TbAdvertisement> tbAdvertisements=tbAdvertisementService.getAdsByPosition(1, "1","1",1);
            return SupplyResult.ok(tbAdvertisements);
        }catch (Exception e) {
            log.error("获取banner异常",e);
            return SupplyResult.build(300,"获取banner异常",null);
        }

    }
}
