package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import it.sauronsoftware.jave.AudioUtils;
import org.springframework.http.HttpMethod;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 98548
 * @create 2019-04-23 10:45
 * @description
 */
public class MainApp013 {
    public static void main(String[] args) throws IOException, InterruptedException {


    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            String s = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS").format(LocalDateTime.now()) + new Random().nextInt(10);
            System.out.println("DEM-" + s);
        }
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
