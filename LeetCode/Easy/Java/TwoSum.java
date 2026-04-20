package Easy.Java;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    @SuppressWarnings("ReassignedVariable")
    public int[] twoSum(int[] num, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < num.length; i++) {
            int diff = target - num[i];

            Integer integer = numToIndex.get(diff);
            if (integer != null) {
                return new int[]{integer, i};
            }

            numToIndex.put(num[i], i);
        }

        throw new IllegalArgumentException("Incorrect array");
    }
}
