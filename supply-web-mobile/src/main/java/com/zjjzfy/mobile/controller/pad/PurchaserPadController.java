package com.zjjzfy.mobile.controller.pad;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.order.service.PadExchangeService;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Saintyun on 2019/4/1.
 */
@RestController
@Slf4j
@RequestMapping("/purchase/pad")
public class PurchaserPadController {

    @Autowired
    private PadExchangeService padExchangeService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private UserService userService;

    @RequestMapping("/giftList")
    public Map<String,Object> giftList(Integer employeeid,String orderBy,String orderCase){
        return padExchangeService.selectGiftList(employeeid,null,orderBy,orderCase);
    }

    @RequestMapping(value="/purchaseLogin",method= RequestMethod.POST)
    public SupplyResult purchaseLogin(String username, String password){
        log.info("登录接口访问成功");
        SupplyResult result =  shiroService.login(username,password);
        EmployeeInfoBean employeeInfoBean = new EmployeeInfoBean();
        TbUserInfo userInfo = userService.findUserByUsername(username);
        if(userInfo!=null) {
            employeeInfoBean.setId(userInfo.getUid());
            employeeInfoBean.setRole(-1);
            employeeInfoBean.setName(userInfo.getName());
        }else{
            employeeInfoBean.setId(-1);
            employeeInfoBean.setRole(-1);
            employeeInfoBean.setName("未知");
        }
        result.setData(employeeInfoBean);
        return result;
    }

    @RequestMapping("/giftDetail")
    public Map<String,Object> giftInfo(Integer productId,Integer employeeid){
        //显示商品详情
        return padExchangeService.selectGiftList(employeeid,productId,null,null);
    }

    @RequestMapping(value="/pay",method= RequestMethod.POST)
    public Map<String,Object> pay(String cartJson,Integer employeeid,String clientno){
        return padExchangeService.pay(cartJson,employeeid,clientno);
    }
}
