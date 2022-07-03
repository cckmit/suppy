package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.provider.SqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RoleMapper {

    /**
     * 选出的角色 状态是"可用的" available=1
     *
     * @param uid
     * @return
     */
    @Select("select role from tb_role where available=1 and id in (select role_id from tb_user_role where uid = #{uid})")
    List<String> findRolesByUid(@Param("uid") int uid);

    @Select("select role_id from tb_user_role where uid = #{uid}")
    List<Integer> findRoleidsByUid(@Param("uid") int uid);

    @Select("select permission from tb_permission a join tb_role_permission trp on a.id = trp.permission_id where trp.role_id = #{roleId}")
    List<String> findPermissionsByRoleId(@Param("roleId") int roleId);

    @Select("select permission from tb_permission a join tb_role_permission trp on a.id = trp.permission_id where trp.role_id in (${roleIds})")
    List<String> findPermissionsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 选出的权限 状态是 "可用的" available=1
     *
     * @param roles
     * @return
     */
    @SelectProvider(type = SqlProvider.class, method = "findPermissionsByRoles")
    List<String> findPermissionsByRoles(@Param("roles") List<String> roles);
}