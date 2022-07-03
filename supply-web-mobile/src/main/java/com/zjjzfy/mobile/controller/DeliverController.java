package com.zjjzfy.mobile.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.ExcelUtil;
import com.zjjzfy.common.utils.StringUtil;
import com.zjjzfy.mobile.config.SupplyProperties;
import com.zjjzfy.order.service.CartService;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.order.service.PurchaseService;
import com.zjjzfy.pojo.TbCart;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbPurchaseForm;
import com.zjjzfy.pojo.dto.*;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hsmz
 * @date: 2019/3/18 下午1:57
 */
@Controller
@RequestMapping("/deliver")
@Slf4j
public class DeliverController {

    @Autowired
    private DeliverService deliverService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private SupplyProperties supplyProperties;
    /**
     * 供货方首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String login(Model model) {
        model.addAttribute("supplyOrgId", userService.getOrg().getId());
        model.addAttribute("type",userService.getOrg().getType());
        return "/deliver/deliverIndex";
    }

    @RequestMapping("/deliverFormInit")
    public String queryDeliverFormList(Model model, String startDate, String endDate, Byte status) {

        model.addAttribute("supplyOrgId", userService.getOrg().getId());
        model.addAttribute("status", status);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "/deliver/order/deliverFormList";
    }


    /**
     * 通过订货方id查看订货单列表
     * 分页
     *
     * @return
     */
    @RequestMapping("/deliverFormList")
    @ResponseBody
    public PageInfo<TbDeliverForm> queryDeliverFormList(DeliverFormExampleDto deliverFormExampleDto) {

        return deliverService.selectDeliverFormByPurchaseId(deliverFormExampleDto);
    }

    /**
     * 通过订货方id查看订货单列表
     * 加个 机构名
     * @param deliverFormExampleDto
     * @return
     */
    @RequestMapping("/selectDeliverForm")
    @ResponseBody
    public PageInfo<Map<String,Object>> selectDeliverForm(DeliverFormExampleDto deliverFormExampleDto){
        return  deliverService.selectTbDeliverForm(deliverFormExampleDto);
    }

    @RequestMapping("/deliverFormDetail")
    public String selectDeliverFormById(Model model, Integer deliverFormId) {

        model.addAttribute("deliverForm", deliverService.selectDeliverFormById(deliverFormId));

        model.addAttribute("productList", deliverService.queryDeliverFormProductList(deliverFormId));
        return "/order/deliverFormDetail";
    }

    @RequestMapping("/purchaseFormInit")
    public String queryPurchaseFormList(Model model, String startDate, String endDate, Byte status) {

        model.addAttribute("supplyOrgId", userService.getOrg().getId());
        model.addAttribute("status", status);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        //等待发货数量
        PageInfo<PurchaseFormDto> purchaseFormDtoPageInfo = purchaseService.selectSupplierPurchaseFormWithPage(userService.getOrg().getId(), 1, 100, 1, null,supplyProperties.getOrderCheck(),null);
        model.addAttribute("waitPurchaseNum",purchaseFormDtoPageInfo.getTotal() <= 99?purchaseFormDtoPageInfo.getTotal():"99+");

        return "/deliver/order/orderList";
    }

    /**
     * 通过id查看订货单列表
     * 分页
     *
     * @return
     */
    @RequestMapping("/purchaseFormList")
    @ResponseBody
    public PageInfo<PurchaseFormDto> queryPurchaseFormList(Integer supplyOrgId, Integer pageNum, Integer pageSize,Integer status,String purchaseBillno) {

        return purchaseService.selectSupplierPurchaseFormWithPage(supplyOrgId, pageNum, pageSize,status,purchaseBillno,supplyProperties.getOrderCheck(),null);
    }

