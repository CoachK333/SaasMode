package com.boot.data.controller;

import com.boot.data.entity.User;
import com.boot.data.service.VideoExtend;
import com.boot.data.util.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.boot.data.util.DateUtils.getMonthInfo;

/**
 * @author 98548
 * @create 2019-04-23 10:45
 * @description
 */
public class MainApp014 {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "1");
        map.put("21", "1");
        map.put("12", "1");
        map.put("13", "1");
        map.put("14", "1");
        map.put("51", "1");
        map.put("61", "1");
        map.put("71", "1");
        map.put("41", "1");
        map.put("61", "1");
        map.put("41", "1");
        map.put("21", "1");
        map.put("2341", "1");
        map.put("4631", "1");
        map.put("6541", "1");
        map.put("4571", "1");
        map.put("4671", "1");
        map.put("34521", "1");
        map.put("232341", "1");
        map.put("23432421", "1");
        map.put("4574571", "1");

        System.out.println(map);
    }

    public static void addStyle(Sheet sheet, CellStyle style, CellRangeAddress region) {
        int firstColumn = region.getFirstColumn();
        int lastColumn = region.getLastColumn();
        int firstRow = region.getFirstRow();
        int lastRow = region.getLastRow();
        for (int i = firstColumn; i <= lastColumn; i++) {
            for (int j = firstRow; j <= lastRow; j++) {
                try {
                    sheet.getRow(j).getCell(i).setCellStyle(style);
                } catch (NullPointerException e) {
                    Cell cell = sheet.createRow(j).createCell(i);
                    cell.setCellStyle(style);
                }
            }
        }
    }

    /**
     * 创建字体
     *
     * @param fontName 字体名称
     * @param fontSize 字体大小
     * @param bold     是否加粗
     * @param workbook
     * @return
     */
    public static HSSFFont createFont(String fontName, Short fontSize, Boolean bold, HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        font.setBold(bold);
        return font;
    }

}
