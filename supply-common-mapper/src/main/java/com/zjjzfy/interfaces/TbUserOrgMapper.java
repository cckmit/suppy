package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbUserOrg;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbUserOrgMapper extends PublicMapper<TbUserOrg> {

    List<Map<String, Object>> getIndentorUser(@Param("username") String username,
                                              @Param("nickname") String nickname,
                                              @Param("branchNo") String branchNo);

    /**
     * 查询用户可审核单位下的用户
     *
     * @param uId
     * @return
     */
    List<Integer> selectChildId(@Param("uId") Integer uId);

    /**
     * 查询用户可审核单位
     *
     * @param uId
     * @return
     */
    List<Integer> selectUserOrgId(@Param("uId") Integer uId);
}