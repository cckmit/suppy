package com.zjjzfy.exchange.pojo;


import io.swagger.models.auth.In;

/**
 *
 * @author      jackshenonly
 * @description 类说明 订单支付通知主体对象
 *
 * @date        2020-01-07 17:47
 */
public class OrderPayBody {

    private String billno;
    /**
     * 支付备注，支付成功的返回码信息,举例=>"Android支付返回:凭证号|商户号|终端号"。
     * 如果是"非消费兑换通知"，该值传：空串即可。
     */
    private String remark;

    /**
     * 终端设置时，获得认证用户的uid,用于自动发放。
     */
    private Integer uid;

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
