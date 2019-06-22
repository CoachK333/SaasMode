package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.util.PoiUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Host;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 98548
 * @create 2019-04-23 10:45
 * @description
 */
@Slf4j
public class MainApp013 {
    public static void main(String[] args) throws Exception {

        test002();
//        test003();
//        test004();
    }

    private static void test004() {

    }

    private static void test003() throws Exception {
        File file1 = new File("C:\\Users\\98548\\Desktop\\temp_1560481070413.xls");

        try (HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file1))) {
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();

            for (int i = 0; i <= rowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = row.getCell(0);
                HSSFCellStyle cellStyle = cell.getCellStyle();
                short fillForegroundColor = cellStyle.getFillForegroundColor();
                System.out.println(i + "-------------------" + fillForegroundColor);
            }
        }
    }

    private static void test002() throws Exception {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {

            HSSFSheet sheet = workbook.createSheet();
            HSSFRow row = sheet.createRow(0);
//            sheet.setColumnWidth(0, (int) (256 * 4.10 + 200));
            int width = (int) 3.5;
            sheet.setDefaultColumnWidth(width);
            HSSFCell cell = row.createCell(0);
            HSSFPalette customPalette = workbook.getCustomPalette();
            customPalette.setColorAtIndex(IndexedColors.LIME.getIndex(), (byte) 238, (byte) 236, (byte) 225);
            HSSFFont font = PoiUtils.createFont("宋体", (short) 11, true, workbook);
            cell.setCellStyle(PoiUtils.createCellStyle(IndexedColors.LIME.getIndex(), FillPatternType.SOLID_FOREGROUND, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, font, true, workbook));
            cell.setCellValue("cnmd");

            File file = new File("tempFiles/excels/temp_" + System.currentTimeMillis() + ".xls");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            workbook.write(file);
            System.out.println(file.getPath());

        } catch (IOException e) {
            log.error("生成excel失败:   {}", e);
            throw new Exception("生成excel失败!");
        }
    }

    private static void digui1(StringBuilder sb) {
        int x = sb.indexOf("\"startDate\":1");
        String substring = sb.substring(x + 12, x + 25);
        Date date = new Date();
        date.setTime(Long.valueOf(substring));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.replace(x + 12, x + 25, "\"" + dateFormat.format(date) + "\"");
        if (sb.indexOf("\"startDate\":1") != -1) {
            digui1(sb);
        } else {
            return;
        }
    }

    private static void digui2(StringBuilder sb) {
        int x = sb.indexOf("\"endDate\":1");
        String substring = sb.substring(x + 10, x + 23);
        Date date = new Date();
        date.setTime(Long.valueOf(substring));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.replace(x + 10, x + 23, "\"" + dateFormat.format(date) + "\"");
        if (sb.indexOf("\"endDate\":1") != -1) {
            digui2(sb);
        } else {
            return;
        }
    }

    static ArrayList<Integer> arr2List(Integer[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        Collections.addAll(list, arr);
        return list;
    }

    static void hehe() {
        //用户目录,即当前执行JAVA命令的目录
        System.out.println(System.getProperty("user.dir"));

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(9090);
        Host host = tomcat.getHost();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            String s = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS").format(LocalDateTime.now()) + new Random().nextInt(10);
            System.out.println("DEM-" + s);
        }
    }

    @Data
    static class User {
        private Boolean aFlag;
        private boolean bFlag;
        private String name;
    }

    public void test001() {
        Date date = new Date();
        List list = new ArrayList();
        list.add(date);
        list.add(DateUtils.addDays(date, 1));
        list.add(DateUtils.addDays(date, 567));
        list.add(DateUtils.addDays(date, 11));
        list.add(DateUtils.addDays(date, -1));
        list.add(DateUtils.addDays(date, -1345));
        list.add(DateUtils.addDays(date, -561));
        list.add(DateUtils.addDays(date, 1678));
        System.out.println(JSON.toJSONString(list));
        Collections.sort(list);
        System.out.println(JSON.toJSONString(list));
        Collections.sort(list, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                if (o1.after(o2)) {
                    return -1;
                } else if (o1.before(o2)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(JSON.toJSONString(list));
    }


    /**
     * 获取当月最后一天 23:59:59.999999999
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getLastMonthDayByLocalDateTime(LocalDateTime dateTime) {
        LocalDate localDate = dateTime.toLocalDate().plusMonths(1);
        LocalDate localDate1 = localDate.minusDays(localDate.getDayOfMonth());
        return LocalDateTime.of(localDate1, LocalTime.MAX);
    }

    /**
     * 获取当月第一天 00:00:00
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getFirstMonthDayByLocalDateTime(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate().minusDays(dateTime.getDayOfMonth() - 1), LocalTime.MIN);
    }

    /**
     * 获取当月最后一天 23:59:59.999999999
     *
     * @param date
     * @return
     */
    public static LocalDateTime getLastMonthDayByLocalDate(LocalDate date) {
        LocalDate localDate = date.plusMonths(1);
        LocalDate localDate1 = localDate.minusDays(localDate.getDayOfMonth());
        return LocalDateTime.of(localDate1, LocalTime.MAX);
    }

    /**
     * 获取当月第一天 00:00:00
     *
     * @param date
     * @return
     */
    public static LocalDateTime getFirstMonthDayByLocalDate(LocalDate date) {
        return LocalDateTime.of(date.minusDays(date.getDayOfMonth() - 1), LocalTime.MIN);
    }

    public static long countDays(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.toLocalDate().toEpochDay() - startDate.toLocalDate().toEpochDay();
    }
}