    /**
     * 订货单详情
     *
     * @param model
     * @return personalCenter/orderDetails
     */
    @RequestMapping("purchaseFormDetail")
    public String checkOrderDetails(Model model, Integer purchaseFormId, Byte deliverStatus) {
        Integer supplyorgid = userService.getOrg().getId();
        //deliverStatus传进来不靠谱，如果列表没刷新 则deliveryStatus没刷新
        //重新请求下确保deliverStatus最新
        List<PurchaseFormDto> dtos = purchaseService.selectSupplierPurchaseForm(supplyorgid,null,null,null,purchaseFormId);
        if(dtos.size()>0){
            deliverStatus = dtos.get(0).getDeliverStatus();
        }
        List<ProductDto> productDtoList = purchaseService.queryProductListBySupplyOrgId(purchaseFormId, userService.getOrg().getId());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductDto productDto : productDtoList) {
            totalPrice = totalPrice.add(productDto.getPrice().multiply(new BigDecimal(productDto.getPurchaseQuantity())));
        }
        TbPurchaseForm form = purchaseService.selectByPurchaseFormId(purchaseFormId);
        model.addAttribute("form", form);
        model.addAttribute("productDtoList", productDtoList);
        model.addAttribute("totalPrice", totalPrice);
        List<HashMap<String, Object>> plist = purchaseService.selectPurchaserInfo(form.getPurchaserId());
        if (plist.size() > 0) {
            model.addAttribute("plist", plist.get(0));
        }
        model.addAttribute("deliverStatus", deliverStatus);
        return "/deliver/order/purchaseFormDetail";
    }

    /**
     * 供货方订货单商品列表
     *
     * @param model
     * @param purchaseFormId
     * @return
     */
    @RequestMapping("/deliverProductInit")
    public String deliverProductInit(Model model, Integer purchaseFormId) {

        model.addAttribute("purchaseFormId", purchaseFormId);
        model.addAttribute("supplyOrgId", userService.getOrg().getId());
        return "/deliver/order/purchaseProductList";
    }

    @RequestMapping("/deliverProductList")
    @ResponseBody
    public List<ProductDto> deliverProductList(Integer purchaseFormId, Integer supplyOrgId) {

        return purchaseService.queryProductListBySupplyOrgId(purchaseFormId, supplyOrgId);
    }

    @RequestMapping("/deliverProduct")
    @ResponseBody
    public SupplyResult deliverProduct(String ids,String nums, Integer purchaseFormId, Integer supplyOrgId) {

        List<Integer> idArray = StringUtil.strArrayToIntArray(StringUtil.stringInArrays(ids));
        List<Integer> numsArray = StringUtil.strArrayToIntArray(StringUtil.stringInArrays(nums));
        TbPurchaseForm purchaseForm = purchaseService.selectByPurchaseFormId(purchaseFormId);
        int purchaserId = purchaseForm.getPurchaserId();
        int purchaserOrgId = purchaseForm.getPurchaseOrgId();
        int supplierId = userService.getCurrentTbUserInfo().getUid();
        String purchaseBillno = purchaseForm.getPurchaseBillno();
        ProductFormDto productFormDto = cartService.generateProductForm(idArray, numsArray);
        SupplyResult result;
        try {
            result = deliverService.generateDeliverForm(purchaserId, purchaserOrgId, supplierId, supplyOrgId, purchaseBillno, purchaseFormId, supplierId, productFormDto);
        } catch (RuntimeException e) {
            result = new SupplyResult();
            result.setMsg(e.getMessage());
            result.setStatus(ReturnStatus.NOT_ENOUGH_QUANTITY.getStatus());
        }
        return result;
    }


    /**
     * 通过发货单id查看商品列表
     *
     * @param model
     * @param deliverFormId
     * @return
     */
    @RequestMapping("/productList")
    public String queryProductList(Model model, Integer deliverFormId) {

        model.addAttribute("productList", deliverService.queryDeliverFormProductList(deliverFormId));
        return "/commodity/deliverFormProductList";
    }


    @RequestMapping("/productStockList")
    public String productStockList(Model model, String content) {
        model.addAttribute("content", content);
        return "/deliver/stock/wareHouseList";
    }


    @RequestMapping("/deliverOrderProductExport")
    public void deliverOrderProductExport(Integer id, HttpServletResponse response){
        try {
            TbDeliverForm tbDeliverForm=deliverService.selectDeliverFormById(id);
            tbDeliverForm.setExportMark(1);
            deliverService.updateExportMark(tbDeliverForm);
            TbOrgInfo supplyOrg = userService.getOne(tbDeliverForm.getSupplyOrgId(), null);
            TbOrgInfo  purchaseOrg = userService.getOne(tbDeliverForm.getPurchaseOrgId(), null);
            List<ProductDto> productDtoList=deliverService.queryDeliverFormProductList(id);
            String filename = "供货单-"+tbDeliverForm.getDeliverBillno()+"-商品详情-" + System.currentTimeMillis() +".xls";
            HSSFWorkbook workbook = ExcelUtil.deliverOrderProductExport(tbDeliverForm,supplyOrg,purchaseOrg,productDtoList);
            ExcelUtil.buildExcelDocument( filename, workbook, response );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计发货单 未发货 已发货
     * @param model
     * @return
     */
    @RequestMapping("deliverFormCount")
    public String deliverFormCount(Model model){
        SupplyResult supplyResult = deliverService.queryDeliverOrderCount();
        model.addAttribute("productList",supplyResult.getData());
        return "/deliver/deliverFormCount";
    }





}
