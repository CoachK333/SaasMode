package com.boot.data.design_pattern.factory.factory_method;

/**
 * @author 98548
 * @create 2019-04-11 18:56
 * @description
 */
public class AatroxFactory implements LOLHeroFactory {
    @Override
    public LOLHero getLOLHero() {
        return new Aatrox();
    }
}
