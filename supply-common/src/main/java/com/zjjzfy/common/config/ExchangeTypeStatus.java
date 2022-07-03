package com.zjjzfy.common.config;

/**
 * 兑换方式
 *
 */
public enum ExchangeTypeStatus {

    /*积分兑换*/
    JIFEN(0),

    /*现金兑换*/
    CASH(1),

    /*混合兑换*/
    MIX(2);


    private Integer status;

    ExchangeTypeStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }


}
