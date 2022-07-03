package com.zjjzfy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_address")
public class TbAddress implements Serializable {
    @Id
    private Integer id;
    private String name;
    private Integer userId;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String areaCode;
    private String postalCode;
    private String tel;
    private Byte isDefault;
    private Date addTime;
    private Date updateTime;
    private Byte deleted;
    private Byte isLastUsed;
    private static final long serialVersionUID = 1L;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Byte getIsLastUsed() {
        return isLastUsed;
    }

    public void setIsLastUsed(Byte isLastUsed) {
        this.isLastUsed = isLastUsed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TbAddress() {

    }
    public TbAddress(Integer id, String name, Integer userId, String province, String city, String county, String addressDetail, String tel, Byte isDefault) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.addressDetail = addressDetail;
        this.tel = tel;
        this.isDefault = isDefault;
        this.addTime = new Date();
        this.deleted = 0;
        this.isLastUsed=0;
        //1,"name",52,"xx省","xx市","xx区","xx街xx号","1591111111",0
    }
    public TbAddress( String name, Integer userId, String province, String city, String county, String addressDetail,String tel, Byte isDefault) {
        this.name = name;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.addressDetail = addressDetail;
        this.tel = tel;
        this.isDefault = isDefault;
        this.addTime = new Date();
        this.deleted = 0;
        this.isLastUsed=0;
        //"name",52,"xx省","xx市","xx区","xx街xx号","1591111111",0
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", userId=").append(userId);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", county=").append(county);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", tel=").append(tel);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", isLastUsed=").append(isLastUsed);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}