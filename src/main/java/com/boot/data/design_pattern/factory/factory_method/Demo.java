package com.boot.data.design_pattern.factory.factory_method;

/**
 * @author 98548
 * @create 2019-04-11 18:50
 * @description
 */
public class Demo {
    public static void main(String[] args) {
        LOLHeroFactory heroFactory = new AatroxFactory();
        heroFactory.getLOLHero().say();

        LOLHeroFactory yasuoFactory = new YasuoFactory();
        yasuoFactory.getLOLHero().say();
    }
}
