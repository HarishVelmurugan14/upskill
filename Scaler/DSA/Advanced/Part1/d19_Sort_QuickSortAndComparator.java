package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 14-02-2025
 * @since 14-02-2025
 */
public class d19_Sort_QuickSortAndComparator {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d19_Sort_QuickSortAndComparator sorting1CountSortMergeSort2 = new d19_Sort_QuickSortAndComparator();
//        int[] A = {54, 26, 93, 17, 77, 31, 44, 55, 20};
        int[] A = {-58, 32, 10, 42, 82, -12, 88, 17, 35, 8, -72, -32, -48, 76, -48, -8, -69, -1, 54, 52, 37, -1, 95, 0, -32, 38, 62, 68, 68, -71, 62, -58, -25, 55, -11, 2};
//        sorting1CountSortMergeSort2.pivotPartition(A, 1, A.length - 1);

//        sorting1CountSortMergeSort2.quickSort(A, 0, A.length - 1);
//        sorting1CountSortMergeSort2.sortByColor();
        sorting1CountSortMergeSort2.largestNumber();
        PrintHelper printHelper = new PrintHelper();
        printHelper.print("", A);

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void largestNumber() {
        int[] A = {3, 30, 34, 5, 9};
    }

    public void quickSort(int[] A, int start, int end) {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        if (start >= end) {
            return;
        }
        int index = pivotPartition(A, start, end);
        System.out.println(index);
        quickSort(A, start, index - 1);
        quickSort(A, index + 1, end);
    }

    public int pivotPartition(int[] A, int first, int last) {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        int pivot = A[first];
        int left = first + 1;
        int right = last;

        while (left <= right) {
            if (A[left] <= pivot) {
                left++;
            } else if (A[right] > pivot) {
                right--;
            } else {
                swap(A, left, right);
            }
        }
        swap(A, first, right);
        return right;
    }

    public void sortByColor() {
        // COUNT SORT
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        int[] A = {0, 1, 2, 0, 1, 2, 1};
        int N = A.length;
        int[] freq = new int[3];

        for (int i = 0; i < N; i++) {
            int x = A[i];
            int y = freq[x];
            freq[x] = y + 1;
        }

        int index = 0;
        int val = 0;
        for (int x : freq) {
            while (x > 0) {
                A[index] = val;
                x--;
                index++;
            }
            val++;
        }

        print("", freq);
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
    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
