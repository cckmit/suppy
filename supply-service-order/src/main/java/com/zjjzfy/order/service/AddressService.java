package com.zjjzfy.order.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zjjzfy.pojo.TbAddress;
import com.zjjzfy.pojo.TbAddressExample;
import com.zjjzfy.pojo.TbPurchaseForm;

import java.util.HashMap;
import java.util.List;

/**
 * 地址
 * @author mask
 * @date: 2019/11/08 上午11:13
 */
public interface AddressService {
    /**
     * 查询当前用户的所有地址
     * @return
     */
    List<TbAddress> queryAds(Integer uid, boolean isFake);
    /**
     * 根据id查询当前用户的地址
     * @return
     */
    TbAddress findAdsById(Integer uid, Integer adsId, boolean isFake);
    /**
     * 查询当前用户的默认地址
     * @return
     */
    TbAddress findAdsByIsdft(Integer uid, boolean isFake);
    /**
     *  如果用户有默认地址,返回默认地址,
     *  如果没有默认地址,返回上一次使用的地址,
     *  如果没有上一次使用的地址,返回第一个地址
     * @return
     */
    TbAddress findAdsByIsdftAndIsLastUsed(Integer uid, boolean isFake);

      /**
     * 插入地址
     * @param ta
     * @return
     */
    Boolean istAds(TbAddress ta, boolean isFake,String pcc);

    /**
     * 更新地址
     * @param ta
     * @return
     */
    Boolean updtAds(TbAddress ta, boolean isFake,String pcc);

    /**
     * 删除地址
     * @return
     */
    Boolean dltAds(Integer uid,Integer id, boolean isFake);

    /**
     * 检查地址
     * @return
     */
    Boolean checkAds(Integer uid, boolean isFake);

    /**
     * 删除所有伪标记地址
     * @return
     */
    Boolean cleanAllDltAds(Integer uid);

    /**
     *查找当前用户的购物车所有物品,根据用户用户所选物品的id数组,筛选出id相等的物品
     * @param uid
     * @param ids
     * @return
     */
    List<HashMap<String, Object>> generCarts(Integer uid,String ids);

    public TbPurchaseForm formSetAds(Integer uId,Integer adsId, TbPurchaseForm form,boolean isFake);

}
