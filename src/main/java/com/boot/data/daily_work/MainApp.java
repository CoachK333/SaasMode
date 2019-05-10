package com.boot.data.daily_work;

import com.alibaba.fastjson.JSON;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author 98548
 * @create 2019-05-09 11:29
 * @description
 */
public class MainApp {

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        System.out.println(JSON.toJSONString(bytes));

        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.clear();
        buffer.put(bytes,0,bytes.length);
        buffer.flip();
        long aLong = buffer.getLong();
        System.out.println(aLong);
    }
}
