package com.zjjzfy.common.entity;

import java.io.Serializable;

/**
 * 自定义响应结构
 * @author hsmz
 */
public class SupplyResult implements Serializable {


    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    public static SupplyResult build(Integer status, String msg, Object data) {
        return new SupplyResult(status, msg, data);
    }

    public static SupplyResult ok(Object data) {
        return new SupplyResult(data);
    }

    public static SupplyResult ok() {
        return new SupplyResult(null);
    }

    public SupplyResult() {

    }

    public static SupplyResult build(Integer status, String msg) {
        return new SupplyResult(status, msg, null);
    }

    public SupplyResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SupplyResult(Object data) {
        this.status = 200;
        this.msg = "操作成功！";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SupplyResult{"+
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
