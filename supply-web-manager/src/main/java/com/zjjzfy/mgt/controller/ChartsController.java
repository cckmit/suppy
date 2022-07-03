package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.DeliverDetailStatus;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.ExcelUtil;
import com.zjjzfy.interfaces.PublicTableMapper;
import com.zjjzfy.mgt.service.ChartsService;
import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.pojo.TbDeliverDetail;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.pojo.dto.ProductDto;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Saintyun on 2019/4/9.
 */
@Controller
@RequestMapping("/mgt")
public class ChartsController {
    @Autowired
    private OrganizeService organizeService;
    @Autowired
    private ChartsService chartsService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private UserService userService;


    @RequestMapping("/indentorProductreportForm")
    public String indentorProductreportForm(Model model, Date startdate, Date enddate,Integer page, Integer limit){
        if(startdate==null||startdate.equals("")){
            startdate = new Date();
        }
        if(enddate==null||enddate.equals("")){
            enddate = new Date();
        }
        model.addAttribute("startdate",startdate);
        model.addAttribute("enddate",enddate);
        PageInfo<Map<String, Object>> p = organizeService.getOrg(null,null,null,null,null,null,page,limit,null);
        model.addAttribute("page",p);
        String type = userService.getOrg().getType().toString();
        Integer id = userService.getOrg().getId();
        model.addAttribute("type",type);
        model.addAttribute("orgId",type.equals("3")?"":id);
        return "charts/purchaseProductStatistic";
    }


    @RequestMapping("/toExchangeOrderCount")
    public String toExchangeOrderCount(Model model){
        String type = userService.getOrg().getType().toString();
        Integer id = userService.getOrg().getId();
        model.addAttribute("type",type);
        model.addAttribute("orgId",type.equals("3")?"":id);
        return "charts/exchangeOrderCount";
    }



    @RequestMapping("/purchaseProductDetail")
    public String purchaseProductDetail(Model model, Date startdate, Date enddate,Integer orgid){
        if(startdate==null||startdate.equals("")){
            startdate = new Date();
        }
        if(enddate==null||enddate.equals("")){
            enddate = new Date();
        }
        List<HashMap<String,Object>> list = chartsService.purchaseProductDetail(orgid,startdate,enddate);
        model.addAttribute("list",list);
        model.addAttribute("startdate",startdate);
        model.addAttribute("enddate",enddate);
        model.addAttribute("orgid",orgid);
        return "charts/productDetail";
    }

    @RequestMapping("/toExchangeOrderData")
    public String toExchangeOrderData(Model model, String startTime, String endTime,Integer orgId,Integer type){

        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        model.addAttribute("orgId",orgId);
        model.addAttribute("type",type);
        return "charts/echangeOrderCountData";
    }

    @RequestMapping("/purchaseProductExport")
    public void purchaseProductExport(String startdate, String enddate, Integer orgid, HttpServletResponse response){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date start = format.parse(startdate);
            Date end = format.parse(enddate);
            String filename = "订货方-商品报表-" + System.currentTimeMillis() +".xls";
            HSSFWorkbook workbook = chartsService.purchasseProductExport(orgid,start,end,false);
            chartsService.buildExcelDocument( filename, workbook, response );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/orderProductExport")
    public void orderProductExport(String startdate, String enddate, HttpServletResponse response,String id){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date start = format.parse(startdate);
            Date end = format.parse(enddate);
            String filename = "订货方-商品报表统计-" + System.currentTimeMillis() +".xls";
            HSSFWorkbook workbook = chartsService.orderProductExport(start,end,id.equals("")?null:Integer.valueOf(id));
            chartsService.buildExcelDocument( filename, workbook, response );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deliverOrderProductExport")
    public void deliverOrderProductExport(Integer id, HttpServletResponse response){
        try {
            TbDeliverForm tbDeliverForm=deliverService.selectDeliverFormById(id);
            tbDeliverForm.setExportMark(1);
            deliverService.updateExportMark(tbDeliverForm);
            TbOrgInfo  supplyOrg = organizeService.getOne(tbDeliverForm.getSupplyOrgId(), null);
            TbOrgInfo  purchaseOrg = organizeService.getOne(tbDeliverForm.getPurchaseOrgId(), null);
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



    @RequestMapping("/supplierProductDetail")
    public String supplierProductDetail(Model model, Date startdate, Date enddate,Integer orgid){
        if(startdate==null||startdate.equals("")){
            startdate = new Date();
        }
        if(enddate==null||enddate.equals("")){
            enddate = new Date();
        }
        //service 方法通用
        List<HashMap<String,Object>> list = chartsService.supplierProductDetail(orgid,startdate,enddate);
        model.addAttribute("list",list);
        model.addAttribute("startdate",startdate);
        model.addAttribute("enddate",enddate);
        model.addAttribute("orgid",orgid);
        return "charts/supplierProductDetail";
    }

    @RequestMapping("/supplierProductExport")
    public void supplierProductExport(String startdate, String enddate, Integer orgid, HttpServletResponse response){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date start = format.parse(startdate);
            Date end = format.parse(enddate);
            String filename = "供货方-商品报表-" + System.currentTimeMillis() +".xls";
            HSSFWorkbook workbook = chartsService.purchasseProductExport(orgid,start,end,true);
            chartsService.buildExcelDocument( filename, workbook, response );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
