package com.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidParantheses {

    public static void main(String[] args) {
        System.out.println(isValid("[(]"));
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
    public static boolean isValid(String s) {
        Short forwardCount = 0;
        Map<Short, Character> forwardMap = new HashMap<>();
        Map<Character, Character> parantheseMap = new HashMap<>();
        parantheseMap.put(']', '[');
        parantheseMap.put('}', '{');
        parantheseMap.put(')', '(');
        for (short i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));
            if (new ArrayList<>(Arrays.asList('(', '{', '[')).contains(s.charAt(i))) {
                forwardCount++;
                forwardMap.put(forwardCount, s.charAt(i));
            } else if (new ArrayList<>(Arrays.asList(')', '}', ']')).contains(s.charAt(i))) {
                if (parantheseMap.get(s.charAt(i)).equals(forwardMap.get(forwardCount))) {
                    forwardCount--;
                } else {
                    return false;
                }
            }
        }
        return forwardCount <= 0;
    }
}
