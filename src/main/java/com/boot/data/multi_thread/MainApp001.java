package com.boot.data.multi_thread;

/**
 * @author 98548
 * @create 2019-05-08 17:57
 * @description
 */
public class MainApp001 {

    private static int count = 5;

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        Thread thread1 = new Thread(thread, "thread-001");
        Thread thread2 = new Thread(thread, "thread-002");
        Thread thread3 = new Thread(thread, "thread-003");
        Thread thread4 = new Thread(thread, "thread-004");
        Thread thread5 = new Thread(thread, "thread-005");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    static class MyThread extends Thread {
        @Override
        public synchronized void run() {
            count--;
            System.out.println(this.currentThread().getName() + "count: " + count);
        }
    }
}
