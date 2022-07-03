package com.zjjzfy.exchange.pojo;


import com.zjjzfy.pojo.TbProduct;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/30
 * Time: 15:04
 */
public class Cart4Exch {
    /**
     * 篮子
     */
    private Map<Integer,CartItem> basket = new HashMap<>();

    /**
     * 增加
     * @param product
     * @param count
     */
    public void add(TbProduct product, Integer count){
        Integer pid = product.getId();
        CartItem item;
        if(basket.containsKey(pid)){
            item = basket.get(pid);
            if(item.getAmount() + count <= 0){
                basket.remove(pid);
                return;
            }
            item.setAmount(item.getAmount() + count);
            basket.put(pid, item);
        }else {
            if(count <= 0){
                return;
            }
            item = new CartItem(product,count);
            basket.put(pid, item);
        }
    }

    /**
     * 移除
     * @param productId
     */
    public void del(Integer productId){
        if(basket.containsKey(productId)){
            basket.remove(productId);
        }
    }

    public Map<Integer, CartItem> getBasket() {
        return basket;
    }

    public void setBasket(Map<Integer, CartItem> basket) {
        this.basket = basket;
    }
}

