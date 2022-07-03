package com.zjjzfy.exchange.controller;


import com.zjjzfy.exchange.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * @Author: jackshenonly
 * Date: 2019/4/26
 * Time: 11:19
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    @ConditionalOnMissingBean
    @Cacheable
    public String sayHello(String name){
        return testService.sayHello(name);
    }
}
