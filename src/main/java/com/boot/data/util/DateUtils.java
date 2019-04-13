package com.boot.data.util;

import com.boot.data.Vo.DateVo;
import com.boot.data.controller.MainApp012;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-05 11:41
 * @description
 */
public class DateUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


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
}
