package com.boot.data.controller;

import com.boot.data.Vo.DateVo;
import com.boot.data.temp.service.LeaveService;
import com.boot.data.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp012 {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws SQLException, IOException {


        Properties properties = FileUtils.getProperties("C:\\Users\\98548\\IdeaProjects\\hello-springboot\\helloworld-data\\src\\main\\resources\\application.yml");

        System.out.println(properties.getProperty("file.path"));
        System.out.println(properties.getProperty("chakanyinyong"));
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
