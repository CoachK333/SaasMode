package com.boot.data.controller;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
public class MainApp021 {
    public static void main(String[] args) throws IOException {


        double ceil = Math.ceil((double) (31 - (7 - 0)) / 7);
        int i = (int) ceil;
        System.out.println(i);

        LocalDate now = LocalDate.now();
        for (int i1 = 0; i1 < 10000; i1++) {
            now = now.plusDays(1);
            if (!now.isLeapYear() && now.getMonthValue() == 2 && now.getDayOfMonth() == 1 && now.getDayOfWeek().getValue() == 7) {
                System.out.println(JSON.toJSONString(now));
            }
        }

    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，
     * 请你在该数组中找出和为目标值的那两个整数，
     * 并返回他们的数组下标。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static int[] twoSum1(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if (map.containsKey(i1) && map.get(i1) != i) {
                return new int[]{i, map.get(i1)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if (map.containsKey(i1)) {
                return new int[]{i, map.get(i1)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
