package com.boot.data.controller;

import com.alibaba.fastjson.JSON;

/**
 * @author 98548
 * @create 2019-06-12 14:33
 * @description
 */
public class MainApp016 {
    public static void main(String[] args) throws InterruptedException {
        String s = "\r\n\r\n\r\n\r\n";
        System.out.println(JSON.toJSONString(s.split("\r\n")));
    }
}
