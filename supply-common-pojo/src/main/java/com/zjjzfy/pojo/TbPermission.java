package com.zjjzfy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_permission")
public class TbPermission implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.id
     *
     * @mbggenerated
     */
    @Id
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.parent_id
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.lvl
     *
     * @mbggenerated
     */
    private Integer lvl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.porder
     *
     * @mbggenerated
     */
    private Integer porder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.available
     *
     * @mbggenerated
     */
    private Byte available;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.permission
     *
     * @mbggenerated
     */
    private String permission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.resource_type
     *
     * @mbggenerated
     */
    private String resourceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_permission.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_permission
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.id
     *
     * @return the value of tb_permission.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.id
     *
     * @param id the value for tb_permission.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.parent_id
     *
     * @return the value of tb_permission.parent_id
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.parent_id
     *
     * @param parentId the value for tb_permission.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.lvl
     *
     * @return the value of tb_permission.lvl
     *
     * @mbggenerated
     */
    public Integer getLvl() {
        return lvl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.lvl
     *
     * @param lvl the value for tb_permission.lvl
     *
     * @mbggenerated
     */
    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.porder
     *
     * @return the value of tb_permission.porder
     *
     * @mbggenerated
     */
    public Integer getPorder() {
        return porder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.porder
     *
     * @param porder the value for tb_permission.porder
     *
     * @mbggenerated
     */
    public void setPorder(Integer porder) {
        this.porder = porder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.available
     *
     * @return the value of tb_permission.available
     *
     * @mbggenerated
     */
    public Byte getAvailable() {
        return available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.available
     *
     * @param available the value for tb_permission.available
     *
     * @mbggenerated
     */
    public void setAvailable(Byte available) {
        this.available = available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.permission
     *
     * @return the value of tb_permission.permission
     *
     * @mbggenerated
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.permission
     *
     * @param permission the value for tb_permission.permission
     *
     * @mbggenerated
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.name
     *
     * @return the value of tb_permission.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.name
     *
     * @param name the value for tb_permission.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.resource_type
     *
     * @return the value of tb_permission.resource_type
     *
     * @mbggenerated
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.resource_type
     *
     * @param resourceType the value for tb_permission.resource_type
     *
     * @mbggenerated
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.url
     *
     * @return the value of tb_permission.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.url
     *
     * @param url the value for tb_permission.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_permission.remark
     *
     * @return the value of tb_permission.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_permission.remark
     *
     * @param remark the value for tb_permission.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_permission
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", lvl=").append(lvl);
        sb.append(", porder=").append(porder);
        sb.append(", available=").append(available);
        sb.append(", permission=").append(permission);
        sb.append(", name=").append(name);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", url=").append(url);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}