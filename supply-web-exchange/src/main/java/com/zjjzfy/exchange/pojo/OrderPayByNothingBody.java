package com.zjjzfy.exchange.pojo;


/**
 *
 * @author      jackshenonly
 * @description 类说明 订单支付通知主体对象
 *
 * @date        2020-01-07 17:47
 */
public class OrderPayByNothingBody
        extends OrderPayBody{

    /**
     * 网点机构号（银行里自己的编号，对应字段 tb_networker.branchno ）
     */
    private String branchno;

    /**
     * 员工编号 （银行自己的员工编号，对应字段 tb_networker.branch_id）
     */
    private String employeeid;

    /**
     * 签名存储位置。
     */
    private String signature;


    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
