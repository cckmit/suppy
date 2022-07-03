package com.zjjzfy.exchange.controller;

import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.cache.ProductCache;
import com.zjjzfy.pojo.TbOrgInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 16:25
 */
@Controller
public class ProductListController {

    @Value("${myconfig.bank-name-zh}")
    private String bankNameZh;
    @Value("${myconfig.bank-name-en}")
    private String bankNameEn;
    @Value("${myconfig.page-size}")
    private int pageSize;
    @Value("${myconfig.web-exchange}")
    private Boolean webExchange;


    @RequestMapping("/")
    public String init(Model model, HttpServletRequest request) {
        if(!webExchange){
            return "web-stop";
        }
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyRequest(request);
        if(orgInfo == null){
            orgInfo = new TbOrgInfo();
            orgInfo.setOrgName("未设置网点");
        }
        model.addAttribute("bankNameZh", bankNameZh);
        model.addAttribute("bankNameEn", bankNameEn);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("orgInfo", orgInfo);
        model.addAttribute("cates", ProductCache.getInstance().getCates());
        return "product-list";
    }
}
