package com.zjjzfy.common.utils;

import com.zjjzfy.pojo.TbDeliverForm;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.dto.ProductDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author : addict
 * date:  2020/2/29/029  11:55
 * @description: 导出Excel
 */
public class ExcelUtil {


    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

     /**
      * @Description  ：发货单导出
      * @author       : addict
      * @param        :
      * @return       :
      * @date         : 2020/3/3/003 16:07
      */
    public static HSSFWorkbook deliverOrderProductExport(TbDeliverForm tbDeliverForm, TbOrgInfo supplyOrg, TbOrgInfo purchaseOrg, List<ProductDto> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String str = "发货单-商品详情";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (list.size() > 0) {
            HSSFSheet sheet = workbook.createSheet(str);
            HSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);

            HSSFFont fonts = workbook.createFont();
            fonts.setColor(HSSFColor.BLACK.index);
            fonts.setFontHeightInPoints((short) 11);
            fonts.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(fonts);

            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 14);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCellStyle style1 = workbook.createCellStyle();
            style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style1.setFont(font);

            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            HSSFFont fontss = workbook.createFont();
            fontss.setColor(HSSFColor.BLACK.index);
            fontss.setFontHeightInPoints((short) 11);
            fontss.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style2.setFont(fontss);


            CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 4);
            sheet.addMergedRegion(region1);
            HSSFRow row = sheet.createRow(0);
            row.setHeightInPoints(30);
            HSSFCell cell = row.createCell(0);
            cell = row.createCell(0);
            cell.setCellStyle(style1);
            cell.setCellValue(supplyOrg.getOrgName() + "发货单");
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region1, sheet,workbook);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region1, sheet,workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region1, sheet,workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region1, sheet,workbook);

            CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 4);
            sheet.addMergedRegion(region2);
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellStyle(style2);
            cell.setCellValue("订货方：" + purchaseOrg.getOrgName() + "   订单号：" + tbDeliverForm.getPurchaseBillno()+"  电话："+purchaseOrg.getTelephone());
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region2, sheet,workbook);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region2, sheet,workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region2, sheet,workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region2, sheet,workbook);


            CellRangeAddress region3 = new CellRangeAddress(2, 2, 0, 4);
            sheet.addMergedRegion(region3);
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellStyle(style2);
            cell.setCellValue("供货方：" + supplyOrg.getOrgName()+"              电话："+supplyOrg.getTelephone());
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region3, sheet,workbook);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region3, sheet,workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region3, sheet,workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region3, sheet,workbook);

            CellRangeAddress region4 = new CellRangeAddress(3, 3, 0, 4);
            sheet.addMergedRegion(region4);
            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellStyle(style2);
            cell.setCellValue("发货单号：" + tbDeliverForm.getDeliverBillno() + "        发货时间：" + format.format(tbDeliverForm.getDeliverDate()));
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region4, sheet,workbook);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region4, sheet,workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region4, sheet,workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region4, sheet,workbook);

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
                cell.setCellStyle(style2);
                cell.setCellValue(list.get(i).getName());
                cell = row.createCell(1);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getDeliverQuantity());
                cell = row.createCell(2);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getUnit());
                cell = row.createCell(3);
                cell.setCellStyle(style);
                cell.setCellValue(list.get(i).getUnitExplan());
                cell = row.createCell(4);
                cell.setCellStyle(style);
                if(null!=list.get(i).getUnitExplan() && !list.get(i).getUnitExplan().equals("")  ){
                    if(null !=list.get(i).getUnitExplan()){
                int num=Integer.valueOf(list.get(i).getUnitExplan().split("\\*")[1]);
                cell.setCellValue(list.get(i).getDeliverQuantity()/num);}

                }else {
                    cell.setCellValue("");
                }

                sheet.autoSizeColumn(i);
            }

            CellRangeAddress region5 = new CellRangeAddress(4 + list.size() + 1, 4 + list.size() + 1, 0, 4);

            sheet.addMergedRegion(region5);
            row = sheet.createRow(4 + list.size() + 1);
            row.setHeightInPoints(30);
            cell = row.createCell(0);
            cell.setCellStyle(style2);
            cell.setCellValue("发货人签字                                                收货人签字");
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region5, sheet,workbook);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region5, sheet,workbook);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region5, sheet,workbook);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region5, sheet,workbook);
      /*      CellRangeAddress region6 = new CellRangeAddress(4 + list.size() + 2, 4 + list.size() +2, 0, 4);
            sheet.addMergedRegion(region6);
            row = sheet.createRow(4 + list.size() + 2);
            row.setHeightInPoints(50);
            cell = row.createCell(0);
            cell.setCellValue("收货人签字");*/

            sheet.setColumnWidth(0,38*256);
            sheet.setColumnWidth(1,10*256);
            sheet.setColumnWidth(4,10*256);


        }
        return workbook;
    }


}
