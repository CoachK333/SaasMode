package com.boot.data.multi_thread;

/**
 * @author 98548
 * @create 2019-05-08 18:47
 * @description
 */
public class MainApp002 {
    public void meth() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MainApp002 mainApp002 = new MainApp002();
        Thread thread001 = new Thread(new Runnable() {
            @Override
            public void run() {
                mainApp002.meth();
            }
        });

        Thread thread002 = new Thread(new Runnable() {
            @Override
            public void run() {
                mainApp002.meth();
            }
        });

        thread001.start();
        thread002.start();
    }
}
