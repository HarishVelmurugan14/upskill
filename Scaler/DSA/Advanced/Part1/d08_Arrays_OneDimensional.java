package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 21-01-2025
 * @since 21-01-2025
 */

@SuppressWarnings("StringTemplateMigration")
public class d08_Arrays_OneDimensional {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs
//        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//         int[] array = {1, 2, 3, 4};
        int[] array = {-500};
        // Call Stack
        d08_Arrays_OneDimensional d08ArraysOneDimensional = new d08_Arrays_OneDimensional();
        d08ArraysOneDimensional.maximumSumContiguosSubArray(array);
        d08ArraysOneDimensional.rainWaterTrappedBetween2Buildings();
        d08ArraysOneDimensional.profitOfBeggarsSittingContiguoslyGettingRandomDonation();
        d08ArraysOneDimensional.addOneToNumberProvidedAsArray();

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce(int[] array) {
        // Complexity : Time : [ O(N^3) ]
        // Complexity : Space : [ O(N) ]

        int n = array.length;
        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = 0;
                for (int k = i; k <= j; k++) {
                    currentSum += array[k];
                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    start = i;
                    end = j;
                }
            }
        }
        print("Brute : ", maxSum, start, end);
    }

    public void prefixSumApproach(int[] array) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(N) ]

        int n = array.length;
        int[] prefixSumArray = new int[n];
        prefixSumArray[0] = array[0];
        for (int i = 1; i < n; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + array[i];
        }
        print("Prefix Array : ", prefixSumArray);


        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = 0;
                if (i == 0) {
                    currentSum = prefixSumArray[j];
                } else {
                    currentSum = prefixSumArray[j] - prefixSumArray[i - 1];
                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    start = i;
                    end = j;
                }
            }
        }
        print("Prefix Sum : ", maxSum, start, end);
    }

    public void carryForwardApproach(int[] array) {
        // Complexity : Time : [ O(N^3) ]
        // Complexity : Space : [ O(N) ]

        int n = array.length;
        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }
        print("Carry Forward : ", maxSum, start, end);
    }

    public void kadanesAlgorithm(int[] array) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        int n = array.length;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            currentSum += array[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        System.out.println("Max " + maxSum);

    }


    public void kadanesAlgorithm_withEndPoints(int[] array) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        int n = array.length;
        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + array[i];
            if (sum > maxSum) {
                maxSum = sum;
                end = i;
            }
            if (sum < 0) {
                sum = 0;
                if (i < n - 1) {
                    start = i + 1;
                }
            }
        }
        print("Kadane's Algorithm : ", maxSum, start, end);
    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Problems ] ------------------------------- */

    public void maximumSumContiguosSubArray(int[] array) {
        //Find the maximum sum of contiguous non-empty subarray within an array A of length N.
        bruteForce(array);
        prefixSumApproach(array);
        carryForwardApproach(array);
        kadanesAlgorithm(array);
        kadanesAlgorithm_withEndPoints(array);
    }

    public void rainWaterTrappedBetween2Buildings() {
        int[] array = {5, 4, 1, 4, 3, 2, 7};
        rainWaterTrapped_brute(array);
        rainWaterTrapped_optimized(array);
    }

    private void rainWaterTrapped_brute(int[] A) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int n = A.length;
        int totalWater = 0;

        for (int i = 1; i < n - 1; i++) {
            int leftMax = A[0];
            for (int j = 1; j <= i - 1; j++) {
                if (A[j] > leftMax) {
                    leftMax = A[j];
                }
            }

            int rightMax = A[n - 1];
            for (int k = n - 2; k >= i + 1; k--) {
                if (A[k] > rightMax) {
                    rightMax = A[k];
                }
            }

            System.out.println(i + " - " + leftMax + " - " + rightMax);
            int waterTrapped = Math.min(leftMax, rightMax) - A[i];
            if (waterTrapped > 0) {
                totalWater += waterTrapped;
            }
        }
        System.out.println(totalWater);
    }

    private void rainWaterTrapped_optimized(int[] A) {
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


    public void profitOfBeggarsSittingContiguoslyGettingRandomDonation() {
        int A = 5;
        int[][] B = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        beggarProfit_brute(A, B);
        beggarProfit_optimized(A, B);

    }

    private void beggarProfit_brute(int A, int[][] B) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(N) ]
        int n = B.length;
        int[] beggarPot = new int[A];
        for (int i = 0; i < n; i++) {
            for (int j = B[i][0] - 1; j <= B[i][1] - 1; j++) {
                beggarPot[j] += B[i][2];
            }
        }
        print("", beggarPot);
        System.out.println("-----------------------");
    }

    private void beggarProfit_optimized(int A, int[][] B) {
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
            print("", beggarPot);
        }
        System.out.println("---------------");
        print("", beggarPot);

        int[] beggarPotResult = new int[A];
        beggarPotResult[0] = beggarPot[0];
        for (int i = 1; i < A; i++) {
            beggarPotResult[i] = beggarPotResult[i - 1] + beggarPot[i];
        }
        print("", beggarPotResult);
    }

    @SuppressWarnings("ReassignedVariable")
    public void addOneToNumberProvidedAsArray() {
        // Complexity : Time : [ O(2N) ]
        // Complexity : Space : [ O(3N) ]
//        int[] A = {0, 1, 9};
        int[] A = {9, 9, 9, 9, 9};
        int n = A.length;
        int[] B = new int[n + 1];
        int remainder = 1;
        int latestNonZeroNumber = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (remainder == 1) {
                if (A[i] != 9) {
                    remainder = 0;
                    B[i + 1] = A[i] + 1;
                    latestNonZeroNumber = i;
                } else {
                    B[i + 1] = 0;
                }
            } else {
                B[i + 1] = A[i];
                if (B[i + 1] > 0) {
                    latestNonZeroNumber = i;
                }
            }
        }
        if (remainder == 1) {
            B[0] = 1;
            latestNonZeroNumber = -1;
        }

        int[] result = new int[n - latestNonZeroNumber];
        System.out.println(n - latestNonZeroNumber);
        int target = n;
        System.out.println(target);
        for (int k = n - latestNonZeroNumber - 1; k >= 0; k--) {
            result[k] = B[target];
            target--;
        }

        System.out.println(latestNonZeroNumber);
        print("", B);
        print("", result);
    }




    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int maxSum, int start, int end) {

        printHelper.print(message + " Max : " + maxSum + " start : " + start + " end : " + end, "");
    }

    private void print(String message, int[] array) {
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
