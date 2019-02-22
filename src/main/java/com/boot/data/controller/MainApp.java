package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
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
//        Map map = new TreeMap();
//        map.put(null, "123");
//        System.out.println(map.get(null).toString());
//
//        Object o = map.get("23");

        String s = "123";
        System.out.println(s.indexOf("4"));
        System.out.println(s.contains("4"));
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId.getId());
        System.out.println(zoneId);
        System.out.println(zoneId.toString());

    }

}
