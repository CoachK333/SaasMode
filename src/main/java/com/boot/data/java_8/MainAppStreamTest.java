package com.boot.data.java_8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
@Slf4j
public class MainAppStreamTest {

    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void test1() {
        list.stream()
                .map(s -> s * s)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println(list.stream()
                .map(e -> 1)
                .reduce(Integer::sum)
                .get());

    }


}
