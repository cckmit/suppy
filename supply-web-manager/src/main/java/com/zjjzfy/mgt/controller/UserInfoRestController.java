package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.NumberUtil;
import com.zjjzfy.mgt.service.WebUserService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mgt/user")
public class UserInfoRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private WebUserService webUserService;

    @RequestMapping("/getUser")
    public Object getUser(String username, String name, Integer orgId, Integer roleId, Integer page, Integer limit) {
        String branchNo = null;
        TbOrgInfo org = userService.getOrg();
        if (org.getType() == OrgType.SUPPLIER.getType() || org.getType() == OrgType.PURCHASER.getType()) {
            branchNo = org.getBranchno();
            orgId = null;
        }
        PageInfo<Map<String, Object>> pages = webUserService.getUser(username, name, orgId, branchNo, roleId, page, limit);
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }

    @RequestMapping("/userAdd")
    public Object userAdd(TbUserInfo tbUserInfo, Integer roleId) {
        SupplyResult result = checkUser(tbUserInfo);
        if (result.getStatus() != 200) {
            return result;
        }
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        TbOrgInfo currentOrg = userService.getOrg();
        result = webUserService.insertUser(tbUserInfo, currentUser, currentOrg, roleId);

        return result;
    }

    @RequestMapping("/userModify")
    public Object userModify(TbUserInfo tbUserInfo, Integer roleId) {
        tbUserInfo.setPassword("0");
        SupplyResult result = checkUser(tbUserInfo);
        if (result.getStatus() != 200) {
            return result;
        }
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        TbOrgInfo currentOrg = userService.getOrg();
        result = webUserService.userModify(tbUserInfo, currentUser, currentOrg, roleId);

        return result;
    }

    @RequestMapping("/updateUserState")
    public Object updateUserState(Integer uid) {
        SupplyResult result = webUserService.updateUserState(uid);
        return result;
    }

    @RequestMapping("/unbind")
    public Object unbind(Integer uid) {
        SupplyResult result = webUserService.unbind(uid);
        return result;
    }

    @RequestMapping("/changePwd")
    public Object changePwd(String oldPwd, String newPwd, String okPwd) {
        if (oldPwd == null || oldPwd.equals("")) {
            return SupplyResult.build(300, "原密码为空");
        }
        if (newPwd == null || newPwd.equals("")) {
            return SupplyResult.build(300, "新密码为空");
        }
        if (okPwd == null || okPwd.equals("")) {
            return SupplyResult.build(300, "确认密码为空");
        }
        if (!newPwd.equals(okPwd)) {
            return SupplyResult.build(300, "两次密码不一致");
        }
        if (!NumberUtil.checkPassword(newPwd)) {
            return SupplyResult.build(300, "密码长度为8到20位,必须包含字母和数字");
        }
        SupplyResult result = shiroService.changePwd(oldPwd, newPwd);
        return result;
    }

    @RequestMapping("/resetPwd")
    public Object resetPwd(Integer uid) {
        SupplyResult result = shiroService.resetPwd(uid);
        return result;
    }

    public SupplyResult checkUser(TbUserInfo tbUserInfo) {
        if (tbUserInfo == null) {
            return SupplyResult.build(300, "用户信息为空");
        }
        if (tbUserInfo.getUsername() == null || tbUserInfo.getUsername().equals("")) {
            return SupplyResult.build(300, "账号为空");
        }
        if (tbUserInfo.getName() == null || tbUserInfo.getName().equals("")) {
            return SupplyResult.build(300, "用户昵称为空");
        }
        if (tbUserInfo.getPassword() == null || tbUserInfo.getPassword().equals("")) {
            return SupplyResult.build(300, "密码为空");
        }
        if (tbUserInfo.getOrgid() == null || tbUserInfo.getOrgid().equals("")) {
            return SupplyResult.build(300, "所属单位为空");
        }
        return SupplyResult.ok();
    }
}