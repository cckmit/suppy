package com.zjjzfy.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbDeliverFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbDeliverFormExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andPurchaserIdIsNull() {
            addCriterion("purchaser_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdIsNotNull() {
            addCriterion("purchaser_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdEqualTo(Integer value) {
            addCriterion("purchaser_id =", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdNotEqualTo(Integer value) {
            addCriterion("purchaser_id <>", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdGreaterThan(Integer value) {
            addCriterion("purchaser_id >", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchaser_id >=", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdLessThan(Integer value) {
            addCriterion("purchaser_id <", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdLessThanOrEqualTo(Integer value) {
            addCriterion("purchaser_id <=", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdIn(List<Integer> values) {
            addCriterion("purchaser_id in", values, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdNotIn(List<Integer> values) {
            addCriterion("purchaser_id not in", values, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdBetween(Integer value1, Integer value2) {
            addCriterion("purchaser_id between", value1, value2, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("purchaser_id not between", value1, value2, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Integer value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Integer value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Integer value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Integer value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Integer> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Integer> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoIsNull() {
            addCriterion("purchase_billno is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoIsNotNull() {
            addCriterion("purchase_billno is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoEqualTo(String value) {
            addCriterion("purchase_billno =", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoNotEqualTo(String value) {
            addCriterion("purchase_billno <>", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoGreaterThan(String value) {
            addCriterion("purchase_billno >", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_billno >=", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoLessThan(String value) {
            addCriterion("purchase_billno <", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoLessThanOrEqualTo(String value) {
            addCriterion("purchase_billno <=", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoLike(String value) {
            addCriterion("purchase_billno like", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoNotLike(String value) {
            addCriterion("purchase_billno not like", value, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoIn(List<String> values) {
            addCriterion("purchase_billno in", values, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoNotIn(List<String> values) {
            addCriterion("purchase_billno not in", values, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoBetween(String value1, String value2) {
            addCriterion("purchase_billno between", value1, value2, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseBillnoNotBetween(String value1, String value2) {
            addCriterion("purchase_billno not between", value1, value2, "purchaseBillno");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdIsNull() {
            addCriterion("purchase_form_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdIsNotNull() {
            addCriterion("purchase_form_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdEqualTo(Integer value) {
            addCriterion("purchase_form_id =", value, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdNotEqualTo(Integer value) {
            addCriterion("purchase_form_id <>", value, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdGreaterThan(Integer value) {
            addCriterion("purchase_form_id >", value, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_form_id >=", value, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdLessThan(Integer value) {
            addCriterion("purchase_form_id <", value, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_form_id <=", value, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdIn(List<Integer> values) {
            addCriterion("purchase_form_id in", values, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdNotIn(List<Integer> values) {
            addCriterion("purchase_form_id not in", values, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdBetween(Integer value1, Integer value2) {
            addCriterion("purchase_form_id between", value1, value2, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andPurchaseFormIdNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_form_id not between", value1, value2, "purchaseFormId");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(BigDecimal value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(BigDecimal value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<BigDecimal> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(Byte value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(Byte value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(Byte value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(Byte value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(Byte value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<Byte> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<Byte> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(Byte value1, Byte value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdIsNull() {
            addCriterion("deliver_person_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdIsNotNull() {
            addCriterion("deliver_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdEqualTo(Integer value) {
            addCriterion("deliver_person_id =", value, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdNotEqualTo(Integer value) {
            addCriterion("deliver_person_id <>", value, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdGreaterThan(Integer value) {
            addCriterion("deliver_person_id >", value, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_person_id >=", value, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdLessThan(Integer value) {
            addCriterion("deliver_person_id <", value, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_person_id <=", value, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdIn(List<Integer> values) {
            addCriterion("deliver_person_id in", values, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdNotIn(List<Integer> values) {
            addCriterion("deliver_person_id not in", values, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdBetween(Integer value1, Integer value2) {
            addCriterion("deliver_person_id between", value1, value2, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverPersonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_person_id not between", value1, value2, "deliverPersonId");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNull() {
            addCriterion("deliver_date is null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNotNull() {
            addCriterion("deliver_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateEqualTo(Date value) {
            addCriterion("deliver_date =", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotEqualTo(Date value) {
            addCriterion("deliver_date <>", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThan(Date value) {
            addCriterion("deliver_date >", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver_date >=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThan(Date value) {
            addCriterion("deliver_date <", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThanOrEqualTo(Date value) {
            addCriterion("deliver_date <=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIn(List<Date> values) {
            addCriterion("deliver_date in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotIn(List<Date> values) {
            addCriterion("deliver_date not in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateBetween(Date value1, Date value2) {
            addCriterion("deliver_date between", value1, value2, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotBetween(Date value1, Date value2) {
            addCriterion("deliver_date not between", value1, value2, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdIsNull() {
            addCriterion("confirm_person_id is null");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdIsNotNull() {
            addCriterion("confirm_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdEqualTo(Integer value) {
            addCriterion("confirm_person_id =", value, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdNotEqualTo(Integer value) {
            addCriterion("confirm_person_id <>", value, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdGreaterThan(Integer value) {
            addCriterion("confirm_person_id >", value, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_person_id >=", value, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdLessThan(Integer value) {
            addCriterion("confirm_person_id <", value, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_person_id <=", value, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdIn(List<Integer> values) {
            addCriterion("confirm_person_id in", values, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdNotIn(List<Integer> values) {
            addCriterion("confirm_person_id not in", values, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdBetween(Integer value1, Integer value2) {
            addCriterion("confirm_person_id between", value1, value2, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmPersonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_person_id not between", value1, value2, "confirmPersonId");
            return (Criteria) this;
        }

        public Criteria andConfirmDateIsNull() {
            addCriterion("confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andConfirmDateIsNotNull() {
            addCriterion("confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmDateEqualTo(Date value) {
            addCriterion("confirm_date =", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateNotEqualTo(Date value) {
            addCriterion("confirm_date <>", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateGreaterThan(Date value) {
            addCriterion("confirm_date >", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_date >=", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateLessThan(Date value) {
            addCriterion("confirm_date <", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("confirm_date <=", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateIn(List<Date> values) {
            addCriterion("confirm_date in", values, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateNotIn(List<Date> values) {
            addCriterion("confirm_date not in", values, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateBetween(Date value1, Date value2) {
            addCriterion("confirm_date between", value1, value2, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("confirm_date not between", value1, value2, "confirmDate");
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

        public Criteria andPurchaseOrgIdIsNull() {
            addCriterion("purchase_org_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdIsNotNull() {
            addCriterion("purchase_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdEqualTo(Integer value) {
            addCriterion("purchase_org_id =", value, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdNotEqualTo(Integer value) {
            addCriterion("purchase_org_id <>", value, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdGreaterThan(Integer value) {
            addCriterion("purchase_org_id >", value, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_org_id >=", value, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdLessThan(Integer value) {
            addCriterion("purchase_org_id <", value, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_org_id <=", value, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdIn(List<Integer> values) {
            addCriterion("purchase_org_id in", values, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdNotIn(List<Integer> values) {
            addCriterion("purchase_org_id not in", values, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("purchase_org_id between", value1, value2, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_org_id not between", value1, value2, "purchaseOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdIsNull() {
            addCriterion("supply_org_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdIsNotNull() {
            addCriterion("supply_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdEqualTo(Integer value) {
            addCriterion("supply_org_id =", value, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdNotEqualTo(Integer value) {
            addCriterion("supply_org_id <>", value, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdGreaterThan(Integer value) {
            addCriterion("supply_org_id >", value, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_org_id >=", value, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdLessThan(Integer value) {
            addCriterion("supply_org_id <", value, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("supply_org_id <=", value, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdIn(List<Integer> values) {
            addCriterion("supply_org_id in", values, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdNotIn(List<Integer> values) {
            addCriterion("supply_org_id not in", values, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("supply_org_id between", value1, value2, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andSupplyOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_org_id not between", value1, value2, "supplyOrgId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIsNull() {
            addCriterion("total_quantity is null");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIsNotNull() {
            addCriterion("total_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityEqualTo(Integer value) {
            addCriterion("total_quantity =", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityNotEqualTo(Integer value) {
            addCriterion("total_quantity <>", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityGreaterThan(Integer value) {
            addCriterion("total_quantity >", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_quantity >=", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityLessThan(Integer value) {
            addCriterion("total_quantity <", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("total_quantity <=", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIn(List<Integer> values) {
            addCriterion("total_quantity in", values, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityNotIn(List<Integer> values) {
            addCriterion("total_quantity not in", values, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityBetween(Integer value1, Integer value2) {
            addCriterion("total_quantity between", value1, value2, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("total_quantity not between", value1, value2, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdIsNull() {
            addCriterion("liquidation_id is null");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdIsNotNull() {
            addCriterion("liquidation_id is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdEqualTo(Integer value) {
            addCriterion("liquidation_id =", value, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdNotEqualTo(Integer value) {
            addCriterion("liquidation_id <>", value, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdGreaterThan(Integer value) {
            addCriterion("liquidation_id >", value, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("liquidation_id >=", value, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdLessThan(Integer value) {
            addCriterion("liquidation_id <", value, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdLessThanOrEqualTo(Integer value) {
            addCriterion("liquidation_id <=", value, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdIn(List<Integer> values) {
            addCriterion("liquidation_id in", values, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdNotIn(List<Integer> values) {
            addCriterion("liquidation_id not in", values, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdBetween(Integer value1, Integer value2) {
            addCriterion("liquidation_id between", value1, value2, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("liquidation_id not between", value1, value2, "liquidationId");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeIsNull() {
            addCriterion("liquidation_time is null");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeIsNotNull() {
            addCriterion("liquidation_time is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeEqualTo(Date value) {
            addCriterion("liquidation_time =", value, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeNotEqualTo(Date value) {
            addCriterion("liquidation_time <>", value, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeGreaterThan(Date value) {
            addCriterion("liquidation_time >", value, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("liquidation_time >=", value, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeLessThan(Date value) {
            addCriterion("liquidation_time <", value, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeLessThanOrEqualTo(Date value) {
            addCriterion("liquidation_time <=", value, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeIn(List<Date> values) {
            addCriterion("liquidation_time in", values, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeNotIn(List<Date> values) {
            addCriterion("liquidation_time not in", values, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeBetween(Date value1, Date value2) {
            addCriterion("liquidation_time between", value1, value2, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationTimeNotBetween(Date value1, Date value2) {
            addCriterion("liquidation_time not between", value1, value2, "liquidationTime");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankIsNull() {
            addCriterion("total_price_bank is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankIsNotNull() {
            addCriterion("total_price_bank is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankEqualTo(BigDecimal value) {
            addCriterion("total_price_bank =", value, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankNotEqualTo(BigDecimal value) {
            addCriterion("total_price_bank <>", value, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankGreaterThan(BigDecimal value) {
            addCriterion("total_price_bank >", value, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price_bank >=", value, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankLessThan(BigDecimal value) {
            addCriterion("total_price_bank <", value, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price_bank <=", value, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankIn(List<BigDecimal> values) {
            addCriterion("total_price_bank in", values, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankNotIn(List<BigDecimal> values) {
            addCriterion("total_price_bank not in", values, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price_bank between", value1, value2, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBankNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price_bank not between", value1, value2, "totalPriceBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankIsNull() {
            addCriterion("settle_status_bank is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankIsNotNull() {
            addCriterion("settle_status_bank is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankEqualTo(Byte value) {
            addCriterion("settle_status_bank =", value, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankNotEqualTo(Byte value) {
            addCriterion("settle_status_bank <>", value, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankGreaterThan(Byte value) {
            addCriterion("settle_status_bank >", value, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankGreaterThanOrEqualTo(Byte value) {
            addCriterion("settle_status_bank >=", value, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankLessThan(Byte value) {
            addCriterion("settle_status_bank <", value, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankLessThanOrEqualTo(Byte value) {
            addCriterion("settle_status_bank <=", value, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankIn(List<Byte> values) {
            addCriterion("settle_status_bank in", values, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankNotIn(List<Byte> values) {
            addCriterion("settle_status_bank not in", values, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankBetween(Byte value1, Byte value2) {
            addCriterion("settle_status_bank between", value1, value2, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBankNotBetween(Byte value1, Byte value2) {
            addCriterion("settle_status_bank not between", value1, value2, "settleStatusBank");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdIsNull() {
            addCriterion("liquidation_bank_id is null");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdIsNotNull() {
            addCriterion("liquidation_bank_id is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdEqualTo(Integer value) {
            addCriterion("liquidation_bank_id =", value, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdNotEqualTo(Integer value) {
            addCriterion("liquidation_bank_id <>", value, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdGreaterThan(Integer value) {
            addCriterion("liquidation_bank_id >", value, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("liquidation_bank_id >=", value, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdLessThan(Integer value) {
            addCriterion("liquidation_bank_id <", value, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdLessThanOrEqualTo(Integer value) {
            addCriterion("liquidation_bank_id <=", value, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdIn(List<Integer> values) {
            addCriterion("liquidation_bank_id in", values, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdNotIn(List<Integer> values) {
            addCriterion("liquidation_bank_id not in", values, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdBetween(Integer value1, Integer value2) {
            addCriterion("liquidation_bank_id between", value1, value2, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankIdNotBetween(Integer value1, Integer value2) {
            addCriterion("liquidation_bank_id not between", value1, value2, "liquidationBankId");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeIsNull() {
            addCriterion("liquidation_bank_time is null");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeIsNotNull() {
            addCriterion("liquidation_bank_time is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeEqualTo(Date value) {
            addCriterion("liquidation_bank_time =", value, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeNotEqualTo(Date value) {
            addCriterion("liquidation_bank_time <>", value, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeGreaterThan(Date value) {
            addCriterion("liquidation_bank_time >", value, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("liquidation_bank_time >=", value, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeLessThan(Date value) {
            addCriterion("liquidation_bank_time <", value, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeLessThanOrEqualTo(Date value) {
            addCriterion("liquidation_bank_time <=", value, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeIn(List<Date> values) {
            addCriterion("liquidation_bank_time in", values, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeNotIn(List<Date> values) {
            addCriterion("liquidation_bank_time not in", values, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeBetween(Date value1, Date value2) {
            addCriterion("liquidation_bank_time between", value1, value2, "liquidationBankTime");
            return (Criteria) this;
        }

        public Criteria andLiquidationBankTimeNotBetween(Date value1, Date value2) {
            addCriterion("liquidation_bank_time not between", value1, value2, "liquidationBankTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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