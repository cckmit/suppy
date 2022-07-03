package com.zjjzfy.mgt.controller;

import com.zjjzfy.interfaces.TbRoleMapper;
import com.zjjzfy.pojo.TbPermission;
import com.zjjzfy.pojo.TbRole;
import com.zjjzfy.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgt")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleManager")
    public String roleManager(Model model) {
        List<TbRole> roles = (List<TbRole>) roleService.getRoleAll().getData();
        List<Map<String, Object>> permissions = (List<Map<String, Object>>) roleService.getPermissionAll().getData();

        model.addAttribute("roles", roles);
        model.addAttribute("permissions", permissions);
        return "role/roleManager";
    }

    @RequestMapping("/roleAddPage")
    public String roleAddPage() {
        return "role/roleAdd";
    }
}