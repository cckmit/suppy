package com.zjjzfy.exchange.pojo;

/**
 *
 * @author      jackshenonly
 * @description 类说明:库存查询，返回对象
 * @date        2020-05-15 14:11
 */
public class RepositoryReturnBody {
    /**
     * 礼品id
     */
    private Integer productId;
    /**
     * 数量
     */
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
