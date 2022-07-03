package com.zjjzfy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.DeliverSettleStatus;
import com.zjjzfy.common.config.DeliverStatus;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.InvoiceMapper;
import com.zjjzfy.interfaces.TbPermissionMapper;
import com.zjjzfy.order.service.InvoiceService;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbInvoice;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbPermission;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class InvoiceServicelmpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private TbPermissionMapper permissionMapper;

/*    @Override
    public SupplyResult queryInvoicByClass(DeliverStatus ds, Integer sellerId, DeliverSettleStatus dss, Date startTime, Date endTime, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> delivers = invoiceMapper.queryAllInvoiceByClass(status, sellerId, settleStatus, startTime, endTime);
        return SupplyResult.ok(new PageInfo(delivers));
    }*/



    @Override
    public SupplyResult queryInvoiceTotal(Date startTime,Date endTime,Integer type,Integer sellerId) {
        if(type == 1 ){
            return SupplyResult.ok(invoiceMapper.queryInvoiceTotal(startTime,endTime,sellerId));
        }else if(type == 2){
            return SupplyResult.ok(invoiceMapper.querybankInvoiceTotal(startTime,endTime,sellerId));
        }else{
            return SupplyResult.ok(0);
        }

    }

    @Override
    public SupplyResult addInvoice(Double total, Integer supplierId,Integer state,String invoiceId,String type,String deliver_billno,Integer applicater) {
        TbInvoice invoice = new TbInvoice();
        invoice.setApplicationDate(new Date());
        invoice.setTotal(total);
        if (type == null){
            invoice.setInvoiceType("普通发票");
        }else{
            invoice.setInvoiceType(type);
        }
        invoice.setState(state);
        invoice.setInvoiceId(invoiceId);
        invoice.setDeliverBillno(deliver_billno);
        invoice.setApplicaterId(applicater);
        invoice.setSupplierId(supplierId);
        int num = invoiceMapper.insert(invoice);
        return SupplyResult.ok();
    }
    @Override
    public void changeInvoiceState(String billo,Integer state,String changType,double total,Integer sellerId,Integer applicater) {
        String[] str = billo.split(",");
        if(changType.equals("bank")){
            for (String s : str) {
                invoiceMapper.changeBankInvoiceState(s,state);
                String invoiceId = UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
                this.addInvoice(total,sellerId,state,invoiceId,null,s,applicater);
                updateUpdateDate(s);
            }
        }else if(changType.equals("supply")){
            for (String s : str) {
                invoiceMapper.changeSupplyInvoiceState(s,state);
                String invoiceId = UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
                this.addInvoice(total,sellerId,state,invoiceId,null,s,applicater);
                updateUpdateDate(s);
            }
        }
    }

    @Override
    public LayuiData queryInvoiceHistory(Date startTime, Date endTime,Integer checkType,String likeSearch,Integer page,Integer limit,Integer applicaterId) {
        PageHelper.startPage(page, limit);
        if(checkType == 3){
            checkType = null;
        }

        List<Map<String, Object>> list = null;
        if(likeSearch.length() ==32 &&(likeSearch.getBytes().length == likeSearch.length()) ){
            //符合说明是uuid 即发票id
            list= invoiceMapper.queryInvoiceHistory(startTime,endTime,checkType,null,likeSearch,applicaterId);
        }else{
            list = invoiceMapper.queryInvoiceHistory(startTime,endTime,checkType,likeSearch,null,applicaterId);
        }
        SupplyResult result = SupplyResult.ok(new PageInfo(list));
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

    @Override
    public SupplyResult queryInvoiceLikeId(String invoiceId) {
        List<TbInvoice> list = new ArrayList<>();
        if (invoiceId.getBytes().length == invoiceId.length() && invoiceId.length() >= 32) {
            list = invoiceMapper.queryInvoiceLikeId(invoiceId,null);
        } else {
            list = invoiceMapper.queryInvoiceLikeId(null,invoiceId);
        }
        return SupplyResult.ok(list);
    }

    @Override
    public SupplyResult queryPermission() {
        List<TbPermission> list = permissionMapper.queryPermissionAll();
        return SupplyResult.ok(list);
    }

    @Override
    public SupplyResult querySellersAll(Integer type) {
        List<TbOrgInfo> list = invoiceMapper.querySellersAll(type);
        return SupplyResult.ok(list);
    }

    // sellerId 订货方机构号
    @Override
    public LayuiData queryBankInvoic(Integer sellerId, Date startTime, Date endTime, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Map<String, Object>> list = invoiceMapper.queryBankInvoice(new Byte("1"), sellerId, new Byte("0"), startTime, endTime);
        SupplyResult result = SupplyResult.ok(new PageInfo(list));
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

    @Override
    public LayuiData querySupplyInvoic(Integer sellerId, Date startTime, Date endTime, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Map<String, Object>> list = invoiceMapper.queryAllInvoiceByClass(new Byte("1"), sellerId, new Byte("0"), startTime, endTime);
        SupplyResult result = SupplyResult.ok(new PageInfo(list));
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

    @Override
    public LayuiData queryInvoiceCheck(Integer type,Integer sellerId, Date startTime, Date endTime, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Map<String, Object>> list = null;
       /* if(type == 1){
            list = invoiceMapper.queryInvoicCheck(new Byte("1"),sellerId,null,null,startTime,endTime);
        }else if(type == 3){
            list = invoiceMapper.queryInvoicCheck(null,null,new Byte("1"),sellerId,startTime,endTime);
        }*/
        if(type==1){
            list = invoiceMapper.queryInvoiceCheck2(sellerId,null,startTime,endTime);
        }else if(type ==2){
            list = invoiceMapper.queryInvoiceCheck2(null,sellerId,startTime,endTime);
        }
        SupplyResult result = SupplyResult.ok(new PageInfo(list));
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

    @Override
    public void changeTbInvoice(String billos,Integer state) {
        String[] str = billos.split(",");
        for (String s : str) {
            invoiceMapper.changeInvoiceState(s,state);
        }

    }

    public void updateUpdateDate(String billo){
        invoiceMapper.updateUpdateDate(billo);
    }

}
