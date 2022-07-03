package com.zjjzfy.mgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgt")
public class OrganizeUserCheckController {

    /**
     * 用户审核行社订单
     *
     * @return
     */
    @RequestMapping("/userCheckOrg")
    public String userOrgCheck() {
        return "userCheckOrg/users";
    }

    @RequestMapping("/checkOrgs")
    public String checkOrgs(Model model, Integer uId) {
        model.addAttribute("uId", uId);
        return "userCheckOrg/orgs";
    }
}