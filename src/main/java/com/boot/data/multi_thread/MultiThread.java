package com.boot.data.multi_thread;

/**
 * @author 98548
 * @create 2019-05-08 18:08
 * @description
 */
public class MultiThread {

    private static int num = 200;

    public static synchronized void printNum(String threadName, String tag) {
        if ("a".equals(tag)) {
            num = num - 100;
            System.out.println(threadName + " tag a..............");
        } else {
            num = num - 200;
            System.out.println(threadName + "tag b------------");
        }
        System.out.println(threadName + "tag: " + tag + "; num = " + num);
    }

    public static void main(String[] args) {
        MultiThread thread001 = new MultiThread();
        MultiThread thread002 = new MultiThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                thread001.printNum("thread001", "a");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread002.printNum("thread002", "b");
            }
        }).start();


    }
}
