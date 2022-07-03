package com.zjjzfy.mgt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjjzfy.interfaces.PublicTableMapper;
import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.dto.ProductDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Saintyun on 2019/4/9.
 */
@Service
public class ChartsService {
    @Autowired
    private PublicTableMapper publicTableMapper;


    public List<HashMap<String, Object>> getPurchaseProducts(Integer orgid, Date startdate, Date enddate) {
        return publicTableMapper.queryProductsTwoDimensionalCharts(orgid, startdate, enddate);
    }

    public List<HashMap<String, Object>> branchPurchase(Integer orgid, Date startdate, Date enddate) {
        return publicTableMapper.queryBranchPurchase(orgid, startdate, enddate);
    }

    public List<HashMap<String, Object>> branchTotalPurchase(Integer orgid, Date startdate, Date enddate) {
        return publicTableMapper.queryBranchTotalPurchase(orgid, startdate, enddate);
    }

    public List<HashMap<String, Object>> getPurchaseDetailProducts(Integer orgid, Date startdate, Date enddate) {
        return publicTableMapper.selectPurchaseDetail(orgid, startdate, enddate);
    }

    public List<HashMap<String, Object>> purchaseProductDetail(Integer orgid, Date startdate, Date enddate) {
        return publicTableMapper.purchaseProductDetail(orgid, startdate, enddate);
    }

    public List<HashMap<String, Object>> supplierProductDetail(Integer orgid, Date startdate, Date enddate) {
        return publicTableMapper.supplierProductDetail(orgid, startdate, enddate);
    }

