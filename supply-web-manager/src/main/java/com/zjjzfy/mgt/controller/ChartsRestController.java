package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.LayuiData;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.mgt.service.ChartsService;
import com.zjjzfy.mgt.service.OrganizeService;
import com.zjjzfy.order.service.DeliverService;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.user.service.UserOrgService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Saintyun on 2019/4/9.
 */
@RestController
@RequestMapping("/mgt")
public class ChartsRestController {
    @Autowired
    private ChartsService chartsService;
    @Autowired
    private OrganizeService organizeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserOrgService userOrgService;
    @Autowired
    private DeliverService deliverService;

    /**
     * 订货方商品图表，第二层
     * @param orgid
     * @param startdate
     * @param enddate
     * @return
     */
    @RequestMapping("/getPurchaseProducts")
    public SupplyResult getPurchaseProducts(Integer orgid, Date startdate, Date enddate){
        if(startdate!=null&&startdate.equals("")){
            startdate = null;
        }
        if(enddate!=null&&enddate.equals("")){
            enddate = null;
        }
        //判断是否是平台方，可以看到所有的
        if(userService.getOrg().getType().equals(OrgType.PLATFORM.getType())){
            orgid = null;
        }
        List<HashMap<String,Object>> list = chartsService.getPurchaseProducts(orgid,startdate,enddate);
        return SupplyResult.ok(list);
    }

    /**
     * 订货方商品图表，第二层
     * @param orgid
     * @param startdate
     * @param enddate
     * @return
     */
    @RequestMapping("/getPurchaseDetailCount")
    public SupplyResult getPurchaseDetail(Integer orgid, Date startdate, Date enddate){
        if(startdate!=null&&startdate.equals("")){
            startdate = null;
        }
        if(enddate!=null&&enddate.equals("")){
            enddate = null;
        }
        List<HashMap<String,Object>> list = chartsService.getPurchaseDetailProducts(orgid,startdate,enddate);
        return SupplyResult.ok(list);
    }

    @RequestMapping("/getProducts")
    public SupplyResult getProducts(){
        return productService.selectOnlineProducts();
    }


    /**
     * 订货方商品图表，第一层
     *
     * @param startdate
     * @param enddate
     * @return
     */
    @RequestMapping("/branchPurchaseCharts")
    public SupplyResult branchPurchaseChartsJson(Date startdate, Date enddate){
        if(startdate!=null&&startdate.equals("")){
            startdate = null;
        }
        if(enddate!=null&&enddate.equals("")){
            enddate = null;
        }
        Integer orgid = null;
        //判断是否是平台方，可以看到所有的
        if(userService.getOrg().getType().equals(OrgType.PLATFORM.getType())){
            orgid = null;
        }else{
            orgid = userService.getOrg().getId();
        }
        List<HashMap<String,Object>> list = chartsService.branchTotalPurchase(orgid,startdate,enddate);
        return SupplyResult.build(200,"成功",list);
    }

    @RequestMapping("/getSupplier")
    public Object supplierList(Integer page, Integer limit){
        TbOrgInfo org = userService.getOrg();
        List<TbOrgInfo> orgs = new ArrayList<>();
        if (org.getType() == OrgType.SUPPLIER.getType()) {
            orgs.add(org);
        } else if (org.getType() == OrgType.PLATFORM.getType()) {
            PageInfo<Map<String, Object>> pages = organizeService.getOrg(null,null,null,null,null,"1",page,limit,null);
            return LayuiData.ok(pages.getList(),pages.getTotal());
        }
        return LayuiData.ok(orgs, Long.valueOf(orgs.size()));
    }


    @RequestMapping("/getProductForm")
    public Object productForm( Date startdate, Date enddate,Integer page, Integer limit,Integer id){
        List<Byte> statusList = new ArrayList<>();
        if(startdate==null||startdate.equals("")){
            startdate = new Date();
        }
        if(enddate==null||enddate.equals("")){
            enddate = new Date();
        }
        PageInfo<Map<String, Object>> pages = organizeService.getOrg(null,null,null,null,null,"2",page,limit,id);
        for(int i=0;i<pages.getList().size();i++){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            pages.getList().get(i).put("date",format.format(startdate)+"--"+format.format(enddate));
        }
        return LayuiData.ok(pages.getList(), pages.getTotal());
    }

    @RequestMapping("getExchageOrderCount")
    public PageInfo getExchageOrderCount(Date startTime,Date endTime,Integer pageNo,Integer pageSize,Integer type,Integer orgId){
        return deliverService.selectExchangeOrderCount(startTime, endTime, pageNo, pageSize,type,orgId);
    }
    @RequestMapping("getExchageOrderCountDataByOrgId")
    public SupplyResult getExchageOrderCountDataByOrgId(Date startTime,Date endTime,Integer orgId,Integer type){
        return deliverService.selectExchageOrderCountDataByOrgId(startTime, endTime,orgId,type);
    }


}
