package com.boot.data.controller;

import jdk.nashorn.internal.ir.ReturnNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp012 {

    public static void main(String[] args) throws SQLException {

        Map map = getMap(null);
        System.out.println(map.get("string"));

        String s = "0.0.101";
        System.out.println(versionIteration(s));

        try {
            MultipartFile multipartFile = new MockMultipartFile("file", new FileInputStream("G:\\java相关\\视频\\lucene_solr\\day03\\代码\\solr-jd\\WebContent\\images\\20071124223011438.jpg"));
            Image image = ImageIO.read(multipartFile.getInputStream());
            System.out.println(image != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 版本号迭代
     *
     * @param oldVersion
     * @return
     */
    private static String versionIteration(String oldVersion) {
        String s_end = oldVersion.substring(oldVersion.lastIndexOf(".") + 1);
        String s_prefix = oldVersion.substring(0, oldVersion.lastIndexOf(".") + 1);
        String newVersion = s_prefix.concat(String.valueOf(Integer.valueOf(s_end) + 1));
        return newVersion;
    }

    private static Map getMap(String s) {
        Map map = new CaseInsensitiveMap();
        map.put("String", "123");
        System.out.println(map.get("String"));
        System.out.println(map.get("string"));
        System.out.println(map.get("STRING"));
        return map;
    }

}
