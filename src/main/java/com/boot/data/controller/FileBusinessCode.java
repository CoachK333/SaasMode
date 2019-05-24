package com.boot.data.controller;

public enum FileBusinessCode {
    LEAVE("leave", 130),//请假
    DUTYSCHEDULING("dutyscheduling", 191),//排班审批
    TOOLBOX1("toolbox", 201),//工具箱附件
    TOOLBOX2("toolbox", 202),//工具箱其他附件
    DEMAND("demand", 203),//需求
    TEMP("temp", 204),;

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
