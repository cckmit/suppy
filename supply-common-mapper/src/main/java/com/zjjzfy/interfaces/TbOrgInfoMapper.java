package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbOrgInfoExample;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbOrgInfoMapper extends PublicMapper<TbOrgInfo> {
    List<Map<String, Object>> selectOrg(@Param("userBranchNo") String userBranchNo,
                                        @Param("branch") String branch,
                                        @Param("branchName") String branchName,
                                        @Param("contact") String contact,
                                        @Param("telephone") String telephone,
                                        @Param("type") String type,
                                        @Param("id") Integer id);

    Double selectOrgUsedBalance(@Param("branch") String branch,
                                @Param("branch2") String branch2);

    List<Map<String,Object>> selectNotDeleteOrgInfo();

}