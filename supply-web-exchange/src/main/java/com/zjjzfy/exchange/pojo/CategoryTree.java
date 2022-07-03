package com.zjjzfy.exchange.pojo;

import com.zjjzfy.pojo.TbCategory;

import java.util.ArrayList;
import java.util.List;


public class CategoryTree {
    private TbCategory data;
    private List<CategoryTree> children;

    public CategoryTree(List<TbCategory> cates) {

    }

    public CategoryTree(TbCategory data) {
        this.data = data;
        this.children = new ArrayList<CategoryTree>();
    }

    public CategoryTree(TbCategory data, List<CategoryTree> children) {
        this.data = data;
        this.children = children;
    }

    public CategoryTree findCategoryTree(int id){
        if (this.getData().getId() == id) {
            return this;
        }
        if (this.getChildren() == null || this.getChildren().isEmpty() ) {
            return null;
        } else {
            for (CategoryTree ct :this.getChildren()) {
                CategoryTree resultNode = ct.findCategoryTree(id);
                if (resultNode != null ) {
                    return resultNode;
                }
            }
            return null;
        }

    }

    public TbCategory getData() {
        return data;
    }

    public void setData(TbCategory data) {
        this.data = data;
    }

    public List<CategoryTree> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTree> children) {
        this.children = children;
    }
}
