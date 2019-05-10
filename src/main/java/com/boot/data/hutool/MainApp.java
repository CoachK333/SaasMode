package com.boot.data.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.boot.data.controller.AttendanceType;
import com.boot.data.util.DateUtils;
import org.apache.poi.ss.usermodel.Font;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 98548
 * @create 2019-05-08 10:05
 * @description
 */
public class MainApp {
    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        System.out.println(t0);

        Map<String, String> map1_1 = new LinkedHashMap<>();
        map1_1.put("姓名", "张三");
        map1_1.put("日期", "上午");

        Map<String, String> map1_2 = new LinkedHashMap<>();
        map1_2.put("姓名", "");
        map1_2.put("日期", "下午");
        Map<String, String> map2_1 = new LinkedHashMap<>();
        map2_1.put("姓名", "李四");
        map2_1.put("日期", "下午");
        Map<String, String> map2_2 = new LinkedHashMap<>();
        map2_2.put("姓名", "");
        map2_2.put("日期", "下午");
        Map<String, String> map3_1 = new LinkedHashMap<>();
        map3_1.put("姓名", "王五");
        map3_1.put("日期", "下午");
        Map<String, String> map3_2 = new LinkedHashMap<>();
        map3_2.put("姓名", "");
        map3_2.put("日期", "下午");
        Map<String, String> map4_1 = new LinkedHashMap<>();
        map4_1.put("姓名", "赵六");
        map4_1.put("日期", "下午");
        Map<String, String> map4_2 = new LinkedHashMap<>();
        map4_2.put("姓名", "");
        map4_2.put("日期", "下午");
        Map<String, String> map5_1 = new LinkedHashMap<>();
        map5_1.put("姓名", "张小三");
        map5_1.put("日期", "下午");
        Map<String, String> map5_2 = new LinkedHashMap<>();
        map5_2.put("姓名", "");
        map5_2.put("日期", "下午");
        Map<String, String> map6_1 = new LinkedHashMap<>();
        map6_1.put("姓名", "李小四");
        map6_1.put("日期", "下午");
        Map<String, String> map6_2 = new LinkedHashMap<>();
        map6_2.put("姓名", "");
        map6_2.put("日期", "下午");
        Map<String, String> map7_1 = new LinkedHashMap<>();
        map7_1.put("姓名", "王五");
        map7_1.put("日期", "下午");
        Map<String, String> map7_2 = new LinkedHashMap<>();
        map7_2.put("姓名", "");
        map7_2.put("日期", "下午");
        Map<String, String> map8_1 = new LinkedHashMap<>();
        map8_1.put("姓名", "赵小六");
        map8_1.put("日期", "下午");
        Map<String, String> map8_2 = new LinkedHashMap<>();
        map8_2.put("姓名", "");
        map8_2.put("日期", "下午");
        Map<String, String> map9_1 = new LinkedHashMap<>();
        map9_1.put("姓名", "伍子胥");
        map9_1.put("日期", "下午");
        Map<String, String> map9_2 = new LinkedHashMap<>();
        map9_2.put("姓名", "");
        map9_2.put("日期", "下午");
        Map<String, String> map10_1 = new LinkedHashMap<>();
        map10_1.put("姓名", "屈原");
        map10_1.put("日期", "下午");
        Map<String, String> map10_2 = new LinkedHashMap<>();
        map10_2.put("姓名", "");
        map10_2.put("日期", "下午");


        LocalDateTime start_date = LocalDateTime.now();
        LocalDateTime end_date = start_date.plusDays(40);

        LocalDateTime temp_date = start_date;

        long days = DateUtils.countDays(start_date, end_date) + 1;
        AttendanceType[] values = AttendanceType.values();
        for (long i = 1; i <= days; i++) {
            map1_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map1_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map2_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map2_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map3_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map3_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map4_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map4_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map5_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map5_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map6_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map6_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map7_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map7_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map8_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map8_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map9_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map9_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map10_1.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            map10_2.put(temp_date.format(DateTimeFormatter.ofPattern("MM月dd日")), values[(int) (i % 14)].getValue());
            temp_date = temp_date.plusDays(1);
        }


        List<Map<String, String>> list = Arrays.asList(
                map1_1, map1_2,
                map2_1, map2_2,
                map3_1, map3_2,
                map4_1, map4_2,
                map5_1, map5_2,
                map6_1, map6_2,
                map7_1, map7_2,
                map8_1, map8_2,
                map9_1, map9_2,
                map10_1, map10_2
        );


        String destFilePath = "/temp/temp" + System.currentTimeMillis() + ".xls";
        ExcelWriter writer = ExcelUtil.getWriter(destFilePath);
        writer.passCurrentRow();

        writer.merge(map1_1.size() - 1, "测试标题");

        Font font = writer.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setColor(Font.COLOR_RED);
        writer.getStyleSet().setFont(font, true);

        writer.write(list, true);
        writer.close();

        long t1 = System.currentTimeMillis();
        System.out.println(destFilePath);
        System.out.println("耗时: " + (double) (t1 - t0) / 1000 + "s");
    }
}
