package com.zjjzfy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_advertisement")
public class TbAdvertisement {

    @Id
    private Integer id;

    private String name;

    private String href;

    private String img;

    private Integer rank;

    private String status;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    private String detailImg;

    private String advClassification;

    private Integer advPosition;

    private Integer hrefClass;

    private static final long serialVersionUID = 1L;

    public Integer getHrefClass() {
        return hrefClass;
    }

    public void setHrefClass(Integer hrefClass) {
        this.hrefClass = hrefClass;
    }

    public Integer getAdvPosition() {
        return advPosition;
    }

    public void setAdvPosition(Integer advPosition) {
        this.advPosition = advPosition;
    }

    public String getAdvClassification() {
        return advClassification;
    }

    public void setAdvClassification(String advClassification) {
        this.advClassification = advClassification;
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
        this.name = name == null ? null : name.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public TbAdvertisement setRank(Integer rank) {
        this.rank = rank;
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }
}