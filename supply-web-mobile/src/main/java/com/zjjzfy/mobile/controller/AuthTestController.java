package com.zjjzfy.mobile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class AuthTestController {

//    @RequestMapping({"/","/home","/index"})
//    public ModelAndView home(ModelAndView modelAndView){
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping("/login")
//    public String login(@RequestParam(name="username",required=false) String username,
//                        @RequestParam(name="password",required=false)String password,
//                        ModelMap map) throws Exception{
//
//        log.info("HomeController.login()");
//        if(username==null || password==null){
//            map.put("msg","");
//            return "login";
//        }
//        String msg = "";
//        try {
//            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//            SecurityUtils.getSubject().login(token);
//        }catch (UnknownAccountException e1){
//            log.info("UnknownAccountException -- > 账号不存在：");
//            msg = "UnknownAccountException -- > 账号不存在：";
//        }catch (IncorrectCredentialsException e2){
//            log.info("IncorrectCredentialsException -- > 密码不正确：");
//            msg = "IncorrectCredentialsException -- > 密码不正确：";
//        }catch (Exception e3){
//            log.info("else -- >" + e3.getMessage());
//            msg = "else -- >"+ e3.getMessage();
//        }
//        map.put("msg", msg);
//
//        return "login";
//    }
//
//    @RequestMapping("/403")
//    public String unauthorizedRole(){
//        log.info("------没有权限-------");
//        return "403";
//    }
//
//    /**
//     * 用户查询.
//     * @return
//     */
//    @RequestMapping("/userList")
//    @RequiresPermissions("userInfo:view")
//    public String userInfo(){
//        return "userInfo";
//    }
//
//    /**
//     * 用户添加;
//     * @return
//     */
//    @RequestMapping("/userAdd")
//    public String userInfoAdd( ModelMap map){
//
//        Subject current = SecurityUtils.getSubject();
//        System.out.println(current.hasRole("admin"));
//        System.out.println(current.hasRole("extr"));
//        System.out.println(current.isPermitted("userinfo:view"));
//        System.out.println(current.isPermitted("userinfo:del"));
//
//        return "userInfoAdd";
//
//    }
//
//    /**
//     * 用户删除;
//     * @return
//     */
//    @RequestMapping("/userDel")
//    @RequiresPermissions("userInfo:del")
//    public String userDel(){
//        return "userInfoDel";
//    }

}
