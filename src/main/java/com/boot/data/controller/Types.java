package com.boot.data.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 98548 on 2018/12/17.
 */
@Getter
public enum Types {

    SERV("serv", "公众号"),
    CORP("corp", "企业号");

    private String name;

    private String desc;

    Types(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
