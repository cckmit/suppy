package com.zjjzfy.common.config;

/**
 * Created by yzx on 2019/3/19.
 */
public enum UserState {
    //1 正常
    NORMAL(new Byte("1")),
    //2 禁用
    DISABLE(new Byte("0"));

    private Byte type;

    UserState(Byte type) {
        this.type = type;
    }

    public byte getType(){
        return type;
    }
}
