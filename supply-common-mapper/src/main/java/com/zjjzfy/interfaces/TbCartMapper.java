package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbCartExample;

import java.util.HashMap;
import java.util.List;

import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

public interface TbCartMapper extends PublicMapper<TbCart> {

    void updateByPurchaseId(@Param("purchaseid")Integer purchaseId);

//    List<TbCart> selectTbCartByPurchaseId(@Param("purchaseId")Integer purchaseId);
    List<HashMap<String,Object>> selectByPurchaseId(@Param("purchaseId")Integer purchaseId,@Param("status")Byte status);
}