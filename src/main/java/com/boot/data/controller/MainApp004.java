package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-01-09 10:47
 * @description
 */
@Slf4j
public class MainApp004 {

    public static void main(String[] args) throws Exception {
        File file = new File("temp_" + System.currentTimeMillis() + ".xls");

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {

            //字体3:(宋体,小四<12>)
            HSSFFont font003 = workbook.createFont();
            font003.setFontName("宋体");
            font003.setFontHeightInPoints((short) 12);
            font003.setBold(true);
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font003);
            //字体4:(Times New Roman,小四<12>,加粗)
            HSSFFont font004 = workbook.createFont();
            font004.setFontName("Times New Roman");
            font004.setFontHeightInPoints((short) 12);
            font004.setBold(true);
            HSSFCellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setFont(font004);

            HSSFSheet sheet = workbook.createSheet();
            HSSFRow row001 = sheet.createRow(0);
            HSSFRow row002 = sheet.createRow(1);

            HSSFCell row001Cell001 = row001.createCell(0);
            HSSFCell row002Cell001 = row002.createCell(0);

            row001Cell001.setCellValue("sanjiao");
            row002Cell001.setCellValue("sanjiao");

            HSSFCellStyle cellStyle2 = row001Cell001.getCellStyle();
            System.out.println(JSON.toJSONString(cellStyle2));

            row001Cell001.setCellStyle(cellStyle);
            row002Cell001.setCellStyle(cellStyle1);

            workbook.write(file);

            System.out.println("文件生成成功!" + file.getPath());
        } catch (Exception e) {
            log.error("文件生成失败,{}", e);
        }


    }
}