    public void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public HSSFWorkbook purchasseProductExport(Integer orgid, Date startdate, Date enddate, Boolean issupplier) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        List<HashMap<String, Object>> list = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook();
        String str = "订货单-商品报表";
        if (issupplier) {
            str = "供货商-商品报表";
            list = supplierProductDetail(orgid, startdate, enddate);
        } else {
            list = purchaseProductDetail(orgid, startdate, enddate);
        }
        HSSFSheet sheet = workbook.createSheet(str);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short) 0);
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("起始时间：");
        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue(format.format(startdate));
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("结束时间：");
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue(format.format(enddate));


        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("序号");
        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("网点");
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("商品名称");
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("商品单价");
        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("入库量");
        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("出库量");
        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("库存");
        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("商品金额");
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(2 + i);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(i + 1);
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(String.valueOf(list.get(i).get("org_name")));
            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(String.valueOf(list.get(i).get("name")));
            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(String.valueOf(list.get(i).get("price") + ""));
            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(String.valueOf(list.get(i).get("purchase_quantity")));
            cell = row.createCell(5);
            cell.setCellStyle(style);
            cell.setCellValue(String.valueOf(list.get(i).get("out_quantity")));
            cell = row.createCell(6);
            cell.setCellStyle(style);
            cell.setCellValue(String.valueOf(list.get(i).get("quantity")));
            cell = row.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue("");
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);

        return workbook;
    }

    /**
     * @author addict
     * @description TODO订货统计查询
     * @date 2019/11/27 下午4:22
     */
    public List<HashMap> supplierProductDetails(Date startdate, Date enddate,Integer id) {
        List<HashMap<String, Object>> list = publicTableMapper.supplierProductDetails(startdate, enddate,id);
        List<HashMap<String, Object>> list1 = publicTableMapper.selectProuct(startdate, enddate);
        List<HashMap<String, Object>> list2 = publicTableMapper.selectPurchase(startdate, enddate,id);
        List<HashMap> r = new ArrayList<>();
        HashMap<Object, Integer> item = new HashMap<>();
        for (int i = 0; i < list1.size(); i++) {
            item.put(list1.get(i).get("NAME"), 0);
        }
        for (int j = 0; j < list2.size(); j++) {
            HashMap o = (HashMap) item.clone();
            o.put("name", list2.get(j).get("org_name"));
            o.put("branchno", list2.get(j).get("branchno"));
            //o.put("parent_branchno", list2.get(j).get("parent_branchno"));

           /* if(list2.get(j).get("parent_branchno").equals("-1")){
                 HashMap os=(HashMap)item.clone();
                 os.put("name",list2.get(j).get("org_name")+"总计");
                 os.put("branchno",list2.get(j).get("branchno"));
                 os.put("parent_branchno",list2.get(j).get("parent_branchno"));
                 r.add(os);
             }*/
            r.add(o);
        }

        for (int k = 0; k < list.size(); k++) {
            HashMap<String, Object> rv = list.get(k);
            for (int l = 0; l < r.size(); l++) {
                if (r.get(l).get("name").equals(rv.get("org_name"))) {
                    r.get(l).put(rv.get("NAME"), rv.get("num"));
                }

               /* else if(r.get(l).get("parent_branchno").equals(rv.get("branchno")) || r.get(l).get("branchno").equals(rv.get("branchno"))){
                    r.get(l).put(rv.get("NAME"),rv.get("num"));
                }
*/

            }

        }
        return r;
    }

    public HSSFWorkbook orderProductExport(Date startdate, Date enddate,Integer id) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        List<HashMap> list = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook();
        String str = "订货单-商品报表";
        list = supplierProductDetails(startdate, enddate,id);

        if (list.size() > 0) {
            HSSFSheet sheet = workbook.createSheet(str);
            HSSFCellStyle style = workbook.createCellStyle();
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);

            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell((short) 0);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("起始时间：");
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(format.format(startdate));
            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue("结束时间：");
            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(format.format(enddate));


            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("网点");
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue("机构号");

            List list1 = new ArrayList();
            for (Object key : list.get(0).keySet()) {
                if (!key.equals("name") && !key.equals("branchno")) {
                    list1.add(key);
                }
            }
            for (int n = 0; n < list1.size(); n++) {
                cell = row.createCell(n + 2);
                cell.setCellStyle(style);
                cell.setCellValue(String.valueOf(list1.get(n)));
            }

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(2 + i);
                cell = row.createCell(0);
                cell.setCellStyle(style);
                cell.setCellValue(String.valueOf(list.get(i).get("name")));
                cell = row.createCell(1);
                cell.setCellStyle(style);
                cell.setCellValue(String.valueOf(list.get(i).get("branchno")));
                for (int m = 0; m < list1.size(); m++) {
                    cell = row.createCell(m + 2);
                    cell.setCellStyle(style);
                    cell.setCellValue(String.valueOf(list.get(i).get(list1.get(m))));
                }


                sheet.autoSizeColumn(i);
            }


        }
        return workbook;
    }


    public HSSFWorkbook deliverOrderProductExport(TbDeliverForm tbDeliverForm, String supplyOrgName, String purchaseOrgName, List<ProductDto> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String str = "发货单-商品详情";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (list.size() > 0) {
            HSSFSheet sheet = workbook.createSheet(str);
            HSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中


            CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 4);
            sheet.addMergedRegion(region1);
            HSSFRow row = sheet.createRow(0);
            row.setHeightInPoints(50);
            HSSFCell cell = row.createCell(0);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("发货单");

            CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 4);
            sheet.addMergedRegion(region2);
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("订货方：" + purchaseOrgName + "        订单号：" + tbDeliverForm.getPurchaseBillno());


            CellRangeAddress region3 = new CellRangeAddress(2, 2, 0, 4);
            sheet.addMergedRegion(region3);
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("供货方：" + supplyOrgName);

            CellRangeAddress region4 = new CellRangeAddress(3, 3, 0, 4);
            sheet.addMergedRegion(region4);
            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("发货单号：" + tbDeliverForm.getDeliverBillno() + "         发货时间：" + format.format(tbDeliverForm.getDeliverDate()));

            row = sheet.createRow(4);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("商品名称");
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue("发货数量");
            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue("单位");
            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue("规格");
            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue("整箱数量");

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(5 + i);
                cell = row.createCell(0);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getName());
                cell = row.createCell(1);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getDeliverQuantity());
                cell = row.createCell(2);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getUnit());
                cell = row.createCell(3);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getModel());
                cell = row.createCell(4);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getUnitExplan());

                sheet.autoSizeColumn(i);
            }

            CellRangeAddress region5 = new CellRangeAddress(4 + list.size() + 1, 4 + list.size() + 1, 0, 4);
            sheet.addMergedRegion(region5);
            row = sheet.createRow(4 + list.size() + 1);
            row.setHeightInPoints(50);
            cell = row.createCell(0);
            cell.setCellValue("发货人签字");

            CellRangeAddress region6 = new CellRangeAddress(4 + list.size() + 2, 4 + list.size() +2, 0, 4);
            sheet.addMergedRegion(region6);
            row = sheet.createRow(4 + list.size() + 2);
            row.setHeightInPoints(50);
            cell = row.createCell(0);
            cell.setCellValue("收货人签字");


        }
        return workbook;
    }
}
