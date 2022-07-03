package com.zjjzfy.common.config;

/**
 * @author zyx
 * @date 2020/3/27 下午4:41
 */
public enum NetWorkerStatus {
    /** 未完成*/
    ON("0"),
    /** 已完成*/
    DOWN("1")
    ;


    private String  value;

    NetWorkerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
