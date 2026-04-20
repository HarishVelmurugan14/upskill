package com.leetcode.easy;

class RomanToInteger {

    public int romanToInt(String s) {
        String[] array = s.split("(?!^)");
        int total = 0;
        int equivNumber = 0, prevEquivNumber = 1000;
        String prevLetter = "";
        for (String letter : array) {
            equivNumber = conversion(letter);
            if (equivNumber <= prevEquivNumber) {
                total = total + equivNumber;
                prevEquivNumber = equivNumber;
                prevLetter = letter;
            } else {
                total = total - prevEquivNumber + specialCases(prevLetter + letter);
            }
        }
        return total;
    }

    public int conversion(String letter) {
        switch (letter) {
            case "M":
                return 1000;
            case "D":
                return 500;
            case "C":
                return 100;
            case "L":
                return 50;
            case "X":
                return 10;
            case "V":
                return 5;
            case "I":
                return 1;
            default:
                return -1;
        }
    }

    public int specialCases(String pattern) {
        switch (pattern) {
            case "IV":
                return 4;
            case "IX":
                return 9;
            case "XL":
                return 40;
            case "XC":
                return 90;
            case "CD":
                return 400;
            case "CM":
                return 900;
            default:
                return -1;
        }
    }
}