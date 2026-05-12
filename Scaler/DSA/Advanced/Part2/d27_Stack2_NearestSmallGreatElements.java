package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Harish Velmurugan
 * @last-modified 05-03-2025
 * @since 05-03-2025
 */
@SuppressWarnings("UnusedReturnValue")
public class d27_Stack2_NearestSmallGreatElements {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d27_Stack2_NearestSmallGreatElements d27_Stack2_NearestSmallGreatElements = new d27_Stack2_NearestSmallGreatElements();

        int[] A = {8, 2, 4, 9, 7, 5, 3, 10};
        d27_Stack2_NearestSmallGreatElements.print("", A);
        d27_Stack2_NearestSmallGreatElements.nearestSmallerElementsToTheLeft(A);
        d27_Stack2_NearestSmallGreatElements.nearestGreaterElementsToTheRight(A);
        d27_Stack2_NearestSmallGreatElements.nearestSmallerElementsToTheRight(A);
        d27_Stack2_NearestSmallGreatElements.nearestGreaterElementsToTheRight(A);

        d27_Stack2_NearestSmallGreatElements.largestRectangleInAHistogram(new int[]{2, 1, 5, 6, 2, 3}); //#-scl-q1 // Q1 // LC 84
        d27_Stack2_NearestSmallGreatElements.prevSmallerValuesInAnArray(new int[]{4, 5, 2, 10, 8}); //#-scl-q2 // Q2 // LC901
        d27_Stack2_NearestSmallGreatElements.identifyMaximumMinusMinimumInAllPossibleSubArrays(new int[]{1, 3, 3}); //#-scl-q3 // Q3

