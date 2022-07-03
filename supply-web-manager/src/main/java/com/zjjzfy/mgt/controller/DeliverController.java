package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.pojo.dto.DeliverFormExampleDto;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : addict
 * date:  2020/2/28/028  16:21
 * @description: 发货
 */
@Controller
@RequestMapping("/mgt")
public class DeliverController {

    @Autowired
    private DeliverService deliverService;
    @Autowired
    private UserService userService;


    @RequestMapping("deliverList")
    public String deliverList() {
        return "deliver/deliverList";
    }


    @RequestMapping("/deliverFormList")
    @ResponseBody
    public PageInfo<TbDeliverForm> queryDeliverFormList(DeliverFormExampleDto deliverFormExampleDto) {
        TbOrgInfo tbOrgInfo=userService.getOrg();
        if (null!=tbOrgInfo && tbOrgInfo.getType()==(byte)1){
            deliverFormExampleDto.setSupplyOrgId(tbOrgInfo.getId());
        }

        return deliverService.selectDeliverFormByPurchaseId(deliverFormExampleDto);
    }



}
