package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Harish Velmurugan
 * @last-modified 11-02-2025
 * @since 11-02-2025
 */
public class d17_Hashing2_Problems {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d17_Hashing2_Problems d17_Hashing2_Problems = new d17_Hashing2_Problems();

        // still in progress
        d17_Hashing2_Problems.firstSubArrayWithSumAsK();


        d17_Hashing2_Problems.findThePairWithSumAsK(); // Q1
        d17_Hashing2_Problems.countOfPairsWithDifferenceEqualsK(); // Q2
        d17_Hashing2_Problems.countOfSubArrayWithSumEqualsK(); // Q3
        d17_Hashing2_Problems.distinctNumbersInWindow(); // Q4
        d17_Hashing2_Problems.longestSubArrayWithSumAsZero(); // Q5

        d17_Hashing2_Problems.longestSubArrayWithSumAsK(); // Ext
        d17_Hashing2_Problems.firstNonRepeatingElementInAnArray(); // Ext

        d17_Hashing2_Problems.countThePairWithSumAsK(); // AQ1

    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */


    public void findThePairWithSumAsK() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        int[] B = {9, 4, 7, 5, 10, 9, 2, 4, 8, 6};
        int A = 9;

        int N = B.length;

        HashSet<Integer> targetHashset = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int target = A - B[i];
            if (targetHashset.contains(B[i])) {
                System.out.println(1);
                break;
            } else {
                targetHashset.add(target);
            }
        }
        System.out.println(0);
    }

    public void countThePairWithSumAsK() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        int[] A = {1, 2, 1, 2};
        int B = 3;

        int N = A.length;
        long count = 0;

        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = A[i];
            int target = B - num;

            if (hash.containsKey(target)) {
                int occurrence = hash.get(target);
                count += occurrence;
            }
            if (hash.containsKey(num)) {
                hash.put(num, hash.get(num) + 1);
            } else {
                hash.put(num, 1);
            }
        }

        System.out.println(count % 1_000_000_007);
    }
    //----------------------------------------------------------------------------------------------------------------

    public void countOfPairsWithDifferenceEqualsK() {
        int[] A = {18, 26, 17, 30, 13, 30, 20, 13};
        int B = 10;
        int N = A.length;
        int count = 0;

        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < N; i++) {

            int num = A[i];
            int target = B + num;
            if (hash.containsKey(target)) {
                int occurrence = hash.get(target);
                count += occurrence;
            }
            target = num - B;
            if (hash.containsKey(target)) {
                int occurrence = hash.get(target);
                count += occurrence;
            }

            if (hash.containsKey(num)) {
                hash.put(num, hash.get(num) + 1);
            } else {
                hash.put(num, 1);
            }
        }

        System.out.println(count);
    }

    //----------------------------------------------------------------------------------------------------------------

    public void countOfSubArrayWithSumEqualsK() {
        countOfSubArrayWithSumEqualsK_myApproach();
        countOfSubArrayWithSumEqualsK_Optimized();
    }

    private void countOfSubArrayWithSumEqualsK_myApproach() {
        /*int[] A = {-2, 16, -12, 5, 7, -1, 2, 12, 11};
        int[] A = {2, 1, 0, 0, 0, 16, 0};
        int[] A = {19, -10, -13, 10, -13, 6, 7, 2, 18, -19, -4};
        int[] A = {-1, -21, 20, -48, 13, -8, 26, 25, 12, 26, 19, 25, -29, -32, -24,
         -18, 41, -22, -39, -32, -26, 0, -37, -47, -42, -17, -23, 20, -50, 37, 37, 15,
          48, -5, 35, 38, -37, -24, 15, 49, 6, -35, -5, 41, 42, -4, -18, -23, 36, 30, -17,
          -5, 32, 2, 15, 18, 4, -40, -15, 19, -21, 22, -25, -5, -42, -28, -34, -13, -23, -7, 49, 34, 30};
        int B = -48;*/
        int[] A = {-20, -13, 4, 2, 18, -17, 17};
        int B = 11;
        int N = A.length;
        int sum = 0;
        int myCount = 0;
        HashMap<Integer, Integer> sumHash = new HashMap<>();
        sumHash.put(0, 1);
        for (int i = 0; i < N; i++) {
            int num = A[i];
            sum += num;
            int target = sum - B;
            if (sumHash.containsKey(target)) {
                int occurrence = sumHash.get(target);
                myCount += occurrence;
            }

            if (sumHash.containsKey(sum)) {
                sumHash.put(sum, sumHash.get(sum) + 1);
            } else {
                sumHash.put(sum, 1);
            }
        }
        System.out.println(myCount);
    }

    private void countOfSubArrayWithSumEqualsK_Optimized() {
        int[] A = {-20, -13, 4, 2, 18, -17, 17};
        int B = 11;

        int count = 0, currSum = 0;
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Initialize with sum 0 having 1 occurrence

        for (int num : A) {
            currSum += num; // Update cumulative sum

            // Check if (currSum - K) exists in map
            if (prefixSumMap.containsKey(currSum - B)) {
                count += prefixSumMap.get(currSum - B);
            }

            // Update the frequency of the current sum in the map
            prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        }

        System.out.println(count);
    }

    //----------------------------------------------------------------------------------------------------------------

    public void distinctNumbersInWindow() {
        distinctNumbersInWindow_brute();
        distinctNumbersInWindow_approach();
    }

    private int[] distinctNumbersInWindow_approach() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        /*int[] A = {1, 2, 1, 3, 4, 3};
        int B = 3;*/
        int[] A = {2, 7, 7, 81, 81};
        int B = 1;
        int N = A.length;
        int[] result = new int[N - B + 1];
        HashMap<Integer, Integer> store = new HashMap<>();
        int start = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (store.containsKey(A[i])) {
                store.put(A[i], store.get(A[i]) + 1);
            } else {
                store.put(A[i], 1);
                count++;
            }
            if (i >= B - 1) {
                result[i - B + 1] = count;

                int x = store.get(A[start]);
                if (x == 1) {
                    count--;
                    store.remove(A[start]);
                } else {
                    store.put(A[start], x - 1);
                }
                start++;
            }
        }
        print("", result);

        return result;
    }

    private int[] distinctNumbersInWindow_brute() {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(N) ]
        int[] A = {1, 2, 1, 3, 4, 3};
        int B = 3;
        int N = A.length;
        int[] result = new int[N - B + 1];
        for (int i = 0; i < N - B + 1; i++) {
            HashSet<Integer> temp = new HashSet<>();
            int count = 0;
            for (int j = i; j < i + B; j++) {
                if (!temp.contains(A[j])) {
                    temp.add(A[j]);
                    count++;
                }
            }
            result[i] = count;
        }

        print("", result);
        return result;
    }
    //----------------------------------------------------------------------------------------------------------------

    public void longestSubArrayWithSumAsZero() {
        subArrayWithSumAsZero_prefixSum();
        subArrayWithSumAsZero_carryForward();
        longestSubArrayWithSumAsK();
    }

    private void subArrayWithSumAsZero_prefixSum() {
        int[] A = {-16, 16, 3};
        int N = A.length;
        long[] sumArray = new long[A.length];
        long currentSum = 0;
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            currentSum += A[i];
            sumArray[i] = currentSum;
        }
        HashMap<Long, Integer> prefixHash = new HashMap<>();
        for (int i = 0; i < N; i++) {
            long target = sumArray[i];
            int length = 0;
            if (target == 0) {
                length = i + 1;
            } else if (prefixHash.containsKey(target)) {
                int targetIndex = prefixHash.get(target);
                length = i - targetIndex;
            } else {
                prefixHash.put(target, i);
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }
    }

    private void subArrayWithSumAsZero_carryForward() {
        int[] A = {-16, 16, 3};
        int N = A.length;
        int maxLength = 0;
        HashMap<Long, Integer> prefixSumArray = new HashMap<>();
        long currentSum = 0;
        int length = 0;
        for (int i = 0; i < N; i++) {
            currentSum = currentSum + A[i];
            if (currentSum == 0) {
                length = i + 1;
            } else if (prefixSumArray.containsKey(currentSum)) {
                int targetIndex = prefixSumArray.get(currentSum);
                length = i - targetIndex;
            } else {
                prefixSumArray.put(currentSum, i);
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }
    }

    public void longestSubArrayWithSumAsK() {
        /*int[] B = {1, 0, 1};
        int A = 1;*/
        int[] B = {-20, -13, 4, 2, 18, -17, 17};
        int A = 11;
        int N = B.length;
        int maxLength = 0;
        HashMap<Long, Integer> prefixSumArray = new HashMap<>();
        long currentSum = 0;
        int length = 0;
        for (int i = 0; i < N; i++) {
            currentSum = currentSum + B[i];
            long target = currentSum - A;
            if (currentSum == A) {
                length = i + 1;
            } else if (prefixSumArray.containsKey(target)) {
                int targetIndex = prefixSumArray.get(target);
                System.out.println(targetIndex + " - " + i);
                length = i - targetIndex;
            } else {
                prefixSumArray.put(currentSum, i);
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }
        System.out.println(maxLength);
    }

    public void firstSubArrayWithSumAsK() {
        /*int[] A = {1, 0, 1};
        int A = 1;*/
        int[] A = {5, 10, 20, 100, 105};
        int B = 110;
        int N = A.length;
        HashMap<Long, Integer> prefixSumArray = new HashMap<>();
        long currentSum = 0;
        int start = 0;
        int end = -1;
        for (int i = 0; i < N; i++) {
            currentSum = currentSum + A[i];
            long target = currentSum - B;
            if (currentSum == B) {
                start = i;
                end = i;
            } else if (prefixSumArray.containsKey(target)) {
                int targetIndex = prefixSumArray.get(target);
                start = targetIndex + 1;
                end = i;
                System.out.println("ENTER " + start + " - " + end);
                break;
            } else {
                prefixSumArray.put(currentSum, i);
            }
        }

        if ((end - start + 1) > 0) {
            int[] res = new int[end - start + 1];
            int index = 0;
            for (int i = start; i <= end; i++) {
                res[index] = A[i];
                index++;
            }
            print("", res);
        }

        print("", new int[]{-1});


    }

    //----------------------------------------------------------------------------------------------------------------


    public void firstNonRepeatingElementInAnArray() {
        // Complexity : Time : [ O(2N) ]
        // Complexity : Space : [ O(N) ]

        int[] A = {6, 5, 1, 2, 1, 4, 5, 6};
        int N = A.length;
        HashMap<Integer, Integer> freqHashmap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            if (freqHashmap.containsKey(A[i])) {
                int frequency = freqHashmap.get(A[i]);
                freqHashmap.put(A[i], frequency + 1);
            } else {
                freqHashmap.put(A[i], 1);
            }
        }

        for (int i = 0; i < N; i++) {
            int freq = freqHashmap.get(A[i]);
            if (freq == 1) {
                System.out.println("First Non Repeating character is " + A[i]);
            }
        }
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    private void print(String message, int[] array) {
        printHelper.print(message, array);
    }

    private void print(String message, HashMap array) {
        printHelper.print(message, array);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
