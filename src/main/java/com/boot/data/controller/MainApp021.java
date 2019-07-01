package com.boot.data.controller;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author 98548
 * @create 2019-06-26 16:50
 * @description
 */
public class MainApp021 {
    public static void main(String[] args) throws IOException {
        int[] ints = twoSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 10);
        System.out.println(JSON.toJSONString(ints));
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
}
