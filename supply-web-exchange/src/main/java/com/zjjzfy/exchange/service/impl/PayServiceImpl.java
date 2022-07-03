package com.zjjzfy.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.config.DeliverOrderStatus;
import com.zjjzfy.common.config.DeliverOrderType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.StringUtil;
import com.zjjzfy.exchange.api.BusinessFactory;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.common.SessionKey;
import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.OrderPayBody;
import com.zjjzfy.exchange.pojo.OrderPayByNothingBody;
import com.zjjzfy.exchange.pojo.Param;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.PayService;
import com.zjjzfy.exchange.utils.RsaUtil;
import com.zjjzfy.interfaces.JfDeliverOrderMapper;
import com.zjjzfy.interfaces.TbNetworkerMapper;
import com.zjjzfy.order.service.ExchangeOrderService;
import com.zjjzfy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/5
 * Time: 14:28
 */
@Service
public class PayServiceImpl extends BaseService implements PayService{

    @Value("${client.public-key}")
    private String clientPublicKey;

    @Autowired
    private ExchangeOrderService exchangeOrderService;
    @Autowired
    private JfDeliverOrderMapper jfDeliverOrderMapper;
    @Autowired
    private TbNetworkerMapper tbNetworkerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplyResult pay(String billno, String paycode, HttpServletRequest request) throws Exception{
        //校验paycode
        if(null == paycode || !paycode.equals(request.getSession().getAttribute(SessionKey.PAY_CODE))){
            return SupplyResult.build(301,"支付页面已过期，请重新打开支付页面");
        }else{
            //使用过后，置空
            request.getSession().setAttribute(SessionKey.PAY_CODE,null);
        }

        JfDeliverOrder deliverOrder = exchangeOrderService.queryDeliverOrderByBillno(billno);
        if(deliverOrder == null){
            return SupplyResult.build(302,"订单异常，不能支付");
        }else if(!DeliverOrderStatus.UNPAID.getValue().equals(deliverOrder.getStatus())){
            return SupplyResult.build(303,"订单不是未支付状态，无需支付！");
        }
        //处理订单杆状态,支付失败，状态回滚
        deliverOrder.setStatus(DeliverOrderStatus.PAID.getValue());
        jfDeliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

        SupplyResult resultPay = BusinessFactory.build("TAIZHOU").pay(deliverOrder);

        if(resultPay.isOK()){
            return SupplyResult.build(200,"支付成功",resultPay.getData());
        }else {
            //支付失败，回滚
            throw new PayFailedException("支付失败：原因"+resultPay.getMsg());

        }

    }

    @Override
    public SupplyResult payByOcx(String billno, String paycode, String remark, HttpServletRequest request) throws Exception {
        //前端调用OCX支付成功后,对订单状态进行修改。并且记录ocx返回的信息。
        //校验paycode
        if(null == paycode || !paycode.equals(request.getSession().getAttribute(SessionKey.PAY_CODE))){
            return SupplyResult.build(301,"支付页面已过期，请重新打开支付页面");
        }else{
            //使用过后，置空
            request.getSession().setAttribute(SessionKey.PAY_CODE,null);
        }
        JfDeliverOrder deliverOrder = exchangeOrderService.queryDeliverOrderByBillno(billno);
        if(deliverOrder == null){
            return SupplyResult.build(302,"订单异常，不能支付");
        }else if(!DeliverOrderStatus.UNPAID.getValue().equals(deliverOrder.getStatus())){
            return SupplyResult.build(303,"订单不是未支付状态，无需支付！");
        }
        //处理订单杆状态,支付失败，状态回滚
        deliverOrder.setStatus(DeliverOrderStatus.PAID.getValue());
        deliverOrder.setRemark(remark);
        jfDeliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

        return SupplyResult.build(200,"支付通知成功",deliverOrder.getRemark());

    }

    @Override
    public SupplyResult payByAndroidPos(Param param) throws RsaCheckException {
        if(!RsaUtil.verify(param,clientPublicKey)){
            return SupplyResult.build(800,"是你吗？","");
        }
        OrderPayBody body = JSON.parseObject(param.getCs(),OrderPayBody.class);
        JfDeliverOrder deliverOrder = exchangeOrderService.queryDeliverOrderByBillno(body.getBillno());
        if(deliverOrder == null){
            return SupplyResult.build(302,"订单异常，不能支付");
        }else if(!DeliverOrderStatus.UNPAID.getValue().equals(deliverOrder.getStatus())){
            return SupplyResult.build(303,"订单不是未支付状态，无需支付！");
        }
        //处理订单状态,支付失败，状态回滚
        deliverOrder.setStatus(DeliverOrderStatus.PAID.getValue());
        deliverOrder.setRemark(body.getRemark());
        jfDeliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

        // 自动发放 [AUTO],如果自动发放失败，提示在返回参数的msg中；需要增加回传柜员uid
        String extraMsg = autoOut(deliverOrder,body.getUid());
        //自动发放 结束
        String imsg = "支付通知成功![自动发放结果]:"+extraMsg;
        log.info(imsg);
        return SupplyResult.build(200,imsg,deliverOrder.getRemark());
    }

