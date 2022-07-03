package com.zjjzfy.common.config;

/**
 * @author: hsmz
 * @date: 2019/3/21 下午4:42
 */
public enum ProductLabelStatus {

    /**
     * 新品上架
     */
    NEWPRODUCT(new Byte("0")),
    /**
     * 热卖推荐
     */
    HOTPRODUCT(new Byte("1"));


    /**
     * 发货单状态
     */
    private Byte status;

    ProductLabelStatus(Byte status) {
        this.status = status;
    }

    public byte getStatus(){
        return status;
    }
}
