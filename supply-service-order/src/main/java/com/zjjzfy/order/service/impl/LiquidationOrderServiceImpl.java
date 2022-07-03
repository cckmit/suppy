package com.zjjzfy.order.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.LiquidationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class LiquidationOrderServiceImpl implements LiquidationOrderService {

    @Autowired
    private DeliverService deliverService;

    /*private Lock lock = new ReentrantLock();*/

    @Transactional
    @Override
    public SupplyResult branchLiquidationOrders(List<Integer> ids, Integer sellerId, Integer managerId) {
        if (ids == null || ids.size() == 0) {
            return SupplyResult.build(1, "发起清算失败--请选择要清算的供货单");
        }

        if (sellerId == null) {
            return SupplyResult.build(2, "发起清算失败--请选择要清算的商户");
        }

        if (managerId == null) {
            return SupplyResult.build(3, "发起清算失败--登陆状态过期请重新登陆");
        }

        int count = deliverService.branchLiquidationOrders(ids, sellerId);
        if (count != ids.size()) {
            throw new RuntimeException("发起清算失败--部分供货单已被清算或已经发起或供货单与商户不匹配");
        }

        return SupplyResult.ok();
    }

    @Transactional
    @Override
    public SupplyResult branchLiquidationOrdersBank(List<Integer> ids, Integer purchaseId, Integer managerId) {
        if (ids == null || ids.size() == 0) {
            return SupplyResult.build(1, "发起清算失败--请选择要清算的供货单");
        }

        if (purchaseId == null) {
            return SupplyResult.build(2, "发起清算失败--请选择要清算的商户");
        }

        if (managerId == null) {
            return SupplyResult.build(3, "发起清算失败--登陆状态过期请重新登陆");
        }

        int count = deliverService.branchLiquidationOrdersBank(ids, purchaseId);
        if (count != ids.size()) {
            throw new RuntimeException("发起清算失败--部分供货单已被清算或已经发起或供货单与商户不匹配");
        }

        return SupplyResult.ok();
    }

    @Transactional
    @Override
    public SupplyResult trueLiquidationOrders(List<Integer> ids, Integer sellerId, Integer managerId) {

        if (ids == null || ids.size() == 0) {
            return SupplyResult.build(1, "清算完成确认失败--请选择要完成确认清算的供货单");
        }

        if (sellerId == null) {
            return SupplyResult.build(2, "清算完成确认失败--请选择要清算确认的商户");
        }

        if (managerId == null) {
            return SupplyResult.build(3, "清算完成确认失败--登陆状态过期请重新登陆");
        }

        int count = deliverService.trueLiquidationOrders(ids, sellerId, managerId);
        if (count != ids.size()) {
            throw new RuntimeException("清算完成确认失败--部分供货单已完成清算或供货单与商户不匹配");
        }

        return SupplyResult.ok();
    }

    @Transactional
    @Override
    public SupplyResult trueLiquidationOrdersBank(List<Integer> ids, Integer purchaseId, Integer managerId) {

        if (ids == null || ids.size() == 0) {
            return SupplyResult.build(1, "清算完成确认失败--请选择要完成确认清算的供货单");
        }

        if (purchaseId == null) {
            return SupplyResult.build(2, "清算完成确认失败--请选择要清算确认的行社");
        }

        if (managerId == null) {
            return SupplyResult.build(3, "清算完成确认失败--登陆状态过期请重新登陆");
        }

        int count = deliverService.trueLiquidationOrdersBank(ids, purchaseId, managerId);
        if (count != ids.size()) {
            throw new RuntimeException("清算完成确认失败--部分供货单已完成清算或供货单与行社不匹配");
        }

        return SupplyResult.ok();
    }
}
