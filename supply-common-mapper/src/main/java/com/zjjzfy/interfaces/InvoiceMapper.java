package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbInvoice;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InvoiceMapper extends PublicMapper<TbInvoice> {

    // 供货商和平台 全部发票信息
    List<Map<String, Object>> queryAllInvoiceByClass(@Param("status") Byte status, @Param("sellerId") Integer sellerId, @Param("settleStatus") Byte settleStatus, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //行社跟平台
    List<Map<String, Object>> queryBankInvoice(@Param("status") Byte status, @Param("sellerId") Integer sellerId, @Param("settleStatus") Byte settleStatus, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //查询未发发票总额 平台跟供货商
    Integer queryInvoiceTotal(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sellerId") Integer sellerId);

    //改变 平台跟行社 状态  传入发货单号   改变settle_status 改变供货商发票状态
    Integer changeBankInvoiceState(@Param("billno") String billno, @Param("state") Integer state);

    //改变 平台跟供货商 状态  传入发货单号   改变settle_status 改变供货商发票状态
    Integer changeSupplyInvoiceState(@Param("billno") String billno, @Param("state") Integer state);

    //查看发票记录
    List<Map<String, Object>> queryInvoiceHistory(@Param("startTime") Date starTime, @Param("endTime") Date endTime, @Param("checkType") Integer checkType, @Param("supplier") String supplier, @Param("invoice_id") String invoice_id,@Param("applicaterId")Integer applicaterId);

    //模糊查询
    List<TbInvoice> queryInvoiceLikeId(@Param("invoiceId") String invoiceId, @Param("supplier") String supplier);

    //查询所有供货商
    List<TbOrgInfo> querySellersAll(@Param("type") Integer type);

    //统计全部未开发票 行社和平台
    Integer querybankInvoiceTotal(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sellerid") Integer sellerid);

    // 查询全部待审核的发票
    List<Map<String, Object>> queryInvoicCheck(@Param("settleStatus") Byte settleStatus, @Param("supplierId") Integer supplierId, @Param("settleStatusBank") Byte settleStatusBank, @Param("purchaseId") Integer purchaseId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    List<Map<String, Object>> queryInvoiceCheck2(@Param("sellerId") Integer sellerId,@Param("applicater") Integer applicater, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

     //更新tb_deliver_form 更新时间
    void updateUpdateDate(@Param("billno") String billno);

    //改变 tb_voice state
    void changeInvoiceState(@Param("billno") String billo, @Param("state") Integer state);
}
