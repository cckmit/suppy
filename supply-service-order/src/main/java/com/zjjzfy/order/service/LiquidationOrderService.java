package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;

import java.util.List;

public interface LiquidationOrderService {

    /*总行发起清算 ids发货单ID*/
    SupplyResult branchLiquidationOrders(List<Integer> ids, Integer sellerId, Integer managerId);

    SupplyResult branchLiquidationOrdersBank(List<Integer> ids, Integer purchaseId, Integer managerId);

    /*总行确认清算 ids发货单ID*/
    SupplyResult trueLiquidationOrders(List<Integer> ids, Integer sellerId, Integer managerId);

    SupplyResult trueLiquidationOrdersBank(List<Integer> ids, Integer purchaseId, Integer managerId);
}
