package com.leetcode.easy;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

@SuppressWarnings("ReassignedVariable")
public class HappyNumber {

    public static void main(String[] args) {
        isHappy(19);
    }

    public static boolean isHappy(int n) {
        int temp = 0;
        int nStart = n;
        while (true) {
//            if (nStart / 10 == 0) {
//                System.out.println("Unhappy");
//                return false;
//            }
            temp = (int) (temp + pow(n % 10, 2));
            System.out.println("Temp -> "+ temp + " n -> " + n);
            if (n / 10 > 0) {
                n = n / 10;
            } else {
                if (temp == 1) {
                    return true;
                } else if (temp / 10 == 0) {
                    return false;
                }
                n = temp;
                temp = 0;
            }
        }
    }
}
