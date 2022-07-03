package com.zjjzfy.order.service.impl;

import com.zjjzfy.SupplyMobileWebApplication;
import com.zjjzfy.common.config.DeliverSettleStatus;
import com.zjjzfy.common.config.DeliverStatus;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.pojo.dto.DeliverFormDto;
import com.zjjzfy.pojo.dto.DeliverFormExampleDto;
import com.zjjzfy.product.service.impl.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: hsmz
 * @date: 2019/3/28 上午11:17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplyMobileWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliverFormServiceTest {


    @Autowired
    private DeliverService deliverService;

    @Test
    public void test() {

        deliverService.selectSellerDelivers(DeliverStatus.CONFIRMED,1, DeliverSettleStatus.CLEARED,new Date(),new Date(),1,10);

    }
}
