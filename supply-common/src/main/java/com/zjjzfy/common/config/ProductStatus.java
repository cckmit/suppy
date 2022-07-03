package com.zjjzfy.common.config;

/**
 * 货品状态
 *
 * @author hsmz
 */
public enum ProductStatus {

    /**
     * 可订货，会出现在礼品列表
     */
    ON(new Byte("1")),
    /**
     * 下架，不会出现在礼品列表
     */
    OFF(new Byte("2"));

    /**
     * 货品状态
     */
    private Byte status;

    ProductStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }
}
