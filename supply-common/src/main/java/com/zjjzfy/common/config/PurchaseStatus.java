package com.zjjzfy.common.config;

/**
 * 订货单状态
 * 状态：0未付款1未完成2已完成3强制结束
 * @author hsmz
 */
public enum PurchaseStatus {

    /**
     * 未付款
     */
    UNPAID(new Byte("0")),
    /**
     * 未完成
     */
    UNFINISHED(new Byte("1")),
    /**
     * 已完成
     */
    FINISHED(new Byte("2")),
    /**
     * 订货方自己关闭(订货单货品不用全部确认收到,如果有相应未确认的发货单未确认,订货方
     * 应先处理发货单后才能关闭订货单)
     */
    CLOSED(new Byte("3")),
    /**
     * 订货单超时未付款
     */
    EXPIRED(new Byte("4"));


    /**
     * 订货单状态
     */
    private Byte status;

    PurchaseStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus(){
        return status;
    }
}
