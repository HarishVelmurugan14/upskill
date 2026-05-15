package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 26-02-2025
 * @since 26-02-2025
 */
@SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedReturnValue"})
public class d23_Searching_BinarySearchOnAnswerSpace {

    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs

        // Call Stack
        d23_Searching_BinarySearchOnAnswerSpace d23SearchingBinarySearchOnAnswerSpace = new d23_Searching_BinarySearchOnAnswerSpace();
        int[] boards = {3, 5, 1, 7, 8, 2, 5, 3};
        int paintersAvailable = 3;
        int timeTakenForOneUnit = 2;
        d23SearchingBinarySearchOnAnswerSpace.paintersPartitionMinimumTimeToPaint(paintersAvailable, timeTakenForOneUnit, boards); // Q1 //##
        d23SearchingBinarySearchOnAnswerSpace.isPaintingCompletionPossible(boards, 23, paintersAvailable);

        int[] cowStalls = {2, 6, 11, 14, 19, 25, 30, 39, 43};
        int cows = 4;

        d23SearchingBinarySearchOnAnswerSpace.aggressiveCowsLargestMinDistance(cowStalls, cows); //Q2 //##
        d23SearchingBinarySearchOnAnswerSpace.canPlaceCows(cowStalls, cows, 8);

        int[] books = new int[]{12, 34, 67, 90};
        int students = 2;

        d23SearchingBinarySearchOnAnswerSpace.minimumDifferenceBetweenBooksAllotted(books, students); // AQ1 //##

        int[] nums = new int[]{5, 17, 100, 11};
        int sum = 130;
        d23SearchingBinarySearchOnAnswerSpace.maxSubArrayFactorLessThanB(nums, sum); // AQ2 //##

        //check lc 410, 2517, 1011, 1658
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    private boolean isPaintingCompletionPossible(int[] C, int A, long maxTime) {
        /*
         * GREEDY CHECK: Can A painters finish all boards within maxTime?
         *
         * Strategy: Assign boards left-to-right to current painter.
         *           If adding next board exceeds maxTime → new painter takes it.
         *           If painters needed > A → impossible, return false.
         *
         * painters > A check is INSIDE the loop (early exit, not after)
         *
         * Time: O(N)  Space: O(1)
         */
        int painters = 1;
        long currentSum = 0;

        for (int board : C) {
            if (currentSum + board > maxTime) {
                painters++; // Assign a new painter
                currentSum = board; // Start new painter's workload
                if (painters > A) return false; // More painters needed than available
            } else {
                currentSum += board;
            }
        }
        return true;
    }

    public int paintersPartitionMinimumTimeToPaint(int A, int B, int[] C) {

        /* QUESTION :
        Given 2 integers A and B and an array of integers C of size N. Element C[i] represents the length of ith board.
        You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B
        units of time to paint 1 unit of the board.
        Calculate and return the minimum time required to paint all boards under the constraints that any painter will
        only paint contiguous sections of the board
        1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter,
        and partially by another.
        2. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3
        but not 2 is invalid.
        Return the ans % 10000003
        */

        /*
         * PAINTERS PARTITION - Binary search on answer
         *
         * SEARCH SPACE:
         *   low  = maxBoard  → guessTime below maxBoard always fails
         *   high = totalSum  → 1 painter does everything
         *
         * BINARY SEARCH:
         *   guessTime = candidate maxTime
         *   canPaint(guessTime) → true  : store bestTime, try smaller → high = guessTime-1
         *                       → false : need more time             → low  = guessTime+1
         *
         * canPaint: greedy left-to-right, new painter when sum exceeds guessTime
         *           painter > noPainter inside loop → early exit
         *
         * FINAL: bestTime * B (board units → actual time)
         *
         * Time: O(N log(sum))  Space: O(1)
         */
        int MOD = 10000003;
        if (C.length == 0) return 0;
        if (A > C.length) A = C.length; // If more painters than boards, reduce A to C.length

        int maxBoard = 0;
        long totalSum = 0;

        for (int board : C) {
            maxBoard = Math.max(maxBoard, board);
            totalSum += board;
        }

        long left = maxBoard, right = totalSum, result = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (isPaintingCompletionPossible(C, A, mid)) {
                result = mid; // Store best possible answer
                right = mid - 1; // Try smaller max time
            } else {
                left = mid + 1; // Increase time limit
            }
        }

