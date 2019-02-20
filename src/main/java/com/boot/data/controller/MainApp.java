package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp {

    public static void main(String[] args) throws Exception {
        Map map = new TreeMap();
        map.put(null, "123");
        System.out.println(map.get(null).toString());

        Object o = map.get("23");

    }

}
