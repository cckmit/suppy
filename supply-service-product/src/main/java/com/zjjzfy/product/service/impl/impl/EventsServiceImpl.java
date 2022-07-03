package com.zjjzfy.product.service.impl.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbCategoryMapper;
import com.zjjzfy.interfaces.TbEventsMapper;
import com.zjjzfy.interfaces.TbProductMapper;
import com.zjjzfy.pojo.TbEvents;
import com.zjjzfy.product.service.impl.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private TbCategoryMapper categoryMapper;
    @Autowired
    private TbProductMapper productMapper;
    @Autowired
    private TbEventsMapper eventsMapper;


    @Override
    public List<TbEvents> getEventsList(Integer evtClass,Integer evtStatus,Integer type) {
//        eventsMapper.getEventsList(new Date(),evtClass,evtStatus,type).forEach(e->{
//            System.err.println("eventsMapper.getEventsList(evtClass)"+e.toString());
//        });
        return eventsMapper.getEventsList(new Date(),evtClass,evtStatus,type);
    }

    @Override
    public SupplyResult doAllEventsEdit(List<TbEvents> pageList,List<TbEvents> dbList,Integer evtClass,Integer evtStatus,Integer type) {
        //设置rank
        for (int i = 0,j= pageList.size();i<j;i++){
            TbEvents te  = pageList.get(i);
            te=setValue(te);
            te.setEvtRank(i);
            pageList.set(i,te);
        }
        //与数据库数据为主的遍历
        for (int i = 0,j= dbList.size();i<j;i++){
            TbEvents e  = dbList.get(i);
            Integer index = existOrNot(e,pageList);
            if(index!=null){
                //更新
                eventsMapper.updateByPrimaryKey(pageList.get(index));
            }else{
                //数据库里有但界面没有
                //删除
                e.setStatus(1);
                eventsMapper.updateByPrimaryKey(e);
            };
        }
        //与界面数据为主的遍历
        for (int i = 0,j= pageList.size();i<j;i++){
            TbEvents e = pageList.get(i);
            Integer index = existOrNot(e,pageList);
            if(index!=null){
                //更新
            }else{
                //界面里有但数据库没有
                //插入
                eventsMapper.insert(e);
            };
        }
        return SupplyResult.ok();
    }
    public TbEvents setValue(TbEvents ta){
        Integer id = ta.getId();
        if(id==null){
            Date date = ta.getEndTime()==null ?  new Date(new Date().getTime()+999999999l*100l):ta.getEndTime();
            ta.setEndTime(date);
            ta.setStartTime(new Date());
            ta.setCreateTime(new Date());
        }
        //新品和热卖
        if(new Integer(1).equals(id)||new Integer(2).equals(id)){
            ta.setEvtClass(0);
        }else{
            ta.setEvtClass(1);
        }
        return ta;
    }
    private Integer existOrNot(TbEvents te, List<TbEvents> list) {
        if(list!=null){
            for (int i = 0,j= list.size();i<j;i++){
                Integer id = list.get(i).getId()==null ?-1:list.get(i).getId();
                if(id.equals(te.getId())){
                    return i;
                }
            }
        }
        return null;
    }
}
