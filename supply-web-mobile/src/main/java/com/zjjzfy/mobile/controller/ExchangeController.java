package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.ExchangeService;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pojo.dto.ProductFormDto;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author: hsmz
 * @date: 2019/4/2 下午2:16
 */
@Controller
@RequestMapping("/purchase")
public class ExchangeController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private CartService cartService;

    /**
     * 跳转商品兑换列表页面
     * @return
     */
    @RequestMapping("/exchangeListInit")
    public String exchangeInit() {

        return "/purchase/exchange/exchangeList";
    }

    /**
     * 获取商品信息库存列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/exchangeList")
    @ResponseBody
    public PageInfo<ProductDto> selectProductBySupplierId(Integer pageNo, Integer pageSize) {
        Integer supplierId = userService.getCurrentTbUserInfo().getUid();
        return productService.selectProductBySupplierId(supplierId, pageNo, pageSize);
    }

    /**
     * 跳转商品兑换列表页面
     * @return
     */
    @RequestMapping("/exchange")
    @ResponseBody
    public SupplyResult exchange(@RequestParam("ids[]") ArrayList<Integer> ids, @RequestParam("nums[]") ArrayList<Integer> nums) {

        ProductFormDto productFormDto = cartService.generateProductForm(ids,nums);
        Integer supplierId = userService.getCurrentTbUserInfo().getUid();
        SupplyResult result = null;
        try {
            result = exchangeService.exchange(productFormDto,"",supplierId);
        }catch (RuntimeException e){
            result.setMsg(e.getMessage());
            result.setStatus(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus());
        }
        return result;
    }

}
