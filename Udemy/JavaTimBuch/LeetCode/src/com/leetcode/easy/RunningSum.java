package com.leetcode.easy;

@SuppressWarnings("ReassignedVariable")
public class RunningSum {
    public static void main(String[] args) {
        runningSumMySolution(new int[]{1, 1, 1, 1, 1});
        runningSumOptimalSolution(new int[]{1, 1, 1, 1, 1});
    }

    public static int[] runningSumMySolution(int[] nums) {
        int[] runningSumArray = new int[nums.length];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {

            runningSumArray[i] = temp = nums[i] + temp;
        }
        return runningSumArray;
    }

    public static int[] runningSumOptimalSolution(int[] nums) {
       //Your code is better
        return new int[0];
    }

}
