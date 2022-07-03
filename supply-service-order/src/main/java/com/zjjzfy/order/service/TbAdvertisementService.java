package com.zjjzfy.order.service;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbAdvertisement;
import com.zjjzfy.pojo.TbAdvertisementDto;

import java.util.List;
import java.util.Map;

public interface TbAdvertisementService {

    /**
     * 获取活动信息以及相关的活动链接以及参数列表
     * type 1展示中的广告 2未开始的广告 3已结束的广告4:文字接口
     * advClassification:0为文字广告,1为图片广告
     * advPosition:广告所在位置1:轮播2:公告3:超值组合限时特卖4:新品上架热卖推荐
     * status 0下架的 1上架的
     */
    List<TbAdvertisement> getAdsByTime(Integer type, String status,String advClassification,Integer advPosition);

    List<TbAdvertisement> getAdsByPosition(Integer type, String status,String advClassification,Integer advPosition);

    List<TbAdvertisement> getAdsByPositionByIndex(Integer type, String status, String advClassification, Integer advPosition);

    List<TbAdvertisementDto> getAdsByPositionByIndexExpire(Integer type, String status, String advClassification, Integer advPosition);

    Map<String,Object> getAdsByPosition(Integer type, String status);

    SupplyResult adsDown(Integer id);

    SupplyResult addAdvertisement(TbAdvertisement ad);

    SupplyResult modifyAd(TbAdvertisement ad);

    TbAdvertisement getAdsById(Integer adId);
    SupplyResult uploadImg(String base64Img, String fileName);

    /**
     * 公告的新增
     * @param ta
     * @return
     */
    SupplyResult doTbAdvertisementAdd(TbAdvertisement ta);

    SupplyResult doTbAdvertisementEdit(TbAdvertisement ta);

    SupplyResult doTbAdvertisementDlt(Integer id);

    SupplyResult doAllTbAdvertisementEdit(List<TbAdvertisement> list, List<TbAdvertisement> dbList,Integer type, String status,String advClassification,Integer advPosition);

    SupplyResult doAllTextAdv3Edit(List<TbAdvertisement> list, List<TbAdvertisement> dbList,Integer type, String status,String advClassification,Integer advPosition);

    SupplyResult doAllAdvMoreEdit(List<TbAdvertisement> list, List<TbAdvertisement> dbList,Integer type, String status,String advClassification,Integer advPosition);

    List<TbAdvertisement> ImgPathoperation(String peImgPath,List<TbAdvertisement> tbAdvertisements);
}