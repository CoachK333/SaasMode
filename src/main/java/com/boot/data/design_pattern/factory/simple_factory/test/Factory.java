package com.boot.data.design_pattern.factory.simple_factory.test;

/**
 * @author 98548
 * @create 2019-05-13 9:34
 * @description
 */
public class Factory {
    public static Product create(String s) {
        if ("A".equalsIgnoreCase(s)) {
            return new ProductA();
        } else if ("B".equalsIgnoreCase(s)) {
            return new ProductB();
        } else {
            return null;
        }
    }
}
