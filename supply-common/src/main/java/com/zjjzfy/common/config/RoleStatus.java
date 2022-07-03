package com.zjjzfy.common.config;

/**
 * 角色状态
 *
 * @author yzx
 */
public enum RoleStatus {

    /**
     * 可用
     */
    ON(new Byte("1")),
    /**
     * 停用
     */
    OFF(new Byte("0"));

    /**
     * 角色状态
     */
    private Byte status;

    RoleStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }
}
