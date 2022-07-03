package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.DeliverSettleStatus;
import com.zjjzfy.common.config.DeliverStatus;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.InvoiceService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zyx
 * @date 2019/11/8 上午11:40
 */
@RestController
@RequestMapping("/mgt")
public class InvoiceRestController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserService userService;

    /**
     * 查询 发票总价
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/queryInvoiceTotal")
    public SupplyResult queryInvoiceTotal(Date startTime, Date endTime,Integer type,Integer sellerId){
        return invoiceService.queryInvoiceTotal(startTime,endTime,type,sellerId);
    }

    /**
     * 根据供货商分类显示 代开发票 总发票...
     * sellerid  供货商id
     * status 订单状态 0待确认 1确认 2拒绝
     * settleStatus 平台和供货商关系  0未清算(未开发票) 1 平台已发起 2 清算完成
     *
     */
   /* @GetMapping("/queryInvoiceByClass")
    public LayuiData queryInvoiceByClass(String ds, Integer sellerId, String dss, Date startTime, Date endTime, Integer page, Integer limit){
        SupplyResult result = invoiceService.queryInvoicByClass(status, sellerId, settleStatus, startTime, endTime, page, limit);
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
    }*/

    //查看行社跟平台发票
    @GetMapping("/queryBankInvoice")
    public LayuiData queryBankInvoice( Integer sellerId,  Date startTime, Date endTime, Integer page, Integer limit){
        return invoiceService.queryBankInvoic(sellerId, startTime, endTime, page, limit);
    }

    //平台跟供货商发票
    @GetMapping("/querySupplyInvoice")
    public LayuiData querySupplyInvoice(Integer sellerId,  Date startTime, Date endTime, Integer page, Integer limit){
        return invoiceService.querySupplyInvoic(sellerId, startTime, endTime, page, limit);
    }


    @GetMapping("/queryInvoiceHistory")
    public LayuiData queryInvoiceHistory(Date startTime,Date endTime,Integer checkType,String likeSearch, Integer page, Integer limit){
        Integer applicaterId = userService.getOrg().getId();
        // 平台能看所有发票
        if(userService.getOrg().getType() == 3){
            applicaterId = null;
        }
        return invoiceService.queryInvoiceHistory(startTime, endTime,checkType,likeSearch,page,limit,applicaterId);
    }

   /* @GetMapping("/addInvoice")
    public SupplyResult addInvoice(Double total, String supplierName,String type){
        return invoiceService.addInvoice(total,supplierName,type);
    }*/

    @GetMapping("/changeInvoiceState")
    public SupplyResult changeInvoiceState(String billo,Integer state,String changeType,double total,Integer supplierId,Integer applicater){
        invoiceService.changeInvoiceState(billo,state,changeType,total,supplierId,applicater);
        return SupplyResult.ok();
    }

    @GetMapping("/queryPermission")
    public SupplyResult queryPermission(){
        return invoiceService.queryPermission();
    }

    @GetMapping("/querySellerAll")
    public SupplyResult querySellerAll(Integer type){
        return  invoiceService.querySellersAll(type);
    }

    @GetMapping("/queryInvoiceCheck")
    public LayuiData queryInvoiceCheck(Integer type, Integer sellerId,  Date startTime, Date endTime, Integer page, Integer limit){
        return invoiceService.queryInvoiceCheck(type,sellerId,startTime,endTime,page,limit);
    }

    @GetMapping("/changeInvoice")
    public void changeInvoice(String billos,Integer state){
         invoiceService.changeTbInvoice(billos,state);
    }


}
