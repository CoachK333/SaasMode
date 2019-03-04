package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.dao.UserRepository;
import com.boot.data.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.List;

/**
 * @author 98548
 * @create 2019-02-19 15:06
 * @description
 */
@RestController
@RequestMapping("/user")
@Api("/用户查询")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addOne(String code, String name) {
        User user = new User();
        user.setName(name);
        user.setDesc(code);
        userRepository.save(user);
        return "ok";
    }

    @GetMapping("/getAll")
    public String getAll() {
        List<User> users = userRepository.findAll();
        return JSON.toJSONString(users);
    }

    @GetMapping("/test001")
    public String test001() {
        return JSON.toJSONString(userRepository.getAll());
    }

    @GetMapping("/test002")
    public String test002(String name) {
        Integer exo = userRepository.exo(name);
        return JSON.toJSONString(exo);
    }
}
