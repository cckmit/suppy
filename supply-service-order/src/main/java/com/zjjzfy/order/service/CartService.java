package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbCartExample;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbPurchaseForm;
import com.zjjzfy.pojo.dto.ProductFormDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Saintyun on 2019/3/13.
 */
public interface CartService {
    SupplyResult addToCart(Integer productId,Integer count, Integer purchaseId,String model);

    List<HashMap<String,Object>> generCarts(Integer purchaseId);

    SupplyResult clearCart(Integer purchaser_id,List<Integer> productids,Byte flag);

    List<TbCart> selectCartByExample(TbCartExample example);

    Integer obtainCartCount(Integer purchaserId);

    /**
     * 通过商品id、数量返回购物车list
     * @param ids
     * @param nums
     * @return
     */
    List<TbCart> generateCart(List<Integer> ids, List<Integer> nums);

    /**
     * 通过商品id、数量返回商品表单对象
     * @param ids
     * @param numbers
     * @return
     */
    ProductFormDto generateProductForm(List<Integer> ids, List<Integer> numbers);

    SupplyResult modifyCart(Integer productid,Integer count,Integer uid);

    /**
     * 下单时候判断商品 是否满足 起购数量
     * @param ids
     * @return
     */
    SupplyResult productnumOverLimit(String ids);
}
