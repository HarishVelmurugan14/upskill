package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 25-02-2025
 * @since 22-02-2025
 */
@SuppressWarnings({"AutoRefact", "ConstantValue"})
public class d21_Searching_BinarySearchOnArray {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d21_Searching_BinarySearchOnArray d21SearchingBinarySearchOnArray = new d21_Searching_BinarySearchOnArray();
        d21SearchingBinarySearchOnArray.searchForStartAndEndIndexOfAnElementInAnArray(); // Q1 //##
        d21SearchingBinarySearchOnArray.positionWhereTargetIsPresentOrShouldBeInserted(); // Q2 //##
        d21SearchingBinarySearchOnArray.singleElementInASortedArray(); // Q3 //##
        d21SearchingBinarySearchOnArray.peakElement(); // Q4 //##

        d21SearchingBinarySearchOnArray.searhAMatrix(); // AQ1 //##
        d21SearchingBinarySearchOnArray.minimumCostToBuildAnArray(3, 2,new int[][]{ {7, 3}, {2, 1},{4, 9} });// AQ2 //##
        d21SearchingBinarySearchOnArray.maximumHeightOfAStaircase(20); // AQ3 //##


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int[] searchForStartAndEndIndexOfAnElementInAnArray() {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        /* QUESTION :
        Given a sorted array of integers A(0 - indexed) of size N, find the left most and the right most
        index of a given integer B in the array A.
        Return an array of size 2, such that First element = Left most index of B in A
        Second element = Right most index of B in A.
        If B is not found in A,return [-1, -1].
        The time complexity of your algorithm must be O(log n).
        A = {5, 7, 7, 8, 8, 10};
        B = 8;
        output: [3, 4]; */

        int[] A = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 7, 7, 8, 8, 10};
        int B = 7;
        int N = A.length;
        int[] result = new int[]{-1, -1};

        if (N == 0) {
            return result;
        }

        int low = 0;
        int high = N - 1;
        int first = -1;
        int last = -1;

