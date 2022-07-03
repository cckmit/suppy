package com.zjjzfy.order.service;

import com.zjjzfy.common.config.DeliverSettleStatus;
import com.zjjzfy.common.config.DeliverStatus;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbInvoice;

import java.util.Date;
import java.util.List;

/**
 * 发票管理
 */
public interface InvoiceService {

   // SupplyResult queryInvoicByClass(DeliverStatus ds, Integer sellerId, DeliverSettleStatus dss, Date startTime, Date endTime, Integer pageNo, Integer pageSize);

    //查询未发发票总价  1统计行社和平台 2平台和供货
    SupplyResult queryInvoiceTotal(Date startTime,Date endTime,Integer type,Integer sellerId);

    //增加发票
    SupplyResult addInvoice(Double total, Integer supplierId,Integer state,String invoiceId,String type,String deliver_billno,Integer applicater);

    //改变发票状态
    void changeInvoiceState(String billo,Integer state,String changType,double total,Integer sellerId,Integer applicater);

    //查看发票历史
    LayuiData queryInvoiceHistory(Date startTime, Date endTime,Integer checkType,String likeSearch, Integer page, Integer limit,Integer applicaterId);

    //模糊查询发票  传入编号或者供货商
    SupplyResult queryInvoiceLikeId(String invoiceId);

    //查询全部菜单选项
    SupplyResult queryPermission();

    //查询所有供货商 1供货 2行社   3平台
    SupplyResult querySellersAll(Integer type);

    //查询行社跟平台的未开票记录
    LayuiData queryBankInvoic(Integer sellerId, Date startTime, Date endTime, Integer page, Integer limit);

    //查询供货商跟平台的未开票记录
    LayuiData querySupplyInvoic(Integer sellerId, Date startTime, Date endTime, Integer page, Integer limit);

    //查询审核
    LayuiData queryInvoiceCheck(Integer type,Integer sellerId, Date startTime, Date endTime, Integer page, Integer limit);

    //改变tb_invoice 的state
    void changeTbInvoice(String billos,Integer state);

}
