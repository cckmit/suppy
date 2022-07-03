package com.zjjzfy.exchange.pojo;

import java.util.List;

/**
 *
 * @author      jackshenonly
 * @description 类说明 订单提交主体对象
 *               产品编号和礼品数量依次对应
 * @date        2020-01-07 17:47
 */
public class OrderSubmitBody {

    private List<Integer> productIds;
    private List<Integer> counts;
    private String branchno;

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }
}
