package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.DeliverSettleStatus;
import com.zjjzfy.common.config.DeliverStatus;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.LiquidationOrderService;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mgt/liquidation")
public class LiquidationController {

    @Autowired
    private DeliverService deliverService;
    @Autowired
    private LiquidationOrderService liquidationOrderService;
    @Autowired
    private UserService userService;

    @RequestMapping("selectSellerDelivers")
    public LayuiData selectSellerDelivers(String ds, Integer sellerId, String dss, Date startTime, Date endTime, Integer page, Integer limit) {
        DeliverStatus status = null;
        if (ds.length() != 0) {
            status = DeliverStatus.valueOf(ds);
        }

        DeliverSettleStatus settleStatus = null;
        if (dss.length() != 0) {
            settleStatus = DeliverSettleStatus.valueOf(dss);
        }

        SupplyResult result = deliverService.selectSellerDelivers(status, sellerId, settleStatus, startTime, endTime, page, limit);

        LayuiData data = new LayuiData();
        if (result.isOK()) {
            PageInfo pageInfo = (PageInfo) result.getData();
            data.setCode(200);
            data.setCount(pageInfo.getTotal());
            data.setData(pageInfo.getList());
        } else {
            data.setCode(0);
        }

        return data;
    }

    @RequestMapping("selectPurchaseDelivers")
    public LayuiData selectPurchaseDelivers(String ds, Integer purchaseId, String dss, Date startTime, Date endTime, Integer page, Integer limit) {
        DeliverStatus status = null;
        if (ds.length() != 0) {
            status = DeliverStatus.valueOf(ds);
        }

        DeliverSettleStatus settleStatus = null;
        if (dss.length() != 0) {
            settleStatus = DeliverSettleStatus.valueOf(dss);
        }

        SupplyResult result = deliverService.selectPurchaseDelivers(status, purchaseId, settleStatus, startTime, endTime, page, limit);

        LayuiData data = new LayuiData();
        if (result.isOK()) {
            PageInfo pageInfo = (PageInfo) result.getData();
            data.setCode(200);
            data.setCount(pageInfo.getTotal());
            data.setData(pageInfo.getList());
        } else {
            data.setCode(0);
        }

        return data;
    }

    @RequestMapping("statisticsPriceGroupBySettleStatus")
    public SupplyResult statisticsPriceGroupBySettleStatus(Integer sellerId, Date startTime, Date endTime) {
        return deliverService.statisticsPriceGroupBySettleStatus(sellerId, startTime, endTime);
    }

    @RequestMapping("statisticsPriceGroupBySettleStatusBank")
    public SupplyResult statisticsPriceGroupBySettleStatusBank(Integer purchaseId, Date startTime, Date endTime) {
        return deliverService.statisticsPriceGroupBySettleStatusBank(purchaseId, startTime, endTime);
    }

    @RequestMapping("statisticsPriceGroupByStatus")
    public SupplyResult statisticsPriceGroupByStatus(Integer sellerId, Date startTime, Date endTime) {
        return deliverService.statisticsPriceGroupByStatus(sellerId, startTime, endTime);
    }

    @RequestMapping("statisticsPriceGroupByStatusBank")
    public SupplyResult statisticsPriceGroupByStatusBank(Integer purchaseId, Date startTime, Date endTime) {
        return deliverService.statisticsPriceGroupByStatusBank(purchaseId, startTime, endTime);
    }

    @RequestMapping("branchLiquidationOrders")
    public SupplyResult branchLiquidationOrders(@RequestParam("ids[]") ArrayList<Integer> ids, Integer sellerId) {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        try {
            return liquidationOrderService.branchLiquidationOrders(ids, sellerId, user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(1, e.getMessage());
        }
    }

    @RequestMapping("branchLiquidationOrdersBank")
    public SupplyResult branchLiquidationOrdersBank(@RequestParam("ids[]") ArrayList<Integer> ids, Integer purchaseId) {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        try {
            return liquidationOrderService.branchLiquidationOrdersBank(ids, purchaseId, user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(1, e.getMessage());
        }
    }

    @RequestMapping("trueLiquidationOrders")
    public SupplyResult trueLiquidationOrders(@RequestParam("ids[]") ArrayList<Integer> ids, Integer sellerId) {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        try {
            return liquidationOrderService.trueLiquidationOrders(ids, sellerId, user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(1, e.getMessage());
        }
    }

    @RequestMapping("trueLiquidationOrdersBank")
    public SupplyResult trueLiquidationOrdersBank(@RequestParam("ids[]") ArrayList<Integer> ids, Integer purchaseId) {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        try {
            return liquidationOrderService.trueLiquidationOrdersBank(ids, purchaseId, user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            return SupplyResult.build(1, e.getMessage());
        }
    }
}