        // Finding the 1st occurrence
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] < B) {
                low = mid + 1;
            } else if (A[mid] > B) {
                high = mid - 1;
            } else {
                first = mid;
                high = mid - 1; // continue searching towards the left
            }
        }

        // Finding the last occurrence
        low = 0;
        high = N - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] < B) {
                low = mid + 1;
            } else if (A[mid] > B) {
                high = mid - 1;
            } else {
                last = mid;
                low = mid + 1; // continue searching towards the right
            }
        }

        // Checking if B was found
        if (first != -1 && last != -1) {
            result[0] = first;
            result[1] = last;
        }

        return result;
    }

    public int positionWhereTargetIsPresentOrShouldBeInserted() {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        /* QUESTION
        You are given a sorted array A of size N and a target value B.
        Your task is to find the index (0-based indexing) of the target value in the array.

        If the target value is present, return its index.
        If the target value is not found, return the index of least element greater than equal to B.
        If the target value is not found and least number greater than equal to target is also not present,
        return the length of array (i.e. the position where target can be placed)
        Your solution should have a time complexity of O(log(N)).
        */

        int[] A = {1, 2, 2, 3, 5, 6, 7, 8, 9};
        int B = 11;

        int N = A.length;
        int low = 0;
        int high = N - 1;
        int res = N;

        while (low <= high) {
            int mid = low + ((high - low) / 2);

            if (A[mid] < B) {
                low = mid + 1;
            } else if (A[mid] > B) {
                res = mid;
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return res;
    }

    public int singleElementInASortedArray() {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        /*
        Given a sorted array of integers A where every element appears twice except for one
        element which appears once, find and return this single element that appears only once.
        Elements which are appearing twice are adjacent to each other.
        Users are expected to solve this in O(log(N)) time.
            */

        int[] A = {1, 1, 2, 2, 3, 3, 4, 5, 5};

        int N = A.length;

        if (N == 1) {
            return A[0];
        }
        if (A[0] != A[1]) {
            return A[0];
        }
        if (A[N - 1] != A[N - 2]) {
            return A[N - 1];
        }

        int low = 1;
        int high = N - 2;


        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] == A[mid + 1]) {
                if (mid % 2 == 0) {
                    low = mid + 2;
                } else {
                    high = mid - 1;
                }
            } else if (A[mid] == A[mid - 1]) {
                mid = mid - 1;
                if (mid % 2 == 0) {
                    low = mid + 2;
                } else {
                    high = mid - 1;
                }
            } else {

                System.out.println(mid);
                return mid;

            }
        }
        return -1;
    }

    public int peakElement() {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        /* QUESTION :
        Given an array of integers A, find and return the peak element in it.
        An array element is considered a peak if it is not smaller than its neighbors.
        For corner elements, we need to consider only one neighbor.
        It is guaranteed that the array contains only a single peak element.
        Users are expected to solve this in O(log(N)) time. The array may contain duplicate elements.
        */

        int[] A = {5, 17, 100, 11};
        int N = A.length;

        if (N == 1) {
            return A[0];
        }

        if (A[0] >= A[1]) {
            return A[0];
        }
        if (A[N - 1] >= A[N - 2]) {
            return A[N - 1];
        }

        int low = 1;
        int high = N - 2;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
                return A[mid];
            } else if (A[mid] > A[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int searhAMatrix() {
        /* QUESTION :
        Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm
        that searches for integer B in matrix A.
        This matrix A has the following properties:
        Integers in each row are sorted from left to right.
        The first integer of each row is greater than or equal to the last integer of the previous row.
        Return 1 if B is present in A, else return 0.
                */

        int[][] A = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int B = 30;

        int N = A.length;
        int M = A[0].length;

        int low = 0;
        int high = (N * M) - 1;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int inC = mid % M;
            int inR = mid / M;

            if (A[inR][inC] == B) {
                System.out.println(A[inR][inC]);
                return 1;
            } else if (A[inR][inC] < B) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return 0;
    }

    public int maximumHeightOfAStaircase(int A) {
        // Complexity : Time : [ ?? ]
        // Complexity : Space : [ ?? ]
        /* QUESTION
        Given an integer A representing the number of square blocks. The height of each square block is 1.
        The task is to create a staircase of max-height using these blocks.
        The first stair would require only one block, and the second stair would require two blocks, and so on.
        Find and return the maximum height of the staircase.
                */

        int x = 1;
        int steps = 0;
        while (A >= x) {
            steps++;
            A -= x;
            x++;
        }
        return steps;
    }

    public int minimumCostToBuildAnArray(int A, int B, int[][] C) {
        // Complexity : Time : [ O(A * B * logB) ]
        // Complexity : Space : [ O(1) ]
        /* QUESTION :
        Given a matrix C of size A x B, where A is the number of rows and B is the number of columns,
        find the minimum absolute difference between any element chosen from row i and any element
        chosen from the adjacent row i+1, across all consecutive row pairs.
        Each row is sorted independently and binary search (lower_bound) is used to find the closest
        element in the next row for each element in the current row.
        Return the minimum such absolute difference.
        Example:
        A = 3, B = 2, C = {{7, 3}, {2, 1}, {4, 9}}
        Sorted C = {{3, 7}, {1, 2}, {4, 9}}
        Min diff between row 0 and row 1: |3-2| = 1
        Min diff between row 1 and row 2: |2-4| = 2
        Output: 1
        */
      int x;
      int ans = 1000000000; // Initialize answer as a large number
      int lb;
      for (int i = 0; i < A; i++) {
         Arrays.sort(C[i]); // Sort each row of matrix
      }
      for (int i = 0; i < A - 1; i++) {
         for (int j = 0; j < B; j++) {
            lb = lower_bound(C[i + 1], C[i][j]); // check for next element as in soltion
            if (lb != B) { // no elements greater
               ans = Math.min(ans, Math.abs(C[i][j] - C[i + 1][lb])); // Update answer
            }
            if (lb != 0) { // all elements greater
               ans = Math.min(ans, Math.abs(C[i][j] - C[i + 1][lb - 1]));
            }
         }
      }
      return ans;
   }

   // function used to find element index just greater than or equal to val
   public int lower_bound(int a[], int val) {
      int low = 0, high = a.length - 1, ans = a.length;
      while (low <= high) {
         int mid = (high - low) / 2 + low;
         if (a[mid] < val) {
            low = mid + 1;
         } else {
            ans = mid;
            high = mid - 1;
         }
      }
      return ans;
   }


    /* Section : ------------------------------- [ Problems ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
        * Notebook_06012025:Page_99
        * */
    }

    private void links() {
        /*
        * academy/mentee-dashboard/class/345269/session?navref=cl_tb_br
        * academy/mentee-dashboard/class/345269/assignment/problems?navref=cl_tb_br
        * academy/mentee-dashboard/class/345269/homework/problems?navref=cl_tb_br
        * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
