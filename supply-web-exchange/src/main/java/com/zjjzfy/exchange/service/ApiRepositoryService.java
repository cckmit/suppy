package com.zjjzfy.exchange.service;

import com.zjjzfy.common.entity.SupplyResult;

import java.util.List;

/**
 * 
 * @author      jackshenonly
 * @description 类说明:库存查询相关
 * @date        2020-05-15 13:44
 */
public interface ApiRepositoryService {

    /**
     * 查询单个礼品库存
     * @param productId
     * @param uid
     * @param branchno
     * @return
     */
    SupplyResult repositoryOne(Integer productId,Integer uid,String branchno);

    /**
     * 查询多个礼品库存
     * @param productIds
     * @param uid
     * @param branchno
     * @return
     */
    SupplyResult repositoryMore(List<Integer> productIds, Integer uid, String branchno);

    /**
     * 查询多个礼品库存
     * @param productIds  英文逗号隔开的产品id
     * @param uid
     * @param branchno
     * @return
     */
    SupplyResult repositoryMore(String productIds, Integer uid, String branchno);

}
