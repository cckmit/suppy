package com.zjjzfy.exchange.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * @describe  这里的购物车，是自助兑换的购物车，即兑即用，不做记录
 * 存放在session中,对应的键为：SessionKey.SHOPPING_CART。
 * Date: 2019/4/30
 * Time: 13:40
 */
public interface ShoppingCartService {

    /**
     * 将某个礼品按一定数量加入购物车
     * @param productId
     * @param count
     * @param request
     * @return
     */
    SupplyResult cartAdd(int productId,int count,HttpServletRequest request);

    /**
     * 将某个礼品全部移除购物车
     * @param productId
     * @param request
     * @return
     */
    SupplyResult cartDel(int productId,HttpServletRequest request);

    /**
     * 获取购物车的产品总数量
     * @param request
     * @return
     */
    SupplyResult amountGet(HttpServletRequest request);

    /**
     * 设置表单提交有效的token；存在session.token
     * @param request
     */
    void putSubmitToken(HttpServletRequest request);

    /**
     * 提交订单，提交后订单是未支付的状态 status=0
     * @param request
     * @param productIds
     * @param counts
     * @return
     */
    SupplyResult submit(HttpServletRequest request, String token, List<Integer> productIds, List<Integer> counts);

    /**
     * 客户端 提交订单。
     * @param param
     * @return
     */
    SupplyResult submit(Param param) throws RsaCheckException;

}
