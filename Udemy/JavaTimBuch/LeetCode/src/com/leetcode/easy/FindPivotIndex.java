package com.leetcode.easy;

@SuppressWarnings("ReassignedVariable")
public class FindPivotIndex {
    public static void main(String[] args) {
        System.out.println(pivotIndexMySolution(new int[]{1, 2, 3}));
        System.out.println(pivotIndexOptimalSolution(new int[]{1, 2, 3}));
    }
    public static int pivotIndexOptimalSolution(int[] nums) {
        ////Your code is better
        return -1;
    }
    public static int pivotIndexMySolution(int[] nums) {
        int[] runningSumArrayLeft = new int[nums.length];
        int[] runningSumArrayRight = new int[nums.length];
        int tempLeft = 0;
        int tempRight = 0;
        int length = nums.length - 1;
        for (int i = 0; i < nums.length; i++, length--) {
            runningSumArrayLeft[i] = tempLeft = nums[i] + tempLeft;
            runningSumArrayRight[length] = tempRight = nums[length] + tempRight;
        }

        for (int i = 0; i < nums.length; i++) {
            if (runningSumArrayLeft[i] == runningSumArrayRight[i]) {
                return i;
            }
        }
        if ((runningSumArrayLeft[nums.length - 1] - runningSumArrayLeft[0]) == 0) {
            return 0;
        } else if ((runningSumArrayRight[0] - runningSumArrayRight[nums.length - 1]) == 0) {
            return nums.length - 1;
        }
        return -1;
    }
}
