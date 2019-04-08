package com.boot.data.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp012 {

    public static void main(String[] args) throws SQLException {

        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        String s = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(s);

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
