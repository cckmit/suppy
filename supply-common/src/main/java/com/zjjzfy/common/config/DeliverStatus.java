package com.zjjzfy.common.config;

/**
 * 发货单状态
 * @author: hsmz
 * @date: 2019/3/13 上午10:36
 */
public enum DeliverStatus {

    /**
     * 订货方未确认
     */
    UNCONFIRMED(new Byte("0")),
    /**
     * 订货方已确认
     */
    CONFIRMED(new Byte("1")),
    /**
     * 订货方已拒绝
     */
    REJECTED(new Byte("2"));


    /**
     * 发货单状态
     */
    private Byte status;

    DeliverStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus(){
        return status;
    }
}
