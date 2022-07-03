package com.zjjzfy.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbPurchaseDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPurchaseDetailExample() {
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

        public Criteria andPurchaseQuantityIsNull() {
            addCriterion("purchase_quantity is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityIsNotNull() {
            addCriterion("purchase_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityEqualTo(Integer value) {
            addCriterion("purchase_quantity =", value, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityNotEqualTo(Integer value) {
            addCriterion("purchase_quantity <>", value, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityGreaterThan(Integer value) {
            addCriterion("purchase_quantity >", value, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_quantity >=", value, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityLessThan(Integer value) {
            addCriterion("purchase_quantity <", value, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_quantity <=", value, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityIn(List<Integer> values) {
            addCriterion("purchase_quantity in", values, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityNotIn(List<Integer> values) {
            addCriterion("purchase_quantity not in", values, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityBetween(Integer value1, Integer value2) {
            addCriterion("purchase_quantity between", value1, value2, "purchaseQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_quantity not between", value1, value2, "purchaseQuantity");
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

        public Criteria andOnQualityIsNull() {
            addCriterion("on_quality is null");
            return (Criteria) this;
        }

        public Criteria andOnQualityIsNotNull() {
            addCriterion("on_quality is not null");
            return (Criteria) this;
        }

        public Criteria andOnQualityEqualTo(Integer value) {
            addCriterion("on_quality =", value, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityNotEqualTo(Integer value) {
            addCriterion("on_quality <>", value, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityGreaterThan(Integer value) {
            addCriterion("on_quality >", value, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("on_quality >=", value, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityLessThan(Integer value) {
            addCriterion("on_quality <", value, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityLessThanOrEqualTo(Integer value) {
            addCriterion("on_quality <=", value, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityIn(List<Integer> values) {
            addCriterion("on_quality in", values, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityNotIn(List<Integer> values) {
            addCriterion("on_quality not in", values, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityBetween(Integer value1, Integer value2) {
            addCriterion("on_quality between", value1, value2, "onQuality");
            return (Criteria) this;
        }

        public Criteria andOnQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("on_quality not between", value1, value2, "onQuality");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityIsNull() {
            addCriterion("surplus_quantity is null");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityIsNotNull() {
            addCriterion("surplus_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityEqualTo(Integer value) {
            addCriterion("surplus_quantity =", value, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityNotEqualTo(Integer value) {
            addCriterion("surplus_quantity <>", value, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityGreaterThan(Integer value) {
            addCriterion("surplus_quantity >", value, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("surplus_quantity >=", value, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityLessThan(Integer value) {
            addCriterion("surplus_quantity <", value, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("surplus_quantity <=", value, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityIn(List<Integer> values) {
            addCriterion("surplus_quantity in", values, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityNotIn(List<Integer> values) {
            addCriterion("surplus_quantity not in", values, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityBetween(Integer value1, Integer value2) {
            addCriterion("surplus_quantity between", value1, value2, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andSurplusQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("surplus_quantity not between", value1, value2, "surplusQuantity");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("`status` not between", value1, value2, "status");
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

        public Criteria andSettlePriceIsNull() {
            addCriterion("settle_price is null");
            return (Criteria) this;
        }

        public Criteria andSettlePriceIsNotNull() {
            addCriterion("settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andSettlePriceEqualTo(BigDecimal value) {
            addCriterion("settle_price =", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("settle_price <>", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("settle_price >", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_price >=", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceLessThan(BigDecimal value) {
            addCriterion("settle_price <", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_price <=", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceIn(List<BigDecimal> values) {
            addCriterion("settle_price in", values, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("settle_price not in", values, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_price between", value1, value2, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_price not between", value1, value2, "settlePrice");
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

        public Criteria andProductColorIsNull() {
            addCriterion("product_color is null");
            return (Criteria) this;
        }

        public Criteria andProductColorIsNotNull() {
            addCriterion("product_color is not null");
            return (Criteria) this;
        }

        public Criteria andProductColorEqualTo(String value) {
            addCriterion("product_color =", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorNotEqualTo(String value) {
            addCriterion("product_color <>", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorGreaterThan(String value) {
            addCriterion("product_color >", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorGreaterThanOrEqualTo(String value) {
            addCriterion("product_color >=", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorLessThan(String value) {
            addCriterion("product_color <", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorLessThanOrEqualTo(String value) {
            addCriterion("product_color <=", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorLike(String value) {
            addCriterion("product_color like", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorNotLike(String value) {
            addCriterion("product_color not like", value, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorIn(List<String> values) {
            addCriterion("product_color in", values, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorNotIn(List<String> values) {
            addCriterion("product_color not in", values, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorBetween(String value1, String value2) {
            addCriterion("product_color between", value1, value2, "productColor");
            return (Criteria) this;
        }

        public Criteria andProductColorNotBetween(String value1, String value2) {
            addCriterion("product_color not between", value1, value2, "productColor");
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