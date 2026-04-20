package com.leetcode.easy;

public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
    }

    public static boolean isPalindrome(int x) {
        int original = x;
        int rev = 0;
        while (x > 0) {
            rev = x % 10 + rev * 10;
            x = x / 10;
        }
        return rev == original ? true : false;
    }
}
