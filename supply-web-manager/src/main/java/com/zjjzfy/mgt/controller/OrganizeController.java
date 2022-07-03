package com.zjjzfy.mgt.controller;

import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.RoleService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mgt")
public class OrganizeController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizeService organizeService;

    /**
     * 组织管理
     *
     * @return
     */
    @RequestMapping("/organizeManager")
    public String organizeManager(Model model) {
        TbOrgInfo org = userService.getOrg();
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        List<String> data = (List<String>) roleService.getRoleByUid(currentUser.getUid()).getData();
        model.addAttribute("orgType", org.getType());
        model.addAttribute("userRole", data.get(0));
        return "organize/organizeManager";
    }

    @RequestMapping("/organizeAddPage")
    public String organizeAddPage(Model model) {
        List<TbOrgInfo> orgs = organizeService.getBranch(null);
        TbOrgInfo org = userService.getOrg();
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        List<String> data = (List<String>) roleService.getRoleByUid(currentUser.getUid()).getData();

        TbOrgInfo orgInfo = new TbOrgInfo();
        orgInfo.setBranchno("0");
        orgInfo.setOrgName("无");
        orgs.add(0, orgInfo);
        model.addAttribute("org", org);
        model.addAttribute("orgs", orgs);
        model.addAttribute("userRole", data.get(0));
        return "organize/organizeAdd";
    }

    @RequestMapping("/organizeModifyPage")
    public String organizeModifyPage(Model model, Integer id) {
        TbOrgInfo org = organizeService.getOne(id, null);
//        if (!org.getParentBranchno().equals("0") && !org.getParentBranchno().equals("-1")) {
        List<TbOrgInfo> orgs = organizeService.getBranch(org.getBranchno());
        model.addAttribute("orgs", orgs);
//        }
        TbOrgInfo org2 = userService.getOrg();
        model.addAttribute("org", org);
        model.addAttribute("orgType", org2.getType());
//        Map<String,String> map = new HashMap<>();
//        map.put("Supplier","平台方");
//        map.put("orderer","订货方");
//        map.put("platform","平台");
        return "organize/organizeModify";
    }

    @RequestMapping("/toNetworkUserManager")
    public String toNetworkUserManager(Model model){
        TbOrgInfo org = userService.getOrg();
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        List<String> data = (List<String>) roleService.getRoleByUid(currentUser.getUid()).getData();
        model.addAttribute("orgType", org.getType());
        model.addAttribute("userRole", data.get(0));
        return "organize/networkUserManager";
    }

    @RequestMapping("/toNetworkAdd")
    public String toNetworkAdd(){
        return "organize/networkAdd";
    }
}