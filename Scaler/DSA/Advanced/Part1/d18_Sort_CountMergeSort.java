package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;


/**
 * @author Harish Velmurugan
 * @last-modified 12-02-2025
 * @since 12-02-2025
 */
public class d18_Sort_CountMergeSort {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d18_Sort_CountMergeSort sorting1CountSortMergeSort2 = new d18_Sort_CountMergeSort();
        sorting1CountSortMergeSort2.findTheSmallestNumberByRearrangingTheDigits();
        sorting1CountSortMergeSort2.mergeTwoSortedArrays();
        sorting1CountSortMergeSort2.mergeContiguousSortedSubArrays();
        int[] A = {4, 5, 1, 3, 5, 7, 2, 4, 6, 8, 10, 1, 2, 3};
        sorting1CountSortMergeSort2.mergeSort(A, 0, A.length - 1);
        sorting1CountSortMergeSort2.inversionSort(new int[]{3, 4, 1, 2, 5, 7, 6}, 0, 6);

    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    private int inversionSort(int[] A, int low, int high) {
//        Given an array of integers A. If i < j and A[i] > A[j], then the pair (i, j) is called an inversion of A.
//        Find the total number of inversions of A modulo (109 + 7).
        if (low == high) {
            return 0;
        }
        int invCount = 0;
        int mid = (low + high) / 2;
        invCount = (invCount + inversionSort(A, low, mid)) % 1_000_000_007;
        invCount = (invCount + inversionSort(A, mid + 1, high)) % 1_000_000_007;
        invCount = (invCount + inversionMerge(A, low, mid, high)) % 1_000_000_007;
        print("", A, low, high);
        System.out.println("INV COUNT : " + invCount);
        return invCount;
    }

    private int inversionMerge(int[] A, int low, int mid, int high) {
        int P1 = low;
        int P2 = mid + 1;
        int index = 0;
        int invCount = 0;
        int[] temp = new int[high - low + 1];
        while (P1 < mid + 1 && P2 < high + 1) {
            if (A[P1] <= A[P2]) {
                temp[index] = A[P1];
                P1++;
            } else {
                invCount += mid + 1 - P1;
                temp[index] = A[P2];
                P2++;
            }
            index++;
        }

        while (P1 < mid + 1) {
            temp[index] = A[P1];
            P1++;
            index++;
        }
        while (P2 < high + 1) {
            temp[index] = A[P2];
            P2++;
            index++;
        }
        index = low;

        for (int i = 0; i < high - low + 1; i++) {
            A[index] = temp[i];
            index++;
        }
        return invCount;
    }


    private int[] mergeSort(int[] A, int low, int high) {
        // Complexity : Time : [ O(NlogN) ]
        // Complexity : Space : [ O(N) ]
        if (low == high) {
            return A;
        }
        int mid = (low + high) / 2;
        mergeSort(A, low, mid);
        mergeSort(A, mid + 1, high);
        merge(A, low, mid, high);
        return A;
    }

    private int[] merge(int[] A, int low, int mid, int high) {
        int P1 = low;
        int P2 = mid + 1;
        int index = 0;
        int[] temp = new int[high - low + 1];
        while (P1 < mid + 1 && P2 < high + 1) {
            if (A[P1] <= A[P2]) {
                temp[index] = A[P1];
                P1++;
            } else {
                temp[index] = A[P2];
                P2++;
            }
            index++;
        }

        while (P1 < mid + 1) {
            temp[index] = A[P1];
            P1++;
            index++;
        }
        while (P2 < high + 1) {
            temp[index] = A[P2];
            P2++;
            index++;
        }
        index = low;

        for (int i = 0; i < high - low + 1; i++) {
            A[index] = temp[i];
            index++;
        }
        return A;
    }

    private void mergeContiguousSortedSubArrays() {
        int low = 3;
        int mid = 6;
        int high = 9;
        // Complexity : Time : [ O(high-low+1) ]
        // Complexity : Space : [ O(high-low+1) ]
        int[] A = {10, 9, 8, 1, 2, 2, 4, 3, 3, 6, 1, 9};

        int[] temp = new int[high - low + 1];

        int P1 = low;
        int P2 = mid + 1;
        int index = 0;

        while (P1 < mid + 1 && P2 < high + 1) {
            if (A[P1] < A[P2]) {
                temp[index] = A[P1];
                P1++;
            } else {
                temp[index] = A[P2];
                P2++;
            }
            index++;
        }

        while (P1 < mid + 1) {
            temp[index] = A[P1];
            P1++;
            index++;
        }

        while (P2 < high + 1) {
            temp[index] = A[P2];
            P2++;
            index++;
        }

        print("", temp);
        index = 0;
        for (int i = low; i <= high; i++) {
            A[i] = temp[index];
            index++;
        }
        print("", A);

    }

    private void mergeTwoSortedArrays() {
        // Complexity : Time : [ O(N+M) ]
        // Complexity : Space : [ O(N+M) ]

        int[] A = {1, 2, 2, 4};
        int[] B = {3, 5, 7, 9, 10};


        int N = A.length;
        int M = B.length;
        int[] ans = new int[N + M];
        int P1 = 0;
        int P2 = 0;
        int index = 0;


        while (P1 < N && P2 < M) {
            if (A[P1] < B[P2]) {
                ans[index] = A[P1];
                P1++;
            } else {
                ans[index] = B[P2];
                P2++;
            }
            index++;
        }

        while (P1 < N) {
            ans[index] = A[P1];
            P1++;
            index++;
        }

        while (P2 < M) {
            ans[index] = B[P2];
            P2++;
            index++;
        }

        print("", ans);
    }

    public void findTheSmallestNumberByRearrangingTheDigits() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        int[] A = {5, 2, 1, 2, 4};
        int N = A.length;

        int[] frequencyArray = new int[10];
        for (int i = 0; i < N; i++) {
            frequencyArray[A[i]] += 1;
        }
        print("", frequencyArray);

        int k = 0;
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < frequencyArray[j]; i++) {
                A[k] = j;
                k++;
            }
        }

        print("", A);

    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    private void print(String message, int[] array) {
        printHelper.print(message, array);
    }

    private void print(String message, int[] array, int start, int end) {
        printHelper.print(message, array, start, end);
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
