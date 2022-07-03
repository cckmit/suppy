package com.zjjzfy.common.config;

/**
 * 发货单清算状态
 *
 * @author: hsmz
 * @date: 2019/3/13 下午5:06
 */
public enum DeliverSettleStatus {

    /*发货单未清算*/
    UNCLEARED(new Byte("0")),

    /*行社已发起清算*/
    LAUNCHED(new Byte("1")),

    /*发货单已清算*/
    CLEARED(new Byte("2"));

    /**
     * 发货单清算状态
     */
    private Byte status;

    DeliverSettleStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }


}
