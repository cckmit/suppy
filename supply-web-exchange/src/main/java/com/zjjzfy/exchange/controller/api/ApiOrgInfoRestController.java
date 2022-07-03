package com.zjjzfy.exchange.controller.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.pojo.TbOrgInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author      jackshenonly
 * @description 类说明:机构相关
 * @date        2020-02-27 15:30
 */
@RequestMapping("/rest")
@RestController
@Slf4j
@Api(tags = "机构信息查询")
public class ApiOrgInfoRestController {

    @GetMapping("/orgInfo")
    @ApiOperation(value ="机构信息查询",notes = "根据机构号查询机构信息")
    public SupplyResult orgInfo(@RequestParam("branchno")@ApiParam("机构编号") String branchno){
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if(orgInfo == null){
            return SupplyResult.build(404,"未找到该机构("+branchno+")信息","");
        }else {
            return SupplyResult.build(200,"查询机构信息成功",orgInfo);
        }

    }
}
