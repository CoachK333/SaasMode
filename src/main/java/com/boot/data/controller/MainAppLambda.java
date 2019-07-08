package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
@Slf4j
public class MainAppLambda {

    @FunctionalInterface
    interface A {
        Integer test(Integer x);
    }

    @Test
    public void test1() {
        int num = 1;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("helloworld!" + num);
            }
        };
        r1.run();
        System.out.println("------------------------------------");
        Runnable r2 = () -> System.out.println("helloworld!" + num);
        r2.run();
        System.out.println("------------------------------------");
    }

    @Test
    public void test2() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("哎呀我去!");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("哈哈");
            return Integer.compare(x, y);
        };
        System.out.println(comparator.compare(1, 2));
    }

    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(1, 2));
    }

    @Test
    public void test5() {
        System.out.println(operation(200, x -> x + 200));
    }

    private Integer operation(Integer num, A a) {
        return a.test(num);
    }

}
