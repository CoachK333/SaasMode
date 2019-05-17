package com.boot.data.controller;

public enum FileBusinessCode {
    leave("leave", 130),//请假
    dutyscheduling("dutyscheduling", 191),//排班审批
    toolbox1("toolbox", 201),//工具箱附件
    toolbox2("toolbox", 202),//工具箱其他附件
    demand("demand", 203)//需求
    ;

    private String key;
    private Integer value;

    FileBusinessCode(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}