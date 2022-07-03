package com.zjjzfy.mgt.controller;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.ProductCheckStatus;
import com.zjjzfy.common.utils.ExportUtil;
import com.zjjzfy.pojo.TbProduct;
import com.zjjzfy.product.service.impl.ProductService;
import com.zjjzfy.repository.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyx
 * @date 2020/6/15 11:42 上午
 */
@Slf4j
@RequestMapping("/mgt")
@Controller
public class ExportController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProductService productService;

    /**
     * 商品库存导出
     * @param response
     */
    @RequestMapping("/ShopRepositoryExportExcel")
    public void ShopRepositoryExportExcel(HttpServletResponse response,Integer supplyOrgId,String brand,String goodsName) {
        try {
            if((supplyOrgId+"")==""){
                supplyOrgId = null;
            }

            List<Map<String,Object>> data =  (List<Map<String,Object>>)repositoryService.queryRepository(supplyOrgId,brand,1,1000,goodsName).getData();;
            String content = "";
            //导出EXCEL
            String sheetName = "商品库存导出";
            String[] title = {"供货商", "商品", "品牌 ","库存数量","历史库存"};
            String[] column = {"orgName", "pName","brand", "num","historyNum"};
            String fileName = "ShopRepository.xlsx";

            ExportUtil.export(response, fileName, sheetName, title, column, data,content);
        } catch (Exception e) {
            log.info("==== 商品库存导出 失败 ====");
            e.printStackTrace();
            throw new RuntimeException("商品库存导出 失败");
        }
    }
    /**
     * 商品审核导出
     * @param response
     */
    @RequestMapping("/ShopCheckExportExcel")
    public void ShopCheckExportExcel(HttpServletResponse response,Integer index,String name) {
        try {
            List<Map<String,Object>> mapList = new ArrayList<>();
           if(index == 0){
               List<Byte> statusList = new ArrayList<>();
               statusList.add(ProductCheckStatus.UNCHECK.getStatus());
               List<TbProduct> list = productService.selectProduct(null, name, null, statusList, null, false, 1, 1000).getList();

               list.forEach(e->{
                   Map map = new HashMap();
                   map.put("1",1);
                   mapList.add(map);
               });
           }

           // List<Map<String,Object>> data =  (List<Map<String,Object>>)repositoryService.queryRepository(supplyOrgId,brand,1,1000,goodsName).getData();;
            String content = "";
            //导出EXCEL
            String sheetName = "商品审核导出";
            String[] title = {"供货商", "商品", "品牌 ","库存数量","历史库存"};
            String[] column = {"orgName", "pName","brand", "num","historyNum"};
            String fileName = "ShopRepository.xlsx";

            ExportUtil.export(response, fileName, sheetName, title, column, mapList,content);
        } catch (Exception e) {
            log.info("==== 商品审核导出 失败 ====");
            e.printStackTrace();
            throw new RuntimeException("商品审核导出 失败");
        }
    }
}
