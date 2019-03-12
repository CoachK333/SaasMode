package com.boot.data.controller;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 98548
 * @create 2019-03-04 12:15
 * @description
 */
public class MainApp009 {
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
