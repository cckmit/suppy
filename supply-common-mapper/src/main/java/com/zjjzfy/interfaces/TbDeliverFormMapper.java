package com.zjjzfy.interfaces;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbRepository;
import com.zjjzfy.pojo.dto.DeliverFormDto;
import com.zjjzfy.pojo.dto.DeliverFormExampleDto;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TbDeliverFormMapper extends PublicMapper<TbDeliverForm> {

    List<TbDeliverForm> selectSellerDelivers(@Param("status") Byte status, @Param("sellerId") Integer sellerId, @Param("settleStatus") Byte settleStatus, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<TbDeliverForm> selectPurchaseDelivers(@Param("status") Byte status, @Param("purchaseId") Integer purchaseId, @Param("settleStatus") Byte settleStatus, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> statisticsPriceGroupBySettleStatus(@Param("sellerId") Integer sellerId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> statisticsPriceGroupBySettleStatusBank(@Param("purchaseId") Integer purchaseId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> statisticsPriceGroupByStatus(@Param("sellerId") Integer sellerId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> statisticsPriceGroupByStatusBank(@Param("purchaseId") Integer purchaseId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Integer branchLiquidationOrders(@Param("ids") List<Integer> ids, @Param("sellerId") Integer sellerId);

    Integer branchLiquidationOrdersBank(@Param("ids") List<Integer> ids, @Param("purchaseId") Integer purchaseId);

    Integer trueLiquidationOrders(@Param("ids") List<Integer> ids, @Param("sellerId") Integer sellerId, @Param("managerId") Integer managerId);

    Integer trueLiquidationOrdersBank(@Param("ids") List<Integer> ids, @Param("purchaseId") Integer purchaseId, @Param("managerId") Integer managerId);

    BigDecimal liquidationAmount(@Param("ids") List<Integer> ids);

    /**
     * 通过自定义对象查询发货单
     *
     * @return
     */
    List<DeliverFormDto> selectByDeliverFormExampleDto();


    /**
     * 重置供货方库存
     * @return
     */
    List<TbRepository> queryDeliverRepository();

    /**
     * 重置采购方库存
     * @return
     */
    List<TbRepository> queryPurchaseRepository();

    /**
     * 查询发货单 加上 订货方机构名称
     * @return
     */
    List<Map<String,Object>> selectDeliveFormById(@Param("purchaseId") Integer purchaseId,@Param("supplyorgId") Integer supplyorgId,@Param("status") Byte status);

    List<Map<String,Object>> selectDeliverCount(@Param("supplyorgId") Integer supplyorgId);
}