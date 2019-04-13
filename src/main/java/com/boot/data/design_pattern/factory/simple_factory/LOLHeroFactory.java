package com.boot.data.design_pattern.factory.simple_factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 98548
 * @create 2019-04-11 17:10
 * @description 类似源码 {@link java.util.Calendar} 简单工厂模式
 *                      {@link org.slf4j.LoggerFactory} 简单工厂模式
 *                      那些都没法和咱们比,都是渣渣!!!
 */
public final class LOLHeroFactory {

    private LOLHeroFactory() {
        throw new RuntimeException();
    }

    public static LOLHero createHero(Class clazz) {
        try {
            Class<?> aClass = Class.forName(clazz.getName());

            Constructor<?> constructor = aClass.getDeclaredConstructor();

            constructor.setAccessible(true);

            return (LOLHero) constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static LOLHero createHero(String type) {
//        switch (type) {
//            case "Riven":
//                return new Riven();
//            case "Fiora":
//                return new Fiora();
//            default:
//                return new LOLHero() {
//                    @Override
//                    void say() {
//                        System.out.println("无 Fuck 说");
//                    }
//                };
//        }
//    }
}
