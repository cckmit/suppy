package com.zjjzfy.order.service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Saintyun on 2019/3/13.
 * 各类统计 值 方法类
 */
public interface StatisticService {
    //计算 订货单 确定到货的金额
    Double countConfirmMoney(Integer p_id);

    //查询 订货单内 商品-订货数量（总数，已发，在途，剩余）
    List<HashMap<String,Object>> queryPurchaseProductNum(Integer p_id);
}
