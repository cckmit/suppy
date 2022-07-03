package com.zjjzfy.common.config;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/6
 * Time: 16:54
 */
public enum DeliverOrderType {
    /**积分支付兑换*/
    EXCHANGE(0),
    /**配送*/
    DISTRIBUTION(1),
    /**无消费兑换*/
    PAY_NOTHING(2),
    /**现金支付兑换*/
    CASH(3),
    /**现金积分混合兑换*/
    MIX_CASH_EXCHANGE(4)
    ;


    private Integer value;

    DeliverOrderType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
