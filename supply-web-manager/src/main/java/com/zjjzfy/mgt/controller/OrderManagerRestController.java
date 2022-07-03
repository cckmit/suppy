package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.mgt.config.SupplyProperties;
import com.zjjzfy.order.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author zyx 订单管理
 * @date 2020/5/6 下午3:05
 */
@RequestMapping("/mgt")
@RestController
@PropertySource("application.yml")
public class OrderManagerRestController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private SupplyProperties supplyProperties;

    @RequestMapping("selectPurchaseOrder")
    public SupplyResult selectPurchaseOrder(Integer pageNo, Integer pageSize, Date startTime,Date endTime,Integer status,Integer orgId){
        return purchaseService.selectPurchaseOrder(pageNo,pageSize,startTime,endTime,status,orgId);
    }

    /**
     * 作废订货单
     *
     * @param formId
     * @return
     */
    @RequestMapping("/invalidOrder")
    public SupplyResult invalidOrder(Integer formId, String remark) {
        SupplyResult result = null;
        synchronized (this) {
            result = purchaseService.invalidOrder(formId, remark, supplyProperties.getOrderCheck());
        }
        return result;
    }

    /**
     * 预购单查询
     * @param pageNo
     * @param pageSize
     * @param
     * @param
     * @param orgId
     * @return
     */
    @RequestMapping("selectWillOrder")
    public SupplyResult selectWillOrder(Integer pageNo,Integer pageSize,Date startTime,Date endTime,Integer orgId){
        PageInfo<Map<String,Object>> list = purchaseService.selectWillPurchaseListByOrgid(orgId,startTime,endTime,pageNo,pageSize);
        return SupplyResult.ok(list);
    }




}
