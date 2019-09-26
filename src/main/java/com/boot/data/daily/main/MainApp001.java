package com.boot.data.daily.main;

import com.alibaba.fastjson.JSON;
import com.boot.data.util.DateUtils;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 98548
 * @create 2019-09-10 11:47
 * @description
 */
public class MainApp001 {

    static ScheduledThreadPoolExecutor schedule;

    volatile static Map<String, Date> dateMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        schedule = new ScheduledThreadPoolExecutor(1, ThreadPoolUtil.buildThreadFactory("test-"));
        init();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20000);
                    update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        invoke(3);
    }

    private static synchronized void init() {
        LocalDateTime now = LocalDateTime.now();
        dateMap.put("1", DateUtils.localDateTime2Date(now.plusSeconds(5)));
        dateMap.put("2", DateUtils.localDateTime2Date(now.plusSeconds(-1)));
        dateMap.put("3", DateUtils.localDateTime2Date(now.plusSeconds(10)));
        sort();
        System.out.println("init:" + JSON.toJSONString(dateMap));

    }

    private static synchronized void update() {
        dateMap.put(String.valueOf(Math.random()), DateUtils.localDateTime2Date(LocalDateTime.now()));
        sort();
        System.out.println("update:" + JSON.toJSONString(dateMap));
    }

    private static void sort() {
        ArrayList<Map.Entry<String, Date>> entries = new ArrayList<>(dateMap.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<String, Date> tempMap = new LinkedHashMap<>();
        entries.forEach(entry -> tempMap.put(entry.getKey(), entry.getValue()));
        dateMap = tempMap;
    }

    static void invoke(int delaySeconds) {
        schedule.schedule(() -> {
            int nextDelay = 3;
            Iterator<Map.Entry<String, Date>> iterator = dateMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Date> entry = iterator.next();
                Date date = new Date();
                long time = date.getTime();
                Date date0 = entry.getValue();
                System.out.println(entry.getKey());
                if (time - date0.getTime() > 10000) {
                    System.out.println("超时了!对比时间是" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(date0) + "现在是:" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(date));
                    iterator.remove();
                } else {
                    System.out.println("未超时!对比时间是" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(date0) + "现在是:" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(date));

                }
            }
            invoke(nextDelay);

        }, delaySeconds, TimeUnit.SECONDS);
    }
}
