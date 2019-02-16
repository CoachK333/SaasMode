package com.boot.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 98548
 * @create 2019-02-13 11:50
 * @description
 */
@Component
public class FileUtils {

    private static String hehePath;

    private static String filePath;


    @Value("${file.path.hehe}")
    public void setHehePath(String hehePath) {
        FileUtils.hehePath = hehePath;
    }

    @Value("${file.path}")
    public void setFilePath(String filePath) {
        FileUtils.filePath = filePath;
    }


    public static String getPath002() {
        return hehePath + " : " + filePath;
    }


}
