package com.zjjzfy.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProductExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`NAME` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`NAME` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`NAME` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`NAME` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`NAME` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`NAME` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`NAME` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`NAME` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`NAME` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`NAME` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`NAME` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`NAME` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`NAME` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("BARCODE is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("BARCODE is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("BARCODE =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("BARCODE <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("BARCODE >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("BARCODE >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("BARCODE <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("BARCODE <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("BARCODE like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("BARCODE not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("BARCODE in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("BARCODE not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("BARCODE between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("BARCODE not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("BRAND is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("BRAND is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("BRAND =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("BRAND <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("BRAND >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("BRAND >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("BRAND <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("BRAND <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("BRAND like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("BRAND not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("BRAND in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("BRAND not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("BRAND between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("BRAND not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("UNIT =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("UNIT <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("UNIT >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("UNIT <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("UNIT <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("UNIT like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("UNIT not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("UNIT in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("UNIT not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("UNIT between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("UNIT not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("IMAGE =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("IMAGE <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("IMAGE >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("IMAGE <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("IMAGE <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("IMAGE like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("IMAGE not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("IMAGE in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("IMAGE not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("IMAGE between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("IMAGE not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("PRICE =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("PRICE <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("PRICE >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("PRICE <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("PRICE in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("PRICE not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andReferencePriceIsNull() {
            addCriterion("reference_price is null");
            return (Criteria) this;
        }

        public Criteria andReferencePriceIsNotNull() {
            addCriterion("reference_price is not null");
            return (Criteria) this;
        }

        public Criteria andReferencePriceEqualTo(BigDecimal value) {
            addCriterion("reference_price =", value, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceNotEqualTo(BigDecimal value) {
            addCriterion("reference_price <>", value, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceGreaterThan(BigDecimal value) {
            addCriterion("reference_price >", value, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reference_price >=", value, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceLessThan(BigDecimal value) {
            addCriterion("reference_price <", value, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reference_price <=", value, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceIn(List<BigDecimal> values) {
            addCriterion("reference_price in", values, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceNotIn(List<BigDecimal> values) {
            addCriterion("reference_price not in", values, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reference_price between", value1, value2, "referencePrice");
            return (Criteria) this;
        }

        public Criteria andReferencePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reference_price not between", value1, value2, "referencePrice");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("model like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("model not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("model not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNull() {
            addCriterion("product_status is null");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNotNull() {
            addCriterion("product_status is not null");
            return (Criteria) this;
        }

        public Criteria andProductStatusEqualTo(Byte value) {
            addCriterion("product_status =", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotEqualTo(Byte value) {
            addCriterion("product_status <>", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThan(Byte value) {
            addCriterion("product_status >", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("product_status >=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThan(Byte value) {
            addCriterion("product_status <", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThanOrEqualTo(Byte value) {
            addCriterion("product_status <=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusIn(List<Byte> values) {
            addCriterion("product_status in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotIn(List<Byte> values) {
            addCriterion("product_status not in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusBetween(Byte value1, Byte value2) {
            addCriterion("product_status between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("product_status not between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andProductLabelIsNull() {
            addCriterion("product_label is null");
            return (Criteria) this;
        }

        public Criteria andProductLabelIsNotNull() {
            addCriterion("product_label is not null");
            return (Criteria) this;
        }

        public Criteria andProductLabelEqualTo(Byte value) {
            addCriterion("product_label =", value, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelNotEqualTo(Byte value) {
            addCriterion("product_label <>", value, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelGreaterThan(Byte value) {
            addCriterion("product_label >", value, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelGreaterThanOrEqualTo(Byte value) {
            addCriterion("product_label >=", value, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelLessThan(Byte value) {
            addCriterion("product_label <", value, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelLessThanOrEqualTo(Byte value) {
            addCriterion("product_label <=", value, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelIn(List<Byte> values) {
            addCriterion("product_label in", values, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelNotIn(List<Byte> values) {
            addCriterion("product_label not in", values, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelBetween(Byte value1, Byte value2) {
            addCriterion("product_label between", value1, value2, "productLabel");
            return (Criteria) this;
        }

        public Criteria andProductLabelNotBetween(Byte value1, Byte value2) {
            addCriterion("product_label not between", value1, value2, "productLabel");
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

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Byte value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Byte value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Byte value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Byte value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Byte value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Byte> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Byte> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Byte value1, Byte value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andUnitexplanIsNull() {
            addCriterion("unitExplan is null");
            return (Criteria) this;
        }

        public Criteria andUnitexplanIsNotNull() {
            addCriterion("unitExplan is not null");
            return (Criteria) this;
        }

        public Criteria andUnitexplanEqualTo(String value) {
            addCriterion("unitExplan =", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanNotEqualTo(String value) {
            addCriterion("unitExplan <>", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanGreaterThan(String value) {
            addCriterion("unitExplan >", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanGreaterThanOrEqualTo(String value) {
            addCriterion("unitExplan >=", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanLessThan(String value) {
            addCriterion("unitExplan <", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanLessThanOrEqualTo(String value) {
            addCriterion("unitExplan <=", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanLike(String value) {
            addCriterion("unitExplan like", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanNotLike(String value) {
            addCriterion("unitExplan not like", value, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanIn(List<String> values) {
            addCriterion("unitExplan in", values, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanNotIn(List<String> values) {
            addCriterion("unitExplan not in", values, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanBetween(String value1, String value2) {
            addCriterion("unitExplan between", value1, value2, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andUnitexplanNotBetween(String value1, String value2) {
            addCriterion("unitExplan not between", value1, value2, "unitexplan");
            return (Criteria) this;
        }

        public Criteria andIsdiscountIsNull() {
            addCriterion("isDiscount is null");
            return (Criteria) this;
        }

        public Criteria andIsdiscountIsNotNull() {
            addCriterion("isDiscount is not null");
            return (Criteria) this;
        }

        public Criteria andIsdiscountEqualTo(String value) {
            addCriterion("isDiscount =", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountNotEqualTo(String value) {
            addCriterion("isDiscount <>", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountGreaterThan(String value) {
            addCriterion("isDiscount >", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountGreaterThanOrEqualTo(String value) {
            addCriterion("isDiscount >=", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountLessThan(String value) {
            addCriterion("isDiscount <", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountLessThanOrEqualTo(String value) {
            addCriterion("isDiscount <=", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountLike(String value) {
            addCriterion("isDiscount like", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountNotLike(String value) {
            addCriterion("isDiscount not like", value, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountIn(List<String> values) {
            addCriterion("isDiscount in", values, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountNotIn(List<String> values) {
            addCriterion("isDiscount not in", values, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountBetween(String value1, String value2) {
            addCriterion("isDiscount between", value1, value2, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andIsdiscountNotBetween(String value1, String value2) {
            addCriterion("isDiscount not between", value1, value2, "isdiscount");
            return (Criteria) this;
        }

        public Criteria andNewproductIsNull() {
            addCriterion("newProduct is null");
            return (Criteria) this;
        }

        public Criteria andNewproductIsNotNull() {
            addCriterion("newProduct is not null");
            return (Criteria) this;
        }

        public Criteria andNewproductEqualTo(String value) {
            addCriterion("newProduct =", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductNotEqualTo(String value) {
            addCriterion("newProduct <>", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductGreaterThan(String value) {
            addCriterion("newProduct >", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductGreaterThanOrEqualTo(String value) {
            addCriterion("newProduct >=", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductLessThan(String value) {
            addCriterion("newProduct <", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductLessThanOrEqualTo(String value) {
            addCriterion("newProduct <=", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductLike(String value) {
            addCriterion("newProduct like", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductNotLike(String value) {
            addCriterion("newProduct not like", value, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductIn(List<String> values) {
            addCriterion("newProduct in", values, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductNotIn(List<String> values) {
            addCriterion("newProduct not in", values, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductBetween(String value1, String value2) {
            addCriterion("newProduct between", value1, value2, "newproduct");
            return (Criteria) this;
        }

        public Criteria andNewproductNotBetween(String value1, String value2) {
            addCriterion("newProduct not between", value1, value2, "newproduct");
            return (Criteria) this;
        }

        public Criteria andSalesIsNull() {
            addCriterion("sales is null");
            return (Criteria) this;
        }

        public Criteria andSalesIsNotNull() {
            addCriterion("sales is not null");
            return (Criteria) this;
        }

        public Criteria andSalesEqualTo(Integer value) {
            addCriterion("sales =", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesNotEqualTo(Integer value) {
            addCriterion("sales <>", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesGreaterThan(Integer value) {
            addCriterion("sales >", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales >=", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesLessThan(Integer value) {
            addCriterion("sales <", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesLessThanOrEqualTo(Integer value) {
            addCriterion("sales <=", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesIn(List<Integer> values) {
            addCriterion("sales in", values, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesNotIn(List<Integer> values) {
            addCriterion("sales not in", values, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesBetween(Integer value1, Integer value2) {
            addCriterion("sales between", value1, value2, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesNotBetween(Integer value1, Integer value2) {
            addCriterion("sales not between", value1, value2, "sales");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNull() {
            addCriterion("sort_id is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("sort_id is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Integer value) {
            addCriterion("sort_id =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Integer value) {
            addCriterion("sort_id <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Integer value) {
            addCriterion("sort_id >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_id >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Integer value) {
            addCriterion("sort_id <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Integer value) {
            addCriterion("sort_id <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Integer> values) {
            addCriterion("sort_id in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Integer> values) {
            addCriterion("sort_id not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Integer value1, Integer value2) {
            addCriterion("sort_id between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_id not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andExchangePriceIsNull() {
            addCriterion("exchange_price is null");
            return (Criteria) this;
        }

        public Criteria andExchangePriceIsNotNull() {
            addCriterion("exchange_price is not null");
            return (Criteria) this;
        }

        public Criteria andExchangePriceEqualTo(BigDecimal value) {
            addCriterion("exchange_price =", value, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceNotEqualTo(BigDecimal value) {
            addCriterion("exchange_price <>", value, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceGreaterThan(BigDecimal value) {
            addCriterion("exchange_price >", value, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_price >=", value, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceLessThan(BigDecimal value) {
            addCriterion("exchange_price <", value, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_price <=", value, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceIn(List<BigDecimal> values) {
            addCriterion("exchange_price in", values, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceNotIn(List<BigDecimal> values) {
            addCriterion("exchange_price not in", values, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_price between", value1, value2, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andExchangePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_price not between", value1, value2, "exchangePrice");
            return (Criteria) this;
        }

        public Criteria andClickrateIsNull() {
            addCriterion("clickrate is null");
            return (Criteria) this;
        }

        public Criteria andClickrateIsNotNull() {
            addCriterion("clickrate is not null");
            return (Criteria) this;
        }

        public Criteria andClickrateEqualTo(String value) {
            addCriterion("clickrate =", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateNotEqualTo(String value) {
            addCriterion("clickrate <>", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateGreaterThan(String value) {
            addCriterion("clickrate >", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateGreaterThanOrEqualTo(String value) {
            addCriterion("clickrate >=", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateLessThan(String value) {
            addCriterion("clickrate <", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateLessThanOrEqualTo(String value) {
            addCriterion("clickrate <=", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateLike(String value) {
            addCriterion("clickrate like", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateNotLike(String value) {
            addCriterion("clickrate not like", value, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateIn(List<String> values) {
            addCriterion("clickrate in", values, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateNotIn(List<String> values) {
            addCriterion("clickrate not in", values, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateBetween(String value1, String value2) {
            addCriterion("clickrate between", value1, value2, "clickrate");
            return (Criteria) this;
        }

        public Criteria andClickrateNotBetween(String value1, String value2) {
            addCriterion("clickrate not between", value1, value2, "clickrate");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdIsNull() {
            addCriterion("flash_sale_id is null");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdIsNotNull() {
            addCriterion("flash_sale_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdEqualTo(Integer value) {
            addCriterion("flash_sale_id =", value, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdNotEqualTo(Integer value) {
            addCriterion("flash_sale_id <>", value, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdGreaterThan(Integer value) {
            addCriterion("flash_sale_id >", value, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flash_sale_id >=", value, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdLessThan(Integer value) {
            addCriterion("flash_sale_id <", value, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdLessThanOrEqualTo(Integer value) {
            addCriterion("flash_sale_id <=", value, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdIn(List<Integer> values) {
            addCriterion("flash_sale_id in", values, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdNotIn(List<Integer> values) {
            addCriterion("flash_sale_id not in", values, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdBetween(Integer value1, Integer value2) {
            addCriterion("flash_sale_id between", value1, value2, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andFlashSaleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flash_sale_id not between", value1, value2, "flashSaleId");
            return (Criteria) this;
        }

        public Criteria andProductLimitIsNull() {
            addCriterion("product_limit is null");
            return (Criteria) this;
        }

        public Criteria andProductLimitIsNotNull() {
            addCriterion("product_limit is not null");
            return (Criteria) this;
        }

        public Criteria andProductLimitEqualTo(Integer value) {
            addCriterion("product_limit =", value, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitNotEqualTo(Integer value) {
            addCriterion("product_limit <>", value, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitGreaterThan(Integer value) {
            addCriterion("product_limit >", value, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_limit >=", value, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitLessThan(Integer value) {
            addCriterion("product_limit <", value, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitLessThanOrEqualTo(Integer value) {
            addCriterion("product_limit <=", value, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitIn(List<Integer> values) {
            addCriterion("product_limit in", values, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitNotIn(List<Integer> values) {
            addCriterion("product_limit not in", values, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitBetween(Integer value1, Integer value2) {
            addCriterion("product_limit between", value1, value2, "productLimit");
            return (Criteria) this;
        }

        public Criteria andProductLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("product_limit not between", value1, value2, "productLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeCashIsNull() {
            addCriterion("exchange_cash is null");
            return (Criteria) this;
        }

        public Criteria andExchangeCashIsNotNull() {
            addCriterion("exchange_cash is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeCashEqualTo(BigDecimal value) {
            addCriterion("exchange_cash =", value, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashNotEqualTo(BigDecimal value) {
            addCriterion("exchange_cash <>", value, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashGreaterThan(BigDecimal value) {
            addCriterion("exchange_cash >", value, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_cash >=", value, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashLessThan(BigDecimal value) {
            addCriterion("exchange_cash <", value, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashLessThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_cash <=", value, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashIn(List<BigDecimal> values) {
            addCriterion("exchange_cash in", values, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashNotIn(List<BigDecimal> values) {
            addCriterion("exchange_cash not in", values, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_cash between", value1, value2, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeCashNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_cash not between", value1, value2, "exchangeCash");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIsNull() {
            addCriterion("exchange_type is null");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIsNotNull() {
            addCriterion("exchange_type is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeEqualTo(Integer value) {
            addCriterion("exchange_type =", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotEqualTo(Integer value) {
            addCriterion("exchange_type <>", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeGreaterThan(Integer value) {
            addCriterion("exchange_type >", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exchange_type >=", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeLessThan(Integer value) {
            addCriterion("exchange_type <", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("exchange_type <=", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIn(List<Integer> values) {
            addCriterion("exchange_type in", values, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotIn(List<Integer> values) {
            addCriterion("exchange_type not in", values, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeBetween(Integer value1, Integer value2) {
            addCriterion("exchange_type between", value1, value2, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("exchange_type not between", value1, value2, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkIsNull() {
            addCriterion("exchange_remark is null");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkIsNotNull() {
            addCriterion("exchange_remark is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkEqualTo(String value) {
            addCriterion("exchange_remark =", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkNotEqualTo(String value) {
            addCriterion("exchange_remark <>", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkGreaterThan(String value) {
            addCriterion("exchange_remark >", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_remark >=", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkLessThan(String value) {
            addCriterion("exchange_remark <", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkLessThanOrEqualTo(String value) {
            addCriterion("exchange_remark <=", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkLike(String value) {
            addCriterion("exchange_remark like", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkNotLike(String value) {
            addCriterion("exchange_remark not like", value, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkIn(List<String> values) {
            addCriterion("exchange_remark in", values, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkNotIn(List<String> values) {
            addCriterion("exchange_remark not in", values, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkBetween(String value1, String value2) {
            addCriterion("exchange_remark between", value1, value2, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andExchangeRemarkNotBetween(String value1, String value2) {
            addCriterion("exchange_remark not between", value1, value2, "exchangeRemark");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotIsNull() {
            addCriterion("purchase_ornot is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotIsNotNull() {
            addCriterion("purchase_ornot is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotEqualTo(Integer value) {
            addCriterion("purchase_ornot =", value, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotNotEqualTo(Integer value) {
            addCriterion("purchase_ornot <>", value, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotGreaterThan(Integer value) {
            addCriterion("purchase_ornot >", value, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_ornot >=", value, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotLessThan(Integer value) {
            addCriterion("purchase_ornot <", value, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_ornot <=", value, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotIn(List<Integer> values) {
            addCriterion("purchase_ornot in", values, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotNotIn(List<Integer> values) {
            addCriterion("purchase_ornot not in", values, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_ornot between", value1, value2, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrnotNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_ornot not between", value1, value2, "purchaseOrnot");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureIsNull() {
            addCriterion("product_prefecture is null");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureIsNotNull() {
            addCriterion("product_prefecture is not null");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureEqualTo(Integer value) {
            addCriterion("product_prefecture =", value, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureNotEqualTo(Integer value) {
            addCriterion("product_prefecture <>", value, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureGreaterThan(Integer value) {
            addCriterion("product_prefecture >", value, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_prefecture >=", value, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureLessThan(Integer value) {
            addCriterion("product_prefecture <", value, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureLessThanOrEqualTo(Integer value) {
            addCriterion("product_prefecture <=", value, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureIn(List<Integer> values) {
            addCriterion("product_prefecture in", values, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureNotIn(List<Integer> values) {
            addCriterion("product_prefecture not in", values, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureBetween(Integer value1, Integer value2) {
            addCriterion("product_prefecture between", value1, value2, "productPrefecture");
            return (Criteria) this;
        }

        public Criteria andProductPrefectureNotBetween(Integer value1, Integer value2) {
            addCriterion("product_prefecture not between", value1, value2, "productPrefecture");
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