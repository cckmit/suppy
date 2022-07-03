package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.PurchaseCheckStatus;
import com.zjjzfy.common.config.PurchaseStatus;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.mobile.config.SupplyProperties;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.OrgBalanceService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.order.service.impl.OrgBalanceServiceImpl;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbPurchaseDetail;
import com.zjjzfy.pojo.TbPurchaseForm;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.pojo.dto.DeliverFormExampleDto;
import com.zjjzfy.repository.service.RepositoryService;
import com.zjjzfy.pojo.dto.PurchaseFormExampleDto;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Saintyun on 2019/3/13.
 */
@RequestMapping("/purchase")
@Controller
@PropertySource("application.yml")
public class PurchaseController {
    private static final Object object = new Object();
    private static final Object object2 = new Object();
    private static final Object object3 = new Object();

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private OrgBalanceService orgBalanceService;
    @Autowired
    private DeliverService deliverService;
    @Value("${cancel.timeout}")
    private Integer timeout;
    @Autowired
    private SupplyProperties supplyProperties;


    /**
     * 购物车下单-》生成订货单-》进入订货单详情
     *
     * @param ids
     * @param purchaseId
     * @return
     */
    @RequestMapping("/cartToForm")
    @ResponseBody
    public SupplyResult cartToForm(String ids, Integer purchaseId,Integer adsId) {
        List<Integer> list = new ArrayList<>();
        String[] strs = ids.split(",");
        for (int i = 0; i < strs.length; i++) {
            list.add(Integer.valueOf(strs[i]));
        }
        return purchaseService.generPurchaseForm(list, purchaseId,adsId, supplyProperties.getOrderCheck());
    }


    @RequestMapping("/deliverFormInit")
    public String queryDeliverFormList(Model model, String startDate, String endDate, Byte status) {

        model.addAttribute("purchaserId", userService.getCurrentTbUserInfo().getUid());
        model.addAttribute("status", status);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "/purchase/deliverFormList";
    }

    /**
     * 通过自定义条件查看订货单列表
     * 分页
     *
     * @return
     */
    @RequestMapping("/deliverFormList")
    @ResponseBody
    public PageInfo<TbDeliverForm> queryDeliverFormList(DeliverFormExampleDto deliverFormExampleDto) throws ParseException {

        return deliverService.selectDeliverFormByPurchaseId(deliverFormExampleDto);
    }

    @RequestMapping("/deliverFormDetail")
    public String selectDeliverFormById(Model model, Integer deliverFormId) {
        model.addAttribute("productList", deliverService.queryDeliverFormProductList(deliverFormId));
        model.addAttribute("deliverForm", deliverService.selectDeliverFormById(deliverFormId));
        return "/purchase/deliverFormDetail";
    }

    /**
     * 通过各种条件查看订货单列表
     * 分页
     *
     * @return
     */
    @RequestMapping("/purchaseFormList")
    @ResponseBody
    public PageInfo<TbPurchaseForm> queryPurchaseFormList(PurchaseFormExampleDto purchaseFormExampleDto) {

        return purchaseService.selectPurchaseFormByExampleDto(purchaseFormExampleDto);
    }


    /**
     * 通过purchaseFormId查看订货单详情
     *
     * @param model
     * @param purchaseFormId
     * @return
     */
    @RequestMapping("/purchaseDetail")
    public String queryPurchaseDetail(Model model, Integer purchaseFormId) {
        TbPurchaseForm form = purchaseService.selectByPurchaseFormId(purchaseFormId);
        List<TbPurchaseDetail> details = purchaseService.findPurchaseDetailListById(purchaseFormId);
        model.addAttribute("category", details.size());
        model.addAttribute("purchaseForm", form);
        if (form != null) {
            Byte payStatus = form.getStatus();
            Byte checkStatus = form.getCheckStatus();
            Subject current = SecurityUtils.getSubject();
            if (payStatus - PurchaseStatus.CLOSED.getStatus() == 0) {
                //已作废
                model.addAttribute("flag", 6);
            } else if (payStatus - PurchaseStatus.UNPAID.getStatus() == 0) {
                //等待付款——》返回 |立即付款|作废|
                model.addAttribute("flag", 1);
            } else if (payStatus - PurchaseStatus.UNFINISHED.getStatus() == 0) {
                //未完成
                //已付款，进入审核判断
                if (checkStatus - PurchaseCheckStatus.uncheck.getStatus() == 0 ) {
                    //等待审核  有无审核权限判断 有审核权限
                    model.addAttribute("flag", 2);
                } else if (checkStatus - PurchaseCheckStatus.checked.getStatus() == 0) {
                    //|已审核|作废|
                    model.addAttribute("flag", 4);
                }
            } else if (payStatus - PurchaseStatus.FINISHED.getStatus() == 0) {
                //|已完成|
                model.addAttribute("flag", 5);
            }
        }
        model.addAttribute("timeout", timeout);
        model.addAttribute("productList", purchaseService.queryPurchaseFormProductList(purchaseFormId));
        return "/purchase/orderListDetail";
    }


    /**
     * 立即付款界面
     *
     * @param model
     * @param purchaseFormId
     * @return
     */
    @RequestMapping("/pay")
    public String pay(Model model, Integer purchaseFormId) {
        TbPurchaseForm form = purchaseService.selectByPurchaseFormId(purchaseFormId);
        model.addAttribute("totalprice", form.getTotalPrice());
        model.addAttribute("purchaseFormId", purchaseFormId);
        return "/purchase/orderPay";
    }