    @Override
    public SupplyResult payByNothing(Param param) throws RsaCheckException {
        if(!RsaUtil.verify(param,clientPublicKey)){
            return SupplyResult.build(800,"是你吗？","");
        }
        OrderPayByNothingBody body = JSON.parseObject(param.getCs(),OrderPayByNothingBody.class);
        JfDeliverOrder deliverOrder = exchangeOrderService.queryDeliverOrderByBillno(body.getBillno());
        if(deliverOrder == null){
            return SupplyResult.build(302,"订单异常，不能直接兑换");
        }else if(!DeliverOrderStatus.UNPAID.getValue().equals(deliverOrder.getStatus())){
            return SupplyResult.build(303,"订单不是未支付状态，不能进行该操作！");
        }
        // 校验 输入的柜员号和"银行网点号"是否匹配；
        // 且该"银行网点号"和自助兑换机所在的网点是否匹配。
        if(!isMatch2(body.getBranchno(),deliverOrder.getBranchid())){
            return SupplyResult.build(501,"提交信息不匹配[网点和终端/机构和柜员]");
        }


        //处理订单状态,处理失败，状态回滚
        deliverOrder.setStatus(DeliverOrderStatus.PAID.getValue());
        deliverOrder.setRemark(nullFormat(body.getRemark(),"")+body.getBranchno()+":"+body.getEmployeeid());
        // 将该订单置为 type=2,无消费兑换。
        deliverOrder.setType(DeliverOrderType.PAY_NOTHING.getValue());
        jfDeliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

        // 自动发放 [AUTO],如果自动发放失败，提示在返回参数的msg中；需要增加回传柜员uid
        String extraMsg = autoOut(deliverOrder,body.getUid());
        //自动发放 结束

        String imsg = "无消费通知成功![自动发放结果]:"+extraMsg;
        log.info(imsg);
        return SupplyResult.build(200,imsg,deliverOrder.getRemark());
    }

    /**
     *
     * @param branchno  输入的行号（银行自己的编号）
     * @param employeeid  输入员工号（银行自己的编号）
     * @param org_id  当前兑换终端的所在的机构id。
     * @return true 匹配，false 不匹配。
     */
    public Boolean isMatch(String branchno,String employeeid,Integer org_id){
        TbNetworkerExample tbNetworkerExample = new TbNetworkerExample();
        tbNetworkerExample.createCriteria().andBranchnoEqualTo(branchno)
                .andBranchIdEqualTo(employeeid)
                .andOrgIdEqualTo(org_id);
        List<TbNetworker> list = tbNetworkerMapper.selectByExample(tbNetworkerExample);

        if(list.size() > 0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 应客户要求，只校验输入的机构号和当前订单的机构id是否匹配。
     * @param bankNo  输入的行号（银行自己的编号）
     * @param org_id  当前兑换终端的所在的机构id。
     * @return true 匹配，false 不匹配。
     */
    public Boolean isMatch2(String bankNo,Integer org_id){
        if(bankNo == null ){
            return false;
        }
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyId(org_id);
        if(orgInfo == null){
            return false;
        }else if(bankNo.equals(orgInfo.getBankNo())){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 自动发放
     * @param deliverOrder 订单信息
     * @param userinfoUid 用户 Uid
     * @return
     */
    @Transactional
    public String autoOut(JfDeliverOrder deliverOrder,Integer userinfoUid){
        String extraMsg;
        // 旧版客户端 不传输 uid
        if(userinfoUid == null){
            extraMsg = "发放失败！您当前自助兑换app版本过低，请联系相关人员更新至最新版本！";
            return extraMsg;
        }

        try {
            log.info("[自动发放]=billno:{},uid:{}",deliverOrder.getBillno(),userinfoUid);
            TbUserInfo tbUserInfo = new TbUserInfo();
            tbUserInfo.setUid(userinfoUid);
            SupplyResult dt = exchangeOrderService.orderOut(deliverOrder.getBillno(),tbUserInfo);
            if(dt.isOK()){
                // 自动发放成功，给订单打上 自动发放的标记 。[AUTO OUT]
                String tag = "[AUTO OUT]";
                JfDeliverOrder dorder = new JfDeliverOrder();
                dorder.setId(deliverOrder.getId());
                dorder.setRemark(nullFormat(deliverOrder.getRemark(),"") + tag);
                jfDeliverOrderMapper.updateByPrimaryKeySelective(dorder);
            }
            extraMsg = dt.getMsg();
        }catch (Exception e){
            e.printStackTrace();
            log.error("[自动发放]失败：billno:{},uid:{}",deliverOrder.getBillno(),userinfoUid);
            extraMsg = "[自动发放]失败!"+nullFormat(e.getMessage(),"");
        }

        return extraMsg;

    }

    /**
     * null校验器，当s 为 null时，将其赋值为指定的 串 deft
     * @param s
     * @param deft
     * @return
     */
    private String nullFormat(String s,String deft){

        return s == null ? deft : s;
    }

    @Override
    public void putPaycode(HttpServletRequest request) {
        String paycode = StringUtil.getRandomString(32);
        request.getSession().setAttribute(SessionKey.PAY_CODE, paycode);
        log.info("重新生成paycode:"+paycode);
    }

    @Override
    public Boolean payCodeCheck(String rtCode) {
        JfDeliverOrderExample jdoe = new JfDeliverOrderExample();
        jdoe.createCriteria().andRemarkLike(rtCode + "%");

        return jfDeliverOrderMapper.selectByExample(jdoe).size() > 0 ? true : false;
    }
}

class PayFailedException extends Exception{
    public PayFailedException() {
    }

    public PayFailedException(String message) {
        super(message);
    }

    public PayFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayFailedException(Throwable cause) {
        super(cause);
    }

    public PayFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
