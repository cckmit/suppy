package com.zjjzfy.controller;

import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/mbl")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/loginSelect")
    public String loginSelect() {
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        if (tbUserInfo != null) {
            return "redirect:/mbl/home";
        }
        return "/login/loginSelect";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, String cls, String openId) {
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        if (tbUserInfo != null) {
            return "redirect:/mbl/home";
        }

        if(cls!=null &&cls.equals("5")){
            return "redirect:http://hynsgl.tianqun.club:8080/mbl/login?cls=5";
        }

        if (openId != null && !openId.equals("")) {
            SupplyResult result = shiroService.densityFreeLogin(openId);
            if (result.getStatus() == 200) {
                //记录登录日志
                userService.wxUserLogin(null, openId);
                return "redirect:/mbl/home";
            }
        }

        request.getSession().setAttribute("cls", cls);
        model.addAttribute("cls", cls);
        model.addAttribute("openId", openId);
        return "/login/login";
    }

    /**
     * 登出
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, Model model, HttpServletResponse response) {
        String cls = (String) request.getSession().getAttribute("cls");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "安全退出！");
        try {
            response.sendRedirect("login?cls=" + cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/logon")
    public String logon(String username, String password, String openId, Model model) {

        SupplyResult result;
        if (openId != null && !openId.equals("")) {
            result = shiroService.densityFreeLogin(openId);

            if (result.getStatus() == 200) {
                TbOrgInfo org = userService.getOrg();
                if (org.getType().equals(OrgType.PLATFORM.getType())) {
                    model.addAttribute("msg", "当前用户属于平台方！");
                    return "login/login";
                }
                //记录登录日志
                userService.wxUserLogin(username, openId);
                return "redirect:/mbl/home";
            }
        }
        if (username == null || username.equals("")) {
            model.addAttribute("msg", "请输入账号！");
            return "login/login";
        }
        if (password == null || password.equals("")) {
            model.addAttribute("msg", "请输入密码！");
            return "login/login";
        }

        result = shiroService.login(username, password);

        if (result.getStatus() == 200) {
            //记录登录日志
            userService.wxUserLogin(username, openId);
            return "redirect:/mbl/home";
        }

        model.addAttribute("msg", result.getMsg());
        return "login/login";
    }

    @RequestMapping("/home")
    public String home() {
        TbOrgInfo org = userService.getOrg();
        if (org.getType().equals(OrgType.SUPPLIER.getType())) {
            //供货方跳转首页
            //TODO 需修改
            return "redirect:/deliver/index";
        } else if (org.getType().equals(OrgType.PURCHASER.getType())) {
            //订货方跳转首页
            return "redirect:/purchase/index";
        } else {
            //平台方直接登出
            return "redirect:/mbl/logout";
        }
    }
}