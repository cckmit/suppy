package com.zjjzfy;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbDeliverFormMapper;
import com.zjjzfy.interfaces.TbPurchaseFormMapper;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.OrgBalanceService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.dto.DeliverFormDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Saintyun on 2019/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplyMobileWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseTest {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private CartService cartService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private OrgBalanceService orgBalanceService;

    @Autowired
    private TbPurchaseFormMapper tbPurchaseFormMapper;
    @Autowired
    private TbDeliverFormMapper tbDeliverFormMapper;

    @Test
    public void deliver(){
        SupplyResult result = deliverService.statisticsPriceGroupBySettleStatus(1, null, null);
        System.out.println(result);
    }

  /*  @Test
    public void addTocart(){
        //添加到购物车
        BigDecimal productPrice = new BigDecimal(100);
        SupplyResult result = cartService.addToCart(2,3,13);

        BigDecimal productPrice1 = new BigDecimal(100);
        SupplyResult result1 = cartService.addToCart(1,2,13);
        System.out.println(result1);
    }*/

    @Test
    public void generCarts(){
//        List<TbCart> tbCarts = cartService.generCarts(1);
    }

    @Test
    public void buildPurchaseOrder(){
//        List<TbCart> tbCarts = cartService.generCarts(1);
//        purchaseService.generPurchaseForm(tbCarts,1);
//        orgBalanceService.usedOrgBalance(2,new BigDecimal(-100));
        List<DeliverFormDto> dtos = tbDeliverFormMapper.selectByDeliverFormExampleDto();
    }

    @Test
    public void payForOrder(){
//        SupplyResult result = purchaseService.payForPurchaseOrder(15,new Double(698),962001);
//        System.out.println(result);
    }

}
