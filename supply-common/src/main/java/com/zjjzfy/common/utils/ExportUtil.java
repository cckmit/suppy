package com.zjjzfy.common.utils;

import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通用导出
 *
 * @author yinzi
 */
public class ExportUtil {

    public static void export(HttpServletResponse response, String fileName,
                              String sheetName, String[] title, String[] column,
                              List<Map<String, Object>> maps, String content) throws Exception {

        XSSFWorkbook wb = getXSSFWorkbook(sheetName, title, column, maps, content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        //响应到客户端
        setResponseHeader(response, sdf.format(currentDate) + "_" + fileName);
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

    /**
     * 发送响应流方法
     *
     * @param response
     * @param fileName
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 返回Workbook
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param column    content中获取value的key
     * @param maps      内容
     * @return
     */
    public static XSSFWorkbook getXSSFWorkbook(String sheetName, String[] title, String[] column,
                                               List<Map<String, Object>> maps,
                                               String content) throws Exception {

        // 第一步，创建一个XSSFWorkbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        //声明列对象
        XSSFCell cell = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        //第一行
        cell = row.createCell(0);
        cell.setCellValue("导出日期：" + sdf.format(currentDate));
        cell.setCellStyle(style);
        if (content != null && !"".equals(content)) {
            cell = row.createCell(1);
            cell.setCellValue(content);
            cell.setCellStyle(style);
        }

        //创建标题
        row = sheet.createRow(1);
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        Map<String, Object> map;
        String key;
        //创建内容
        if (column.length > 0 && maps != null && maps.size() > 0) {
            for (int i = 0; i < maps.size(); i++) {
                row = sheet.createRow(i + 2);
                for (int j = 0; j < column.length; j++) {
                    key = column[j];
                    map = maps.get(i);

                    row.createCell(j).setCellValue(check(map.get(key)));
                }
            }
        }

        return wb;
    }

    public static String check(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}