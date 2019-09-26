package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.bean.OutResult;
import com.boot.data.bean.OutResult.Detectedbox;
import com.boot.data.bean.OutResult.Imagesize;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2019-09-03 10:53
 * @description
 */
@Slf4j
public class MainApp025 {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    static LinkedHashSet set = new LinkedHashSet();
    static int i = 1;

    private LinkedHashSet<String> strSet = new LinkedHashSet<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                synchronized (MainApp025.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error("a------", e);
                    }
                    set.add(i++);
                    threadLocal.set(i);
                    System.out.println("a------" + JSON.toJSONString(set) + "---" + i + "---threadlocal:" + threadLocal.get());
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (MainApp025.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error("b-------------", e);
                    }
                    set.add(i--);
                    threadLocal.set(i);
                    System.out.println("b------" + JSON.toJSONString(set) + "---" + i + "---threadlocal:" + threadLocal.get());
                }
            }
        }).start();

    }

    @Data
    static class Element {
        private Integer times;
        private String name;
    }

    @Test
    public void test001() {


        Detectedbox detectedbox = new Detectedbox("1", 1.00, 1, 2, 3, 4);
        Detectedbox detectedbox1 = new Detectedbox("2", 2.00, 1, 2, 3, 4);
        Detectedbox detectedbox2 = new Detectedbox("3", 3.00, 1, 2, 3, 4);
        Detectedbox detectedbox3 = new Detectedbox("4", 4.00, 1, 2, 3, 4);
        List<Detectedbox> list = Arrays.asList(detectedbox, detectedbox1, detectedbox2, detectedbox3);

        OutResult result = new OutResult("result-01", new Date().toString(), new Imagesize(2040, 1080), list);

        System.out.println(JSON.toJSONString(result));

        LinkedHashSet<Detectedbox> set = new LinkedHashSet<>();
        set.addAll(list);

        System.out.println(JSON.toJSONString(set));
    }

    @Test
    public void test002() {
        String str = "[{\"id\":\"1\",\"score\":1,\"x1\":1,\"x2\":3,\"y1\":2,\"y2\":4},{\"id\":\"2\",\"score\":2,\"x1\":1,\"x2\":3,\"y1\":2,\"y2\":4},{\"id\":\"3\",\"score\":3,\"x1\":1,\"x2\":3,\"y1\":2,\"y2\":4},{\"id\":\"4\",\"score\":4,\"x1\":1,\"x2\":3,\"y1\":2,\"y2\":4}]";

        List<Detectedbox> list = JSON.parseArray(str, Detectedbox.class);
        System.out.println(list);
        for (int i1 = 0; i1 < 3; i1++) {
            list.stream().forEach(bean -> bean.setTimes(bean.getTimes() + 1));
            list = list.stream().filter(bean -> bean.getTimes() < 3).collect(Collectors.toList());
            System.out.println(JSON.toJSONString(list));
        }

    }

    @Test
    public void test003() {
        int i = 1;
        System.out.println(i++);
        System.out.println(i);


        System.out.println(++i);
        System.out.println(i);
    }

    @Test
    public void test004() {


    }

}
