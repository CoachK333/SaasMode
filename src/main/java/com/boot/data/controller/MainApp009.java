package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 98548
 * @create 2019-03-04 12:15
 * @description
 */
public class MainApp008 {
    public static void main(String[] args) {

        Date date = new Date();
        Date date1 = DateUtils.setYears(date, 2018);
        Date date2 = DateUtils.setMonths(date1, 8);
        Date date3 = DateUtils.setDays(date2, 8);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(date3));

        String s = "1dfug            ldhfs edfh we hjsd";
        System.out.println(s.replaceAll(" ", ""));

        Integer i = 123;
        System.out.println(i == 123);

        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list.contains("1"));
        String s1 = "1";
        System.out.println(list.contains(s1));
    }
}
