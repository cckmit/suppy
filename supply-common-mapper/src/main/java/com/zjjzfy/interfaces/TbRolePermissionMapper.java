package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbRolePermission;
import com.zjjzfy.pojo.TbRolePermissionExample;
import java.util.List;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbRolePermissionMapper extends PublicMapper<TbRolePermission> {
    List<String> selectPermissionIdRoleId(@Param("roleId") Integer roleId);
}