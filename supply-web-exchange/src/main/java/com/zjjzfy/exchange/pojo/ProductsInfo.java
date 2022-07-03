package com.zjjzfy.exchange.pojo;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.pojo.TbProduct;

/**
 * 
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-01-07 23:58
 */
public class ProductsInfo {
    /**礼品分页信息*/
    private PageInfo<TbProduct> pageInfo ;
    public PageInfo<TbProduct> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<TbProduct> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
