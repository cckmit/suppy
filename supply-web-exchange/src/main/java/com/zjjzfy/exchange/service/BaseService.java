package com.zjjzfy.exchange.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/28
 * Time: 17:01
 */
public class BaseService {
    protected Logger log = null;
    public BaseService(){
        this.log = LoggerFactory.getLogger(getClass().getName());
    }
}
