package com.leetcode.easy;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        int[] nums = {1, 1, 2};
        removeDuplicates(nums);
    }

    public static int removeDuplicates(int[] nums) {
        int[] expectedNums = new int[nums.length];
        short validCount = 0;
        short prevNum = -101;
        for (short i = 0; i < nums.length; i++) {
            if (nums[i] > prevNum) {
                expectedNums[validCount] = nums[i];
                validCount++;
                prevNum = (short) nums[i];
            }
        }
        for (short i = validCount; i < nums.length; i++) {
            expectedNums[i] = -1;
        }

        for (int num : expectedNums) {
            System.out.print(num);
            System.out.print(",");
        }

        return validCount;
    }
}
