package com.boot.data.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.boot.data.entity.DutyScheduling;
import com.boot.data.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 98548
 * @create 2019-03-04 12:15
 * @description
 */
public class MainApp007 {
    public static void main(String[] args) {

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\98548\\Desktop\\temp - 副本.xls");) {

            Workbook workbook = new HSSFWorkbook(fileInputStream);
            Sheet sheet001 = workbook.getSheetAt(0);
            int i0 = sheet001.getFirstRowNum();
            int iend = sheet001.getLastRowNum();

            System.out.println(i0);
            System.out.println(iend);

            Row row001 = sheet001.getRow(i0);
            short j0 = row001.getFirstCellNum();
            short jend = row001.getLastCellNum();

            System.out.println(j0);
            System.out.println(jend);

            Map map = new HashMap();

            String date_prefix = row001.getCell(j0).getStringCellValue();
            String dayStr = row001.getCell(1).getStringCellValue();
            String yearStr = date_prefix.substring(0, 4);
            String monthStr = date_prefix.substring(4, 6);

            LocalDate startDate = LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), Integer.parseInt(dayStr));
            map.put("satrtDate", startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            LocalDate endDate = startDate.plusDays(7);
            map.put("endDate", endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            List<VOExcelData> list = new ArrayList();
            Set<String> dutyTypes = new HashSet<>();
            for (int i = i0 + 2; i <= iend; i++) {
                Row row = sheet001.getRow(i);
                startDate = LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), Integer.parseInt(dayStr));
                for (short j = (short) (j0 + 1); j < jend; j++) {
                    VOExcelData voExcelData = new VOExcelData();

                    voExcelData.setDate(DateUtils.localdateToDate(startDate));
                    voExcelData.setName(row.getCell(j0).getStringCellValue());
                    String content = row.getCell(j).getStringCellValue();
                    if (StringUtils.isNotBlank(content) && !"0".equals(content)) {
                        voExcelData.setContent(content);
                        dutyTypes.add(content);
                        list.add(voExcelData);
                    }
                    startDate = startDate.plusDays(1);
                }
            }
            map.put("data", list);
            System.out.println(JSON.toJSON(map));

            Map<String, String[]> map1 = new HashMap<>();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
