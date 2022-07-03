package com.zjjzfy.user.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbNetworker;

/**
 * @author zyx 网点
 * @date 2020/3/27 下午4:07
 */
public interface NetWorkUserService {

    SupplyResult insertNetWorkUser(TbNetworker tbNetworker);

    SupplyResult updateNetWorkUser(TbNetworker tbNetworker);

    SupplyResult deleteNetWorkUser(Integer id);

    SupplyResult selectNetWorkUser(Integer pageNo,Integer pageSize,Integer orgId);

    SupplyResult selectOrgInfo();

    /**
     * 根据类型 查找机构 1 供货 2 订货 3 平台
     * @param type
     * @return
     */
    SupplyResult selectOrgInfoByType(Byte type);

    /**
     * 网点库存
     * @param pageNo
     * @param pageSize
     * @return
     */
    SupplyResult selectNetWorkRepertory(Integer pageNo,Integer pageSize,Integer orgId);
}
