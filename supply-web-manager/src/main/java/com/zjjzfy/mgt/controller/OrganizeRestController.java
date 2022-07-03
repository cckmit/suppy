package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.NumberUtil;
import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgt")
public class OrganizeRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserOrgService userOrgService;
    @Autowired
    private OrganizeService organizeService;


    @RequestMapping("/getOrganize")
    public Object getOrganize(String branch, String branchName, String contact, String telephone, String type, Integer page, Integer limit) {
        TbOrgInfo org = userService.getOrg();
        String userBranchNo = null;
        if (org.getType().equals(OrgType.PURCHASER.getType())) {
            //订货方可查看当前单位和子单位
            userBranchNo = org.getBranchno();
        }
        PageInfo<Map<String, Object>> pages = organizeService.getOrg(userBranchNo, branch, branchName, contact, telephone, type, page, limit,null);
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }

    /**
     * 停用/启动
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateOrganizeState")
    public Object updateOrganizeState(Integer id) {
        SupplyResult result = organizeService.updateState(id);
        return result;
    }

    /**
     * 单位新增
     *
     * @param tbOrgInfo
     * @return
     */
    @RequestMapping("/organizeAdd")
    public Object organizeAdd(TbOrgInfo tbOrgInfo) {
        SupplyResult result = check(tbOrgInfo);
        if (result.getStatus() != 200) {
            return result;
        }
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        TbOrgInfo currentOrg = userService.getOrg();
        result = userOrgService.checkUserOrg(currentOrg, tbOrgInfo, currentUser.getUid());
        if (result.getStatus() != 200) {
            return result;
        }
        result = organizeService.insertOrg(tbOrgInfo);
        return result;
    }

    /**
     * 单位修改
     *
     * @param tbOrgInfo
     * @return
     */
    @RequestMapping("/organizeModify")
    public Object organizeModify(TbOrgInfo tbOrgInfo) {
        if (tbOrgInfo == null || tbOrgInfo.getId() == null) {
            return SupplyResult.build(300, "修改单位不明确");
        }
        SupplyResult result = check(tbOrgInfo);
        if (result.getStatus() != 200) {
            return result;
        }
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        TbOrgInfo currentOrg = userService.getOrg();
        result = userOrgService.checkUserOrg(currentOrg, tbOrgInfo, currentUser.getUid());
        if (result.getStatus() != 200) {
            return result;
        }
        result = organizeService.updateOrg(tbOrgInfo, currentOrg);
        return result;
    }

    public SupplyResult check(TbOrgInfo tbOrgInfo) {
        if (tbOrgInfo.getBranchno() == null || tbOrgInfo.getBranchno().equals("")) {
            return SupplyResult.build(300, "单位编号为空");
        }
        if (tbOrgInfo.getOrgName() == null || tbOrgInfo.getOrgName().equals("")) {
            return SupplyResult.build(300, "单位名称为空");
        }
        if (tbOrgInfo.getContact() == null || tbOrgInfo.getContact().equals("")) {
            return SupplyResult.build(300, "联系人为空");
        }
        if (tbOrgInfo.getTelephone() == null || tbOrgInfo.getTelephone().equals("")) {
            return SupplyResult.build(300, "联系电话为空");
        }
        if (tbOrgInfo.getAddress() == null || tbOrgInfo.getAddress().equals("")) {
            return SupplyResult.build(300, "地址为空");
        }
        if (tbOrgInfo.getParentBranchno() == null) {
            tbOrgInfo.setParentBranchno("-1");
        }
        return SupplyResult.ok();
    }

    /**
     * 获取所有订货方单位
     *
     * @return
     */
    @RequestMapping("/getPurchaserOrganizeAll")
    public Object getPurchaserOrganizeAll() {
        List<TbOrgInfo> orgs = organizeService.getOrgAll(null, OrgType.PURCHASER.getType());
        return LayuiData.ok(orgs, (long) orgs.size());
    }

    @RequestMapping("selectAllSellerPaging")
    public LayuiData selectAllSellerPaging(Integer limit, Integer page, String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        PageInfo<TbOrgInfo> pageInfo = userOrgService.selectOrgPageByType(limit, page, OrgType.valueOf(type));
        LayuiData data = new LayuiData();
        data.setCode(200);
        data.setMsg("");
        data.setCount(pageInfo.getTotal());
        data.setData(pageInfo.getList());
        return data;
    }

    @RequestMapping("/getOrgName")
    public SupplyResult organizeModifyPage(Model model, Integer id) {
        TbOrgInfo org = organizeService.getOne(id, null);
        return SupplyResult.ok(org);
    }
}