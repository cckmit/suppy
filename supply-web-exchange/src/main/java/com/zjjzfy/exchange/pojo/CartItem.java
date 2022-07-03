package com.zjjzfy.exchange.pojo;

import com.zjjzfy.pojo.TbProduct;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 10:18
 */
public class CartItem{
    private TbProduct product;
    private Integer amount;

    public CartItem(TbProduct product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public TbProduct getProduct() {
        return product;
    }

    public void setProduct(TbProduct product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
