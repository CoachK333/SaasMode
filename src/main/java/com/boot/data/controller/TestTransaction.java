package com.boot.data.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTransaction {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Trader {
        private String name;
        private String city;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Transaction {

        private Trader trader;
        private int year;
        private int value;
    }

    List<Transaction> transactions;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brain", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brain, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test1() {
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equalsIgnoreCase("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .distinct()
                .forEach(System.out::println);
        System.out.println("--------------------------------------");
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining()));
        System.out.println("--------------------------------------");

        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", String::concat));

        System.out.println("-----------------------------------------");

        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.joining()));
    }

    public static Stream<String> filterCharacter(String str) {
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        for (char c : chars) {
            list.add(String.valueOf(c));
        }
        return list.stream();
    }

    @Test
    public void test5() {
        System.out.println(transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("Milan")));
    }

    @Test
    public void test6() {
        System.out.println(transactions.stream()
                .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .collect(Collectors.summingDouble(t -> t.getValue())));
    }

    @Test
    public void test7() {
        System.out.println(transactions.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Transaction::getValue)))
                .get()
                .getValue());
    }

    @Test
    public void test8() {
        Optional<Transaction> optional = transactions.stream()
                .collect(Collectors.minBy(Comparator.comparingDouble(Transaction::getValue)));
        System.out.println(optional.get());

        Optional<Transaction> optional1 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(optional1.get());
    }
}