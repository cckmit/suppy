package com.zjjzfy.user.service;

import com.zjjzfy.common.entity.SupplyResult;

import java.util.List;

public interface RoleService {
    SupplyResult getRoleAll();

    SupplyResult getPermissionAll();

    SupplyResult getRoleByUid(Integer uId);

    SupplyResult getPermissionByRoleId(Integer roleId);

    SupplyResult getPermissionIdRoleId(Integer roleId);

    SupplyResult roleAdd(String role);

    SupplyResult rolePermissionChange(Integer roleId, String permissionStr, String roleName);

    SupplyResult roleDelete(Integer roleId);
}