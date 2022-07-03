package com.zjjzfy.mgt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbRole;
import com.zjjzfy.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgt")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    /**
     * 所有角色
     *
     * @return
     */
    @RequestMapping("/getRoleAll")
    public Object getRoleAll() {
        List<TbRole> roles = (List<TbRole>) roleService.getRoleAll().getData();
        return LayuiData.ok(roles, (long) roles.size());
    }

    /**
     * 所有菜单
     *
     * @return
     */
    @RequestMapping("/getPermissionAll")
    public Object getPermissionAll() {
        List<Map<String, Object>> permissions = (List<Map<String, Object>>) roleService.getPermissionAll().getData();
        return LayuiData.ok(0, new HashMap<String, Object>() {{
            put("trees", permissions);
        }}, (long) permissions.size());
    }

    /**
     * 角色对应菜单
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/getPermissionByRoleId")
    public Object getPermissionByRoleId(Integer roleId) {
        List<String> list = (List<String>) roleService.getPermissionIdRoleId(roleId).getData();
        return list;
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/roleAdd")
    public Object roleAdd(String role) {
        SupplyResult result = roleService.roleAdd(role);
        return result;
    }

    /**
     * 角色修改
     *
     * @param roleId
     * @param permissionStr
     * @param roleName
     * @return
     */
    @RequestMapping("/rolePermissionChange")
    public Object rolePermissionChange(Integer roleId, String permissionStr, String roleName) {
        SupplyResult result = roleService.rolePermissionChange(roleId, permissionStr, roleName);
        return result;
    }

    /**
     * 角色删除
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/roleDelete")
    public Object roleDelete(Integer roleId) {
        SupplyResult result = roleService.roleDelete(roleId);
        return result;
    }
}