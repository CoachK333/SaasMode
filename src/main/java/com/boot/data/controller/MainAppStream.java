package com.boot.data.controller;

import com.boot.data.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
@Slf4j
public class MainAppStream {

    List<Employee> employees = Arrays.asList(
            new Employee(1, "张三", 18, 9999.99),
            new Employee(2, "李四", 59, 6666.66),
            new Employee(3, "王五", 28, 3333.33),
            new Employee(4, "赵柳", 8, 7777.77),
            new Employee(5, "田七", 38, 5555.55),
            new Employee(5, "田七", 38, 5555.55),
            new Employee(5, "田七", 38, 5555.55)
    );

    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 0);
        Employee[] employees = new Employee[10];
        Stream<Integer> stream = list.parallelStream();
        Stream<Integer> stream1 = list.stream();
        Stream<Employee> stream2 = Arrays.stream(employees);
        Stream<Employee> stream3 = Stream.of(employees);

        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(20).forEach(System.out::println);
        System.out.println();
        Stream.generate(() -> "-").limit(20).forEach(System.out::print);

        System.out.println();
        Stream<Double> stream4 = Stream.generate(() -> Math.random());
        stream4.limit(10).forEach(System.out::println);

    }

    @Test
    public void test2() {
        employees.stream()
                .skip(2)
                .filter(x -> {
                    System.out.println("走了一遍!");
                    return x.getAge() > 18;
                })
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map(x -> x.toUpperCase())
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        employees.stream()
                .map(Employee::getName)
                .distinct()
                .forEach(System.out::println);
    }


    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream().map(this::filterCharter).forEach((stream) -> stream.forEach(System.out::println));
        System.out.println("-------------------");
        list.stream().flatMap(this::filterCharter).forEach(System.out::println);
        System.out.println("-------------------");

        list.stream().sorted().forEach(System.out::println);
        System.out.println("--------------------------");
        employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).forEach(System.out::println);
        System.out.println("--------------------------");
        employees.stream().sorted((e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getSalary().compareTo(e2.getSalary());
            } else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }

    private Stream<Character> filterCharter(String str) {
        Character[] characters = ArrayUtils.toObject(str.toCharArray());
        return Arrays.stream(characters);
    }

}
