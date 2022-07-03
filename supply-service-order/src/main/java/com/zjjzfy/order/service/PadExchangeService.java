package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;

import java.util.Map;

/**
 * Created by Saintyun on 2019/4/2.
 */
public interface PadExchangeService {
    Map<String,Object> selectGiftList(Integer employeeid,Integer productId,String orderBy,String orderCase);

    //兑换
    Map<String,Object> pay(String cartJson,Integer employeeid,String clientno);

}
