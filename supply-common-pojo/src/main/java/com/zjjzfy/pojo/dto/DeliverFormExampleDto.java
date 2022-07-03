package com.zjjzfy.pojo.dto;

/**
 * 自定义发货单条件
 * @author: hsmz
 * @date: 2019/3/26 上午11:03
 */
public class DeliverFormExampleDto {

    /**
     * 订货方id
     */
    private Integer purchaserId;
    /**
     * 供货方id
     */
    private Integer supplierId;
    /**
     * 供货方机构号id
     */
    private Integer supplyOrgId;
    /**
     * 分页编号
     */
    private Integer pageNo;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 订货单状态
     */
    private Byte status;

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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSupplyOrgId() {
        return supplyOrgId;
    }

    public void setSupplyOrgId(Integer supplyOrgId) {
        this.supplyOrgId = supplyOrgId;
    }
}
