package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
@Slf4j
public class MainAppStream {

    List<Employee> employees = Arrays.asList(
            new Employee(1, "张三", 18, 9999.99, Employee.Status.BUSY),
            new Employee(2, "李四", 59, 6666.66, Employee.Status.FREE),
            new Employee(3, "王五", 28, 3333.33, Employee.Status.VACATION),
            new Employee(4, "赵柳", 8, 7777.77, Employee.Status.BUSY),
            new Employee(5, "田七", 38, 5555.55, Employee.Status.FREE),
            new Employee(5, "田七", 38, 5555.55, Employee.Status.VACATION),
            new Employee(5, "田七", 38, 5555.55, Employee.Status.BUSY)
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

    @Test
    public void test6() {
        System.out.println(employees.stream().allMatch(employee -> employee.getStatus() == Employee.Status.BUSY));
    }

    @Test
    public void test7() {
        System.out.println(employees.stream().anyMatch(employee -> employee.getStatus() == Employee.Status.BUSY));
        System.out.println(employees.stream().noneMatch(employee -> employee.getStatus() == Employee.Status.BUSY));
    }

    @Test
    public void test8() {
        Optional<Employee> optional = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst();
        System.out.println(optional.get());
    }

    @Test
    public void test9() {
        Optional<Employee> optional = employees.parallelStream().filter(employee -> employee.getStatus() == Employee.Status.FREE).findAny();
        System.out.println(optional.get());
    }

    @Test
    public void test10() {
        System.out.println(employees.parallelStream().filter(employee -> employee.getSalary() > 5000).count());
        System.out.println(employees.parallelStream().filter(employee -> employee.getSalary() > 5000).max(Comparator.comparingInt(Employee::getAge)));
        System.out.println(employees.parallelStream()
                .filter(employee -> employee.getSalary() > 5000)
                .min(Comparator.comparingInt(Employee::getAge)));

        System.out.println(employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare).get());
    }

    @Test
    public void test11() {
        String s = "123123123";


        new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };

        new Consumer<String>() {
            @Override
            public void accept(String o) {

            }
        };

        new Function<String, String>() {
            @Override
            public String apply(String o) {
                return null;
            }
        };

        new Predicate<String>() {

            @Override
            public boolean test(String s) {
                return false;
            }
        };
    }

    @Test
    public void test12() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(list.stream().reduce(0, (x, y) -> x + y));

        Optional<Double> optional = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(optional.get());

        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        LinkedHashSet<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(collect);

        Double aDouble = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(aDouble);

        System.out.println(employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary)));

        Optional<Double> optional1 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println(optional1.get());
    }

    @Test
    public void test13() {
        Map<Employee.Status, List<Employee>> listMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(listMap);
    }

    @Test
    public void test14() {
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() < 30) {
                        return "青年";
                    } else if (((Employee) e).getAge() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test15() {
        DoubleSummaryStatistics statistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
    }

    @Test
    public void test16() {
        System.out.println(employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",")));
    }


}
