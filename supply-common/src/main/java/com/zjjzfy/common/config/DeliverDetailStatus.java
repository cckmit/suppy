package com.zjjzfy.common.config;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/6
 * Time: 16:59
 */
public enum DeliverDetailStatus {
    /** 未完成*/
    UNDO(new Byte("0")),
    /** 已完成*/
    DONE(new Byte("1"))
    ;


    private Byte value;

    DeliverDetailStatus(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }
}
