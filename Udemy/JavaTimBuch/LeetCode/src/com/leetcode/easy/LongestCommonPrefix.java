package com.leetcode.easy;

import java.lang.reflect.Array;

public class LongestCommonPrefix {


    public String longestCommonPrefix(String[] strs) {
        return "";
    }

    public static void main(String[] args) {
        String[] arr = {"flower", "flow", "flight"};
         arr = new String[]{"", "b"};
        System.out.println(identify(arr));
    }

    public static String identify(String[] strs) {
        String prevWord = "";
        String matchingPattern = "";
        int count = 0;
        for (String currentWord : strs) {
            //System.out.println(currentWord);
            matchingPattern = "";
            if (prevWord != "") {
                String[] prevArray = prevWord.toString().split("((?!^))");
                String[] currentArray = currentWord.split("((?!^))");
                for (int i = 0; i < (Math.min(prevWord.length(), currentWord.length())); i++) {
                    if (prevArray[i].equals(currentArray[i])) {
                        matchingPattern = matchingPattern + prevArray[i];
                    } else {
                        break;
                    }
                }
                if (matchingPattern.length() > 0) {
                    prevWord = matchingPattern;
                } else {
                    break;
                }
            } else {
                if (count == 0) {
                    prevWord = currentWord;
                    matchingPattern = currentWord;
                }
            }
            count++;
        }
        return matchingPattern;
    }


}
