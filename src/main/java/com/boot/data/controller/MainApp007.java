package com.boot.data.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-02-16 10:55
 * @description
 */
public class MainApp007 {
    public static void main(String[] args) throws ParseException {


        LocalDateTime dateTime = LocalDateTime.parse("2018-12-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int hour = dateTime.getHour();
        System.out.println(hour);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2018-12-01 00:00:00");
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (instance.get(Calendar.HOUR_OF_DAY) == 0 && instance.get(Calendar.MINUTE) == 0 && instance.get(Calendar.SECOND) == 0) {
            instance.add(Calendar.MILLISECOND, -1);
            Date time = instance.getTime();
            System.out.println(sdf.format(time));

        }


    }


}
