package com.zjjzfy.interfaces;

import com.zjjzfy.pojo.TbEvents;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.pub.PublicMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TbEventsMapper extends PublicMapper<TbEvents> {

    List<TbEvents> getEventsList(Date time,Integer evtClass,Integer evtStatus,Integer type);
}