package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.JfDeliverOrder;
import com.zjjzfy.pojo.TbUserInfo;


/**
 * Created by IntelliJ IDEA.
 * 自助兑换相关
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 10:38
 */
public interface ExchangeOrderService {

    /**
     * 查询自助兑换单详情；主单信息+明细
     * @param billno
     * @return
     */
    SupplyResult orderInfo(String billno);

    /**
     * 查询自助兑换单详情；主单信息+明细
     * @param billno billno可能传入【凭证号】或者本地订单号。
     *               【凭证号】，对于一个网点来说是唯一的
     *               （同一个POS机返回，网点的POS机"一般"会不边变化）。
     * @param userInfo 当传入的是凭证号时，用柜员所在网点去联合，查询出唯一的本地单号。
     * @return
     */
    SupplyResult orderInfo(String billno, TbUserInfo userInfo);

    /**
     * 查询柜员所在网点最近 自助兑换单详情
     * @param recentNo 最近第几条
     * @return
     */
    SupplyResult orderInfoRecent(Integer recentNo,TbUserInfo userInfo);

    /**
     * 查询 当前用户所在网点的自助兑换；最近数条记录 （非详情）。
     * @param pageNo
     * @param pageSize
     * @param orgid
     * @return
     */
    SupplyResult orderRecent(Integer pageNo, Integer pageSize,Integer orgid);

    /**
     * 某个机构最近 自助兑换单详情
     * @param recentNo
     * @param orgid
     * @return
     */
    SupplyResult orderInfoRecent(Integer recentNo, Integer orgid);

    /**
     * 查看自助兑换单，主要情况
     * @param billno
     * @return
     */
    JfDeliverOrder queryDeliverOrderByBillno(String billno);

    /**
     * 兑换单出库
     * @param billno
     * @param userInfo 出库人
     * @return
     */
    SupplyResult orderOut(String billno, TbUserInfo userInfo) throws Exception;
}
