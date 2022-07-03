package com.zjjzfy.common.config;

/**
 * Created by Saintyun on 2019/3/18.
 */
public enum CartStatus {
    //0正常1生成订单移除2自己删除
    NORMAL(new Byte("0")),
    ORDER_GENERRATED(new Byte("1")),
    DELETED_BYSELF(new Byte("2"));

    /**
     * 购物车状态
     */
    private Byte status;

    CartStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus(){
        return status;
    }
}
