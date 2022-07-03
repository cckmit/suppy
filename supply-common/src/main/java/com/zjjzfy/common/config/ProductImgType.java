package com.zjjzfy.common.config;

/**
 * 货品图片类型
 */
public enum ProductImgType {

    REVEAL("0"),//商品展示图
    DETAIL("1");//商品详情图

    /**
     * 货品图片类型
     */
    private String type;

    ProductImgType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
