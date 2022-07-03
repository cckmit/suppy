package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbAdvertisement;

import java.util.List;

public interface PurchaseIndexService {

    /**
     * 获取活动信息以及相关的活动链接以及参数列表
     * type 1展示中的广告 2未开始的广告 3已结束的广告
     * advClassification:0为文字广告,1位图片广告
     * advPosition:广告所在位置
     * status 0下架的 1上架的
     */
    SupplyResult addAdvertisement(TbAdvertisement ad);

    SupplyResult uploadImg(String base64Img, String fileName);

    SupplyResult doAllAdvEdit(List<TbAdvertisement> list, List<TbAdvertisement> dbList);

    SupplyResult uploadImgPc(String imgUrl,String base64Img, String fileName);
}