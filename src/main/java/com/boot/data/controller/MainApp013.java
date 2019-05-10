package com.boot.data.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Currency;

/**
 * @author 98548
 * @create 2019-04-23 10:45
 * @description
 */
public class MainApp013 {
    public static void main(String[] args) throws IOException {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int i1 = 0; i1 < 100; i1++) {
                for (int i2 = 0; i2 < 100; i2++) {
                    System.out.println("1111111111111111");
                }
            }
        }
        long t1 = System.currentTimeMillis();
        System.out.println((double) (t1 - t0) / 1000);
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
