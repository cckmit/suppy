package com.zjjzfy.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbInvoice implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.application_date
     *
     * @mbggenerated
     */
    private Date applicationDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.total
     *
     * @mbggenerated
     */
    private Double total;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.supplier_id
     *
     * @mbggenerated
     */
    private Integer supplierId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.invoice_id
     *
     * @mbggenerated
     */
    private String invoiceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.invoice_type
     *
     * @mbggenerated
     */
    private String invoiceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.deliver_billno
     *
     * @mbggenerated
     */
    private String deliverBillno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_invoice.applicater_id
     *
     * @mbggenerated
     */
    private Integer applicaterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_invoice
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.id
     *
     * @return the value of tb_invoice.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.id
     *
     * @param id the value for tb_invoice.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.application_date
     *
     * @return the value of tb_invoice.application_date
     *
     * @mbggenerated
     */
    public Date getApplicationDate() {
        return applicationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.application_date
     *
     * @param applicationDate the value for tb_invoice.application_date
     *
     * @mbggenerated
     */
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.total
     *
     * @return the value of tb_invoice.total
     *
     * @mbggenerated
     */
    public Double getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.total
     *
     * @param total the value for tb_invoice.total
     *
     * @mbggenerated
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.supplier_id
     *
     * @return the value of tb_invoice.supplier_id
     *
     * @mbggenerated
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.supplier_id
     *
     * @param supplierId the value for tb_invoice.supplier_id
     *
     * @mbggenerated
     */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.state
     *
     * @return the value of tb_invoice.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.state
     *
     * @param state the value for tb_invoice.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.invoice_id
     *
     * @return the value of tb_invoice.invoice_id
     *
     * @mbggenerated
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.invoice_id
     *
     * @param invoiceId the value for tb_invoice.invoice_id
     *
     * @mbggenerated
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.invoice_type
     *
     * @return the value of tb_invoice.invoice_type
     *
     * @mbggenerated
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.invoice_type
     *
     * @param invoiceType the value for tb_invoice.invoice_type
     *
     * @mbggenerated
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.deliver_billno
     *
     * @return the value of tb_invoice.deliver_billno
     *
     * @mbggenerated
     */
    public String getDeliverBillno() {
        return deliverBillno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.deliver_billno
     *
     * @param deliverBillno the value for tb_invoice.deliver_billno
     *
     * @mbggenerated
     */
    public void setDeliverBillno(String deliverBillno) {
        this.deliverBillno = deliverBillno == null ? null : deliverBillno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_invoice.applicater_id
     *
     * @return the value of tb_invoice.applicater_id
     *
     * @mbggenerated
     */
    public Integer getApplicaterId() {
        return applicaterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_invoice.applicater_id
     *
     * @param applicaterId the value for tb_invoice.applicater_id
     *
     * @mbggenerated
     */
    public void setApplicaterId(Integer applicaterId) {
        this.applicaterId = applicaterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_invoice
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
        sb.append(", applicationDate=").append(applicationDate);
        sb.append(", total=").append(total);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", state=").append(state);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", deliverBillno=").append(deliverBillno);
        sb.append(", applicaterId=").append(applicaterId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}