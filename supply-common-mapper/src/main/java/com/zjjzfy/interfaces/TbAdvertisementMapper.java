package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbAdvertisement;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TbAdvertisementMapper extends PublicMapper<TbAdvertisement> {

    List<TbAdvertisement> getAdsByTime(@Param("type") Integer type,
                                       @Param("status") String status,
                                       @Param("time") Date time,
                                       @Param("advClassification")String advClassification,
                                       @Param("advPosition")Integer advPosition);

    List<TbAdvertisement> getAdsByPosition(@Param("type") Integer type,
                                       @Param("status") String status,
                                       @Param("time") Date time,
                                       @Param("advClassification")String advClassification,
                                       @Param("advPosition")Integer advPosition);


    Integer adsDown(@Param("id") Integer id);

    List<TbAdvertisement> getAdsByPositionByStatus(@Param("status")String status);

    List<TbAdvertisement> getAdsByPositionByIndex(@Param("type") Integer type,
                                                     @Param("status") String status,
                                                     @Param("time") Date time,
                                                     @Param("advClassification")String advClassification,
                                                     @Param("advPosition")Integer advPosition);;
}