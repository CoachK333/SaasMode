package com.boot.data.daily.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.data.util.DateUtils;
import com.boot.data.util.PlateUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 98548
 * @create 2019-09-10 14:03
 * @description
 */
public class Test002 {


    @Test
    public void test001() {
        int i = 3;

        if (i > 2) {
            System.out.println(">2");
        }
        if (i % 2 == 1) {
            System.out.println("%2 = 1");
        }
    }

    @Test
    public void test002() {
        Map<String, Date> dateMap = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        dateMap.put("1", DateUtils.localDateTime2Date(now.plusSeconds(4)));
        dateMap.put("2", DateUtils.localDateTime2Date(now.plusSeconds(-4)));
        dateMap.put("e", DateUtils.localDateTime2Date(now.plusSeconds(0)));
        dateMap.put("4", DateUtils.localDateTime2Date(now.plusSeconds(-10)));


        ArrayList<Map.Entry<String, Date>> entries = new ArrayList<>(dateMap.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<String, Date> tempMap = new LinkedHashMap<>();
        entries.forEach(entry -> tempMap.put(entry.getKey(), entry.getValue()));
        dateMap = tempMap;

        System.out.println(JSON.toJSONString(dateMap));
    }

    @Test
    public void test003() {
        System.out.println(PlateUtils.check("京A12345"));
        System.out.println(PlateUtils.check("京黑A12345"));
        System.out.println(PlateUtils.check("云AD16666"));
        System.out.println(PlateUtils.check("京AHD038"));
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.getClass().getTypeName());
    }

    @Test
    public void test004() {
        LocalDateTime localDateTime = LocalDateTime.of(1970, 1, 1, 8, 0, 0);
        Date date = DateUtils.localDateTime2Date(localDateTime);
        System.out.println(date.getTime());
    }

    @Test
    public void test005() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "展示刚");
        System.out.println(jsonObject);
    }

    private String getToken(String url, String params) {
        String s = url + params + "8b04af78904144919e37dfa10da78258";
        return DigestUtils.md5Hex(s);
    }

    private String post(String uri, JSONObject paramJson) {
        return null;
    }
}
