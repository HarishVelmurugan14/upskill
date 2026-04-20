package com.leetcode.easy;

@SuppressWarnings({"StatementWithEmptyBody", "IdempotentLoopBody"})
public class JumpGame {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        int a[] = {3, 2};
       a = new int[]{2, 3, 1, 1, 4};
        System.out.println(canJump(a));
    }

    public static boolean canJump(int[] nums) {

        int step = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (step == 0) return false;
           // System.out.println(step);
            step = Math.max(--step, nums[i]);
            System.out.println(step);
        }
        return true;
    }
}
