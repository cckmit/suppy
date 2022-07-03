package com.zjjzfy.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductDto implements Serializable {

    private String supplyName;//供货商名称
    private Integer salesVolume;//销量
    private Integer quantity;//商品库存
    private Integer quantityTotal;//商品历史总库存
    private List<String> imgs;//商品展示图片列表
    private List<String> imgs2;//商品详情图片列表

    private Integer productLimit;

    /**
     * 订货数量
     */
    private Integer purchaseQuantity;

    /**
     * 剩余数量
     */
    private Integer surplusQuantity;

    /**
     * 发货数量
     */
    private Integer deliverQuantity;

    /**
     * 采购方供货价格
     */
    private BigDecimal purchasePrice;

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
    private String unitExplan;

    private static final long serialVersionUID = 1L;

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

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
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        this.remark = remark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Integer getSurplusQuantity() {
        return surplusQuantity;
    }

    public void setSurplusQuantity(Integer surplusQuantity) {
        this.surplusQuantity = surplusQuantity;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Integer getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(Integer quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getDeliverQuantity() {
        return deliverQuantity;
    }

    public void setDeliverQuantity(Integer deliverQuantity) {
        this.deliverQuantity = deliverQuantity;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public List<String> getImgs2() {
        return imgs2;
    }

    public void setImgs2(List<String> imgs2) {
        this.imgs2 = imgs2;
    }

    public String getUnitExplan() {
        return unitExplan;
    }

    public void setUnitExplan(String unitExplan) {
        this.unitExplan = unitExplan;
    }
}