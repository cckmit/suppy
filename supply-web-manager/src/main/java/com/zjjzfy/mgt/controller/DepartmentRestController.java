package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbDepartmentMapper;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.pojo.TbDepartment;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbOrgInfoExample;
import com.zjjzfy.user.service.TbDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zyx
 * @date 2020/6/11 10:18 上午
 */
@RequestMapping("/mgt")
@RestController
public class DepartmentRestController {

    @Autowired
    private TbDepartmentService tbDepartmentService;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbDepartmentMapper departmentMapper;

    @RequestMapping("selectDepartment")
    public SupplyResult selectDepartment(Integer pageNo,Integer pageSize,Integer orgId){
        return tbDepartmentService.selectTbDepartment(pageNo,pageSize,orgId);
    }

    @RequestMapping("DeleteDepartment")
    public SupplyResult DeleteDepartment(Integer id){
        return tbDepartmentService.deleteTblDepartment(id);
    }

    @RequestMapping("modifyDeartment")
    public SupplyResult modifyDeartment(String deptname,Integer orgId,Integer deptId){
        return tbDepartmentService.modifyDepartment(deptname,orgId,deptId);
    }

    @RequestMapping("addDept")
    public SupplyResult addDept(String deptname,Integer orgId){
        return tbDepartmentService.addDepartment(deptname, orgId);
    }

    @RequestMapping("delDepartment")
    public SupplyResult delDepartment(Integer id){
        return  tbDepartmentService.deleteTblDepartment(id);
    }

    @RequestMapping("addDepts")
    public void addDept(){
        TbOrgInfoExample example = new TbOrgInfoExample();
        example.createCriteria().andTypeEqualTo(OrgType.PURCHASER.getType());
        List<TbOrgInfo> tbOrgInfos = tbOrgInfoMapper.selectByExample(example);
        for (TbOrgInfo tbOrgInfo : tbOrgInfos) {
            List<TbDepartment> list = new ArrayList<>();

            TbDepartment department1 = new TbDepartment();
            department1.setName("前台");
            department1.setCreateTime(new Date());
            department1.setStatus(0);
            department1.setOrgId(tbOrgInfo.getId());



            TbDepartment department2 = new TbDepartment();
            department2.setName("储蓄部");
            department2.setCreateTime(new Date());
            department2.setStatus(0);
            department2.setOrgId(tbOrgInfo.getId());

            TbDepartment department3 = new TbDepartment();
            department3.setName("小额信贷部");
            department3.setCreateTime(new Date());
            department3.setStatus(0);
            department3.setOrgId(tbOrgInfo.getId());

            TbDepartment department4 = new TbDepartment();
            department4.setName("业务一部");
            department4.setCreateTime(new Date());
            department4.setStatus(0);
            department4.setOrgId(tbOrgInfo.getId());

            TbDepartment department5 = new TbDepartment();
            department5.setName("业务二部");
            department5.setCreateTime(new Date());
            department5.setStatus(0);
            department5.setOrgId(tbOrgInfo.getId());

            TbDepartment department6 = new TbDepartment();
            department6.setName("业务三部");
            department6.setCreateTime(new Date());
            department6.setStatus(0);
            department6.setOrgId(tbOrgInfo.getId());

            TbDepartment department7 = new TbDepartment();
            department7.setName("业务四部");
            department7.setCreateTime(new Date());
            department7.setStatus(0);
            department7.setOrgId(tbOrgInfo.getId());

            list.add(department1);
            list.add(department2);
            list.add(department3);
            list.add(department4);
            list.add(department5);
            list.add(department6);
            list.add(department7);
            departmentMapper.insertList(list);
        }
    }
}

