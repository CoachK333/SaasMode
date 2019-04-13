package com.boot.data.design_pattern.factory.simple_factory;

/**
 * @author 98548
 * @create 2019-04-11 17:04
 * @description
 */
public class Riven extends LOLHero {

    private Riven() {
    }

    @Override
    void say() {
        System.out.println("断剑重铸之日,剑士归来之时");
    }
}
