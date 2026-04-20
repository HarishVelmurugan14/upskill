package com.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;

public class IsomrphicStrings {
    public static void main(String[] args) {
        System.out.println(isIsomorphicMySolution("paper", "tiple"));
        System.out.println(isIsomorphicOptimalSolution("paper", "title"));
    }

    private static boolean isIsomorphicOptimalSolution(String paper, String title) {
        //your code is better
        return false;
    }

    public static boolean isIsomorphicMySolution(String s, String t) {

        if (s.length() == t.length()) {
            HashMap<Character, Character> pairMap = new HashMap<>();
            ArrayList<Character> newItemList = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char sChar = s.charAt(i);
                char tChar = t.charAt(i);
                if (pairMap.containsKey(sChar)) {
                    if (!(pairMap.get(sChar) == (tChar))) {
                        return false;
                    }
                } else if (newItemList.contains(tChar)) {
                    return false;
                } else {
                    pairMap.put(sChar, tChar);
                    newItemList.add(tChar);
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
