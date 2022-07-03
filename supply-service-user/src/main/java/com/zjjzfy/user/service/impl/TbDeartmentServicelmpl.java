package com.zjjzfy.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.annotation.PagehelpService;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbDepartmentMapper;
import com.zjjzfy.pojo.TbDepartment;
import com.zjjzfy.user.service.TbDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2020/6/11 9:52 上午
 */
@Service
public class TbDeartmentServicelmpl implements TbDepartmentService {

    @Autowired
    private TbDepartmentMapper tbDepartmentMapper;

    @Override
    public SupplyResult selectTbDepartment(Integer pageNo, Integer pageSize,Integer orgId) {
        if(pageNo == null){
            pageNo =1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String, Object>> maps = tbDepartmentMapper.selectDepartment(orgId);
        return SupplyResult.ok(new PageInfo<>(maps))  ;
    }

    @Override
    public SupplyResult deleteTblDepartment(Integer id) {
        if(id != null && id > 0){
            tbDepartmentMapper.deleteByPrimaryKey(id);
        }else{
            return SupplyResult.build(300,"请选择部门");
        }

        return SupplyResult.ok();
    }

    @Override
    public SupplyResult modifyDepartment(String deptname,Integer orgId,Integer deptId) {
        if(deptId == null){
            return SupplyResult.build(300,"请选择部门");
        }
        TbDepartment department = tbDepartmentMapper.selectByPrimaryKey(deptId);
        department.setName(deptname);
        department.setOrgId(orgId);
        department.setUpdateTime(new Date());
        tbDepartmentMapper.updateByPrimaryKey(department);
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult addDepartment(String deptname,Integer orgId) {
        try {
            TbDepartment department = new TbDepartment();
            department.setCreateTime(new Date());
            department.setName(deptname);
            department.setOrgId(orgId);
            department.setStatus(0);
            tbDepartmentMapper.insertSelective(department);
        }catch(Exception e){
            return SupplyResult.build(300,"新增失败");
        }
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult delDepartment(Integer id) {

        TbDepartment department = tbDepartmentMapper.selectByPrimaryKey(id);
        department.setDeleteTime(new Date());
        department.setStatus(1);
        tbDepartmentMapper.updateByPrimaryKey(department);
        return SupplyResult.ok();
    }
}
