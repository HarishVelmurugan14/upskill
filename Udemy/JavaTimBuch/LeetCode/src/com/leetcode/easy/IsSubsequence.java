package com.leetcode.easy;

public class IsSubsequence {
    public static void main(String[] args) {
        System.out.println(isSubsequenceMySolution("b", "abc"));
        System.out.println(isSubsequenceOptimalSolution("b", "abc"));
    }

    private static boolean isSubsequenceOptimalSolution(String b, String abc) {
        //your code is better
        return false;
    }

    public static boolean isSubsequenceMySolution(String s, String t) {
        int j = 0;
        if (s.length() == 0) {
            return true;
        } else if (t.length() == 0) {
            return false;
        }
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (s.length() == j) {
                    return true;
                }
            }
        }

        return false;
    }
}
