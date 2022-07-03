package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbPurchaseForm;
import com.zjjzfy.pojo.TbPurchaseFormExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjjzfy.pojo.dto.DeliverDto;
import com.zjjzfy.pojo.dto.DeliverFormDto;
import com.zjjzfy.pojo.dto.PurchaseFormDto;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;



public interface TbPurchaseFormMapper extends PublicMapper<TbPurchaseForm> {
    List<Integer> selectPurchaseBySupplyOrgId(@Param("supplyOrgId")Integer supplyOrgId);

    List<PurchaseFormDto> selectPurchaseDto(@Param("purchaseIds")List<Integer> purchaseIds,
                                            @Param("status")Byte status,
                                            @Param("checkStatus")Byte checkStatus);

    /**
     * 供货方查看订货单列表
     * @param ids
     * @param checkStatus
     * @return
     */
    List<PurchaseFormDto> deliverSelectPurchaseDto(@Param("ids")List<Integer> ids,
                                            @Param("checkStatus")Byte checkStatus);


    /**
     * 通过供货方orgid查相应订货单
     * @param supplyOrgId
     * @return
     */
    List<PurchaseFormDto>  selectPurchaseFormBySupplyOrgId(@Param("supplyOrgId") Integer supplyOrgId, @Param("status") Byte status, @Param("purchaseBillo") String purchaseBillo);


    List<PurchaseFormDto> selectPurchaseFormByStatus(@Param("status")Integer status,
                                                    @Param("supplyOrgId")Integer supplyOrgId,
                                                    @Param("orgName") String orgName,
                                                     @Param("check")Boolean check,
                                                     @Param("purchaseFormId")Integer purchaseFormId);

    List<Map<String,Object>> selectPurchaseOrder(@Param("starTime") Date startTime, @Param("endTime") Date endTime, @Param("status") Byte status, @Param("orgId") Integer orgId);

}
