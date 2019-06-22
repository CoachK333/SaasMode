package com.boot.data.controller;

public enum RequestSourceType {
    S01("电话报修", 1),
    S02("上门报修", 2),
    S03("补录", 3),
    S04("电话找人", 4),
    S05("微信报修", 5),
    S06("运维助手派单", 6),
    S07("网页报修", 7),
    S08("app报修", 8),
    S09("钉钉", 9),;
    private String key;
    private Integer value;

    RequestSourceType(String key, Integer value) {
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