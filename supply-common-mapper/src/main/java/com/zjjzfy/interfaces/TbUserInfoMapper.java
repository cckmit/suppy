package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbUserInfo;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbUserInfoMapper extends PublicMapper<TbUserInfo> {

    @Override
    int insertUseGeneratedKeys(TbUserInfo tbUserInfo);

    List<Map<String, Object>> selectUser(
            @Param("username") String username,
            @Param("name") String name,
            @Param("orgId") Integer orgId,
            @Param("branchNo") String branchNo,
            @Param("roleId") Integer roleId);
}