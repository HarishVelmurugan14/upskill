
import java.util.HashMap;

public class Hashing {
    public int[] countFrequency(int n, int x, int[] nums) {
        int[] result = new int[n];
        for (int num : nums) {
            if (num <= n) {
                result[num - 1] = result[num - 1] + 1;
            }
        }
        return result;
    }

    public int[] getFrequencies(int[] v) {
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        for (int num : v) {
            resultMap.merge(num, 1, Integer::sum);
        }
        int maxOccurrence = 0;
        int maxNum = Integer.MAX_VALUE;
        int minNum = Integer.MAX_VALUE;
        int minOccurrence = Integer.MAX_VALUE;
        for (int num : resultMap.keySet()) {
            if (resultMap.get(num) >= maxOccurrence) {
                if (resultMap.get(num) == maxOccurrence && num < maxNum) {
                    maxNum = num;
                } else if (resultMap.get(num) > maxOccurrence) {
                    maxNum = num;
                    maxOccurrence = resultMap.get(num);
                }
            }
            if (resultMap.get(num) <= minOccurrence) {
                if (resultMap.get(num) == minOccurrence && num < minNum) {
                    minNum = num;
                } else if (resultMap.get(num) < minOccurrence) {
                    minNum = num;
                    minOccurrence = resultMap.get(num);
                }
            }
        }
        v = new int[]{maxNum, minNum};
        return v;
    }
}
