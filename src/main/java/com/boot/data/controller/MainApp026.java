package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.util.DateUtils;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 98548
 * @create 2019-09-04 21:26
 * @description
 */
public class MainApp026 {

    static ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1, ThreadPoolUtil.buildThreadFactory("car-vip"));

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("呵呵");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("哈哈");
        }).start();
        System.out.println("????????????????");
    }

    static void fun1() {
        schedule.schedule(() -> {
            System.out.println("呵呵呵123");
            fun1();
        }, 2, TimeUnit.SECONDS);
    }

    private static void fun2() {
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "在运行...");
            }
        }).start();

        while (true) {
            System.out.println(Thread.currentThread().getName() + "也在运行---");
        }
    }

    private static void fun3() {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(1);
        ScheduledThreadPoolExecutor bakExecutor = new ScheduledThreadPoolExecutor(1, ThreadPoolUtil.buildThreadFactory("car-analysis"));
        System.out.println(Thread.currentThread().getName() + " is OK!");
        bakExecutor.schedule(() -> {
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, ThreadPoolUtil.buildThreadFactory("inner"));
            if (LocalDateTime.now().isBefore(localDateTime)) {
                executor.scheduleWithFixedDelay(() -> {
                    System.out.println(Thread.currentThread().getName());
                }, 3, 3, TimeUnit.SECONDS);
            } else {
                executor.shutdownNow();
            }
        }, 3, TimeUnit.SECONDS);
    }

    @Test
    public void test001() {
        System.out.println(new Date().getTime() + 1000 * 3600 * 24 * 365);
    }

    @Test
    public void test002() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        System.out.println(list.remove("11"));
    }

    @Test
    public void test003() {
        System.out.println(CameraType.HIK.name().equals("HIK"));
        System.out.println(CameraType.HIK.toString());
    }

    public enum CameraType {
        HIK, DAHUA

    }

    @Test
    public void test004() {
        List<Date> dates = Arrays.asList(new Date(), DateUtils.localdateToDate(LocalDate.now().plusDays(1)));
        dates.sort(Comparator.comparingLong(Date::getTime).reversed());
        System.out.println(JSON.toJSONString(dates));
        System.out.println(new Date().compareTo(DateUtils.localdateToDate(LocalDate.now().plusDays(1))));
    }

    @Test
    public void test005() {

        Map<Integer, String> map = new TreeMap<>((o1, o2) -> o1.compareTo(o2));

        map.put(3, "3");
        map.put(2, "3");
        map.put(6, "3");
        map.put(7, "3");
        map.put(123, "3");

        System.out.println(map);


    }

    @Test
    public void test006() {
        Map<String, Date> map = new LinkedHashMap<>();
        map.put("1", DateUtils.localdateToDate(LocalDate.now()));
        map.put("2", DateUtils.localdateToDate(LocalDate.now().plusDays(1)));
        map.put("3", DateUtils.localdateToDate(LocalDate.now().plusDays(-1)));
        map.put("4", DateUtils.localdateToDate(LocalDate.now().plusDays(2)));
        map = sort(map);
        System.out.println(map);
    }

    public Map<String, Date> sort(Map<String, Date> map) {
        ArrayList<Map.Entry<String, Date>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Comparator.comparing(o -> o.getValue().getTime()));
        Map<String, Date> map1 = new ConcurrentHashMap<>();
        entries.forEach(entry -> {
            map1.put(entry.getKey(), entry.getValue());
        });
        return map1;
    }

    @Test
    public void test007() {
        LinkedHashSet set = new LinkedHashSet();

        set.add(new CarInfo("123"));
        set.add(new CarInfo("23234"));
        CarInfo e = new CarInfo("123");
        e.setEnteredForbiddenArea(1000);
        set.add(e);

        set.forEach(System.out::println);
    }

    @Test
    public void test008() throws InterruptedException {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(10);
        while (LocalDateTime.now().isBefore(localDateTime)) {
            System.out.println("thanks");
            Thread.sleep(1000);
        }
        System.out.println("OK");
    }

    @Test
    public void test009() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test010() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(3000);
        long millis = Duration.between(now, LocalDateTime.now()).toMillis();
        System.out.println((double) millis / 1000);
    }

    @Test
    public void test011() {
        String s = "12345";
        System.out.println(s.substring(1));
    }

    @Test
    public void test012() {
        List<Integer> list = Arrays.asList(3, 4, 6, 1, 3, 2);
        list.sort((i1, i2) -> i1.compareTo(i2));
        System.out.println(list);
    }

    @Test
    public void test013() {
        Date date = new Date();
        System.out.println(date.getTime());

        Date date1 = DateUtils.localDateTime2Date(LocalDateTime.now().plusSeconds(3));
        System.out.println(date1.getTime() - date.getTime());
    }

    @Test
    public void test014() {
        String s = "圣诞节和覅%s";
        String s1 = String.format(s, 1);
        System.out.println(s1);
    }
}
