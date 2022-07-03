package com.zjjzfy.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: hsmz
 * @date: 2019/3/28 上午10:37
 */
public class DeliverFormDto {

    private Integer id;

    private String deliverBillno;

    private Integer purchaserId;

    private Integer supplierId;

    private String purchaseBillno;

    private Integer purchaseFormId;

    private BigDecimal totalPrice;

    private Byte status;

    private Byte settleStatus;

    private Integer deliverPersonId;

    private Date deliverDate;

    private Integer confirmPersonId;

    private Date confirmDate;

    private Date createDate;

    private Date updateDate;

    private Integer purchaseOrgId;

    private Integer supplyOrgId;

    private Integer totalQuantity;

    private Integer liquidationId;

    private Date liquidationTime;

    private String orgName;

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

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getPurchaseBillno() {
        return purchaseBillno;
    }

    public void setPurchaseBillno(String purchaseBillno) {
        this.purchaseBillno = purchaseBillno;
    }

    public Integer getPurchaseFormId() {
        return purchaseFormId;
    }

    public void setPurchaseFormId(Integer purchaseFormId) {
        this.purchaseFormId = purchaseFormId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPurchaseOrgId() {
        return purchaseOrgId;
    }

    public void setPurchaseOrgId(Integer purchaseOrgId) {
        this.purchaseOrgId = purchaseOrgId;
    }

    public Integer getSupplyOrgId() {
        return supplyOrgId;
    }

    public void setSupplyOrgId(Integer supplyOrgId) {
        this.supplyOrgId = supplyOrgId;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getLiquidationId() {
        return liquidationId;
    }

    public void setLiquidationId(Integer liquidationId) {
        this.liquidationId = liquidationId;
    }

    public Date getLiquidationTime() {
        return liquidationTime;
    }

    public void setLiquidationTime(Date liquidationTime) {
        this.liquidationTime = liquidationTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
