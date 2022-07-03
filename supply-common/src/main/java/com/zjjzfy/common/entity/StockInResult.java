package com.zjjzfy.common.entity;

/**
 * 永康入库接收对象
 * @author: hsmz
 * @date: 2019-11-27 11:15
 */
public class StockInResult {

    String msg;

    Boolean stockInResult;

    String billNo;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStockInResult() {
        return stockInResult;
    }

    public void setStockInResult(Boolean stockInResult) {
        this.stockInResult = stockInResult;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}
