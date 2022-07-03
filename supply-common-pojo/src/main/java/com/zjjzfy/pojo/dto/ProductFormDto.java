package com.zjjzfy.pojo.dto;

import com.zjjzfy.pojo.TbCart;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author: hsmz
 * @date: 2019/4/9 上午10:12
 */
public class ProductFormDto {

    private List<TbCart> cartList;

    private int totalProductNumber;

    private BigDecimal totalProductPrice;

    private BigDecimal totalProductPurchasePrice;

    public List<TbCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<TbCart> cartList) {
        this.cartList = cartList;
    }

    public int getTotalProductNumber() {
        return totalProductNumber;
    }

    public void setTotalProductNumber(int totalProductNumber) {
        this.totalProductNumber = totalProductNumber;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public BigDecimal getTotalProductPurchasePrice() {
        return totalProductPurchasePrice;
    }

    public void setTotalProductPurchasePrice(BigDecimal totalProductPurchasePrice) {
        this.totalProductPurchasePrice = totalProductPurchasePrice;
    }
}
