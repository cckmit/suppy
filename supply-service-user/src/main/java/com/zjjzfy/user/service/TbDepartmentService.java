package com.zjjzfy.user.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbDepartment;

import java.util.List;

/**
 * @author zyx
 * @date 2020/6/11 9:52 上午
 */
public interface TbDepartmentService {


    SupplyResult selectTbDepartment(Integer pageNo, Integer pageSize,Integer orgId);

    SupplyResult deleteTblDepartment(Integer id);

    SupplyResult modifyDepartment(String deptname,Integer orgId,Integer deptId);

    SupplyResult addDepartment(String deptname,Integer orgId);

    SupplyResult delDepartment(Integer id);
}
