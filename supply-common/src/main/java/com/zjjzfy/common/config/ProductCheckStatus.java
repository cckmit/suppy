package com.zjjzfy.common.config;

/**
 * 货品审核状态
 *
 * @author hsmz
 */
public enum ProductCheckStatus {
    //商品审核状态
    /**
     * 未审核
     */
    UNCHECK(new Byte("1")),
    /**
     * 审核通过
     */
    CHECK_PASS(new Byte("2")),
    /**
     * 审核未通过
     */
    CHECK_NOT_PASS(new Byte("3")),

    REFUSE_PASS(new Byte("4"));

    /**
     * 货品状态
     */
    private Byte status;

    ProductCheckStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }
}
