package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbNetworker;
import com.zjjzfy.user.service.NetWorkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyx  网点员工管理
 * @date 2020/3/27 下午5:15
 */
@RequestMapping("mgt")
@RestController
public class NetWorkController {

    @Autowired
    private NetWorkUserService netWorkUserService;

    @RequestMapping("insertNetWorkUser")
    public SupplyResult insertNetWorkUser(TbNetworker tbNetworker){
        return netWorkUserService.insertNetWorkUser(tbNetworker);
    }

    @RequestMapping("updateNetWorkUser")
    public SupplyResult updateNetWorkUser(TbNetworker tbNetworker){
        return netWorkUserService.updateNetWorkUser(tbNetworker);
    }

    @RequestMapping("deleteNetWorkUser")
    public SupplyResult deleteNetWorkUser(Integer id){
        return  netWorkUserService.deleteNetWorkUser(id);
    }

    @RequestMapping("selectNetWorkUser")
    public SupplyResult insertNetWorkUser(Integer pageNo,Integer pageSize,Integer orgId){
        return  netWorkUserService.selectNetWorkUser(pageNo,pageSize,orgId);
    }

    @RequestMapping("selectOrgInfo")
    public SupplyResult selectOrgInfo(){
        return netWorkUserService.selectOrgInfo();
    }

    @RequestMapping("selectNetWorkRepertory")
    public SupplyResult selectNetWorkRepertory(Integer pageNo,Integer pageSize,Integer orgId){
        return netWorkUserService.selectNetWorkRepertory(pageNo, pageSize,orgId);
    }

}
