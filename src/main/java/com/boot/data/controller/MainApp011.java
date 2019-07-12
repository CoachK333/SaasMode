package com.boot.data.controller;

import com.boot.data.util.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp011 {

    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, ParseException {


        LocalDate startDate = LocalDate.parse("20190305", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse("20190306", DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDateTime startTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(endDate, LocalTime.MAX);

        System.out.println(DateUtils.localDateTime2Date(startTime));
        System.out.println(DateUtils.localDateTime2Date(endTime));

        System.out.println(DateUtils.localdateToDate(startDate));
        System.out.println(DateUtils.localdateToDate(endDate));

        Map<String, List> map = new HashMap<>();
        List<String> list = Arrays.asList("1", "11", "12831", "2", "87", "1728", "28395t");
        for (String s : list) {
            String c = String.valueOf(s.charAt(0));
            if (!map.containsKey(c)) {
                List<String> list1 = new ArrayList<>();
                list1.add(s);
                map.put(c, list1);
            } else {
                List list1 = map.get(c);
                list1.add(s);
                map.put(c, list1);
            }
        }
        System.out.println(map);

        LocalDate date0 = LocalDate.now();
        LocalDate date1 = LocalDate.now();

        Map<LocalDate, String> map1 = new HashMap<>();

        map1.put(date0, "123");
        System.out.println(map1.containsKey(LocalDate.of(2019, 3, 13)));

        String s = "123";
        Long l = 123L;
        System.out.println(s.equals(l.toString()));

        LocalDate now = LocalDate.now();
        System.out.println(now.equals(now.plusDays(0)));

        Date date = DateUtils.localDateTime2Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        System.out.println(date);

    }
}
