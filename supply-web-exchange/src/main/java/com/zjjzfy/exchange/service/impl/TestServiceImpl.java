package com.zjjzfy.exchange.service.impl;

import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * @Author: jackshenonly
 * Date: 2019/4/26
 * Time: 11:19
 */
@Service
public class TestServiceImpl extends BaseService implements TestService {

    @Override
    public String sayHello(String name){
        log.info("向{},问好！",name);
        return "Hello " + name;
    }
}
