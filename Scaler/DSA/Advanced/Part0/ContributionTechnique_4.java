package DSA.Advanced.Part0;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 12-01-2025
 * @since 10-01-2025
 */
public class ContributionTechnique_4 {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        ContributionTechnique_4 contributionTechnique = new ContributionTechnique_4();

        //Problems

        contributionTechnique.sumOfAllSubArrays();
        contributionTechnique.maxSubArray();
        contributionTechnique.countSubArraysWithSumLessThanB();
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce(int[] array) {
        // Complexity : Time : [ O(N^3) ]
        // Complexity : Space : [ O(1) ]

        int n = array.length;
        int sum = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                for (int i = start; i <= end; i++) {
                    sum += array[i];
                }
            }
        }
        System.out.println("Brute Force : Total Sum : " + sum);
    }

    public void optimal(int[] array) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        approach3(array);
    }

    public void approach1(int[] array) {
        // Complexity : Time : [ O(N^12 ]
        // Complexity : Space : [ O(N) ]
        int n = array.length;
        int[] prefixArray = new int[n];
        prefixArray[0] = array[0];
        for (int i = 1; i < n; i++) {
            prefixArray[i] = prefixArray[i - 1] + array[i];
        }

        System.out.println(Arrays.toString(prefixArray));
        int totalSum = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                if (start == 0) {
                    totalSum = totalSum + prefixArray[end];
                } else {
                    totalSum = totalSum + prefixArray[end] - prefixArray[start - 1];
                }
            }

        }
        System.out.println("Prefix Sum Approach : Total Sum : " + totalSum);
    }

    public void approach2(int[] array) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int n = array.length;
        int totalSum = 0;
        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum = sum + array[end];
                totalSum = totalSum + sum;
            }
        }
        System.out.println("Carry Forward Approach : Total Sum : " + totalSum);
    }

    public void approach3(int[] array) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        // asked in google, meta
        // refer notebook for definition
        // Number of times each element will contribute in the sum of subArray is given by (i+1)(N-i)

        int n = array.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long countOfTimeEachElementWillContributeInSubArray = (long) (i + 1) * (n - i);
            sum = sum + countOfTimeEachElementWillContributeInSubArray * (long) array[i];
        }
        System.out.println("Bag Approach : Total Sum : " + sum);
    }

    /* Section : ------------------------------- [ Problems ] ------------------------------- */

    private void sumOfAllSubArrays() {
        int[] array = {1, 3, 2, 5};
        bruteForce(array);
        approach1(array);
        approach2(array);
        optimal(array);
    }

    private void maxSubArray() {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int A = 5; // N in this case
        int B = 12;
        int[] C = {2, 1, 3, 4, 5};
        /*You are given an integer array C of size A. Now you need to find a subarray
         (contiguous elements) so that the sum of contiguous elements is maximum,
          but the sum must not exceed B.*/

        int maxSum = 0;
        for (int start = 0; start < A; start++) {
            int sum = 0;
            for (int end = start; end < A; end++) {
                sum = sum + C[end];
                if (sum > maxSum && sum <= B) {
                    maxSum = sum;
                }
            }
        }
        System.out.println("Maximum Sum : " + maxSum + " not exceeding : " + B);
    }

    public void countSubArraysWithSumLessThanB() {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        /*
        Given an array A of N non-negative numbers and a non-negative number B,
        you need to find the number of subarrays in A with a sum less than B.
        We may assume that there is no overflow.
        */
        int[] A = {2, 5, 6};
        int B = 10;
        int n = A.length;
        int count = 0;
        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum = sum + A[end];
                if (sum < B) {
                    count++;
                }
            }
        }
        System.out.println("Carry Forward Approach : Total Count : " + count);
    }

    public void findCountOfGoodSubArrays() {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        /*Given an array of integers A, a subarray of an array is said to be good if it
        fulfills any one of the criteria:
        1. Length of the subarray is be even, and the sum of all the elements of the subarray must be less than B.
        2. Length of the subarray is be odd, and the sum of all the elements of the subarray must be greater than B.
        Your task is to find the count of good subarrays in A.

        */

        int[] A = {1, 2, 3, 4, 5};
        int B = 4;
        int n = A.length;
        int count = 0;
        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum = sum + A[end];
                if ((end - start + 1) % 2 == 0) {
                    if (sum < B) {
                        count++;
                    }
                } else {
                    if (sum > B) {
                        count++;
                    }
                }
            }
        }
        System.out.println("Carry Forward Approach : Total Count : " + count);
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */




    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Notebook_06122025 : 10
         *
         * */
    }

    private void links() {
        /*
         * academy/mentee-dashboard/class/348475/homework/problems?navref=cl_tb_br
         * academy/mentee-dashboard/class/348475/session?navref=cl_tb_br
         *
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
