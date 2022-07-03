package com.zjjzfy.common.config;

/**
 * 订货单审核状态
 * @author: hsmz
 * @date: 2019/3/13 上午10:29
 */
public enum PurchaseCheckStatus {

    /**
     * 未审核
     */
    uncheck(new Byte("0")),
    /**
     * 已审核
     */
    checked(new Byte("1"));

    /**
     * 订货单审核状态
     */
    private Byte status;

    PurchaseCheckStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus(){
        return status;
    }

}
