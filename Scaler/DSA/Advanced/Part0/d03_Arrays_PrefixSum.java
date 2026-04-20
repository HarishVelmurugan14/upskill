package DSA.Advanced.Part0;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 07-01-2025
 * @since 06-01-2025
 */

@SuppressWarnings("UnusedReturnValue")
public class d03_Arrays_PrefixSum {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs

        int[] array = {3, -2, 5, -1, 6, -3, 4, -7, 2, 8};
        int[][] queries = {
                {0, 4},
                {2, 7},
                {1, 5},
                {3, 9},
                {0, 9}
        };

        // Call Stack
        d03_Arrays_PrefixSum prefixSum = new d03_Arrays_PrefixSum();

        prefixSum.bruteForce(array, queries);
        prefixSum.optimal(array, queries, "all");
        prefixSum.optimal(array, queries, "even");
        prefixSum.optimal(array, queries, "odd");

        System.out.println("Problems Starts Here .. \n");
        prefixSum.rangeSumQuery(array, queries); // Q3
        prefixSum.specialIndices(); // Q4
        prefixSum.inPlacePrefixSum(); // Q7
        prefixSum.equilibriumIndex(); // AQ2

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce(int[] array, int[][] queries) {
        // Complexity : Time : [ O(n*q) ]
        // Complexity : Space : [ O(1) ]

        for (int i = 0; i < queries.length; i++) {
            int sum = 0;
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                sum += array[j];
            }
            printSum("Brute", queries[i][0], queries[i][1], sum);
        }
        System.out.println();
    }

    public void optimal(int[] array, int[][] queries, String type) {
        // Complexity : Time : [ O(n) ]
        // Complexity : Space : [ O(n+q) ]
        int[] prefixArray = switch (type) {
            case "odd" -> prefixSumOdd(array);
            case "even" -> prefixSumEven(array);
            default -> prefixSum(array);
        };
        prefixSumApproach(array, queries, prefixArray, type);
        System.out.println();
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    private int[] prefixSum(int[] array) {
        int[] prefixArray = new int[array.length];
        prefixArray[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            prefixArray[i] = prefixArray[i - 1] + array[i];
        }
        return prefixArray;
    }

    private int[] prefixSumOdd(int[] array) {
        int[] prefixArray = new int[array.length];
        prefixArray[0] = 0;
        for (int i = 1; i < array.length; i++) {

            if (i % 2 == 0) {
                prefixArray[i] = prefixArray[i - 1];
            } else {
                prefixArray[i] = prefixArray[i - 1] + array[i];
            }
        }
        return prefixArray;
    }

    private int[] prefixSumEven(int[] array) {
        int[] prefixArray = new int[array.length];
        prefixArray[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (i % 2 == 0) {
                prefixArray[i] = prefixArray[i - 1] + array[i];
            } else {
                prefixArray[i] = prefixArray[i - 1];
            }
        }
        return prefixArray;
    }

    private void prefixSumApproach(int[] array, int[][] queries, int[] prefixArray, String type) {


        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            int sum;
            if (start <= 0) {
                sum = prefixArray[end];
            } else {
                sum = prefixArray[end] - prefixArray[start - 1];
            }

            printSum("Optimal : " + type, start, end, sum);
        }

    }

    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int[] someArray) {
        printHelper.print("Result of " + message, someArray);
    }

    private void printSum(String type, int start, int end, int sum) {
        String message = "Optimal " + type + " : Sum between " + start + " to " + end + " is " + sum;
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * NoteBook_06012025 : Page 1
         * */
    }

    private void links() {
        /*
         * /academy/mentee-dashboard/class/348477/session?joinSession=1
         * /academy/mentee-dashboard/class/348477/assignment/problems
         * */
    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public void specialIndices() {
        /* On removing a index from an array and form a new array, sum of even indexed values should
         match the odd indexed values */
//        int[] array = {2, 1, 6, 4, 5, 2, 8};
        int[] array = {2, 3, 4, 6, 1, 3, 5, 2};

        int n = array.length;
        int count = 0;
        int[] prefixSumArrayOddIndices = prefixSumOdd(array);
        int[] prefixSumArrayEvenIndices = prefixSumEven(array);


        for (int i = 0; i < n; i++) {
            int oddIndexSum;
            int evenIndexSum;
            if (i == 0) {
                oddIndexSum = prefixSumArrayEvenIndices[n - 1] - prefixSumArrayEvenIndices[i];
                evenIndexSum = prefixSumArrayOddIndices[n - 1] - prefixSumArrayOddIndices[i];
            } else {
                oddIndexSum = prefixSumArrayOddIndices[i - 1] + prefixSumArrayEvenIndices[n - 1] - prefixSumArrayEvenIndices[i];
                evenIndexSum = prefixSumArrayEvenIndices[i - 1] + prefixSumArrayOddIndices[n - 1] - prefixSumArrayOddIndices[i];
            }
            if (oddIndexSum == evenIndexSum) {
                count++;
            }
        }
        System.out.println("Total number of special indexes are : " + count);

    }


    public void inPlacePrefixSum() {
        /* Without additional Space */
        int[] array = {1, 2, 3, 4, 5};
        for (int i = 1; i < array.length; i++) {
            array[i] = array[i - 1] + array[i];
        }
        print("In Place Prefix : ", array);
    }

    public void equilibriumIndex() {
        int[] A = {-7, 1, 5, 2, -4, 3, 0};
//        int[] A= {1, 2, 3};
        int n = A.length;
        int[] prefixArray = new int[n];
        prefixArray[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefixArray[i] = prefixArray[i - 1] + A[i];
        }

        int equilibriumIndex = -1;
        for (int i = 0; i < n; i++) {
            int sumOnTheRight = 0;
            int sumOnTheLeft = 0;
            if (i == 0) {
                sumOnTheRight = prefixArray[n - 1] - prefixArray[0];
            } else if (i == n - 1) {
                sumOnTheLeft = prefixArray[n - 2];
            } else {
                sumOnTheLeft = prefixArray[i - 1];
                sumOnTheRight = prefixArray[n - 1] - prefixArray[i];
            }
            if (sumOnTheRight == sumOnTheLeft) {
                equilibriumIndex = i;
            }
        }
        System.out.println("Equilibirium Index is : " + equilibriumIndex);
    }

    public long[] rangeSumQuery(int[] A, int[][] B) {
        long[] prefixArray = new long[A.length];
        prefixArray[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixArray[i] = prefixArray[i - 1] + A[i];
        }

        long[] returnArray = new long[B.length];
        for (int i = 0; i < B.length; i++) {
            int start = B[i][0];
            int end = B[i][1];
            long sum = 0;
            if (start == 0) {
                sum = prefixArray[end];
            } else {
                sum = prefixArray[end] - prefixArray[start - 1];
            }
            returnArray[i] = sum;
        }

        return returnArray;
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */
}
