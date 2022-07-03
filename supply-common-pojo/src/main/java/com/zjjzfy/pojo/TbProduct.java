package com.zjjzfy.pojo;

import javafx.beans.DefaultProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TbProduct implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String barcode;

    private String brand;

    private String unit;

    private String image;

    private BigDecimal price;

    private BigDecimal referencePrice;

    private BigDecimal settlePrice;

    private String remark;

    private String model;

    private Byte productStatus;

    private Integer categoryId;

    private Byte productLabel;

    private Integer supplyOrgId;

    private Date createDate;

    private Date updateDate;

    private BigDecimal purchasePrice;

    private Byte checkStatus;

    private String unitexplan;

    private String isdiscount;

    private String newproduct;

    private Integer sales;

    private Integer sortId;

    private BigDecimal exchangePrice;

    private String clickrate;

    private Integer flashSaleId;

    private Integer productLimit;

    private BigDecimal exchangeCash;

    private Integer exchangeType;

    private String exchangeRemark;

    private Integer purchaseOrnot;

    private Integer productPrefecture;

    private static final long serialVersionUID = 1L;

    public List<TbProductImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<TbProductImg> imgs) {
        this.imgs = imgs;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    @Transient
    private List<TbProductImg> imgs;


    @Transient
    private String imgAddr;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(BigDecimal referencePrice) {
        this.referencePrice = referencePrice;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Byte getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(Byte productLabel) {
        this.productLabel = productLabel;
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getUnitexplan() {
        return unitexplan;
    }

    public void setUnitexplan(String unitexplan) {
        this.unitexplan = unitexplan == null ? null : unitexplan.trim();
    }

    public String getIsdiscount() {
        return isdiscount;
    }

    public void setIsdiscount(String isdiscount) {
        this.isdiscount = isdiscount == null ? null : isdiscount.trim();
    }

    public String getNewproduct() {
        return newproduct;
    }

    public void setNewproduct(String newproduct) {
        this.newproduct = newproduct == null ? null : newproduct.trim();
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public BigDecimal getExchangePrice() {
        return exchangePrice;
    }

    public void setExchangePrice(BigDecimal exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    public String getClickrate() {
        return clickrate;
    }

    public void setClickrate(String clickrate) {
        this.clickrate = clickrate == null ? null : clickrate.trim();
    }

    public Integer getFlashSaleId() {
        return flashSaleId;
    }

    public void setFlashSaleId(Integer flashSaleId) {
        this.flashSaleId = flashSaleId;
    }

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public BigDecimal getExchangeCash() {
        return exchangeCash;
    }

    public void setExchangeCash(BigDecimal exchangeCash) {
        this.exchangeCash = exchangeCash;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getExchangeRemark() {
        return exchangeRemark;
    }

    public void setExchangeRemark(String exchangeRemark) {
        this.exchangeRemark = exchangeRemark == null ? null : exchangeRemark.trim();
    }

    public Integer getPurchaseOrnot() {
        return purchaseOrnot;
    }

    public void setPurchaseOrnot(Integer purchaseOrnot) {
        this.purchaseOrnot = purchaseOrnot;
    }

    public Integer getProductPrefecture() {
        return productPrefecture;
    }

    public void setProductPrefecture(Integer productPrefecture) {
        this.productPrefecture = productPrefecture;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", barcode=").append(barcode);
        sb.append(", brand=").append(brand);
        sb.append(", unit=").append(unit);
        sb.append(", image=").append(image);
        sb.append(", price=").append(price);
        sb.append(", referencePrice=").append(referencePrice);
        sb.append(", settlePrice=").append(settlePrice);
        sb.append(", remark=").append(remark);
        sb.append(", model=").append(model);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", productLabel=").append(productLabel);
        sb.append(", supplyOrgId=").append(supplyOrgId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", unitexplan=").append(unitexplan);
        sb.append(", isdiscount=").append(isdiscount);
        sb.append(", newproduct=").append(newproduct);
        sb.append(", sales=").append(sales);
        sb.append(", sortId=").append(sortId);
        sb.append(", exchangePrice=").append(exchangePrice);
        sb.append(", clickrate=").append(clickrate);
        sb.append(", flashSaleId=").append(flashSaleId);
        sb.append(", productLimit=").append(productLimit);
        sb.append(", exchangeCash=").append(exchangeCash);
        sb.append(", exchangeType=").append(exchangeType);
        sb.append(", exchangeRemark=").append(exchangeRemark);
        sb.append(", purchaseOrnot=").append(purchaseOrnot);
        sb.append(", productPrefecture=").append(productPrefecture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}