package com.boot.data.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author 98548
 * @create 2019-06-12 14:33
 * @description
 */
public class MainApp015 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 5, 89, 0, 345, 2345, 768, 7, 8);
        ArrayList<Integer> list1 = new ArrayList<>(list);
//        for (int i = 0; i < list1.size(); i++) {
//            if (list1.get(i).equals(5)) {
//                list1.remove(i);
//            }
//        }
//        System.out.println(list1);

        Iterator<Integer> iterator = list1.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.equals(5)) {
                iterator.remove();
            }
        }
        System.out.println(list1);

    }
}
