package com.boot.data.design_pattern.factory.simple_factory;

/**
 * @author 98548
 * @create 2019-04-11 17:04
 * @description
 */
public class Fiora extends LOLHero {

    private Fiora() {
    }

    @Override
    void say() {
        System.out.println("我渴望有价值的对手");
    }
}
