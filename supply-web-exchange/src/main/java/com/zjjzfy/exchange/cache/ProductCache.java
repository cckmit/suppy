package com.zjjzfy.exchange.cache;

import com.zjjzfy.interfaces.TbCategoryMapper;
import com.zjjzfy.interfaces.TbProductImgMapper;
import com.zjjzfy.interfaces.TbProductMapper;
import com.zjjzfy.pojo.TbCategory;
import com.zjjzfy.pojo.TbCategoryExample;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 13:55
 */
@Component
@Slf4j
public class ProductCache {
    private static ProductCache instance = null;

    @Value("${myconfig.product-refresh-interval}")
    private int interval;
    /**
     * 上次刷新时间。
     */
    private long refreshTime;
    /**
     * 只包含正常的一级分类。
     */
    private List<TbCategory> cates;
    /**
     * 只包含正常的所有分类
     */
    private List<TbCategory> catesAll;

    @Autowired
    private TbProductMapper tbProductMapper;
    @Autowired
    private TbProductImgMapper tbProductImgMapper;
    @Autowired
    private TbCategoryMapper tbCategoryMapper;


    @PostConstruct
    public void init(){
        setInstance(this);
        refresh();
    }

    public void refresh(){
        instance.setRefreshTime(System.currentTimeMillis());
        TbCategoryExample tce = new TbCategoryExample();
        tce.createCriteria().andStatusEqualTo(new Byte("0"));
        tce.setOrderByClause("parent_id asc,ctgr_class desc,ctgr_rank asc");
        List<TbCategory> listCates = tbCategoryMapper.selectByExample(tce);
        instance.setCatesAll(listCates);
        // 只选择正常的 ，1 级菜单
        instance.setCates(listCates.stream().filter(ca -> 0 == ca.getParentId()).collect(Collectors.toList()));
        log.info("一级分类个数：{}", ProductCache.instance.cates.size());
        log.info("所有分类个数：{}", ProductCache.instance.catesAll.size());
    }

    public static ProductCache getInstance() {
        long now = System.currentTimeMillis();
        if(now - instance.getRefreshTime() > instance.getInterval()){
            instance.refresh();
        }
        return instance;
    }

    public static void setInstance(ProductCache instance) {
        ProductCache.instance = instance;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public List<TbCategory> getCates() {
        return cates;
    }

    public void setCates(List<TbCategory> cates) {
        this.cates = cates;
    }

    public List<TbCategory> getCatesAll() {
        return catesAll;
    }

    public void setCatesAll(List<TbCategory> catesAll) {
        this.catesAll = catesAll;
    }
}
