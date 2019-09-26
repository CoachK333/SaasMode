package com.boot.data.daily.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.data.bean.Mybean;
import com.boot.data.bean.ParkingCamera;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2019-09-04 16:56
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Mybean.class})
public class TestApp001 {

    @Autowired
    private Mybean mybean;

    @Test
    public void test001() {
        System.out.println(mybean);
    }

    @Test
    public void test002() {
        ParkingCamera[] cameras = ParkingCamera.values();
        Arrays.stream(cameras).forEach(bean -> System.out.println(JSON.toJSONString(bean)));
        Arrays.stream(cameras).forEach(bean -> System.out.println(JSON.toJSONString(bean.getIp())));
        Arrays.stream(cameras).forEach(bean -> System.out.println(JSON.toJSONString(bean.getName())));
    }

    @Test
    public void test003() {
        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "张三");
        jsonObject.put("times", 5);
        list.add(jsonObject);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "李四");
        jsonObject1.put("times", 2);
        list.add(jsonObject1);

        String s = list.stream().map(jsonObject2 -> jsonObject2.getString("name") + ":" + jsonObject2.getString("times") + "次").collect(Collectors.joining(",", "", "!"));
        System.out.println(s);
    }


}
