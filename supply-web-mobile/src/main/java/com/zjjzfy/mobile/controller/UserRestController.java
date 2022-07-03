package com.zjjzfy.mobile.controller;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.shiro.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hsmz
 * @date: 2019/4/1 下午5:20
 */
@RestController
@RequestMapping("/purchase")
@Slf4j
public class UserRestController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value="/purchaseLogin",method= RequestMethod.POST)
    public SupplyResult purchaseLogin(String username, String password){
        log.info("登录接口访问成功");
        return shiroService.login(username,password);
    }
}
