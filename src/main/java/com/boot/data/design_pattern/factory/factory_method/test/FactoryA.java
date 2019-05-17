package com.boot.data.design_pattern.factory.factory_method.test;

/**
 * @author 98548
 * @create 2019-05-13 9:43
 * @description
 */
public class FactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
