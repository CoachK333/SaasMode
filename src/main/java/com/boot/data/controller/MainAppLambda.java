package com.boot.data.controller;

import com.boot.data.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    @Test
    public void test6() {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "张三", 18, 9999.99),
                new Employee(2, "李四", 59, 6666.66),
                new Employee(3, "王五", 28, 3333.33),
                new Employee(4, "赵柳", 8, 7777.77),
                new Employee(5, "田七", 38, 5555.55)
        );

        Collections.sort(employees, (e1, e2) -> {
            if (e1.getSalary().equals(e2.getSalary())) {
                return -Integer.compare(e1.getAge(), e2.getAge());
            } else {
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }
        });
        employees.forEach(System.out::println);
    }


    @Test
    public void test7() {
        System.out.println(strHandler(string -> (string.toUpperCase()), "lowB"));
    }

    @FunctionalInterface
    interface OperateStr {
        String getValue(String string);
    }

    private String strHandler(OperateStr ope, String string) {
        return ope.getValue(string);
    }

    @Test
    public void test8() {

        System.out.println(countNums(123L, 321L, (OperateNum<Long, Long>) (t1, t2) -> t1 + t2));
        System.out.println(countNums(123L, 321L, (OperateNum<Long, Long>) (t1, t2) -> t1 * t2));
    }

    @FunctionalInterface
    interface OperateNum<T, R> {
        R method1(T t1, T t2);
    }

    private Long countNums(Long l1, Long l2, OperateNum operateNum) {
        return (Long) operateNum.method1(l1, l2);
    }

    /**
     * java8 内置 四大核心函数式接口
     * {@link Consumer}     消费型接口
     * {@link Supplier}     供给型接口
     * {@link Function}     函数型接口
     * {@link Predicate}    断言型接口
     */
    @Test
    public void test9() {
        happy(10000, x -> System.out.println("每次大保健消费: ¥" + x / 10));

    }

    private void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test10() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        numList.forEach(System.out::println);
    }

    private List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    @Test
    public void test11() {
        System.out.println(strHandler("123Avbswjidfhuisdf", str -> str.toUpperCase()));
        System.out.println(strHandler("123Avbswjidfhuisdf", str -> str.substring(2, 10)));
    }


    private String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    @Test
    public void test12() {
        List<String> strings = Arrays.asList("qiwe", "qwe0qw", "qwe9781", "lambda");
        List<String> list = filterStr(strings, x -> x.length() > 4);
        list.forEach(System.out::println);
    }

    private List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                strList.add(s);
            }
        }
        return strList;

//        return list.stream().filter(predicate).collect(Collectors.toList());
    }

}
