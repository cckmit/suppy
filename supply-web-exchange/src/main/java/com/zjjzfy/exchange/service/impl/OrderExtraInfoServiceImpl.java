package com.zjjzfy.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.OrderExtraInfoService;
import com.zjjzfy.exchange.utils.BillnoHelper;
import com.zjjzfy.interfaces.JfDeliverOrderExtraMapper;
import com.zjjzfy.pojo.JfDeliverOrderExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author      jackshenonly
 * @description 类说明:兑换订单 额外信息相关
 * @date        2020-05-21 10:47
 */
@Service
public class OrderExtraInfoServiceImpl extends BaseService implements OrderExtraInfoService {

    @Autowired
    private JfDeliverOrderExtraMapper jfDeliverOrderExtraMapper;
    @Override
    @Transactional
    public SupplyResult orderExtraInfoSubmit(JfDeliverOrderExtra extra) {
        log.info("开始上传兑换订单额外信息:{}", JSON.toJSONString(extra));

        if(!checkExtraInfo(extra)){
            return SupplyResult.build(301,"请检查传输的参数必填项是否正常！");
        }

        JfDeliverOrderExtra extraQuery = jfDeliverOrderExtraMapper.selectByPrimaryKey(extra.getBillno());

        if(extraQuery == null){
            jfDeliverOrderExtraMapper.insertSelective(extra);
        }else {
            jfDeliverOrderExtraMapper.updateByPrimaryKeySelective(extra);
        }
        log.info("上传兑换订单额外信息[成功]:{}", JSON.toJSONString(extra));
        return SupplyResult.build(200,"上传兑换订单额外信息[成功]！");
    }

    /**
     * 检测必填项
     * @param extra
     * @return 正常 返回true,异常 false
     */
    public Boolean checkExtraInfo(JfDeliverOrderExtra extra){
        if(extra == null){
            return false;
        }
        if(extra.getBillno() == null || extra.getBillno().trim().length() != BillnoHelper.BILLNO_LEN){
            return false;
        }
//        if(extra.getCustName() == null || extra.getCustName().trim().length() == 0){
//            return false;
//        }
//        if(extra.getCustTel() == null || extra.getCustTel().trim().length() == 0){
//            return false;
//        }

        return true;
    }


}
