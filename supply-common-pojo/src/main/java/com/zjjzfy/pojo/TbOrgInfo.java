package com.zjjzfy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_org_info")
public class TbOrgInfo<compareTo> implements Serializable {
    @Id
    private Integer id;

    private String orgName;

    private String branchno;

    private String parentBranchno;

    private String address;

    private String contact;

    private String telephone;

    private Boolean isDelete;

    private Date createDate;

    private Date updateDate;

    private Byte type;

    private Byte supplierType;

    private BigDecimal balance;

    private String bankNo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno == null ? null : branchno.trim();
    }

    public String getParentBranchno() {
        return parentBranchno;
    }

    public void setParentBranchno(String parentBranchno) {
        this.parentBranchno = parentBranchno == null ? null : parentBranchno.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Byte supplierType) {
        this.supplierType = supplierType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgName=").append(orgName);
        sb.append(", branchno=").append(branchno);
        sb.append(", parentBranchno=").append(parentBranchno);
        sb.append(", address=").append(address);
        sb.append(", contact=").append(contact);
        sb.append(", telephone=").append(telephone);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", type=").append(type);
        sb.append(", supplierType=").append(supplierType);
        sb.append(", balance=").append(balance);
        sb.append(", bankNo=").append(bankNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}