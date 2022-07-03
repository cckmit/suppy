package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbBalanceRecord;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbBalanceRecordMapper extends PublicMapper<TbBalanceRecord> {
    List<Map<String, Object>> selectOrgBalance(@Param("orgId") Integer orgId,
                                               @Param("orgName") String orgName);
}