package com.zjjzfy.common.config;

/**
 * Created by Saintyun on 2019/3/14.
 */
public enum PurchaseDetailStatus {
    /**
     * 未完成
     */
    unfinished(new Byte("0")),
    /**
     * 已完成
     */
    finished(new Byte("1"));

    /**
     * 订货单审核状态
     */
    private Byte status;

    PurchaseDetailStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus(){
        return status;
    }
}
