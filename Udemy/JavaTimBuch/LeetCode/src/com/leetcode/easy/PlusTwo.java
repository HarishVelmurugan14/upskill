package com.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;

class PlusTwo {
    public static int[] plusOne(int[] digits) {
        int length = digits.length-1;
        long temp =0;
        for (long num : digits) {
            System.out.println((long)Math.pow(10,length));
            temp =  num*(long)Math.pow(10,length)+temp;
            //System.out.println(temp);
            length--;
        }
        temp = temp+1;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        long n = 0;
        while(temp>0){
            n = temp%10;
            ans.add((int) n);
            temp/=10;
        }
        int[] fuck = new int[ans.size()];
        for (int i=ans.size()-1,j=0;i>=0;i--,j++) {
            fuck[j]=ans.get(i);
        }
        return fuck;
    }


    public static void main(String[] args) {
        int[] digits = {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6};
       // System.out.println(Arrays.toString(plusOne(digits)));
        System.out.println(Long.MAX_VALUE);
    }
}