    /**
     * 立即付款界面json
     *
     * @param purchaseFormId
     * @return
     */
    @RequestMapping("/payJson")
    @ResponseBody
    public SupplyResult pay(Integer purchaseFormId, Double totalprice) {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        SupplyResult result = null;
        synchronized (object) {
            result = purchaseService.payForPurchaseOrder(purchaseFormId, totalprice, user.getOrgid(), supplyProperties.getEnable());
        }
        return result;
    }

    /**
     * 通过订货单id查看商品列表
     *
     * @param model
     * @param purchaseFormId
     * @return
     */
    @RequestMapping("/productList")
    public String queryProductList(Model model, Integer purchaseFormId) {

        model.addAttribute("productList", purchaseService.queryPurchaseFormProductList(purchaseFormId));
        return "/purchase/productList";
    }


    @RequestMapping("/orderQueryInit")
    public String orderQueryInit(Model model) {

        return "/purchase/orderQuery";
    }


    /**
     * 审核订单
     *
     * @param formId
     * @param remark
     * @return
     */
    @RequestMapping("/checkOrder")
    @ResponseBody
    public SupplyResult checkOrder(Integer formId, String remark) {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        return purchaseService.checkOrder(formId, user.getUid(), remark);
    }

    @RequestMapping("/deliverFormConfirmed")
    @ResponseBody
    public SupplyResult deliverFormConfirmed(Integer purchaseFormId, Integer deliverFormId) {

        TbUserInfo user = userService.getCurrentTbUserInfo();

        SupplyResult result = null;
        synchronized (object) {
            result = deliverService.confirmDeliverForm(purchaseFormId, deliverFormId, user.getColumn1(),supplyProperties.getOrderConfirm());
        }
        return result;
    }

    @RequestMapping("/deliverFormRejected")
    @ResponseBody
    public SupplyResult deliverFormRejected(Integer deliverFormId) {
        SupplyResult result = null;
        synchronized (object3) {
            result = deliverService.rejectDeliverForm(deliverFormId);
        }
        return result;
    }

    /**
     * 作废订货单
     *
     * @param formId
     * @return
     */
    @RequestMapping("/invalidOrder")
    @ResponseBody
    public SupplyResult invalidOrder(Integer formId, String remark) {
        SupplyResult result = null;
        synchronized (object2) {
            result = purchaseService.invalidOrder(formId, remark, supplyProperties.getEnable());
        }
        return result;
    }

    @RequestMapping("/toVoidReason")
    public String toVoidReason(Model model, Integer formId) {
        model.addAttribute("formid", formId);
        return "/purchase/toVoidReason";
    }


    @RequestMapping("/changePwd")
    public String changePassword() {
        return "/purchase/setNewPassword";
    }

    @RequestMapping("/changePwdJson")
    @ResponseBody
    public SupplyResult changePasswordJson(String oldPassword, String newPassword) {
        return shiroService.changePwd(oldPassword, newPassword);
    }

    @RequestMapping("/obtainChangeHistory")
    @ResponseBody
    public SupplyResult obtainChangeHistory() {
        TbUserInfo user = userService.getCurrentTbUserInfo();
        return orgBalanceService.getBalanceRecord(user.getUid());
    }

    /**
     * 通过发货单id查看商品列表
     *
     * @param model
     * @param deliverFormId
     * @return
     */
    @RequestMapping("/deliverFormProductList")
    public String queryDeliverFormProductList(Model model, Integer deliverFormId) {

        model.addAttribute("productList", deliverService.queryDeliverFormProductList(deliverFormId));
        return "/purchase/deliverFormProductList";
    }


    /**
     * 获取本网点需要的预购单
     * @return
     */
    @RequestMapping("/willPurchaseList")
    public String willPurchaseList(Model model){
        TbUserInfo currentTbUserInfo = userService.getCurrentTbUserInfo();
        Integer purchaserId = currentTbUserInfo.getUid();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date end = new Date();
        model.addAttribute("end",format.format(end));
        model.addAttribute("start",getPastDate(7));
        model.addAttribute("purchaserId", purchaserId);
        return "/purchase/willPurchaseList";
    }

    @RequestMapping("/willPurchaseListJson")
    @ResponseBody
    public SupplyResult willPurchaseListJson(String startdate,String enddate,Integer page,Integer size){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(startdate);
            Date end = format.parse(enddate);
            Calendar c = Calendar.getInstance();
            c.setTime(end);
            c.add(Calendar.DAY_OF_MONTH, 1);
            end = c.getTime();

            TbUserInfo user = userService.getCurrentTbUserInfo();
            PageInfo<Map<String,Object>> list = purchaseService.selectWillPurchaseListByOrgid(user.getOrgid(),start,end,page,size);
            return SupplyResult.ok(list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return SupplyResult.ok();
    }

    @RequestMapping("/purchaseToForm")
    @ResponseBody
    public SupplyResult willPurchaseToForm(String ids,String counts, String purchaseId){
        List<Integer> idlist = new ArrayList<>();
        List<Integer> ammounts = new ArrayList<>();
        String[] strs = ids.split(",");
        String[] countlist = counts.split(",");
        for (int i = 0; i < strs.length; i++) {
            idlist.add(Integer.valueOf(strs[i]));
            ammounts.add(Integer.valueOf(countlist[i]));
        }
        return purchaseService.generWillPurchaseForm(idlist,ammounts, Integer.valueOf(purchaseId));
    }

    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
}