        return (int) ((result * B) % MOD); // Multiply by B (time per unit) and take modulo
    }

    private boolean canPlaceCows(int[] stalls, int B, int minDist) {
        int count = 1; // Place the first cow at the first stall
        int lastPlaced = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= minDist) {
                count++;
                lastPlaced = stalls[i];
                if (count == B) return true; // Successfully placed all cows
            }
        }
        return false;
    }

    public int aggressiveCowsLargestMinDistance(int[] A, int B) {
        Arrays.sort(A); // Step 1: Sort stall locations

        /*
         * AGGRESSIVE COWS - Binary search on answer
         *
         * GOAL: Place B cows in stalls such that minimum distance between any two cows is maximized.
         *
         * SEARCH SPACE:
         *   left  = 1                    → minimum possible distance
         *   right = A[last] - A[first]   → maximum possible distance (after sorting)
         *
         * BINARY SEARCH:
         *   mid = candidate minDistance
         *   canPlaceCows(mid) → true  : store result, try larger → left  = mid+1
         *                     → false : too large, shrink        → right = mid-1
         *
         * canPlaceCows: greedy left-to-right, place cow when gap >= minDist
         *               count == B inside loop → early exit
         *
         * Time: O(N log(max-min))  Space: O(1)
         */

        int left = 1, right = A[A.length - 1] - A[0], result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlaceCows(A, B, mid)) {
                result = mid; // Store the largest valid minimum distance
                left = mid + 1; // Try for a larger minimum distance
            } else {
                right = mid - 1; // Reduce search space
            }
        }
        return result;
    }


    public int minimumDifferenceBetweenBooksAllotted(int[] books, int students) {

        /*
         * BOOK ALLOCATION - Binary search on answer
         *
         * GOAL: Allocate contiguous books to students minimizing maximum pages any student reads.
         *
         * EDGE CASES: null/empty books → -1, students <= 0 → -1, students > books → -1
         *
         * SEARCH SPACE:
         *   low  = max(1, minPage) → handles 0-page books, minimum possible max
         *   high = totalPages      → 1 student reads everything
         *
         * BINARY SEARCH:
         *   mid = candidate maxPages
         *   canBeAllotted(mid) → true  : store ans, try smaller → high = mid-1
         *                      → false : need more pages        → low  = mid+1
         *
         * canBeAllotted: greedy left-to-right, new student when sum exceeds maxPages
         *                studentAllotted > students inside loop → early exit
         *
         * Time: O(N log(sum))  Space: O(1)
         */

        // Edge cases
        if (books == null || books.length == 0) return -1;
        if (students <= 0) return -1;
        if (students > books.length) return -1;

        int maxPage = 0;
        int maxSingleBook = 0;
        for (int x : books) {
            maxPage += x;
            maxSingleBook = Math.max(x, maxSingleBook);
        }

        int low = Math.max(1, maxSingleBook); // handles 0-page books
        int high = maxPage;
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canBeAllotted(books, students, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(ans);
        return ans;
    }

    private boolean canBeAllotted(int[] books, int students, int maxPages) {
        int studentAllotted = 1;
        int pagesForCurrentStudent = 0;

        for (int pages : books) {
            pagesForCurrentStudent += pages;
            if (pagesForCurrentStudent > maxPages) {
                studentAllotted++;
                pagesForCurrentStudent = pages;
            }
            if (studentAllotted > students) {
                return false;
            }
        }
        return true;
    }


    public int maxSubArrayFactorLessThanB(int[] nums, int sum) {

        /*
         * SPECIAL INTEGER - Binary search on answer
         *
         * GOAL: Find max subarray length K such that NO subarray of size K has sum > B.
         *
         * SEARCH SPACE:
         *   low  = 0            → minimum possible length
         *   high = nums.length  → maximum possible length
         *
         * BINARY SEARCH:
         *   mid = candidate length
         *   check(mid) → true  : no window exceeds B, try larger → low  = mid+1, store K
         *              → false : some window exceeds B, shrink   → high = mid-1
         *
         * check: sliding window of size guessLength
         *   guessLength == 0 → always true (empty window)
         *   any window sum > B → return false (this length is invalid)
         *
         * EXAMPLES:
         *   A=[1,2,3,4,5], B=10 → K=3 ([1,2,3]=6, [2,3,4]=9, [3,4,5]=12 > 10 → K=3 invalid → K=2? )
         *   A=[5,17,100,11], B=130 → K=3 ([5,17,100]=122, [17,100,11]=128, both <=130 → valid)
         *
         * Time: O(N log(N))  Space: O(1)
         */

        int low = 0;
        int high = nums.length;
        int K = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (subArrayWithLengthLContainSumGreaterThanK(nums, sum, mid)) {
                K = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(K);
        return K;
    }

    boolean subArrayWithLengthLContainSumGreaterThanK(int[] nums, int sum, int guessLength) {
        if (guessLength == 0) return true;

        long windowSum = 0;

        for (int i = 0; i < guessLength; i++) {
            windowSum += nums[i];
        }
        if (windowSum > sum) return false;

        for (int i = guessLength; i < nums.length; i++) {
            windowSum += nums[i];
            windowSum -= nums[i - guessLength];
            if (windowSum > sum) return false;
        }
        return true;
    }



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
