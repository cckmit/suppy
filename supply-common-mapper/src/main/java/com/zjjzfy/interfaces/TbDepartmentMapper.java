package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbDepartment;
import com.zjjzfy.pojo.TbDepartmentExample;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbDepartmentMapper extends PublicMapper<TbDepartment> {

    List<Map<String,Object>> selectDepartment(@Param("orgId") Integer orgId);
}