        d27_Stack2_NearestSmallGreatElements.nextGreaterValues(new int[]{4, 5, 2, 10, 8}); //#-scl-aq2 // AQ2 // LC739 // LC503
        d27_Stack2_NearestSmallGreatElements.nextGreaterElementForSubsetQueriesInADistinctArrays(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}); //#-lc-1 // LC496
        d27_Stack2_NearestSmallGreatElements.sortStackUsingAnotherStack(); //#-scl-aq3 // AQ3
    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public int identifyMaximumMinusMinimumInAllPossibleSubArrays(int[] A) {

        /*
         * SUM OF (MAX - MIN) FOR ALL SUBARRAYS - Monotonic stack x4
         *
         * GOAL: sum of (max - min) across all subarrays
         *       = sum of contributions as MAX - sum of contributions as MIN
         *
         * CONTRIBUTION TRICK:
         *   A[i] is MAX of subarrays where it is the greatest element
         *   A[i] is MIN of subarrays where it is the smallest element
         *
         *   count as MAX = (i - prevGreater[i]) * (nextGreater[i] - i)
         *   count as MIN = (i - prevSmaller[i]) * (nextSmaller[i] - i)
         *
         *   sum += A[i] * countAsMax - A[i] * countAsMin
         *
         * 4 ARRAYS:
         *   prevSmaller[i] → index of nearest smaller to LEFT  (or -1)
         *   nextSmaller[i] → index of nearest smaller to RIGHT (or N)
         *   prevGreater[i] → index of nearest greater to LEFT  (or -1)
         *   nextGreater[i] → index of nearest greater to RIGHT (or N)
         *
         * BOUNDARY DEFAULTS:
         *   prev → -1 (no element to left)
         *   next → N  (no element to right)
         *
         * NOTE: stack stores INDICES not values (unlike prevSmallerValues)
         *
         * Time: O(N)  Space: O(N)
         */
        int N = A.length;
//        print("Actual : ", A);
        int[] prevSmaller = new int[N];
        int[] prevGreater = new int[N];
        int[] nextSmaller = new int[N];
        int[] nextGreater = new int[N];
        Stack<Integer> stack = new Stack<>();
        // PREV SMALLER
        stack.push(0);
        prevSmaller[0] = -1;
        for (int i = 1; i < N; i++) {
            while (A[stack.peek()] >= A[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    prevSmaller[i] = -1;
                    break;
                }
            }
            if (!stack.isEmpty()) prevSmaller[i] = stack.peek();
            stack.push(i);
        }
        print("Prev Smaller : ", prevSmaller);

        stack = new Stack<>();
        // Prev Greater
        stack.push(0);
        prevGreater[0] = -1;
        for (int i = 1; i < N; i++) {
            while (A[stack.peek()] <= A[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    prevGreater[i] = -1;
                    break;
                }
            }
            if (!stack.isEmpty()) prevGreater[i] = stack.peek();
            stack.push(i);
        }


        stack = new Stack<>();
        // NEXT SMALLER
        stack.push(N - 1);
        nextSmaller[N - 1] = N;
        for (int i = N - 2; i >= 0; i--) {
            while (A[stack.peek()] > A[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    nextSmaller[i] = N;
                    break;
                }
            }
            if (!stack.isEmpty()) nextSmaller[i] = stack.peek();
            stack.push(i);
        }
        print("Next Smaller : ", nextSmaller);
        print("Prev Greater : ", prevGreater);

        stack = new Stack<>();
        // NEXT SMALLER
        stack.push(N - 1);
        nextGreater[N - 1] = N;
        for (int i = N - 2; i >= 0; i--) {
            while (A[stack.peek()] < A[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    nextGreater[i] = N;
                    break;
                }
            }
            if (!stack.isEmpty()) nextGreater[i] = stack.peek();
            stack.push(i);
        }
        print("Next Greater : ", nextGreater);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int postive = (i - prevGreater[i]) * (nextGreater[i] - i);
            sum += (postive * A[i]);

            int negative = (i - prevSmaller[i]) * (nextSmaller[i] - i);
            sum -= (negative * A[i]);
        }

        System.out.println(sum);

        return sum;
    }

    public int largestRectangleInAHistogram(int[] A) {
        /*
        Given an array of integers A.
        A represents a histogram i.e A[i] denotes the height of the ith histogram's bar. Width of each bar is 1.
        Find the area of the largest rectangle formed by the histogram.
        */

        /*
         * LARGEST RECTANGLE IN HISTOGRAM - Stack (NSE left + right)
         *
         * STRATEGY: for each bar, find max width it can extend as the shortest bar
         *   width = nextSmaller[i] - prevSmaller[i] - 1
         *   area  = height * width
         *
         * EXAMPLE: A=[2,1,5,6,2,3]
         *   i=2, h=5 → prevSmaller=1, nextSmaller=4 → width=4-1-1=2 → area=10
         *   i=3, h=6 → prevSmaller=2, nextSmaller=4 → width=4-2-1=1 → area=6
         *   maxArea=10
         *
         * prevSmaller[i] → index of nearest smaller to LEFT  (or -1 if none)
         * nextSmaller[i] → index of nearest smaller to RIGHT (or N if none)
         *
         * Time: O(N)  Space: O(N)
         */
        int N = A.length;
        int maxArea = -1;
        int[] prevSmallerElements = nearestSmallerElementsToTheLeft(A);
        int[] nextSmallerElements = nearestSmallerElementsToTheRight(A);
        for (int i = 0; i < N; i++) {
            int height = A[i];
            int width = nextSmallerElements[i] - prevSmallerElements[i] - 1;
            int area = height * width;
            maxArea = Math.max(area, maxArea);
        }
        System.out.println(maxArea);
        return maxArea;
    }

    public int[] prevSmallerValuesInAnArray(int[] A) {
        /* QUESTION :
        Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the
        element has an index smaller than i. More formally,
        G[i] for an element A[i] = an element A[j] such that
        j is maximum possible AND
        j < i AND
        A[j] < A[i]
        Elements for which no smaller element exist, consider the next smaller element as -1.
         */

        /*
         * PREVIOUS SMALLER ELEMENT - Monotonic stack (increasing)
         *
         * STRATEGY: maintain stack of increasing values left to right
         *   pop while stack.peek() >= A[i] (not smaller)
         *   stack empty → no smaller exists → res[i] = -1
         *   stack not empty → peek is nearest smaller → res[i] = peek
         *   push A[i]
         *
         * EXAMPLE: A=[4,5,2,10,8]
         *   i=0: res[0]=-1,  stack=[4]
         *   i=1: peek=4<5  → res[1]=4,  stack=[4,5]
         *   i=2: pop 5,4   → empty → res[2]=-1, stack=[2]
         *   i=3: peek=2<10 → res[3]=2,  stack=[2,10]
         *   i=4: pop 10    → peek=2<8  → res[4]=2,  stack=[2,8]
         *   res=[-1,4,-1,2,2]
         *
         * Time: O(N)  Space: O(N)
         */
        int N = A.length;
        int[] res = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.push(A[0]);
        res[0] = -1;

        for (int i = 1; i < N; i++) {
            while (stack.peek() >= A[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    res[i] = -1;
                    break;
                }
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek();
            }
            stack.push(A[i]);
        }
        print("", res);
        return res;
    }

    public int[] nextGreaterElementForSubsetQueriesInADistinctArrays(int[] nums1, int[] nums2) {
        int N = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[N - 1]);
        map.put(nums2[N - 1], -1);

        for (int i = N - 2; i >= 0; i--) {
            while (stack.peek() <= nums2[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    map.put(nums2[i], -1);
                    break;
                }
            }
            if (!stack.isEmpty()) {
                map.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }
        int M = nums1.length;
        int[] res = new int[M];

        for (int i = 0; i < M; i++) {
            res[i] = map.get(nums1[i]);
        }

        print("", res);
        return res;
    }

    public int[] nextGreaterValues(int[] A) {
        /*
         * NEXT GREATER ELEMENT - Monotonic stack (decreasing), right to left
         *
         * STRATEGY: traverse right to left, maintain decreasing stack
         *   pop while peek() <= A[i] (not greater)
         *   stack empty → no greater exists → res[i] = -1
         *   stack not empty → peek is next greater → res[i] = peek
         *   push A[i]
         *
         * EXAMPLE: A=[4,5,2,10,8]
         *   i=4: res[4]=-1,  stack=[8]
         *   i=3: pop 8 → empty → res[3]=-1, stack=[10]
         *   i=2: peek=10>2  → res[2]=10, stack=[10,2]  ← wait, push after
         *   i=1: pop 2,10 → empty → res[1]=-1, stack=[5]
         *   i=0: peek=5>4  → res[0]=5,  stack=[5,4]
         *   res=[5,-1,10,-1,-1]
         *
         * NOTE: stores VALUES not indices (unlike contribution problems)
         *
         * Time: O(N)  Space: O(N)
         */
        int N = A.length;
        int[] res = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.push(A[N - 1]);
        res[N - 1] = -1;

        for (int i = N - 2; i >= 0; i--) {
            while (stack.peek() <= A[i]) {
                stack.pop();
                if (stack.isEmpty()) {
                    res[i] = -1;
                    break;
                }
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek();
            }
            stack.push(A[i]);
        }
        print("", res);
        return res;
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public int[] nearestSmallerElementsToTheLeft(int[] A) {
        int N = A.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[N];
        res[0] = -1;
        stack.push(0);

        for (int i = 1; i < N; i++) {
            int currentConsideration = A[i];
            int prevElement = A[stack.peek()];
            while (prevElement >= currentConsideration) {
                stack.pop();
                if (!stack.isEmpty()) {
                    prevElement = A[stack.peek()];
                } else {
                    break;
                }
            }
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stack.peek();
            }
            stack.push(i);
        }

        print("", res);
        return res;
    }

    public int[] nearestSmallerElementsToTheRight(int[] A) {
        Stack<Integer> stack = new Stack<>();
        int N = A.length;
        int[] res = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? N : stack.peek();
            stack.push(i);
        }
        return res;
    }

    public int[] nearestGreaterElementsToTheRight(int[] A) {
        int N = A.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[N];
        res[N - 1] = N;
        stack.push(N - 1);

        for (int i = N - 2; i >= 0; i--) {
            int currentConsideration = A[i];
            int prevElement = A[stack.peek()];
            while (prevElement <= currentConsideration) {
                stack.pop();
                if (!stack.isEmpty()) {
                    prevElement = A[stack.peek()];
                } else {
                    break;
                }
            }
            if (stack.isEmpty()) {
                res[i] = N;
            } else {
                res[i] = stack.peek();
            }
            stack.push(i);
        }

        print("", res);
        return res;
    }

    public void sortStackUsingAnotherStack() {
        int[] A = new int[]{5, 17, 100, 11};
        int n = A.length;
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> sorted = new Stack<>();
        for (int x : A) {
            stack.push(x);
        }

        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (sorted.isEmpty()) {
                sorted.push(x);
            } else if (sorted.peek() <= x) {
                sorted.push(x);
            } else {
                int count = 0;
                while (!sorted.isEmpty() && sorted.peek() >= x) {
                    count++;
                    stack.push(sorted.pop());
                }
                sorted.push(x);
                while (count != 0) {
                    sorted.push(stack.pop());
                    count--;
                }
            }
        }

        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = sorted.pop();
        }

        for (int x : ans) {
            System.out.println(x);
        }
    }

    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int[] a) {
        printHelper.print(message, a);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Notebook_06012025 : Page No - 134
         * */
    }

    private void links() {
        /*
         * academy/mentee-dashboard/class/345267/session?joinSession=1
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
