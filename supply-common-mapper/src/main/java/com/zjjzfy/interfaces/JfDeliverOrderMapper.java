package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.JfDeliverOrder;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JfDeliverOrderMapper extends PublicMapper<JfDeliverOrder> {

    /**
     * 网点自助兑换 总数量 总价格
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String,Object>> selectExchangeOrderCount(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("type") Integer type,@Param("orgId") Integer orgId);

    /**
     * 查询某一网点兑换详情
     * @param startTime
     * @param endTime
     * @param orgId
     * @return
     */
    List<Map<String,Object>> selectExchangeOrderDataByOrgId(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("orgId") Integer orgId,@Param("type") Integer type);

    /**
     * 查询加锁
     * @param id
     * @return
     */
    JfDeliverOrder selectByPrimaryKeyForUpdate(Integer id);
}