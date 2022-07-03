package com.zjjzfy.mgt.controller;

import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/mgt")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private UserOrgService userOrgService;
    @Autowired
    private ProductService productService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "login";
    }

    @GetMapping("countOrderDataUrl")
    public String demo(){
        return "countOrderData";
    }

    /**
     * 平台首页
     *
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 登出
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "login";
    }


    /**
     * 订货方首页
     *
     * @return
     */
    @RequestMapping("/" +
            "indentorHhome")
    public String indentorHhome() {
        return "indentor/indentorHome";
    }

    /**
     * 供货方方首页
     *
     * @return
     */
    @RequestMapping("/supplierrHome")
    public String supplierrHome() {
        return "supplier/supplierrHome";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "username", required = false) String username,
                        @RequestParam(name = "password", required = false) String password,
                        @RequestParam(name = "vrifyCode", required = false) String vrifyCode,
                        ModelMap map) throws Exception {

        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        if (tbUserInfo != null) {
            return "redirect:/mgt/orgnizeType";
        }

        log.info("HomeController.login()");
        /*if (username == null || password == null || vrifyCode == null) {
            map.put("msg", "请输入必填项");
            return "login";
        }*/
        map.put("username", username);
        map.put("password", password);

        /*Object object = SecurityUtils.getSubject().getSession().getAttribute("vrifyCode");
        String sessionVrifyCode = object == null ? "" : String.valueOf(object);
        log.info("vrifyCode post:" + vrifyCode);
        log.info("vrifyCode get from session:" + sessionVrifyCode);*/

       /* if (!vrifyCode.equalsIgnoreCase(sessionVrifyCode)) {
            map.put("msg", "验证码不正确，请重新获取！");
            return "login";
        }*/

        String msg = "";
        try {
            SupplyResult result = shiroService.login(username, password);
            if (result.getStatus() == 200) {
                return "redirect:/mgt/orgnizeType";
            } else {
                map.put("msg", result.getMsg());
                return "login";
            }
        } catch (Exception e3) {
            log.info("else -- >" + e3.getMessage());
            msg = "else -- >" + e3.getMessage();
        }
        map.put("msg", msg);
        map.put("username", username);

        return "login";
    }

    @RequestMapping("/orgnizeType")
    public String orgnizeType(Model model) {
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        model.addAttribute("tbUserInfo", tbUserInfo);
        model.addAttribute("url","/mgt/userCheckOrg");
        return "layout";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        log.info("------没有权限-------");
        return "403";
    }

    @RequestMapping("clearing")
    public String clearing(Model model) {
        /*return "clearing";*/
        return "clearing_one";
    }

    @RequestMapping("liquidation")
    public String liquidation(Model model) {
        Byte type = userService.getOrg().getType();
        model.addAttribute("type", type);
        return "liquidation_one";
    }

    @RequestMapping("/layout")
    public String layout(Model model) {
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        model.addAttribute("tbUserInfo", tbUserInfo);
        return "layout";
    }

    @GetMapping("/layoutReturn")
    public String layoutReturn(Model model,String url){
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        model.addAttribute("tbUserInfo", tbUserInfo);
        model.addAttribute("url",url);
        return "layout";
    }

    @RequestMapping("echarts")
    public String echarts(Model model, Date startdate, Date enddate) {
        TbOrgInfo org = userService.getOrg();
        if (startdate == null || startdate.equals("")) {
            startdate = new Date();
        }
        if (enddate == null || enddate.equals("")) {
            enddate = new Date();
        }
        model.addAttribute("startdate", startdate);
        model.addAttribute("enddate", enddate);
        if (org.getType() == OrgType.SUPPLIER.getType()) {
            List<TbProduct> products = productService.selectProductBySupplyOrgId(org.getId());
            model.addAttribute("org", org);
            model.addAttribute("products", products);
            return "charts/supplierProductStatistic";
        } else if (org.getType() == OrgType.PLATFORM.getType()) {
            List<TbOrgInfo> orgs = userOrgService.selectOrg(OrgType.SUPPLIER);
            model.addAttribute("orgs", orgs);
            return "charts/supplierProductStatistic";
        }

        return "403";
    }


    @RequestMapping("toNetworkRepertory")
    public String toNetworkRepertory() {
        return "charts/networkRepertory";
    }


}