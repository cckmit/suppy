package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.dto.ProductFormDto;

/**
 * @author: hsmz
 * @date: 2019/4/9 上午11:07
 */
public interface ExchangeService {

    /**
     * 行社人员自主兑换
     * @param productFormDto
     * @param clientNo 真正兑换人员的客户内码
     * @param employeeId 帮助兑换人员工号
     * @return
     */
    SupplyResult exchange(ProductFormDto productFormDto, String clientNo, Integer employeeId);
}
