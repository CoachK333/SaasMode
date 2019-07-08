package com.boot.data.controller;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
@Slf4j
public class MainApp022 {

    @Test
    public void test1() {
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();
        int x = month.maxLength();
        System.out.println(x);


        int i0 = now.getDayOfWeek().getValue();

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
//            sheet.createRow()
        } catch (Exception e) {
            log.error("文件生成失败: {}", e);
        }


    }

    @Test
    public void test2() {
        LocalDate now = LocalDate.now();
        int pLines = countPLine(now);
        //pLines * 4 + 1 行

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {

            HSSFSheet sheet = workbook.createSheet();

            HSSFRow row001 = sheet.createRow(0);
            HSSFCell row001Cell001 = row001.createCell(0);
            row001Cell001.setCellValue("周日");
            HSSFCell row001Cell002 = row001.createCell(1);
            row001Cell002.setCellValue("周一");
            HSSFCell row001Cell003 = row001.createCell(2);
            row001Cell003.setCellValue("周二");
            HSSFCell row001Cell004 = row001.createCell(3);
            row001Cell004.setCellValue("周三");
            HSSFCell row001Cell005 = row001.createCell(4);
            row001Cell005.setCellValue("周四");
            HSSFCell row001Cell006 = row001.createCell(5);
            row001Cell006.setCellValue("周五");
            HSSFCell row001Cell007 = row001.createCell(6);
            row001Cell007.setCellValue("周六");
            now = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
            int days = days4Month(now);

            for (int i = 1; i <= days; i++) {
                HSSFRow row_01 = sheet.getRow((i / 7) * 5 + 1);
                if (row_01 == null) {
                    row_01 = sheet.createRow((i / 7) * 5 + 1);
                }
                HSSFCell row_01Cell_01 = row_01.createCell(day2Index(now));
                row_01Cell_01.setCellValue(now.getDayOfMonth());
                now = now.plusDays(1);
            }

            File file = new File("temp/hehe_" + System.currentTimeMillis() + ".xls");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            workbook.write(file);
            log.info(file.getName());
        } catch (IOException e) {
            log.error("文件生成失败: {}", e);
        }

    }

    private int countPLine(LocalDate date) {
        LocalDate month0 = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        int days = days4Month(date);
        int i = day2Index(month0);
        double d1 = (double) (days - (7 - i)) / 7;
        int plines = (int) Math.ceil(d1) + 1;

        return plines;
    }

    private int day2Index(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        int i = 0;
        switch (dayOfWeek) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
            case 5:
                i = 5;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 0;
                break;
        }
        return i;
    }

    private int days4Month(LocalDate date) {
        LocalDate month0 = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate month_end = month0.plusMonths(1).minusDays(1);
        return (int) (month_end.toEpochDay() - month0.toEpochDay() + 1);
    }

    @Test
    public void test3() throws EncoderException {
        File file = new File("C:\\Users\\98548\\Downloads\\张宇 - 给你们.mp3");
        MultimediaInfo media = new Encoder().getInfo(file);
        String format = media.getFormat();
        long duration = media.getDuration();
        System.out.println(format);
        System.out.println(duration);
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<>();
        System.out.println(list.stream()
                .limit(3)
                .collect(Collectors.joining("、")));
    }



}
