package com.zjjzfy.common.config;

/**
 * Created by yzx on 2019/3/18.
 */
public enum OrgType {
    //1 供货方 - 商户
    SUPPLIER(new Byte("1")),
    //2 买方 - 行社
    PURCHASER(new Byte("2")),
    //3 管理方 -- 平台
    PLATFORM(new Byte("3"));

    private Byte type;

    OrgType(Byte type) {
        this.type = type;
    }

    public byte getType(){
        return type;
    }
}
