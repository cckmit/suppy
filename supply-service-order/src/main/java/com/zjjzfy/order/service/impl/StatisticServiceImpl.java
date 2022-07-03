package com.zjjzfy.order.service.impl;

import com.zjjzfy.interfaces.PublicTableMapper;
import com.zjjzfy.order.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Saintyun on 2019/3/13.
 */
@Service
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private PublicTableMapper publicTableMapper;

    /*
    *  当前订货单 已确认收货金额
    * */
    @Override
    public Double countConfirmMoney(Integer p_id) {
        return publicTableMapper.countConfirmMoney(p_id);
    }

    /*
    * 查询 订货单内 商品-订货数量（总数，已发，在途，剩余）
    * */
    @Override
    public List<HashMap<String, Object>> queryPurchaseProductNum(Integer p_id) {
        return publicTableMapper.queryPurchaseConfirmProducts(p_id);
    }
}
