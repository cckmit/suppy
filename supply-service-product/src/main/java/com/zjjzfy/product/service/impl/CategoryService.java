package com.zjjzfy.product.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pojo.TbCategoryExample;

import java.util.List;

public interface CategoryService {

    int insert(String name, int parentId, Byte isEnd);

    /**
     * 获取子级分类
     *
     * @param parentId
     * @return
     */
    /**
     *
     * @param parentId
     * @param ctgrClass 0代表商品,1代表新品热卖等,2代表自己设计的
     * @return
     */
    List<TbCategory> getCategoryList(Integer parentId,Integer ctgrClass);

    List<TbCategory> getCategoryListTitle(Integer parentId,Integer status);

    List<TbCategory> getCategoryListTitleByIndex(Integer parentId,Integer status);

    List<TbCategory> getCategoryListSp(Integer parentId,Integer ctgrClass);

    List<TbCategory> getCategoryListSpByIndex(Integer parentId,Integer ctgrClass);
    /**
     *
     * @param parentId
     * @param ctgrClass 0代表自有商品类别,1代表自定义类别
     * @return
     */
    List<TbCategory> getCategoryListByIndex(Integer parentId,Integer ctgrClass);

    SupplyResult selectCategory(TbCategoryExample example, Integer id);


    List<TbCategory> queryOneClass();

    List<TbCategory> queryTwoClass(Integer id);

    /**
     * 商品专题的修改
     * @param tc
     */
    SupplyResult doCategoryEdit(TbCategory tc);

    /**
     * 商品专题的删除
     * @param id
     */
    SupplyResult doCategoryDlt(Integer id);
    /**
     * 商品专题的修改(rank)
     * @param pageist
     */
    SupplyResult doAllCategoryEdit(List<TbCategory> pageist,List<TbCategory> dbList,Integer ctgrClass);

    SupplyResult getCategoryByCtgrClass(Integer ctgrClass);
}
