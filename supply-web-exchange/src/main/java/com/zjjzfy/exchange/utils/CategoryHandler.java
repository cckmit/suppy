package com.zjjzfy.exchange.utils;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.exchange.pojo.CategoryTree;
import com.zjjzfy.pojo.TbCategory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 将分类处理成指定结构。
 */
@Slf4j
public class CategoryHandler {

    /**
     * @param categories 按category.parent_id,category.ctgr_rank 升序排列。
     * @return
     */
    public static CategoryTree handle(List<TbCategory> categories) {
        TbCategory tbroot = new TbCategory();
        tbroot.setId(0);
        tbroot.setCategoryName("分类根节点");
        CategoryTree root = new CategoryTree(tbroot);
        for (TbCategory ca : categories) {

            if (ca.getId() == 48) {
                log.info("test");
            }
            CategoryTree tmp = root.findCategoryTree(ca.getParentId());
            try {
                tmp.getChildren().add(new CategoryTree(ca));
            }catch (Exception e){
                log.error("菜单层级异常,该菜单可能没有父级节点，跳过。",e);
                log.debug(JSON.toJSONString(ca));
            }
        }
        return root;
    }


}
