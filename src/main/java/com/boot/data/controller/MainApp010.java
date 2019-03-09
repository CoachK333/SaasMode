package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.data.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp010 {

    public static void main(String[] args) throws ParseException {

        Map<String, Object> params = new HashMap<>();
        params.put("SendUserID", "5459881038564327853");
        params.put("ReceiveUserIDs", Arrays.asList("5459881038564327853"));
        params.put("Title", "测试调用通知");
        params.put("Content", "测试测试");
        params.put("BusinessID", "86");
        params.put("WecharType", "20");
        params.put("MessageType", "news");
        String s = new RestTemplate().postForObject("http://mop.dev.zdha.cn/api/NotifyApi/SendMessage", params, String.class);
        System.out.println(s);

        Date date = new Date();
        Date date1 = new Date();

        LocalDate localDate = LocalDate.of(2018, 8, 8);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date parse = sdf.parse("20180808");
        Date date2 = DateUtils.localdateToDate(localDate);
        System.out.println(parse);
        System.out.println(date2);
        System.out.println(date2.equals(parse));
    }

}
