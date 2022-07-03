package com.zjjzfy.order.service.impl;

import com.zjjzfy.interfaces.HomeMapper;
import com.zjjzfy.interfaces.TbDeliverFormMapper;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.order.service.HomeService;
import com.zjjzfy.pojo.*;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tk.mybatis.mapper.genid.GenId;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2019/11/20 上午9:06
 */
@Service
@Slf4j
public class HomeServicelmpl implements HomeService {

    @Autowired
    private TbDeliverFormMapper deliverFormMapper;
    @Autowired
    private HomeMapper homeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TbOrgInfoMapper orgInfoMapper;
    /**
     * 统计待发货订单数
     *
     * @return
     */
    @Override
    public Map countWaitOrder() {
        /*TbDeliverFormExample deliverFormExample = new TbDeliverFormExample();
        deliverFormExample.createCriteria().andStatusEqualTo(new Byte("0"));
        int count = deliverFormMapper.selectCountByExample(deliverFormExample);
        return count;*/
        return homeMapper.countWaitOrder(new Byte("0"),null,null);
    }



    /**
     * 统计昨日完成 订单数 订单总价
     *
     * @return
     */
    @Override
    public Map countYesterOrdered() {
        return homeMapper.countYesterOrdered(null,null);
    }

    /**
     * 统计昨日 订单数 订单总价
     */
    @Override
    public Map countYesterOrder(){
        return homeMapper.countOrderYester(new Byte("1"),null,null);
    }

    /**
     * 统计代开发票数量，金额
     *
     * @return
     */
    @Override
    public Map countWaitInvoice() {
        return homeMapper.countWaitInvoice(1, null,null);
    }

    /**
     * 首页发货单 利润报表
     * @return
     */
    @Override
    public Object[][] countOrderDate() {
        List<Map<String, Object>> list = homeMapper.countOrderData();
        String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        Object[][] array = new Object[5][13];
        array[0][0] = "月份";  //createMonth
        array[1][0] = "供货商";  //createMonthSum
        array[2][0] = "商品数量";  //quantMonthSum
        array[3][0] = "行社"; //bankMonthSum
        array[4][0] = "利润"; //profit

        for(int i = 0;i<month.length;i++){
            array[0][i+1] = month[i];
        }

        int[] arr = new int[list.size()];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = (int) list.get(j).get("createMonth");
        }
        for(int i = 0;i< 12;i++){
            boolean flag = false;
            for(int j = 0;j<arr.length-1;j++){
                if(month[i].equals(arr[j]+"")){
                    Object[] a = new Object[5];
                    a = list.get(j+1).values().toArray();
                    for (int k = 1; k < a.length; k++) {
                        array[k][i+1] = a[k];
                    }
                    flag = true;
                    break;
                }
            }
            if(!flag){
                for(int m =1;m<5;m++){
                    array[m][i+1] = 0;
                }
            }
        }

        return array;
    }

    /**
     * 订货方显示：待收货 已开发票  待开发票
     * 供货方显示：待开发票 昨日订单 昨日完成订单 待发货订单
     * 平台显示：待开发票 昨日订单 昨日完成订单 待发货订单
     * @return
     */
    @Override
    public Map homeMapAll(){
        Map map = new HashMap();
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        TbOrgInfoExample example = new TbOrgInfoExample();
        example.createCriteria().andIdEqualTo(tbUserInfo.getOrgid());
        List<TbOrgInfo> list = orgInfoMapper.selectByExample(example);
        TbOrgInfo orgInfo = list.get(0);
        Map countYesterOrdered=null;
        Map countWaitOrder=null;
        String countWaitOrderName = "";
        Map countOrderYester =null;
        Map countInvoiceedAll =null;
        if (!list.isEmpty()){
            int type = orgInfo.getType(); /*1 供货 2订货 3 平台*/
            if(type == 1 || type == 3){
                // 订货方不需要待发货订单   分配权限时候应该不分配 要不然js会报错
                countYesterOrdered = homeMapper.countYesterOrdered(type==3?null:orgInfo.getId(), null);
                countWaitOrderName = "待发货订单";
                countWaitOrder = homeMapper.countWaitOrder(new Byte("0"), type==3?null:orgInfo.getId(), null);
                countOrderYester = homeMapper.countOrderYester(new Byte("1"), type==3?null:orgInfo.getId(), null);
                countInvoiceedAll = homeMapper.countWaitInvoice(2,type==3?null:orgInfo.getId(), null);
            }else if(type == 2){
                countWaitOrderName = "待收货订单";
                countWaitOrder = homeMapper.countWaitOrder(new Byte("0"),null,orgInfo.getId());
                countInvoiceedAll = homeMapper.countWaitInvoice(2,null,orgInfo.getId());
            }
           /* else if(type == 3){
                countYesterOrdered = homeMapper.countYesterOrdered(orgInfo.getId(), null);
                countWaitOrderName = "待发货订单";
                countWaitOrder = homeMapper.countWaitOrder(new Byte("0"), orgInfo.getId(), null);
                countOrderYester = homeMapper.countOrderYester(new Byte("1"), orgInfo.getId(), null);
                countInvoiceedAll = homeMapper.countWaitInvoice(2, orgInfo.getId(), null);
            }*/
        }
        // 待开发票
        map.put("countWaitInvoice",homeMapper.countWaitInvoice(1, orgInfo.getId(), null));
        map.put("countInvoicedAll",countInvoiceedAll);
        map.put("countYesterOrdered",countYesterOrdered);
        map.put("countWaitOrderName",countWaitOrderName);
        map.put("countWaitOrder",countWaitOrder);
        map.put("countOrderYester",countOrderYester);
        return map;
    }



}
