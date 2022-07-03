package com.zjjzfy.exchange.controller.api;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.pojo.Param;
import com.zjjzfy.exchange.service.TerminalSettingService;
import com.zjjzfy.order.service.TbAdvertisementService;
import com.zjjzfy.pojo.TbAdvertisement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 
 * @author      jackshenonly
 * @description 类说明:兑换终端设置相关
 * @date        2020-02-17 14:32
 */
@RequestMapping("/rest")
@RestController
@Slf4j
@Api(tags = "兑换终端设置相关")
public class ApiTerminalSettingRestController {

    @Autowired
    private TerminalSettingService terminalSettingService;

    @PostMapping("/terminalLogin")
    @ApiOperation(value ="终端设置认证",notes = "认证成功回传信息有：uid,username(账号),name(昵称)，branchno(所属组织机构号)")
    public SupplyResult terminalLogin(@RequestParam("uname")@ApiParam(value = "账户") String uname ,
                                      @RequestParam("pwd")@ApiParam(value = "认证口令") String pwd){
        log.info("终端设置认证：uname:{},pwd:{}", uname, pwd);
        try {
            SupplyResult rt = terminalSettingService.login(uname,pwd);
            return rt;
        }catch (Exception e) {
            log.error("获取设置终端账户的信息异常",e);
            return SupplyResult.build(300,"获取设置终端账户的信息异常",null);
        }

    }
}
