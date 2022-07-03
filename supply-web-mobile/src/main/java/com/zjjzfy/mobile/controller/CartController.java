package com.zjjzfy.mobile.controller;

import com.zjjzfy.common.config.CartStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: hsmz
 * @date: 2019/3/21 下午5:00
 */
@Controller
@RequestMapping("/purchase")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @RequestMapping("/myCart")
    public String myCart(Model model) {
        TbUserInfo currentTbUserInfo = userService.getCurrentTbUserInfo();
        Integer purchaserId = currentTbUserInfo.getUid();
        List<HashMap<String, Object>> cartList = cartService.generCarts(purchaserId);
        int count = cartService.obtainCartCount(currentTbUserInfo.getUid());

        model.addAttribute("count", count);
        model.addAttribute("cartList", cartList);
        model.addAttribute("purchaserId", purchaserId);

        return "/purchase/shopCart";
    }

    /**
     * 添加到购物车
     *
     * @param productId 商品id
     * @param count     添加商品数量
     * @return
     */
    @RequestMapping("/addToCart")
    @ResponseBody
    public SupplyResult addToCart(Integer productId, Integer count,String model) {
        TbUserInfo userInfo = userService.getCurrentTbUserInfo();
        return cartService.addToCart(productId, count, userInfo.getUid(),model);
    }


    /**
     * 手动删除 购物车
     *
     * @param purchaseId
     * @param productIds
     * @return
     */
    @RequestMapping("/removeCarts")
    @ResponseBody
    public SupplyResult removeCarts(Integer purchaseId, String productIds) {
        List<Integer> list = new ArrayList<>();
        String[] strs = productIds.split(",");
        for (int i = 0; i < strs.length; i++) {
            list.add(Integer.valueOf(strs[i]));
        }
        return cartService.clearCart(purchaseId, list, CartStatus.DELETED_BYSELF.getStatus());
    }

    @RequestMapping("/modifyCarts")
    @ResponseBody
    public SupplyResult modifyCarts(Integer productid,Integer count){
        TbUserInfo userInfo = userService.getCurrentTbUserInfo();
        return cartService.modifyCart(productid, count, userInfo.getUid());
    }


//    @RequestMapping("/pay")
//    @ResponseBody
//    public SupplyResult pay(){
//
//    }

}
