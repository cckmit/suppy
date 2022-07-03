package com.zjjzfy.order.service;

import com.zjjzfy.interfaces.OverTimeDeliverMapper;
import com.zjjzfy.pojo.TbDeliverForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class Timer {
    @Autowired
    private OverTimeDeliverMapper overTimeDeliverMapper;

    //轮询周期为30分钟
  /*  @Scheduled(cron="0 0/30 * * * ? ")
    public void deliverAutoConfirmedTimer(){

        //根据当前时间，获取2天前的时间
        Date timerNowDate=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timerNowDate);
        calendar.add(Calendar.DAY_OF_YEAR,-2);
        Date twoDaysEarly=calendar.getTime();

        //把状态为UNCONFIRMED,且超时的记录过滤出来
        List<TbDeliverForm> tbDeliverFormList=new ArrayList<>();
        tbDeliverFormList=overTimeDeliverMapper.selectOverTimeDelivers(twoDaysEarly);
        TbDeliverForm tbDeliverForm=new TbDeliverForm();
        for(int i=0;i<tbDeliverFormList.size();i++){
            tbDeliverForm=tbDeliverFormList.get(i);
            Integer deliverFormId=tbDeliverForm.getId();

            //tb_deliver_detail deliver_form_id
            overTimeDeliverMapper.updatePurchaseFormQuantityByDeliverFormId(deliverFormId);
            //tb_deliver_form id
            overTimeDeliverMapper.updateDeliverFormStatusByDeliverFormId(deliverFormId);
        }
    }*/
}
