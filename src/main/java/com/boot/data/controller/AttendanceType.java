package com.boot.data.controller;

/**
 * @author 98548
 * @create 2019-01-04 18:38
 * @description
 */
public enum AttendanceType {
    公差("公差", "○"),
    休假("休假", "△"),
    疗养("疗养", "L"),
    公伤("公伤", "G"),
    婚丧("婚丧", "H"),
    产假("产假", "C"),
    探亲("探亲", "T"),
    病假("病假", "B"),
    事假("事假", "S"),
    迟到("迟到", "W"),
    早退("早退", "Z"),
    旷工("旷工", "K"),
    培训("培训", "P"),
    离职("离职", "D");


    private String key;

    private String value;

    AttendanceType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
