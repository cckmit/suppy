package com.zjjzfy.mgt.test;

import com.zjjzfy.mgt.SupplyManagerWebApplication;
import com.zjjzfy.shiro.config.ShiroProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author      jackshenonly
 * @description 测试参数读取
 * @date        2019-03-15 00:19
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplyManagerWebApplication.class)
public class ShiroPropertiesTest {

    /*@Autowired
    private ShiroProperties shiroProperties;

    @Test
    public void shiroTest(){
        System.out.println(shiroProperties.toString());
        shiroProperties.getFilterChain().forEach(k-> System.out.println(k));
        shiroProperties.getFilterChainAuth().forEach(k-> System.out.println(k));
    }*/
}
