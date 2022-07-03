package com.zjjzfy.mgt.controller;

import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgt")
public class InvoiceController {

    @Autowired
    private UserService userService;

    /**
     * 供货商发票
     *
     * @return
     */
    @GetMapping("/endoriInvoice")
    public String endoriInvoice(Model model) {
        TbOrgInfo org = userService.getOrg();
        model.addAttribute("sellerId",org.getId());
        model.addAttribute("type",org.getType());
        return "invoice/endoriInvoice";
    }


    @GetMapping("/echats")
    public String echats() {
        return "countOrderData";
    }

    @GetMapping("/showSureInvoice")
    public String showTotal(Model model, String total, String name, String type) {
        if (type == null || type.equals("")) {
            type = "普通发票";
        }
        model.addAttribute("total", total);
        model.addAttribute("name", name);
        model.addAttribute("type", type);
        return "invoice/showSureInvoice";
    }


    @GetMapping("/invoiceHistory")
    public String invoiceHistory(Model model) {
        TbOrgInfo org = userService.getOrg();
        model.addAttribute("sellerId",org.getId());
        model.addAttribute("type",org.getType());
        return "invoice/invoiceHistory";
    }

    @GetMapping("/invoiceCheck")
    public String invoiceCheck(Model model) {
        TbOrgInfo org = userService.getOrg();
        model.addAttribute("sellerId",org.getId());
        model.addAttribute("type",org.getType());
        return "invoice/invoiceCheck";
    }

    @GetMapping("/invoiceDetails")
    public String invoiceDetails(Integer id, Model model) {
        model.addAttribute("id", id);
        return "invoice/invoiceDetails";
    }

    @GetMapping("retunUrl")
    public String returnUrl(Model model, String url) {
        TbUserInfo tbUserInfo = userService.getCurrentTbUserInfo();
        model.addAttribute("tbUserInfo", tbUserInfo);
        model.addAttribute("url", url);
        return "layout";
    }

}
