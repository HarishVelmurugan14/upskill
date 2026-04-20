package Problems.Approaches;

import java.util.HashMap;
import java.util.Map;

public class PrefixSumApproach {


    public void definitions() {
        /*
         * PrefixSumApproach :   0 1 2 3 4 5 6 7 (need k = 5)
         *                       . . . . . . . .
         *                       if I am standing in 5th pos and sum up to now is 7 check if any of the index had 2 as prev sum
         *                       if so current = x = 7 ; if any n has 2
         *                       x -n = 5  => i - indexWherePrevSum is 2
         *                       Hence it requires prefix sum stored in hashmap : don't replace same sum longest is needed
         *
         * Youtube : https://www.youtube.com/watch?v=7pJo_rM0z_s&ab_channel=MiketheCoder
         * */
    }

    public int prefixSum(int[] a, int k) {
        int n = a.length; // size of the array.

        Map<Integer, Integer> preSumMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            int rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }
}
