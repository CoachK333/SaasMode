package com.boot.data.controller;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 98548
 * @create 2019-01-04 17:30
 * @description
 */
public class MainApp001 {
    public static void main(String[] args) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        Map<String, Map> dateMap = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.set(2019, 0, 8, 13, 0, 0);
        Date startDate = instance.getTime();
        int i = instance.get(Calendar.AM_PM);
        Map<String, String> timeMap = new HashMap<>();
        if (i == 0) {
            timeMap.put("上午", "***");
        }
        timeMap.put("下午", "***");
        dateMap.put(String.valueOf(instance.get(Calendar.DAY_OF_MONTH)), timeMap);

        instance.add(Calendar.DAY_OF_MONTH, 5);
        Date endDate = instance.getTime();
        int j = instance.get(Calendar.AM_PM);
        timeMap = new HashMap<>();
        timeMap.put("上午", "***");
        if (j == 1) {
            timeMap.put("下午", "***");
        }
        dateMap.put(String.valueOf(instance.get(Calendar.DAY_OF_MONTH)), timeMap);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date startD = calendar.getTime();

        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endD = calendar.getTime();

        calendar.setTime(startD);

        for (Date temp = startD; temp.getTime() < endD.getTime(); calendar.add(Calendar.DAY_OF_MONTH, 1)) {
            temp = calendar.getTime();
            timeMap = new HashMap<>();
            timeMap.put("上午", "123");
            timeMap.put("下午", "132");
            dateMap.put(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), timeMap);
        }

        System.out.println(JSON.toJSONString(dateMap));

    }
}
