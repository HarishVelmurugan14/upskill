package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] a = twoSum(new int[]{3, 2, 4}, 7);
        for (int a1 : a) {
            System.out.println(a1);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] numsCopy = new int[nums.length];
        for (short i = 0; i < nums.length; i++) {
            numsCopy[i] = target - nums[i];
            for (short j = 0; j < nums.length; j++) {
                if (j == i) {
                    j++;
                }
                if (nums[j] == numsCopy[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSumOptimal(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            Integer integer = numToIndex.get(diff);
            if (integer != null) {
                return new int[]{integer, i};
            }

            numToIndex.put(nums[i], i);
        }

        throw new IllegalArgumentException("Incorrect array");
    }
}
