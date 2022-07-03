package com.zjjzfy.exchange.controller.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.StringUtil;
import com.zjjzfy.exchange.service.ApkVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest")
@Api(tags = "自助终端安装包更新")
public class ApiApkVersion {

    @Autowired
    private ApkVersionService apkVersionService;

    @PostMapping("/checkVersion")
    @ApiOperation(value ="自助终端安装包更新",notes = "获取到安张宝文件名后，拼接[IP:端口/apk/文件名]'拉取安装包！")
    public SupplyResult checkVersion(@RequestParam("productFlavor")@ApiParam(value = "设备类型") String productFlavor){
        SupplyResult rt = null;
        try {
            rt =  apkVersionService.checkVersion(productFlavor);
        } catch (Exception e) {
            rt = SupplyResult.build(500,"检测版本异常"+e.getMessage());
            log.error("检测版本异常",e);
        }
        return rt;
    }


}
