package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 25-02-2025
 * @since 25-02-2025
 */
@SuppressWarnings("UnusedReturnValue")
public class d22_Searching2_BinarySearchProblems {

    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d22_Searching2_BinarySearchProblems d22Searching2BinarySearchProblems = new d22_Searching2_BinarySearchProblems();
//        d22Searching2BinarySearchProblems.squareRootOfANumber(4); // Q1
        d22Searching2BinarySearchProblems.rotatedSortedArraySearch();

        int[] A = {1, 2, 7, 9, 11, 13};
        int[] B = {1, 3, 4, 5, 8, 10};
        d22Searching2BinarySearchProblems.findMedianInSortedArrays(A, B);

    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public int squareRootOfANumber(int A) {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        /* QUESTION:
        Given an integer A. Compute and return the square root of A.
        If A is not a perfect square, return floor(sqrt(A)).
        The value of A*A can cross the range of Integer.
        Do not use the sqrt function from the standard library.
        Users are expected to solve this in O(log(A)) time.
        */

        long low = 1;
        long high = A / 2;
        long sqrt = 1;

        if (A == 0) {
            return 0;
        }

        while (low <= high) {
            long mid = low + ((high - low) / 2);
            long numPos = mid * mid;
            if (numPos <= A) {
                sqrt = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) sqrt;
    }

    public int rotatedSortedArraySearch() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        /* QUESTION :
        Given a sorted array of integers A of size N and an integer B,
        where array A is rotated at some pivot unknown beforehand.
        For example, the array [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0, 1, 2].
        Your task is to search for the target value B in the array. If found, return its index; otherwise, return -1.
        You can assume that no duplicates exist in the array.
        You are expected to solve this problem with a time complexity of O(log(N)).
        */

        rotatedSortedArraySearch_myversion(new int[]{3,1}, 1);

        int[] A = {9, 10, 3, 5, 6, 8};
        int B = 5;

        int N = A.length;
        int low = 0;
        int high = N - 1;
        int k = rotationFactor(A);

        if (B == A[0]) {
            return 0;
        } else if (B > A[0]) {
            high = k > 0 ? k - 1 : N - 1; // Case where rotation factor is 0
        } else {
            low = k;
        }

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (B < A[mid]) {
                high = mid - 1;
            } else if (B > A[mid]) {
                low = mid + 1;
            } else {
                System.out.println(mid);
                return mid;
            }
        }
        return -1;

    }

    public int rotatedSortedArraySearch_myversion(int[] nums, int target) {
        // nums = new int[] { 3, 1 };
        // target = 3;
        int N = nums.length;

        int k = rotationFactor(nums);
        // System.out.println(k);
        int index = -1;
        int low = k;
        int high = N - 1 + k;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int check = mid;
            if (mid > N - 1) {
                check = mid - N;
            }

            // System.out.println(mid + " - C : " + check + " - L : " + low + " - H : " + high);

            if (nums[check] > target) {
                high = mid - 1;
            } else if (nums[check] < target) {
                low = mid + 1;
            } else {
                index = check;
                low = mid + 1;
            }

        }
        // System.out.println(index);
        return index;
    }

    public int rotationFactor(int[] A) {
        int N = A.length;
        int low = 0;
        int high = N - 1;
        int rotationFactor = 0;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (A[mid] >= A[0]) {
                low = mid + 1;
            } else {
                rotationFactor = mid;
                high = mid - 1;
            }
        }
        System.out.println(rotationFactor);
        return rotationFactor;
    }

    public int findMedianInSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) {
            return findMedianInSortedArrays(B, A); // Ensure A is the smaller array
        }

        int n = A.length, m = B.length;
        int low = 0, high = n;
        int numberOfElementsLeftSideShouldHave = (n + m + 1) / 2;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = numberOfElementsLeftSideShouldHave - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : A[partitionX - 1];
            int minRightX = (partitionX == n) ? Integer.MAX_VALUE : A[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : B[partitionY - 1];
            int minRightY = (partitionY == m) ? Integer.MAX_VALUE : B[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // Found the correct partition
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1; // Move left
            } else {
                low = partitionX + 1; // Move right
            }
        }
        return 0;
    }


//    public int medianOfTwoSortedArrays(int[] A, int[] B) {
//        /* QUESTION :
//        Given two sorted arrays A and B of size M and N respectively, return the median of the two sorted arrays.
//        Round of the value to the floor integer [2.6=2, 2.2=2]
//        */
//
//        int N = A.length;
//        int M = B.length;
//        if (N > M) {
//            medianOfTwoSortedArrays(B, A);
//        }
//
//        int low = 0;
//        int high = N; // since we are picking elements all N elements can be taken
//        int leftSideShouldHave = (N + M + 1) / 2;
//
//        while (low <= high) {
//            int midA = low + ((high - low) / 2);
//            int midB = leftSideShouldHave - midA;
//            int leftA = Integer.MIN_VALUE, leftB = Integer.MIN_VALUE;
//            int rightA = Integer.MAX_VALUE, rightB = Integer.MAX_VALUE;
//            if (midA < N) {
//                rightA = A[midA + 1];
//            }
//            if (midB < M) {
//                rightB = B[midB + 1];
//            }
//            if (midA > 0) {
//                leftA = A[midA - 1];
//            }
//            if (midB > 0) {
//                leftB = A[midB - 1];
//            }
//            if (leftA <= rightB && leftB <= rightA) {
//
//            } else {
//                if (leftA > rightB) {
//
//                } else {
//
//                }
//            }
//        }
//
//        return 0;
//    }

    /* Section : ------------------------------- [Additional Problems ] ------------------------------------ */

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */


    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
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
