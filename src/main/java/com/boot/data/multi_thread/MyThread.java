package com.boot.data.multi_thread;

/**
 * @author 98548
 * @create 2019-05-08 17:41
 * @description
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(this.currentThread().getName());
    }
}
