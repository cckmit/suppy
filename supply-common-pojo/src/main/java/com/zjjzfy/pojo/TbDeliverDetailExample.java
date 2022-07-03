package com.zjjzfy.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbDeliverDetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public TbDeliverDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoIsNull() {
            addCriterion("deliver_billno is null");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoIsNotNull() {
            addCriterion("deliver_billno is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoEqualTo(String value) {
            addCriterion("deliver_billno =", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoNotEqualTo(String value) {
            addCriterion("deliver_billno <>", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoGreaterThan(String value) {
            addCriterion("deliver_billno >", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoGreaterThanOrEqualTo(String value) {
            addCriterion("deliver_billno >=", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoLessThan(String value) {
            addCriterion("deliver_billno <", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoLessThanOrEqualTo(String value) {
            addCriterion("deliver_billno <=", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoLike(String value) {
            addCriterion("deliver_billno like", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoNotLike(String value) {
            addCriterion("deliver_billno not like", value, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoIn(List<String> values) {
            addCriterion("deliver_billno in", values, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoNotIn(List<String> values) {
            addCriterion("deliver_billno not in", values, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoBetween(String value1, String value2) {
            addCriterion("deliver_billno between", value1, value2, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverBillnoNotBetween(String value1, String value2) {
            addCriterion("deliver_billno not between", value1, value2, "deliverBillno");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdIsNull() {
            addCriterion("deliver_form_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdIsNotNull() {
            addCriterion("deliver_form_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdEqualTo(Integer value) {
            addCriterion("deliver_form_id =", value, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdNotEqualTo(Integer value) {
            addCriterion("deliver_form_id <>", value, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdGreaterThan(Integer value) {
            addCriterion("deliver_form_id >", value, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_form_id >=", value, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdLessThan(Integer value) {
            addCriterion("deliver_form_id <", value, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_form_id <=", value, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdIn(List<Integer> values) {
            addCriterion("deliver_form_id in", values, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdNotIn(List<Integer> values) {
            addCriterion("deliver_form_id not in", values, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdBetween(Integer value1, Integer value2) {
            addCriterion("deliver_form_id between", value1, value2, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverFormIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_form_id not between", value1, value2, "deliverFormId");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityIsNull() {
            addCriterion("deliver_quantity is null");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityIsNotNull() {
            addCriterion("deliver_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityEqualTo(Integer value) {
            addCriterion("deliver_quantity =", value, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityNotEqualTo(Integer value) {
            addCriterion("deliver_quantity <>", value, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityGreaterThan(Integer value) {
            addCriterion("deliver_quantity >", value, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_quantity >=", value, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityLessThan(Integer value) {
            addCriterion("deliver_quantity <", value, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_quantity <=", value, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityIn(List<Integer> values) {
            addCriterion("deliver_quantity in", values, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityNotIn(List<Integer> values) {
            addCriterion("deliver_quantity not in", values, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityBetween(Integer value1, Integer value2) {
            addCriterion("deliver_quantity between", value1, value2, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andDeliverQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_quantity not between", value1, value2, "deliverQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityIsNull() {
            addCriterion("storage_quantity is null");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityIsNotNull() {
            addCriterion("storage_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityEqualTo(Integer value) {
            addCriterion("storage_quantity =", value, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityNotEqualTo(Integer value) {
            addCriterion("storage_quantity <>", value, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityGreaterThan(Integer value) {
            addCriterion("storage_quantity >", value, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("storage_quantity >=", value, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityLessThan(Integer value) {
            addCriterion("storage_quantity <", value, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("storage_quantity <=", value, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityIn(List<Integer> values) {
            addCriterion("storage_quantity in", values, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityNotIn(List<Integer> values) {
            addCriterion("storage_quantity not in", values, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityBetween(Integer value1, Integer value2) {
            addCriterion("storage_quantity between", value1, value2, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("storage_quantity not between", value1, value2, "storageQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNull() {
            addCriterion("purchase_price is null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNotNull() {
            addCriterion("purchase_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceEqualTo(BigDecimal value) {
            addCriterion("purchase_price =", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotEqualTo(BigDecimal value) {
            addCriterion("purchase_price <>", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThan(BigDecimal value) {
            addCriterion("purchase_price >", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("purchase_price >=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThan(BigDecimal value) {
            addCriterion("purchase_price <", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("purchase_price <=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIn(List<BigDecimal> values) {
            addCriterion("purchase_price in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotIn(List<BigDecimal> values) {
            addCriterion("purchase_price not in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("purchase_price between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("purchase_price not between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_deliver_detail
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}