package com.zjjzfy.product.service.impl.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbCategoryMapper;
import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pojo.TbCategoryExample;
import com.zjjzfy.product.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TbCategoryMapper categoryMapper;

    @Override
    public int insert(String name, int parentId, Byte isEnd) {
        TbCategory category = new TbCategory();
        category.setCategoryName(name);
        if (0 < parentId) {
            category.setParentId(parentId);
            category.setCategoryLevel(categoryMapper.selectByPrimaryKey(parentId).getCategoryLevel() + 1);
        } else {
            category.setParentId(0);
            category.setCategoryLevel(0);
        }
        category.setIsEnd(isEnd);
        Date date = new Date();
        category.setCreateDate(date);
        category.setUpdateDate(date);
        category.setStatus((byte) 0);
        //
        category = setInfo(category);

        return categoryMapper.insert(category);
    }
    public TbCategory setInfo(TbCategory tc){
        tc.setCtgrClass(1);
        return tc;
    }
    @Override
    public List<TbCategory> getCategoryList(Integer parentId,Integer ctgrClass) {
        if (parentId == null || parentId < 0) {
            return null;
        }
        return categoryMapper.getCategoryList(parentId,ctgrClass);
    }

    @Override
    public List<TbCategory> getCategoryListTitle(Integer parentId, Integer ctgrClass) {
        if (parentId == null || parentId < 0) {
            return null;
        }
        return categoryMapper.getCategoryListTitle(parentId,ctgrClass);
    }
    @Override
    public List<TbCategory> getCategoryListTitleByIndex(Integer parentId,Integer ctgrClass) {
        if (parentId == null || parentId < 0) {
            return null;
        }
        return categoryMapper.getCategoryListTitleByIndex(parentId,ctgrClass);
    }
    @Override
    public List<TbCategory> getCategoryListByIndex(Integer parentId,Integer ctgrClass) {
        if (parentId == null || parentId < 0) {
            return null;
        }
        return categoryMapper.getCategoryListByIndex(parentId,ctgrClass);
    }
    @Override
    public List<TbCategory> getCategoryListSp(Integer parentId,Integer ctgrClass) {
        if (parentId == null || parentId < 0) {
            return null;
        }
        return categoryMapper.getCategoryListSp(parentId,ctgrClass);
    }
    @Override
    public List<TbCategory> getCategoryListSpByIndex(Integer parentId,Integer ctgrClass) {
        if (parentId == null || parentId < 0) {
            return null;
        }
        return categoryMapper.getCategoryListSpByIndex(parentId,ctgrClass);
    }
    @Override
    public SupplyResult selectCategory(TbCategoryExample example, Integer id) {
        if (id != null && id > 0) {
            return SupplyResult.ok(categoryMapper.selectByPrimaryKey(id));
        } else {
            return SupplyResult.ok(categoryMapper.selectByExample(example));
        }
    }




    @Override
    public List<TbCategory> queryOneClass() {
        return categoryMapper.queryOneClass();
    }

    @Override
    public List<TbCategory> queryTwoClass(Integer id) {
        return categoryMapper.queryTwoClass(id);
    }

    @Override
    public SupplyResult doCategoryEdit(TbCategory tc) {
        TbCategory tcDb = categoryMapper.selectByPrimaryKey(tc.getId());
        tcDb.setCategoryName(tc.getCategoryName());
        tcDb.setIsEnd(tc.getIsEnd());
        return SupplyResult.ok(categoryMapper.updateByPrimaryKey(tcDb));
    }

    @Override
    public SupplyResult doCategoryDlt(Integer id) {
        return SupplyResult.ok(categoryMapper.deleteByPrimaryKey(id));
    }

    @Override
    public SupplyResult doAllCategoryEdit(List<TbCategory> pageList,List<TbCategory> dbList,Integer ctgrClass) {
        //name status
        //set rank
        for (int i = 0,j= pageList.size();i<j;i++){
            TbCategory tc  = pageList.get(i);
            tc = setValue(tc);
            tc.setCtgrRank(i);
            tc.setUpdateDate(new Date());
            categoryMapper.updateByPrimaryKey(tc);
        }
        //与数据库数据为主的遍历
        for (int i = 0,j= dbList.size();i<j;i++){
            TbCategory e = dbList.get(i);
            Integer index = exitOrNot(e,pageList);
            if(index!=null){
                //更新
                categoryMapper.updateByPrimaryKey(pageList.get(index));
            }else{
                //数据库里有但界面没有
                //删除
                e.setStatus((byte)1);
                categoryMapper.updateByPrimaryKey(e);
            };
        }
        //与界面数据为主的遍历
        for (int i = 0,j= pageList.size();i<j;i++){
            TbCategory e = pageList.get(i);
            Integer index = exitOrNot(e,pageList);
            if(index!=null){
                //更新
            }else{
                //界面里有但数据库没有
                //插入
                categoryMapper.insert(e);
            };
        }
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult getCategoryByCtgrClass(Integer ctgrClass) {
        List<TbCategory> list = categoryMapper.selectByCtgrClass(ctgrClass);
        TbCategory tc = new TbCategory();
        if(list==null&&list.size()==0){
            if(ctgrClass==1){
                //新品上架
                tc.setCategoryName("新品上架");
            }
            if(ctgrClass==2){
                //热卖推荐
                tc.setCategoryName("活动专区");
            }
            return SupplyResult.build(200,"",tc);
        }else{
            if(list.size()>1){
                System.err.println("getCategoryByCtgrClass获取数量过多:"+list.size());
            }
            return SupplyResult.build(200,"",list.get(0));
        }
    }

    private Integer exitOrNot(TbCategory ta, List<TbCategory> list) {
        if(list!=null){
            for (int i = 0,j= list.size();i<j;i++){
                Integer id = list.get(i).getId()==null ?-1:list.get(i).getId();
                if(id.equals(ta.getId())){
                    return i;
                }
            }
        }
        return null;
    }
    TbCategory setValue(TbCategory tc){
        if(tc.getId()==null){
            tc.setCtgrClass(1);
            tc.setCategoryLevel(0);
            tc.setParentId(0);
            tc.setIsEnd((byte) 0);
            tc.setCreateDate(new Date());
        }
        return tc;
    }
}
