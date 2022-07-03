package com.zjjzfy.user.service.impl;

import com.zjjzfy.common.config.RoleStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbPermissionMapper;
import com.zjjzfy.interfaces.TbRoleMapper;
import com.zjjzfy.interfaces.TbRolePermissionMapper;
import com.zjjzfy.interfaces.TbUserRoleMapper;
import com.zjjzfy.pojo.*;
import com.zjjzfy.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private TbRoleMapper tbRoleMapper;
    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;
    @Autowired
    private TbPermissionMapper tbPermissionMapper;
    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public SupplyResult getRoleAll() {
        TbRoleExample example = new TbRoleExample();
        example.createCriteria().andAvailableEqualTo(RoleStatus.ON.getStatus());
        List<TbRole> list = tbRoleMapper.selectByExample(example);
        return SupplyResult.ok(list);
    }

    @Override
    public SupplyResult getPermissionAll() {
        List<Map<String, Object>> list = tbPermissionMapper.selectPermissionAll();
        return SupplyResult.ok(list);
    }

    @Override
    public SupplyResult getRoleByUid(Integer uId) {
        List<String> roles = tbUserRoleMapper.selectRoleByUid(uId);
        return SupplyResult.ok(roles);
    }

    @Override
    public SupplyResult getPermissionByRoleId(Integer roleId) {
        TbRolePermissionExample example = new TbRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<TbRolePermission> tbRolePermissions = tbRolePermissionMapper.selectByExample(example);
        return SupplyResult.ok(tbRolePermissions);
    }

    @Override
    public SupplyResult getPermissionIdRoleId(Integer roleId) {
        List<String> list = tbRolePermissionMapper.selectPermissionIdRoleId(roleId);
        return SupplyResult.ok(list);
    }

    @Override
    public SupplyResult roleAdd(String role) {
        TbRole tbRole = new TbRole();
        tbRole.setRole(role);
        tbRole.setAvailable(RoleStatus.ON.getStatus());
        tbRole.setDescription(role);
        tbRoleMapper.insert(tbRole);
        return SupplyResult.ok();
    }

    @Override
    @Transactional
    public SupplyResult rolePermissionChange(Integer roleId, String permissionStr, String roleName) {
        if (roleId == null) {
            return SupplyResult.build(300, "未选择角色");
        }
        if (permissionStr == null || permissionStr.equals("")) {
            return SupplyResult.build(300, "未选择权限");
        }
        TbRole tbRole = null;

        if (roleId != null) {
            tbRole = tbRoleMapper.selectByPrimaryKey(roleId);
            if (tbRole == null) {
                return SupplyResult.build(300, "角色不存在");
            }
        } else {
            return SupplyResult.build(300, "角色未选择");
        }
        if (roleName != null && !"".equals(roleName)) {
            tbRole.setDescription(roleName);
        }
        tbRoleMapper.updateByPrimaryKey(tbRole);

        TbRolePermissionExample example = new TbRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        tbRolePermissionMapper.deleteByExample(example);

        TbRolePermission rolePermission = null;
        List<TbRolePermission> rolePermissions = new ArrayList<>();
        String[] permissionIds = permissionStr.split(",");
        for (String permissId : permissionIds) {
            rolePermission = new TbRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(Integer.valueOf(permissId));
            rolePermissions.add(rolePermission);
            tbRolePermissionMapper.insertSelective(rolePermission);
        }

        return SupplyResult.ok();
    }

    @Override
    @Transactional
    public SupplyResult roleDelete(Integer roleId) {
        if (roleId == null || roleId <= 0) {
            return SupplyResult.build(300, "未选择角色");
        }

        TbRole tbRole = null;
        if (roleId != null) {
            tbRole = tbRoleMapper.selectByPrimaryKey(roleId);
            if (tbRole == null) {
                return SupplyResult.build(300, "角色不存在");
            }
        } else {
            return SupplyResult.build(300, "角色未选择");
        }

        TbUserRoleExample example = new TbUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<TbUserRole> userRoles = tbUserRoleMapper.selectByExample(example);
        if (userRoles != null && userRoles.size() > 0) {
            return SupplyResult.build(300, "当前角色已在使用中");
        }
        tbRole.setAvailable(RoleStatus.OFF.getStatus());
        tbRoleMapper.updateByPrimaryKeySelective(tbRole);
        return SupplyResult.ok();
    }
}