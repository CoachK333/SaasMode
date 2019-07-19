package com.boot.data.test;

import org.junit.Test;

import java.util.LinkedList;

public class MainApp02 {

    interface Storage {
        void consume(int num);

        void produce(int num);
    }

    class Storage1 implements Storage {

        private final int MAX_SIZE = 100;

        private LinkedList list = new LinkedList();

        @Override
        public void consume(int num) {
            synchronized (list) {
                while (num > list.size()) {
                    System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                            + list.size() + "\t暂时不能消费!");
                }
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < num; i++) {
                    list.remove();
                }

                System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());
                list.notifyAll();
            }
        }

        @Override
        public void produce(int num) {
            synchronized (list) {
                while (list.size() + num > MAX_SIZE) {
                    System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                            + list.size() + "\t暂时不能执行生产任务!");

                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < num; i++) {
                    list.add(new Object());
                }
                System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

                list.notifyAll();
            }
        }
    }

    class Producer extends Thread {
        private int num;

        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void produce(int num) {
            storage.produce(num);
        }

        @Override
        public void run() {
            produce(num);
        }
    }

    class Consumer extends Thread {
        private int num;

        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        public void setNum(int num) {
            this.num = num;
        }

        private void consume(int num) {
            storage.consume(num);
        }

        @Override
        public void run() {
            consume(num);
        }
    }

    @Test
    public void test1() {
        Storage storage1 = new Storage1();
        Producer p1 = new Producer(storage1);
        Producer p2 = new Producer(storage1);
        Producer p3 = new Producer(storage1);
        Producer p4 = new Producer(storage1);
        Producer p5 = new Producer(storage1);

        Consumer c1 = new Consumer(storage1);
        Consumer c2 = new Consumer(storage1);
        Consumer c3 = new Consumer(storage1);

        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);

        c1.setNum(20);
        c2.setNum(5);
        c3.setNum(5);

        c1.start();
        c2.start();
        c3.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

    }

}

