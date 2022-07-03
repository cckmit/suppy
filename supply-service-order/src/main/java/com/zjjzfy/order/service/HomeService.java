package com.zjjzfy.order.service;

import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2019/11/20 上午9:06
 */
public interface HomeService {

    /**
     * 统计待发货订单数
     * @return
     */
    public Map countWaitOrder();

    /**
     * 统计昨日完成订单数量 订单总价
     * @return
     */
    public Map countYesterOrdered();

    /**
     * 统计昨日订单金额 和订单数
     * @return
     */
    public Map countYesterOrder();


    /**
     * 统计代开发票数量，金额
     * @return
     */
    public Map countWaitInvoice();


    public Object[][] countOrderDate();

    Map homeMapAll();
}
