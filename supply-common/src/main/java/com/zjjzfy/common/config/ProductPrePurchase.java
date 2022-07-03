package com.zjjzfy.common.config;

/**
 * 产品预购状态类
 *
 * @author jacksheonly
 */
public enum ProductPrePurchase {

    /**
     * 可预购状态  （即，在库存不足时，允许生成订单。）
     */
    ALLOWABLE(0),
    /**
     * 不可预购状态（即，在库存不足时，不允许生成订单。）
     */
    FORBIDDEN(1);

    /**
     * 货品状态
     */
    private Integer value;

    ProductPrePurchase(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }}
