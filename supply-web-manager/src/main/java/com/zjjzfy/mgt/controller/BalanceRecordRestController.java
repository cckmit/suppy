package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.order.service.OrgBalanceService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgt")
public class BalanceRecordRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrgBalanceService orgBalanceService;

    @RequestMapping("/queryRechargeRecordData")
    public Object queryRechargeRecordData(Integer org, String orgName, Integer page, Integer limit) {
        TbOrgInfo currentOrg = userService.getOrg();
        if (!currentOrg.getType().equals(OrgType.PLATFORM.getType())) {
            org = currentOrg.getId();
        }
        PageInfo<Map<String, Object>> balances = orgBalanceService.getOrgBalanceRecord(org, orgName, page, limit);
        return LayuiData.ok(balances.getList(), balances.getTotal());
    }
}