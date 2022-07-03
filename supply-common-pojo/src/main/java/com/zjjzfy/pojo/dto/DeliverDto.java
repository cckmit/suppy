package com.zjjzfy.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DeliverDto {

    private String purchaseName;//订货方名称
    private String supplierName;//供货商名称
    private Integer confirmPersonName;//确认人名称
    private Integer deliverPersonName;//发货人名称

    private Integer id;
    private String deliverBillno;//供货单号

    private BigDecimal totalPrice;//总金额

    private Byte status;//是否确认

    private Byte settleStatus;//清算进度

    private Integer deliverPersonId;//发货人ID

    private Date deliverDate;//发货时间

    private Integer confirmPersonId;//确认人ID

    private Date confirmDate;//确认时间

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getConfirmPersonName() {
        return confirmPersonName;
    }

    public void setConfirmPersonName(Integer confirmPersonName) {
        this.confirmPersonName = confirmPersonName;
    }

    public Integer getDeliverPersonName() {
        return deliverPersonName;
    }

    public void setDeliverPersonName(Integer deliverPersonName) {
        this.deliverPersonName = deliverPersonName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliverBillno() {
        return deliverBillno;
    }

    public void setDeliverBillno(String deliverBillno) {
        this.deliverBillno = deliverBillno;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Byte settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Integer getDeliverPersonId() {
        return deliverPersonId;
    }

    public void setDeliverPersonId(Integer deliverPersonId) {
        this.deliverPersonId = deliverPersonId;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Integer getConfirmPersonId() {
        return confirmPersonId;
    }

    public void setConfirmPersonId(Integer confirmPersonId) {
        this.confirmPersonId = confirmPersonId;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
}
