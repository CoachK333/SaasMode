package com.boot.data.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Data
@Component
public class Mybean implements InitializingBean {

    @Value("${mybean.name:张三,李四,王五}")
    private List<String> name;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("com.boot.data.controller.MainApp025.Mybean has been initialized!");
    }
}