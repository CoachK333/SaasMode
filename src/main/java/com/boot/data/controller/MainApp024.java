package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 98548
 * @create 2019-09-03 10:53
 * @description
 */
@Slf4j
public class MainApp024 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.info(Thread.currentThread().getName());
            while (true) {
                System.exit(0);
            }
        });
        thread.start();
        thread.join();
        log.info(Thread.currentThread().getName());
    }

}
