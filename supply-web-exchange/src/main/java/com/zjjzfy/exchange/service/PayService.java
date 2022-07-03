package com.zjjzfy.exchange.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/5
 * Time: 14:28
 */
public interface PayService {
    /**
     * 支付
     * @param billno
     * @param paycode
     * @param request
     * @return
     */
    SupplyResult pay(String billno, String paycode, HttpServletRequest request) throws Exception;

    /**
     * ocx 支付后成功后，才会调用该方法，对订单状态进行处理
     * @param billno
     * @param paycode
     * @param remark
     * @param request
     * @return
     * @throws Exception
     */
    SupplyResult payByOcx(String billno, String paycode, String remark,HttpServletRequest request) throws Exception;

    /**
     * 通过android 调用 pos支付[积分]后 通知后台支付成功
     * @param param
     * @return
     * @throws RsaCheckException
     */
    SupplyResult payByAndroidPos(Param param) throws RsaCheckException;

    /**
     * 非消费兑换，柜员直接兑换出库。
     * @param param
     * @return
     * @throws RsaCheckException
     */
    SupplyResult payByNothing(Param param) throws RsaCheckException;

    /**
     * 设置支付有效的paycode；存在session.paycode
     * @param request
     */
    void putPaycode(HttpServletRequest request);

    /**
     * 支付结果返回码检测
     * 如果存在，true;
     * 如果不存在，false;
     * @param rtCode
     * @return
     */
    Boolean payCodeCheck(String rtCode);
}
