package com.boot.data.config;

/**
 * @author 98548
 * @create 2019-01-21 15:47
 * @description
 */
public class DatasourceHolder {

    private static final ThreadLocal<String> datasource = new ThreadLocal<>();

    public static void setCustomeType(String type) {
        datasource.set(type);
    }

    public static String getCustomeType() {
        return datasource.get();
    }

    public static void remove() {
        datasource.remove();
    }
}
