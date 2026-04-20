package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("ALL")
public class MaximumCapacityWithFullCapacityOfRocks {
    public static void main(String[] args) {
        System.out.println(maximumBagsMySolution(new int[]{54, 18, 91, 49, 51, 45, 58, 54, 47, 91, 90, 20, 85, 20, 90, 49, 10, 84, 59, 29, 40, 9, 100, 1, 64, 71, 30, 46, 91},
                new int[]{14, 13, 16, 44, 8, 20, 51, 15, 46, 76, 51, 20, 77, 13, 14, 35, 6, 34, 34, 13, 3, 8, 1, 1, 61, 5, 2, 15, 18},
                77));
        System.out.println(maximumBagsOptimalSolution(new int[]{54, 18, 91, 49, 51, 45, 58, 54, 47, 91, 90, 20, 85, 20, 90, 49, 10, 84, 59, 29, 40, 9, 100, 1, 64, 71, 30, 46, 91},
                new int[]{14, 13, 16, 44, 8, 20, 51, 15, 46, 76, 51, 20, 77, 13, 14, 35, 6, 34, 34, 13, 3, 8, 1, 1, 61, 5, 2, 15, 18},
                77));
    }

    public static int maximumBagsMySolution(int[] capacity, int[] rocks, int additionalRocks) {
        ArrayList<Integer> neededRocksList = new ArrayList<>();
        int bagFull = 0;
        for (int i = 0; i < capacity.length; i++) {
            neededRocksList.add(capacity[i] - rocks[i]);
        }
        Collections.sort(neededRocksList);
        for (int num : neededRocksList) {
            if (additionalRocks >= num) {
                bagFull++;
                additionalRocks = additionalRocks - num;
            }
        }
        return bagFull;
    }

    public static int maximumBagsOptimalSolution(int[] capacity, int[] rocks, int additionalRocks) {
        final int n = capacity.length;
        for (int i = 0; i < n; i++) {
            capacity[i] -= rocks[i];
        }

        // always pick the smallest left bag
        Arrays.sort(capacity);
        int count = 0;
        for (int i = 0; i < n && additionalRocks > 0; i++) {
            if (additionalRocks >= capacity[i]) {
                count++;
            }
            additionalRocks -= capacity[i];
        }
        return count;
    }
}
