package com.zjjzfy.exchange.pojo;

import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.TbProductImg;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 17:10
 */
public class ProductModel {
    private TbProduct product;
    /**
     * 详情图
     */
    private List<TbProductImg> imgs;
    /**
     * 展示图
     */
    private List<TbProductImg> reveal;
    private Integer stock;

    public TbProduct getProduct() {
        return product;
    }

    public void setProduct(TbProduct product) {
        this.product = product;
    }

    public List<TbProductImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<TbProductImg> imgs) {
        this.imgs = imgs;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<TbProductImg> getReveal() {
        return reveal;
    }

    public void setReveal(List<TbProductImg> reveal) {
        this.reveal = reveal;
    }
}
