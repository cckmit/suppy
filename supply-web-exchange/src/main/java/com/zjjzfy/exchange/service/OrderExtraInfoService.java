package com.zjjzfy.exchange.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.JfDeliverOrderExtra;

/**
 * 
 * @author      jackshenonly
 * @description 类说明:兑换订单额外信息相关
 * @date        2020-05-21 10:46
 */
public interface OrderExtraInfoService {
    /**
     * 兑换订单额外信息上传
     * @param extra
     * @return
     */
    SupplyResult orderExtraInfoSubmit(JfDeliverOrderExtra extra);
}
