package Topics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"UnusedReturnValue", "ExtractMethodRecommender"})
public class ArraysAndHashing {

    public static int combinations = 0;

    public static void main(String[] args) {
        System.out.println(backtrackCount(new int[]{2, 3, 5}, 8, 0));
    }

    private static int backtrackCount(int[] candidates, int target, int start) {
        if (target == 0) return 1;  // Found one valid combination
        if (target < 0) return 0;   // Exceeded the sum

        int count = 0;
        //i = start is a good check
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // Prune
            count += backtrackCount(candidates, target - candidates[i], i);
            // `i` because we can reuse the same element
        }
        return count;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // optional
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // prune since array is sorted
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    public int containerWithMostWater(int[] height) {
        //TwoPointer
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // Calculate area
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int area = width * currentHeight;

            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<>();

        for (int x : nums) {
            if (hash.contains(x)) {
                return true;
            }
            hash.add(x);
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {
        int n = s.length();
        if (n != t.length()) return false;
        int[] array = new int[26];

        for (int i = 0; i < n; i++) {
            array[s.charAt(i) - 'a']++;
            array[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(target - nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            int requiredPair = target - nums[i];
            if (!map.containsKey(requiredPair)) {
                map.put(requiredPair, i);
            }
        }
        return null;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];
            for (char current : s.toCharArray()) {
                count[current - 'a']++;
            }
            String key = Arrays.toString(count);

            map.putIfAbsent(key, new ArrayList());
            map.get(key).add(s);

            // List<String> list = new ArrayList();
            // if(map.containsKey(key)){
            //     list = map.get(key);
            //     list.add(s);
            //     map.put(key, list);

            // } else {
            //     list.add(s);
            //     map.put(key,list);
            // }
        }
        return new ArrayList<>(map.values());
    }

    public void rainWaterTrapped(int[] A) {
        // Complexity : Time : [ O(3N) ]
        // Complexity : Space : [ O(2N) ]
        int n = A.length;
        int totalWater = 0;
        int[] leftMaxArray = new int[n];
        int[] rightMaxArray = new int[n];
        int leftMax = A[0];
        for (int j = 1; j < n - 1; j++) {
            if (A[j - 1] > leftMax) {
                leftMax = A[j - 1];
            }
            leftMaxArray[j] = leftMax;
        }

        int rightMax = A[n - 1];
        for (int k = n - 2; k > 0; k--) {
            if (A[k + 1] > rightMax) {
                rightMax = A[k + 1];
            }
            rightMaxArray[k] = rightMax;
        }


        for (int i = 1; i < n - 1; i++) {
            int leftMaxIn = leftMaxArray[i];
            int rightMaxIn = rightMaxArray[i];
            int waterTrapped = Math.min(leftMaxIn, rightMaxIn) - A[i];
            if (waterTrapped > 0) {
                totalWater += waterTrapped;
            }
        }
        System.out.println(totalWater);
    }

    public int maxSumSubArray(int[] nums) {
        int n = nums.length;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    public void profitOfBeggarsSittingContiguoslyGettingRandomDonation(int A, int[][] B) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(2N) ]
        int n = B.length;
        int[] beggarPot = new int[A];
        for (int i = 0; i < n; i++) {
            int startBeggar = B[i][0] - 1;
            int endBeggar = B[i][1] - 1;
            int donation = B[i][2];
            beggarPot[startBeggar] += donation;
            if (endBeggar + 1 < A) {
                beggarPot[endBeggar + 1] -= donation;
            }
        }
        System.out.println("---------------");

        int[] beggarPotResult = new int[A];
        beggarPotResult[0] = beggarPot[0];
        for (int i = 1; i < A; i++) {
            beggarPotResult[i] = beggarPotResult[i - 1] + beggarPot[i];
        }
    }

    public int rowWithMaximumNumberOfOnes(int[][] A) {
       /* QUESTION
        Given a binary sorted matrix A of size N x N. Find the row with the maximum number of 1.

        If two rows have the maximum number of 1 then return the row which has a lower index.
        Rows are numbered from top to bottom and columns are numbered from left to right.
                Assume 0-based indexing.
                Assume each row to be sorted by values.
                Expected time complexity is O(rows + columns).
                */

        int N = A.length;
        int M = A[0].length;
        int maxNumberOfOneInARow = 0;
        int rowWithMaxOne = 0;

        for (int i = 0; i < N; i++) {
            int testIndex = M - 1;
            int currentRowOnes = 0;
            // System.out.println("Row : "+ i + " - testIndex : "+testIndex + " - M :"+ maxNumberOfOneInARow + " - R : "+rowWithMaxOne );
            while (testIndex >= 0 && A[i][testIndex] == 1) {
                // System.out.println(testIndex);
                currentRowOnes++;
                testIndex--;
            }
            if (currentRowOnes > maxNumberOfOneInARow) {
                rowWithMaxOne = i;
                maxNumberOfOneInARow = currentRowOnes;
            }
            // System.out.println(" RRR : "+rowWithMaxOne);
        }
        return rowWithMaxOne;
    }


//    Input: height = [1,8,6,2,5,4,8,3,7]
//    Output: 49

    public int[] plusOneToAndIntegerArray(int[] digits) {
        int N = digits.length;
        int carry = 0;
        int[] res = new int[N];

        int current = digits[N - 1] + 1;
        res[N - 1] = current % 10;
        if (current == 10) {
            carry = 1;
        }

        for (int i = N - 2; i >= 0; i--) {
            current = digits[i];
            if (carry == 1) {
                current += carry;
                if (current != 10) {
                    carry = 0;
                }
            }
            res[i] = current % 10;
        }

        if (carry == 1) {
            int[] newRes = new int[N + 1];
            newRes[0] = 1;
            for (int i = 1; i < N + 1; i++) {
                newRes[i] = res[i - 1];
            }
            return newRes;
        }

        return res;
    }

    public int[] topKFrequentElements(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<List<Integer>> bucket = new ArrayList<>();

        // Count frequencies
        for (int x : nums) {
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        }

        // Initialize the bucket with empty lists
        for (int i = 0; i <= nums.length; i++) {
            bucket.add(new ArrayList<>());
        }

        // Populate the bucket
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int freq = entry.getValue();
            max = Math.max(max, freq);
            bucket.get(freq).add(entry.getKey());
        }

        // Collect top k frequent elements
        int[] res = new int[k];
        int index = 0;
        for (int i = max; i > 0 && index < k; i--) {
            for (int num : bucket.get(i)) {
                res[index++] = num;
                if (index == k) break;
            }
        }
        return res;
    }

}
