package com.zjjzfy.order.service;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.entity.SupplyResult;

import java.math.BigDecimal;
import java.util.Map;

public interface OrgBalanceService {
    SupplyResult usedOrgBalance(Integer orgId, BigDecimal money, Boolean overpayment);

    /**
     * 获取余额
     *
     * @param orgId
     * @return
     */
    SupplyResult getOrgBalance(Integer orgId);

    SupplyResult getBalanceRecord(Integer userId);

    /**
     * 获取所有已分配的额度
     *
     * @param branch  总行
     * @param branch2 需要排除的支行
     * @return
     */
    SupplyResult getOrgAllBalance(String branch, String branch2);

    /**
     * 获取所有额度变动的记录
     *
     * @param orgId
     * @param orgName
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> getOrgBalanceRecord(Integer orgId, String orgName, Integer pageIndex, Integer pageSize);
}