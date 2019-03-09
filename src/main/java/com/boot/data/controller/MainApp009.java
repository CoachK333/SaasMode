package com.boot.data.controller;

import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 98548
 * @create 2019-03-04 12:15
 * @description
 */
public class MainApp009 {
    public static void main(String[] args) throws IOException {
        String s = UUID.randomUUID().toString().replace("-", "");
        byte[] bytes = s.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.clear();
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        long aLong = buffer.getLong();
        System.out.println(aLong);


        File file = new File("12313123123/temp/temp.txt");
        File parentFile = file.getParentFile();
        System.out.println(parentFile.exists());
        file.createNewFile();

    }

}
