package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2019/11/20 上午9:45
 */
public interface HomeMapper {

    /**
     * 昨天订单总价 数量
     * @param status
     * @return
     */
     Map countOrderYester(@Param("status") Byte status, @Param("supplierId") Integer supplierId, @Param("applicaterId") Integer applicaterId);

     Map countYesterOrdered(@Param("supplierId") Integer supplierId, @Param("applicaterId") Integer applicaterId);

     Map countWaitInvoice(@Param("state") Integer state, @Param("supplierId") Integer supplierId, @Param("applicaterId") Integer applicaterId);

     Map countWaitOrder(@Param("state") Byte state, @Param("supplierId") Integer supplierId, @Param("applicaterId") Integer applicaterId);

    /**
     * 图标统计发货单数据
     * @return
     */
     List<Map<String, Object>> countOrderData();

}
