package com.zjjzfy.mgt.controller;

import com.zjjzfy.interfaces.TbDepartmentMapper;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.pojo.TbDepartment;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbOrgInfoExample;
import com.zjjzfy.user.service.TbDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2020/6/11 10:18 上午
 */
@RequestMapping("/mgt")
@Controller
public class DepartmentController {

    @Autowired
    private TbDepartmentMapper departmentMapper;
    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;

    @RequestMapping("toDepartment")
    public String toDepartment(){
        return "dept/deptManager";
    }

    @RequestMapping("addDepartment")
    public String addDepartment(){
        return "dept/dept-add";
    }


    @RequestMapping("deptEdit")
    public String deptEdit(Integer deptId, Model model){

        TbDepartment department = departmentMapper.selectByPrimaryKey(deptId);
        List<TbOrgInfo> tbOrgInfos = tbOrgInfoMapper.selectAll();
        model.addAttribute("dept",department);
        model.addAttribute("orgs",tbOrgInfos);
        return "dept/dept-edit";
    }
}
