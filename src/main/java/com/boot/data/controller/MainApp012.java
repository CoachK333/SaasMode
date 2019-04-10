package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.ls.LSInput;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp012 {

    public static void main(String[] args) throws SQLException {
        System.out.println((float) 100 / 11);


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
