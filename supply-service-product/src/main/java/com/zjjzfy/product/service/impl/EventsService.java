package com.zjjzfy.product.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbEvents;

import java.util.List;

public interface EventsService {
    /**
     *
     * @param evtClass 0:自有的(不可删除)1:自定义的(可删除)
     * @param evtStatus 0:正常1:下架2:2隐藏
     * @param type 1正常,2未开始,3已经结束,4长期
     * @return
     */
    List<TbEvents> getEventsList(Integer evtClass,Integer evtStatus,Integer type);

    SupplyResult doAllEventsEdit(List<TbEvents> pageList,List<TbEvents> dbList,Integer evtClass,Integer evtStatus,Integer type);

}
