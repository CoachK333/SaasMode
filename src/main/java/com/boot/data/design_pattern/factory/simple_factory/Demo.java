package com.boot.data.design_pattern.factory.simple_factory;

/**
 * @author 98548
 * @create 2019-04-11 17:08
 * @description
 */
public class Demo {

    public static void main(String[] args) {
        LOLHero riven = LOLHeroFactory.createHero(Riven.class);
        riven.say();

        LOLHero fiora = LOLHeroFactory.createHero(Fiora.class);
        fiora.say();
    }
}
