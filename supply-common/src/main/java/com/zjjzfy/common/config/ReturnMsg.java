package com.zjjzfy.common.config;

/**
 * Created by Saintyun on 2019/3/14.
 */
public enum ReturnMsg {
    SUCCESS("操作成功"),
    FAILURE("操作失败"),
    NOT_ENOUGH_MONEY("您的余额不足！"),
    NOT_AUTHORIZED("您没有该权限！"),
    AUTHORIZED("有相关权限"),
    /**
     * 库存不足
     */
    NOT_ENOUGH_QUANTITY("库存不足"),
    /**
     * 产品下架
     */
    PRODUCT_OFF("您所选产品已下架！"),
    /**
     * 库存为负
     */
    FAKE_QUANTITY("库存为负"),
    /**
     * 订货单已完成
     */
    PURCHASE_FORM_FINISHED("订货单已完成,请拒绝改发货单");


    private String msg;

    ReturnMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
