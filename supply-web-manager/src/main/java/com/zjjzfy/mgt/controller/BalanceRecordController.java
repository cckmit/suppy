package com.zjjzfy.mgt.controller;

import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mgt")
public class BalanceRecordController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizeService organizeService;

    @RequestMapping("/rechargeRecordQuery")
    public String rechargeRecordQuery(Model model) {
        List<TbOrgInfo> orgs = organizeService.getOrgAll(null, null);
        TbOrgInfo currentOrg = userService.getOrg();
        model.addAttribute("orgs", orgs);
        model.addAttribute("currentOrg", currentOrg);
        return "rechargeRecord/rechargeRecordQuery";
    }
}