package com.zjjzfy.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbCart implements Serializable {
    @Id
    private Integer id;

    private Integer purchaserId;

    private Integer productId;

    private Integer productQuality;

    private Integer supplyOrgId;

    private Date createDate;

    private Date updateDate;

    private Byte status;

    private BigDecimal productPrice;

    private Integer supplierId;

    private String productColor;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(Integer productQuality) {
        this.productQuality = productQuality;
    }

    public Integer getSupplyOrgId() {
        return supplyOrgId;
    }

    public void setSupplyOrgId(Integer supplyOrgId) {
        this.supplyOrgId = supplyOrgId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor == null ? null : productColor.trim();
    }


    public TbCart(Integer id, Integer purchaserId, Integer productId, Integer productQuality, Integer supplyOrgId, Date createDate, Date updateDate, Byte status, BigDecimal productPrice, Integer supplierId, String productColor) {
        this.id = id;
        this.purchaserId = purchaserId;
        this.productId = productId;
        this.productQuality = productQuality;
        this.supplyOrgId = supplyOrgId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.productPrice = productPrice;
        this.supplierId = supplierId;
        this.productColor = productColor;
    }

    public TbCart(Integer productId, Integer productQuality, Integer supplyOrgId) {
        this.productId = productId;
        this.productQuality = productQuality;
        this.supplyOrgId = supplyOrgId;
    }

    public TbCart() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", purchaserId=").append(purchaserId);
        sb.append(", productId=").append(productId);
        sb.append(", productQuality=").append(productQuality);
        sb.append(", supplyOrgId=").append(supplyOrgId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", productColor=").append(productColor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}