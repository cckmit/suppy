package com.zjjzfy.common.config;

/**
 * Created by IntelliJ IDEA.
 * 兑换单状态
 * @Author: jackshenonly
 * Date: 2019/5/6
 * Time: 16:46
 */
public enum DeliverOrderStatus {
    /**未付款*/
    UNPAID(new Byte("0")),
    /**已付款*/
    PAID(new Byte("1")),
    /**已出库,发放*/
    OUT(new Byte("2")),
    /**其他*/
    OTHER(new Byte("3"));


    private Byte value;

    DeliverOrderStatus(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

}

