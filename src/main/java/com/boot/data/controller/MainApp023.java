package com.boot.data.controller;

import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 98548
 * @create 2019-08-28 19:25
 * @description
 */
public class MainApp023 {

    public static void main(String[] args) {

//        new ThreadPoolExecutor(2, 5,10,TimeUnit.SECONDS);



    }

    private static void fun1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("Time:");
            }
        }, 2000, 400);
    }

    private static void fun2() {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2, ThreadPoolUtil.buildThreadFactory("wxq"));
        poolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName());
        }, 2, 1, TimeUnit.SECONDS);
    }
}
