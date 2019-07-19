package com.boot.data.test;

public class MainApp01 {

    private Object lock;

    private int now, need;

    public void produce(int num) {
        synchronized (lock) {
            while (now < need) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("被唤醒了!");
            }
        }
    }
}
