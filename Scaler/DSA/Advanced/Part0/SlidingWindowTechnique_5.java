package DSA.Advanced.Part0;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 12-01-2025
 * @since 10-01-2025
 */
public class SlidingWindowTechnique_5 {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        SlidingWindowTechnique_5 slidingWindowTechnique = new SlidingWindowTechnique_5();

        slidingWindowTechnique.maxSumSubArray();
        slidingWindowTechnique.checkIfSubArrayWithLengthBHasSumC();
        slidingWindowTechnique.indexOfSubArrayWithLeastAverage();
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void approach1(int[] array, int k) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        approach1_MaxSumSubArray(array, k);
    }

    public void optimal(int[] array, int k) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        approach2_MaxSumSubArray(array, k);

    }

    /* Section : ------------------------------- [ Problems ] ------------------------------- */

    private void maxSumSubArray() {
//        int[] array = {1, 2, 3, 4};
        int[] array = {-3, 4, -2, 5, 3, -2, 8, 2, 1, 4};
        int k = 4;
        approach1(array, k);
        optimal(array, k);
    }

    private void checkIfSubArrayWithLengthBHasSumC() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        /*Given an array A of length N. Also given are integers B and C.
        Return 1 if there exists a subarray with length B having sum C and 0 otherwise*/
        int[] A = {4, 3, 2, 6, 1};
        int B = 3;
        int C = 11;

        int n = A.length;
        int currentSum = 0;
        for (int i = 0; i < B; i++) {
            currentSum += A[i];
        }
        System.out.println(currentSum + " start : 0 ");
        if (currentSum == C) {
//            return 1;
        }
        int start = 1;
        int end = n - B + 1;

        while (end < n) {
            System.out.println("Before : " + currentSum + " start : " + A[start - 1] + " End : " + A[end]);
            currentSum = currentSum - A[start - 1] + A[end];
            System.out.println(currentSum + " start : " + start);
            if (currentSum == C) {
                System.out.println(currentSum + " Success : start : " + start);
//                return 1;
            }
            end++;
            start++;
        }
//        return 0;
    }

    private int indexOfSubArrayWithLeastAverage() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        /*
         * Given an array A of size N, find the subarray of size B with the least average.
         * */

//        int[] A = {3, 7, 90, 20, 10, 50, 40};
//        int[] A = {3, 7, 5, 20, -10, 0, 12};
//        int[] A = {18,11,16,19,11,9,8,15,3,10,9,20,1,19}; // 1
//        int[] A = {15, 7, 11, 7, 9, 8, 18, 1, 16, 18, 6, 1, 1, 4, 18}; //6
        int[] A = {20, 3, 13, 5, 10, 14, 8, 5, 11, 9, 1, 11};// 9
        int B = 9;

        int n = A.length;
        int currentSum = 0;
        for (int i = 0; i < B; i++) {
            currentSum += A[i];
        }
        float leastAverage = (float) currentSum / B;
        System.out.println(currentSum + " start : " + "0" + " LA : " + leastAverage);
        int index = 0;
        int start = 1;
        int end = B;


        while (end < n) {
            currentSum = currentSum - A[start - 1] + A[end];
            float currentAverage = (float) currentSum / B;
            System.out.println(currentSum + " start : " + start + " LA : " + leastAverage + " CA : " + currentAverage);
            if (currentAverage < leastAverage) {
                System.out.println(currentSum + " Success : start : " + start);
                leastAverage = currentAverage;
                index = start;
            }
            end++;
            start++;
        }
        return index;
    }



    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    private void approach1_MaxSumSubArray(int[] array, int k) {

        int n = array.length;
        int maxSum = 0;
        for (int start = 0; start < n - k + 1; start++) {
            int sum = 0;
            for (int end = start; end < start + k; end++) {
                // considered all sub Arrays even with length < k-1
                sum = sum + array[end];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        System.out.println("Approach 1 : MAX SUM : " + maxSum);
    }

    private void approach2_MaxSumSubArray(int[] array, int k) {
        int n = array.length;
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum = maxSum + array[i];
        }
        System.out.println("After 1 : " + maxSum);
        int start = 1;
        int end = k;
        int currentSum = maxSum;
        while (end < n) {
            currentSum = currentSum - array[start - 1] + array[end];
            System.out.println("After : " + start + " : " + currentSum);
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            start++;
            end++;
        }

        System.out.println("Approach 2 : MAX SUM : " + maxSum);
    }



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         *  Notebook_06122025 : 13
         * */
    }

    private void links() {
        /*
         * academy/mentee-dashboard/class/348475/homework/problems?navref=cl_tb_br
         * academy/mentee-dashboard/class/348475/session?navref=cl_tb_br
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
