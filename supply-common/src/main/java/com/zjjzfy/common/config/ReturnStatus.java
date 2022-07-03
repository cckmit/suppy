package com.zjjzfy.common.config;

/**
 * 响应业务状态值
 * @author: hsmz
 * @date: 2019/3/15 上午11:01
 */
public enum ReturnStatus {

    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAILURE(0),

    /**
     * 待付款
     */
    NOT_PAY(210),
    /**
     * 余额不足
     */
    NOT_ENOUGH_MONEY(250),
    /**
     * 无审核权限
     */
    NOT_AUTHORIZATION(251),
    /**
     * 有审核权限
     */
    AUTHORIZED(252),
    /**
     * 产品下架
     */
    PRODUCT_OFF(260),
    /**
     * 库存不足
     */
    NOT_ENOUGH_QUANTITY(300),
    /**
     * 存在负库存
     */
    FAKE_QUANTITY(301),



    /**
     * 订货单已完成
     */
    PURCHASE_FORM_FINISHED(400);


    private Integer status;

    ReturnStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
