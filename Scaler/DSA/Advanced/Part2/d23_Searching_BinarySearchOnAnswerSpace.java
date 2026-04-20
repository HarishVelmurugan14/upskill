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
        d23SearchingBinarySearchOnAnswerSpace.paintersPartitionMinimumTimeToPaint(paintersAvailable, timeTakenForOneUnit, boards); // Q1
        d23SearchingBinarySearchOnAnswerSpace.isPaintingCompletionPossible(boards, 23, paintersAvailable);

        int[] cowStalls = {2, 6, 11, 14, 19, 25, 30, 39, 43};
        int cows = 4;

        d23SearchingBinarySearchOnAnswerSpace.aggressiveCowsLargestMinDistance(cowStalls, cows); //Q2
        d23SearchingBinarySearchOnAnswerSpace.canPlaceCows(cowStalls, cows, 8);

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    private boolean isPaintingCompletionPossible(int[] C, int A, long maxTime) {
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
