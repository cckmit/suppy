package com.zjjzfy.mgt.test;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.mgt.SupplyManagerWebApplication;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.shiro.service.PasswordService;
import com.zjjzfy.shiro.service.ShiroService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jackshenonly
 * @description 测试密码
 * @date 2019-03-15 00:19
 */
@Slf4j
@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplyManagerWebApplication.class)
public class PasswordTest {

    /*@Autowired
    private PasswordService passwordService;
    @Autowired
    private DeliverService deliverService;

    @Test
    public void test2() {
        SupplyResult supplyResult = deliverService.statisticsPriceGroupBySettleStatus(1, null, null);
        System.out.println(supplyResult);
    }

    @Test
    public void test() {
        String salt = "8d78869f470951332959580424d4bf4f";
        String pwd = "000000";
        String username = "admin";
        String target = "6ebe83e39ccee1bbf6151846ac7e2349";
        String md = passwordService.doGetPassword(pwd, username + salt);
        log.info("预期值：" + target);
        log.info("实际值：" + md);

        Assert.assertTrue(target.equals(md));
    }

    @Autowired
    private ShiroService shiroService;

    @Test
    public void test3() {
        SupplyResult result = shiroService.login("yyy", "000000");
        System.out.println(result);
    }*/
}