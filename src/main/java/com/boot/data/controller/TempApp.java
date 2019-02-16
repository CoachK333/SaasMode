package com.boot.data.controller;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 98548
 * @create 2019-01-03 12:00
 * @description
 */
public class TempApp {
    public static void main(String[] args) throws ParseException {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("title", "测试考勤表");
        paramMap.put("department", "信息中心");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date startDate = dateFormat.parse("2019年01月07日");
        Date endDate = dateFormat.parse("2019年02月06日");

        Date temp = startDate;
        Calendar instance = Calendar.getInstance();
        instance.setTime(temp);

        Map<String, String> map = new LinkedHashMap<>();
        Map<String, Map> dateMap = new HashMap<>();
        for (Date start = startDate; start.getTime() < endDate.getTime(); instance.add(Calendar.DAY_OF_MONTH, 1)) {
            start = instance.getTime();

            Map<String, String> timeMap = new HashMap<>();
            timeMap.put("上午", "△");
            timeMap.put("下午", "C");
            int day = instance.get(Calendar.DAY_OF_MONTH);
            if (instance.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || instance.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                map.put(String.valueOf(day), "1");
                timeMap.put("color", "1");
            } else {
                map.put(String.valueOf(day), "0");
                timeMap.put("color", "0");
            }
            dateMap.put(String.valueOf(day), timeMap);
        }
        paramMap.put("startDate", dateFormat.format(startDate));
        paramMap.put("endDate", dateFormat.format(endDate));
        paramMap.put("days", map);

        Map<String, Map> usermap = new HashMap<>();

        usermap.put("test001", dateMap);
        usermap.put("test002", dateMap);
        usermap.put("test003", dateMap);
        usermap.put("test004", dateMap);
        usermap.put("test005", dateMap);
        usermap.put("test006", dateMap);
        usermap.put("test007", dateMap);


        paramMap.put("userMap", usermap);

        System.out.println(paramMap);
        System.out.println(JSON.toJSONString(paramMap));

    }
}
