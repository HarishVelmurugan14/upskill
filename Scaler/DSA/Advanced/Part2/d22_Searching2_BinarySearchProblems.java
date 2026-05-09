package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

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
        d22_Searching2_BinarySearchProblems.readme();

        // Call Stack
        d22_Searching2_BinarySearchProblems d22Searching2BinarySearchProblems = new d22_Searching2_BinarySearchProblems();
        d22Searching2BinarySearchProblems.squareRootOfANumber(4); // Q1
        d22Searching2BinarySearchProblems.rotatedSortedArraySearch();

        int[] A = {1, 2, 7, 9, 11, 13};
        int[] B = {1, 3, 4, 5, 8, 10};
        d22Searching2BinarySearchProblems.findMedianInSortedArrays(A, B);

        d22Searching2BinarySearchProblems.findMatrixMedian(null); //AQ1
        d22Searching2BinarySearchProblems.addOrNot(null, 0); //AQ2
        d22Searching2BinarySearchProblems.athMagicalNumber(0, 0, 0); //AQ3
        d22Searching2BinarySearchProblems.BthSmallestTripletSum(null, 0); //AQ4

    }

    private static void readme() {
        /*
        Problem AQ1 — Matrix Median
        The Setup
            [1, 3, 5]
            [2, 6, 9]
            [3, 6, 9]
        All numbers mixed = 1, 2, 3, 3, 5, 6, 6, 9, 9 →Median = 5
        The Simple Idea
        Instead of sorting everything, just guess a number and ask:

        "How many numbers in the matrix are smaller than my guess?"

        If the answer is less than half →median is bigger, guess higher
        If the answer is more than half →median is smaller, guess lower
        Play it Out
        Total numbers = 9
        Half = 4 (median has exactly 4 numbers smaller than it)
        Guess 1Billion:
        Guess 100:
        Guess 5:
        Row 1: [1, 3, 5] →numbers < 5 = 2 (just 1 and 3)
        Row 2: [2, 6, 9] →numbers < 5 = 1 (just 2)
        Row 3: [3, 6, 9] →numbers < 5 = 1 (just 3)
        Total = 4

        Exactly 4 numbers smaller than 5 ✅ →5 is the median
        Why row -by - row binary search?
        Each row is already sorted, so instead of counting one by one, binary search finds the count instantly.
        */
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

        rotatedSortedArraySearch_myversion(new int[]{3, 1}, 1);

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


        /*
         * MEDIAN OF TWO SORTED ARRAYS - Binary search on partition
         *
         * GOAL: Find partition in both arrays such that all(left) <= all(right)
         * without merging.
         *
         * WHY A smaller: ensures partitionY = leftCount - partitionX stays non-negative (no index crash)
         * WHY +1: (n+m+1)/2 gives left side the extra element on odd totals → median = max(leftSide)
         *
         * 4 boundary values at any partition:
         *   maxLeftX | minRightX   (edges of A's cut)
         *   maxLeftY | minRightY   (edges of B's cut)
         *
         * VALID partition: maxLeftX <= minRightY && maxLeftY <= minRightX
         *   → even : (max(maxLeftX, maxLeftY) + min(minRightX, minRightY)) / 2
         *   → odd  : max(maxLeftX, maxLeftY)
         *
         * MOVE: maxLeftX > minRightY → high = mid-1 (A left too big)
         *       else                 → low  = mid+1 (A left too small)
         *
         * Time: O(log(min(n,m)))  Space: O(1)
         */


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

    public int lowerBound(int A[], int val) {
        int l = 0, h = A.length - 1, ans = -1;
        while (l <= h) {
            int mid = (h - l) / 2 + l;
            if (A[mid] < val) {
                ans = mid;
                l = mid + 1;
            } else h = mid - 1;
        }
        return ans + 1;
    }

    public int findMatrixMedian(int[][] A) {

        /*
        * Given a matrix of integers A of size N x M in which each row is sorted.
            Find and return the overall median of matrix A.
            NOTE: No extra memory is allowed.
            NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
        * */

        A = new int[][]{{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        //A = [1, 2, 3, 3, 5, 6, 6, 9, 9]
        //Median is 5. So, we return 5.

        int low = 0, high = 1000000000, n = A.length, m = A[0].length;
        int medPos = n * m / 2, ans = -1; // number of elements less than median element
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int cnt = 0;
            //count in each row numer of elements <= mid
            for (int i = 0; i < n; i++)
                cnt += lowerBound(A[i], mid);
            if (cnt > medPos) high = mid - 1;
            else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

    public int[] addOrNot(int[] A, int B) {

        /*
        * Given an array of integers A of size N and an integer B.
            In a single operation, any one element of the array can be increased by 1.
            * You are allowed to do at most B such operations.
            Find the number with the maximum number of occurrences and return an array C of size 2,
            * where C[0] is the number of occurrences, and C[1] is the number with maximum occurrence.
            If there are several such numbers, your task is to find the minimum one.
        * */

        A = new int[]{3, 1, 2, 2, 1};
        B = 3;
//        Apply operations on A[2] and A[4] A = [3, 2, 2, 2, 2]
//        Maximum occurrence =  4
//        Minimum value of element with maximum occurrence = 2

        // To do the prefix sum
        long prefix[] = new long[A.length + 1];
        Arrays.sort(A);
        int n = A.length;
        // Make prefix array
        for (int i = 0; i < n; i++) {
            prefix[i + 1] += A[i] + prefix[i];
        }
        int ans[] = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        for (int i = 0; i < n; i++) {
            int lo = 1, hi = i + 1;
            int mx = 0;
            // Binary search to find the value of cnt for each i
            while (lo <= hi) {
                int cnt = (lo + hi) / 2;
                if ((long) A[i] * cnt - (prefix[i + 1] - prefix[i - cnt + 1]) <= B) {
                    mx = cnt;
                    lo = cnt + 1;
                } else {
                    hi = cnt - 1;
                }
            }
            // Update ans
            if (ans[0] < mx) {
                ans[0] = mx;
                ans[1] = A[i];
            }
        }
        return ans;
    }

    public int gcd(int x, int y) {
        if (x == 0)
            return y;
        return gcd(y % x, x);
    }

    public int athMagicalNumber(int A, int B, int C) {

        /*
        * You are given three positive integers, A, B, and C.
            Any positive integer is magical if divisible by either B or C.
            Return the Ath smallest magical number. Since the answer may be very large, return modulo 109 + 7.
            Note: Ensure to prevent integer overflow while calculating.
        * */

        A = 1;
        B = 2;
        C = 3;
//        ans = 2
        // lcm of B and C
        long lcm = (long) B * C / gcd(B, C);
        long low = 2, high = ((long) A * Math.min(B, C)), ans = 0;
        while (low <= high) {
            long mid = (high - low) / 2 + low;
            // f(x) = x / B + x / C - x / lcm(B, C)
            long cntB = mid / B, cntC = mid / C, cntBC = mid / lcm;
            if (cntB + cntC - cntBC >= A) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return (int) (ans % (1000 * 1000 * 1000 + 7));
    }

    public int check(int[] A, int val) {
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            int s = i + 1, e = A.length - 1;
            while (s < e) {
                if (A[i] + A[s] + A[e] < val) {
                    cnt += e - s;
                    s++;
                } else {
                    e--;
                }
            }
        }
        return cnt;
    }

    public int BthSmallestTripletSum(int[] A, int B) {
        /*
        * Given an integer array A of size N.
        If we store the sum of each triplet of the array A in a new list, then find the Bth smallest element among the list.
        NOTE: A triplet consists of three elements from the array. Let's say if A[i], A[j], A[k] are the elements of the triplet then i < j < k.
        *
        * */

        A = new int[]{2, 4, 3, 2};
        B = 3;

//  All the triplets of the array A are:
//
// (2, 4, 3) = 9
// (2, 4, 2) = 8
// (2, 3, 2) = 7
// (4, 3, 2) = 9
//
// So the 3rd smallest element is 9.

        Arrays.sort(A);
        int n = A.length;
        int low = 0, high = A[n - 1] + A[n - 2] + A[n - 3], ans = 0;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            // count of triplets with sum less than mid
            int count = check(A, mid);
            if (count >= B) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

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
