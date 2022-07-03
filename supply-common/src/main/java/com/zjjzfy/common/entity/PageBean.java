package com.zjjzfy.common.entity;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author zyx
 * @date 2020/3/3 上午11:18
 */
@Data
public class PageBean {
    // 当前页
    private Integer pageNo;
    // 每页显示的总条数
    private Integer pageSize;
    // 总条数
    private Integer total;
    // 是否有下一页
    private Integer isNext;
    // 总页数
    private Integer totalPage;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> data;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getIsMore() {
        return isNext;
    }

    public void setIsMore(Integer isNext) {
        this.isNext = isNext;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return data;
    }

    public void setItems(List<T> items) {
        this.data = items;
    }
}
