package com.zjjzfy.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "tb_category")
public class TbCategory implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.id
     *
     * @mbggenerated
     */
    @Id
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.category_name
     *
     * @mbggenerated
     */
    private String categoryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.parent_id
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.category_level
     *
     * @mbggenerated
     */
    private Integer categoryLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.is_end
     *
     * @mbggenerated
     */
    private Byte isEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_category.img
     *
     * @mbggenerated
     */
    private String img;
    private Integer ctgrRank;
    private Integer ctgrClass;

    public Integer getCtgrRank() {
        return ctgrRank;
    }

    public void setCtgrRank(Integer ctgrRank) {
        this.ctgrRank = ctgrRank;
    }

    public Integer getCtgrClass() {
        return ctgrClass;
    }

    public void setCtgrClass(Integer ctgrClass) {
        this.ctgrClass = ctgrClass;
    }

    @Transient
    private List<TbCategory> childCategory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_category
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.id
     *
     * @return the value of tb_category.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.id
     *
     * @param id the value for tb_category.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.category_name
     *
     * @return the value of tb_category.category_name
     *
     * @mbggenerated
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.category_name
     *
     * @param categoryName the value for tb_category.category_name
     *
     * @mbggenerated
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.parent_id
     *
     * @return the value of tb_category.parent_id
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.parent_id
     *
     * @param parentId the value for tb_category.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.category_level
     *
     * @return the value of tb_category.category_level
     *
     * @mbggenerated
     */
    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.category_level
     *
     * @param categoryLevel the value for tb_category.category_level
     *
     * @mbggenerated
     */
    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.is_end
     *
     * @return the value of tb_category.is_end
     *
     * @mbggenerated
     */
    public Byte getIsEnd() {
        return isEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.is_end
     *
     * @param isEnd the value for tb_category.is_end
     *
     * @mbggenerated
     */
    public void setIsEnd(Byte isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.create_date
     *
     * @return the value of tb_category.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.create_date
     *
     * @param createDate the value for tb_category.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.update_date
     *
     * @return the value of tb_category.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.update_date
     *
     * @param updateDate the value for tb_category.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.status
     *
     * @return the value of tb_category.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.status
     *
     * @param status the value for tb_category.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_category.img
     *
     * @return the value of tb_category.img
     *
     * @mbggenerated
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_category.img
     *
     * @param img the value for tb_category.img
     *
     * @mbggenerated
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public List<TbCategory> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<TbCategory> childCategory) {
        this.childCategory = childCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_category
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
        sb.append(", categoryName=").append(categoryName);
        sb.append(", parentId=").append(parentId);
        sb.append(", categoryLevel=").append(categoryLevel);
        sb.append(", isEnd=").append(isEnd);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", img=").append(img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}