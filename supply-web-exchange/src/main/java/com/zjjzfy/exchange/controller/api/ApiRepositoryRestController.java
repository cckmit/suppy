package com.zjjzfy.exchange.controller.api;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.service.ApiRepositoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 
 * @author      jackshenonly
 * @description 类说明:兑换终端设置相关
 * @date        2020-02-17 14:32
 */
@RequestMapping("/rest")
@RestController
@Slf4j
@Api(tags = "库存查询相关")
public class ApiRepositoryRestController {

    @Autowired
    private ApiRepositoryService apiRepositoryService;

    @GetMapping("/repositoryOne")
    @ApiOperation(value ="单个产品库存查询",notes = "返回库存对象json;")
    public SupplyResult terminalLogin(@RequestParam("productId")@ApiParam(value = "产品id,tb_product.id") Integer productId ,
                                      @RequestParam("uid")@ApiParam(value = "用户id,tb_user_info.uid") Integer uid,
                                      @RequestParam("branchno")@ApiParam(value = "机构号，tb_org_info.branchno") String branchno){

        String logInfo = "productId:"+productId +",uid:"+uid+",branchno:"+branchno;
        log.info("开始查询单个产品库存：{}",logInfo);
        try {
            SupplyResult rt = apiRepositoryService.repositoryOne(productId,uid,branchno);
            return rt;
        }catch (Exception e) {
            log.error("单个产品库存查询异常:"+logInfo,e);
            return SupplyResult.build(300,"单个产品库存查询异常:"+logInfo,null);
        }

    }

    @GetMapping("/repositoryMore")
    @ApiOperation(value ="多个产品库存查询",notes = "返回库存对象json;")
    public SupplyResult terminalLogin(@RequestParam("productIds")@ApiParam(value = "产品id列表英文逗号隔开,tb_product.id") String productIds ,
                                      @RequestParam("uid")@ApiParam(value = "用户id,tb_user_info.uid") Integer uid,
                                      @RequestParam("branchno")@ApiParam(value = "机构号，tb_org_info.branchno") String branchno){

        String logInfo = "productId:"+productIds +",uid:"+uid+",branchno:"+branchno;
        log.info("开始查询多个产品库存：{}",logInfo);
        try {
            SupplyResult rt = apiRepositoryService.repositoryMore(productIds,uid,branchno);
            return rt;
        }catch (Exception e) {
            log.error("单个产品库存查询异常:"+logInfo,e);
            return SupplyResult.build(300,"多个产品库存查询异常:"+logInfo,null);
        }

    }
}
