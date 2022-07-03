package com.zjjzfy.exchange.controller;

import com.zjjzfy.exchange.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/30
 * Time: 10:50
 */
@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/cart")
    public String cart(HttpServletRequest request){
        shoppingCartService.putSubmitToken(request);
        return "shopping-cart";
    }
}
