package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCategoryMapper extends PublicMapper<TbCategory> {

    List<TbCategory> getCategoryList(Integer parentId,Integer ctgrClass);

    List<TbCategory> getCategoryListByIndex(Integer parentId,Integer ctgrClass);

    List<TbCategory> queryOneClass();

    List<TbCategory> queryTwoClass(Integer id);

    List<TbCategory> getCategoryListSp(Integer parentId, Integer ctgrClass);

    List<TbCategory> getCategoryListSpByIndex(Integer parentId, Integer ctgrClass);

    List<TbCategory> getCategoryListTitle(Integer parentId, Integer ctgrClass);

    List<TbCategory> getCategoryListTitleByIndex(Integer parentId, Integer ctgrClass);

    List<TbCategory> selectByCtgrClass(@Param("ctgrClass") Integer ctgrClass);
}