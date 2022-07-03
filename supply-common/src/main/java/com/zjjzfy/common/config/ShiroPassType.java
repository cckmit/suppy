package com.zjjzfy.common.config;

/**
 * @author yzx
 */

public enum ShiroPassType {
    /**
     * 密码登录
     */
    PASSWORD("PASSWORD"),
    /**
     * 免密登录
     */
    NOPASSWD("NOPASSWD");

    private String type;

    ShiroPassType(String status) {
        this.type = status;
    }

    public String getType() {
        return type;
    }
}