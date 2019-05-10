package com.boot.data.multi_thread;

/**
 * @author 98548
 * @create 2019-05-08 17:42
 * @description
 */
public class MainApp {
    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();

        Runnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable, "t1");
        thread1.start();

       int i = 1;
        System.out.println(i++);
        System.out.println(i);

        int j = 1;
        System.out.println(++j);
        System.out.println(j);
    }
}
