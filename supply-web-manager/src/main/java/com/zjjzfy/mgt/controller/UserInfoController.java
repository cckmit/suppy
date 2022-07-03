package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.mgt.config.SupplyManagerProperties;
import com.zjjzfy.pojo.*;
import com.zjjzfy.user.service.RoleService;
import com.zjjzfy.mgt.service.WebUserService;
import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mgt/user")
public class UserInfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private OrganizeService organizeService;
    @Autowired
    private SupplyManagerProperties supplyManagerProperties;

    /**
     * 用户管理页面
     *
     * @return
     */
    @RequestMapping("/userManager")
    public String userManager(Model model) {
        setData(model);
        model.addAttribute("bindBtn", supplyManagerProperties.getBindButton());
        return "user/userManager";
    }

    /**
     * 用户新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/userAddPage")
    public String userAddPage(Model model) {
        setData(model);
        return "user/userAdd";
    }

    /**
     * 用户修改页面
     *
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/userModifyPage")
    public String userModifyPage(Model model, Integer uid) {
        TbUserInfo userInfo = webUserService.getOne(uid);
        List<TbRole> roles = (List<TbRole>) roleService.getRoleAll().getData();
        TbOrgInfo org = organizeService.getOne(userInfo.getOrgid(), null);
        List<String> userRoles = (List<String>) roleService.getRoleByUid(uid).getData();

        model.addAttribute("org", org);
        model.addAttribute("roles", roles);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("userRole", userRoles.get(0));
        setData(model);
        return "user/userModify";
    }

    /**
     * 修改密码页面
     *
     * @return
     */
    @RequestMapping("/changePwdPage")
    public String changePwdPage() {
        return "user/changePwd";
    }

    @RequestMapping("/")
    public String s() {
        return "";
    }

    public void setData(Model model) {
        TbOrgInfo org = userService.getOrg();
        List<TbOrgInfo> orgs = null;
        if (org.getType() != OrgType.PLATFORM.getType()) {
            orgs = organizeService.getOrgOrParent(org.getBranchno());
        } else {
            orgs = organizeService.getOrgAll(null, null);
        }
        List<TbRole> roles = (List<TbRole>) roleService.getRoleAll().getData();

        model.addAttribute("orgs", orgs);
        model.addAttribute("roles", roles);
        model.addAttribute("orgType", org.getType());
    }
}