package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgt")
public class OrganizeUserCheckRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserOrgService userOrgService;

    /**
     * 获取所有订货商用户
     *
     * @param username
     * @param nickname
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getIndentorUser")
    public Object getIndentorUser(String username, String nickname, Integer page, Integer limit) {
        String branchNo = null;
        TbOrgInfo org = userService.getOrg();
        if (org.getType() == OrgType.PURCHASER.getType()) {
            branchNo = org.getBranchno();
        }

        PageInfo<Map<String, Object>> pages = (PageInfo<Map<String, Object>>) userOrgService.getIndentorUser(username, nickname, branchNo, page, limit).getData();
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }

    /**
     * 查询客户可审核单位
     *
     * @param uId
     * @return
     */
    @RequestMapping("/getUserCheckOrg")
    public Object getUserCheckOrg(Integer uId) {
        List<Integer> users = (List<Integer>) userOrgService.getUserOrgId(uId).getData();
        return users;
    }

    /**
     * 用户可审核单位修改
     *
     * @param id
     * @param orgIdStr
     * @return
     */
    @RequestMapping("/updateUserCheckOrg")
    public Object updateUserCheckOrg(Integer id, String orgIdStr) {
        SupplyResult result = userOrgService.updateUserCheckOrg(id, orgIdStr);
        return result;
    }
}