package com.boot.data.util;

import com.boot.data.Vo.DateVo;
import com.boot.data.controller.MainApp012;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-03-05 11:41
 * @description
 */
public class DateUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void autoFillTimeStr(DateVo dateVo) {
        String startDateStr = dateVo.getStartDateStr();
        String endDateStr = dateVo.getEndDateStr();
        //起止时间都为空,默认填充当前时间前一个月
        if (org.apache.commons.lang3.StringUtils.isAllBlank(startDateStr, endDateStr)) {
            LocalDate nowDate = LocalDate.now();
            LocalDateTime dateTime = LocalDateTime.of(nowDate, LocalTime.MAX);
            endDateStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDate monthAgo = nowDate.minusMonths(1);
            LocalDateTime aMonthAgoDate = LocalDateTime.of(monthAgo.plusDays(1), LocalTime.MIN);
            startDateStr = aMonthAgoDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else if (org.apache.commons.lang3.StringUtils.isBlank(startDateStr) && org.apache.commons.lang3.StringUtils.isNotBlank(endDateStr)) {
            //结束时间不为空,起始时间:null
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
            LocalDateTime startDate = endDate.minusMonths(1);

            startDateStr = startDate.format(formatter);
            endDateStr = endDate.format(formatter);
        } else if (org.apache.commons.lang3.StringUtils.isBlank(endDateStr) && StringUtils.isNotBlank(startDateStr)) {
            //起始时间不为空,结束时间:null
            LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
            LocalDateTime endDate = startDate.plusMonths(1);

            startDateStr = startDate.format(formatter);
            endDateStr = endDate.format(formatter);
        }

        dateVo.setStartDateStr(startDateStr);
        dateVo.setEndDateStr(endDateStr);
    }

    /**
     * {@link LocalDate} 转 {@link Date}
     *
     * @param localDate
     * @return
     */
    public static Date localdateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }

    /**
     * Date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取当天所在周的周一
     *
     * @param date
     * @return
     */
    public static Date getMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        return calendar.getTime();
    }

    /**
     * 获取当天所在周的周日
     *
     * @param date
     * @return
     */
    public static Date getSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 将时间段按月拆分,如 2019年01月15日------2019年03月15日
     * {2019年01月={startDate=2019-01-15T00:00, endDate=2019-01-31T23:59:59.999999999},
     * 2019年02月={startDate=2019-02-01T00:00, endDate=2019-03-01T23:59:59.999999999},
     * 2019年03月={startDate=2019-03-01T00:00, endDate=2019-03-15T23:59:59.999999999}}
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public static Map<String, Map<String, LocalDateTime>> getMonthInfo(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        DateTimeFormatter dateTimeFormatter001 = DateTimeFormatter.ofPattern("yyyy年MM月");
        Map<String, Map<String, LocalDateTime>> map = new LinkedHashMap<>();
        Map<String, LocalDateTime> monthMap = new LinkedHashMap<>();


        //判断起始日期是否是同一月
        if (startDateTime.getYear() == endDateTime.getYear() && startDateTime.getMonthValue() == endDateTime.getMonthValue()) {
            monthMap.put("startDate", LocalDateTime.of(startDateTime.toLocalDate(), LocalTime.MIN));
            monthMap.put("endDate", LocalDateTime.of(endDateTime.toLocalDate(), LocalTime.MAX));
            map.put(startDateTime.format(dateTimeFormatter001), monthMap);
            return map;
        } else {
            //<起始月>
            monthMap.put("startDate", LocalDateTime.of(startDateTime.toLocalDate(), LocalTime.MIN));
            monthMap.put("endDate", getLastMonthDayByLocalDateTime(startDateTime));
            map.put(startDateTime.format(dateTimeFormatter001), monthMap);

            //<中间月>
            LocalDate startDate0 = getFirstMonthDayByLocalDateTime(startDateTime.plusMonths(1)).toLocalDate();
            LocalDate endDate0 = endDateTime.toLocalDate().minusDays(endDateTime.getDayOfMonth());

            for (LocalDate date = startDate0; date.isBefore(endDate0); date = date.plusMonths(1)) {
                Map<String, LocalDateTime> midMonthMap = new LinkedHashMap<>();
                midMonthMap.put("startDate", getFirstMonthDayByLocalDate(date));
                midMonthMap.put("endDate", getLastMonthDayByLocalDate(date));
                map.put(date.format(dateTimeFormatter001), midMonthMap);
            }
            //<结束月>
            monthMap = new LinkedHashMap<>();
            monthMap.put("startDate", getFirstMonthDayByLocalDateTime(endDateTime));
            monthMap.put("endDate", LocalDateTime.of(endDateTime.toLocalDate(), LocalTime.MAX));
            map.put(endDateTime.format(dateTimeFormatter001), monthMap);

            return map;
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

    /**
     * 获取两个时间点 之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long countDays(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.toLocalDate().toEpochDay() - startDate.toLocalDate().toEpochDay();
    }
}