package com.zjjzfy.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class TbDepartment implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.id
     *
     * @mbggenerated
     */
    @Id
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.dept_no
     *
     * @mbggenerated
     */
    private Integer deptNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.parent_id
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.remark1
     *
     * @mbggenerated
     */
    private Integer remark1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.org_id
     *
     * @mbggenerated
     */
    private Integer orgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_department
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.id
     *
     * @return the value of tb_department.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.id
     *
     * @param id the value for tb_department.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.name
     *
     * @return the value of tb_department.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.name
     *
     * @param name the value for tb_department.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.dept_no
     *
     * @return the value of tb_department.dept_no
     *
     * @mbggenerated
     */
    public Integer getDeptNo() {
        return deptNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.dept_no
     *
     * @param deptNo the value for tb_department.dept_no
     *
     * @mbggenerated
     */
    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.parent_id
     *
     * @return the value of tb_department.parent_id
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.parent_id
     *
     * @param parentId the value for tb_department.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.create_time
     *
     * @return the value of tb_department.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.create_time
     *
     * @param createTime the value for tb_department.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.update_time
     *
     * @return the value of tb_department.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.update_time
     *
     * @param updateTime the value for tb_department.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.delete_time
     *
     * @return the value of tb_department.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.delete_time
     *
     * @param deleteTime the value for tb_department.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.status
     *
     * @return the value of tb_department.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.status
     *
     * @param status the value for tb_department.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.remark
     *
     * @return the value of tb_department.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.remark
     *
     * @param remark the value for tb_department.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.remark1
     *
     * @return the value of tb_department.remark1
     *
     * @mbggenerated
     */
    public Integer getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.remark1
     *
     * @param remark1 the value for tb_department.remark1
     *
     * @mbggenerated
     */
    public void setRemark1(Integer remark1) {
        this.remark1 = remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.org_id
     *
     * @return the value of tb_department.org_id
     *
     * @mbggenerated
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.org_id
     *
     * @param orgId the value for tb_department.org_id
     *
     * @mbggenerated
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_department
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
        sb.append(", name=").append(name);
        sb.append(", deptNo=").append(deptNo);
        sb.append(", parentId=").append(parentId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleteTime=").append(deleteTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", remark1=").append(remark1);
        sb.append(", orgId=").append(orgId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}