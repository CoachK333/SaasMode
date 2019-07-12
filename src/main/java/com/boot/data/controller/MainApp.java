package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp {

    public static void main(String[] args) throws Exception {

        URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551442285440&di=00faca0fbc30f824035715a0a9d08d29&imgtype=0&src=http%3A%2F%2Fimg0.ph.126.net%2FIcxVNMmms4CD3AOIBCh60Q%3D%3D%2F6597621721495591668.jpg");

        try (InputStream inputStream = url.openStream(); FileOutputStream outputStream = new FileOutputStream("temp.jpg")) {
            byte[] bytes = new byte[10240];
            int length = 0;
            while ((length = inputStream.read(bytes, 0, 10240)) != -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = "34656895/123//1/123/456";
        String s1 = s.replaceFirst("123", "hehe");
        System.out.println(s);
        System.out.println(s1);
        int i = s.indexOf("123");
        System.out.println(i);
        System.out.println(s.substring(i + 3));
        System.out.println(s);

        System.out.println(s.indexOf("hehe"));

        Object object = "123";
        System.out.println((String) object);


        Object o1 = null;
        System.out.println((String) o1);

        long l = System.currentTimeMillis();
        System.out.println(l % 100000);

        String s0 = "/123/123/45665.xls";
        System.out.println(s0.substring(s0.lastIndexOf("/") + 1));
        String s2 = "1990-10-10";
        LocalDate date = LocalDate.parse(s2);
        System.out.println(date.plusDays(1).toString());
        Long l1 = new Long(123);
        Long l2 = new Long(123);
        System.out.println(l1 == l2);
        System.out.println(l1.equals(l2));
    }

}
