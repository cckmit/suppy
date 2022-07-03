//package com.zjjzfy;

import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.repository.service.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SupplyMobileWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class RepositoryServiceTests {
//
//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Test
//    public void stockInput() {
//        TbCart cart = new TbCart();
//        cart.setProductId(1);
//        cart.setSupplierId(1);
//        cart.setProductQuality(8);
//
//        TbCart cart1 = new TbCart();
//        cart1.setProductId(2);
//        cart1.setSupplierId(2);
//        cart1.setProductQuality(6);
//
//        List<TbCart> cartList = new ArrayList<>();
//        cartList.add(cart);
//        cartList.add(cart1);
//        repositoryService.stockInput(cartList);
//
//    }
//
//    @Test
//    public void checkFakeStock() {
//        TbCart cart = new TbCart();
//        cart.setProductId(1);
//        cart.setSupplierId(1);
//
//
//        TbCart cart1 = new TbCart();
//        cart1.setProductId(2);
//        cart1.setSupplierId(2);
//
//        List<TbCart> cartList = new ArrayList<>();
//        cartList.add(cart);
//        cartList.add(cart1);
//        repositoryService.checkFakeStock(cartList);
//
//    }


    /**
     * 把url换成你要测试的url
     */
//    private final String url = "http://localhost:8081/sec_kill_success/doKill";
//
//    RestTemplate rest = new RestTemplate();
//
//    private static final int USER_NUM = 1200;
//
//    private static CountDownLatch cdl = new CountDownLatch(USER_NUM);
//
//    @Test
//    public void TestInvoke() throws InterruptedException {
//        for (int i = 0; i < USER_NUM; i++) {
//            new Thread(new UserRequest()).start();
//            cdl.countDown();
//        }
//        Thread.currentThread().sleep(3000);
//    }
//
//    public class UserRequest implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                cdl.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            String result = rest.getForEntity(url, String.class).getBody();
//            System.out.println(result);
//        }
//    }


//}
