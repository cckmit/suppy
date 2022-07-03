package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbUserRole;
import com.zjjzfy.pojo.TbUserRoleExample;
import java.util.List;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbUserRoleMapper extends PublicMapper<TbUserRole> {

    List<String> selectRoleByUid(@Param("uid") Integer uid);
}