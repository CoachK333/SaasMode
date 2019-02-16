package com.boot.data.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

/**
 * @author 98548
 * @create 2019-01-15 16:05
 * @description
 */
@Component
public class MainApp005 {

    public static void main(String[] args) throws Exception {
        //test1
        System.out.println(ObjectUtils.allNotNull("", "iouyeidy", "123"));
        //test2     new Date().getTime() == System.currentTimeMillis()
        long time = new Date().getTime();
        System.out.println(time);
        long x = System.currentTimeMillis();
        System.out.println(x % 100000);

        //test3 静态注入(@Component;set方法注入不能使静态的;main方法获取不到,因为单独启动一个manin方法没有加载yml文件)
        System.out.println(FileUtils.getPath002());

        //test4
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        System.out.println(byteToLong(uuid.getBytes()));
        //test5
        String s = "123";
        Class<? extends String> aClass = s.getClass();
        System.out.println(s.getClass().getTypeName());
        //test6
        File file = new File("C:\\Users\\98548\\Desktop\\加班统计.xls");
      MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", new FileInputStream(file));
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());

    }

    private static long byteToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.clear();
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }


}
