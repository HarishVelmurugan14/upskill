package DSA.Advanced.Part0;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 08-01-2025
 * @since 08-01-2025
 */
public class d04_Arrays_CarryForward {

    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs
        // Consider the number of a, g pairs only in forward order
        char[] charArray = {'a', 'g', 'c', 'g', 'a', 'g', 'g'};

        // Call Stack
        d04_Arrays_CarryForward carryForward = new d04_Arrays_CarryForward();
        carryForward.bruteForce(charArray);
        carryForward.optimal(charArray);

        // Problems
        carryForward.numberOfAGPairsInAString("agcgagg"); // Q6
        carryForward.maxPossibleElementConsideringNEdgeElements(); // AQ1
        carryForward.leaderElements(); // AQ2

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce(char[] charArray) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int n = charArray.length;
        int pairCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (charArray[i] == 'a') {
                for (int j = i + 1; j < n; j++) {
                    if (charArray[j] == 'g') {
                        pairCount++;
                    }
                }
            }
        }
        print("Brute : Number of pairs : ", pairCount);
    }

    public void optimal(char[] charArray) {
        // Complexity : Time : [ O(n) ]
        // Complexity : Space : [ O(1) ]
        int n = charArray.length;
        int numberOfAVisited = 0;
        int numberOfAGPairs = 0;

        for (int i = 0; i < n; i--) {
            if (charArray[i] == 'a') {
                numberOfAVisited++;
            } else if (charArray[i] == 'g') {
                numberOfAGPairs += numberOfAVisited;
            }
        }
        print("Optimal : Number of pairs : ", numberOfAGPairs);
    }
    /* Section : ------------------------------------- [ Problems ] ----------------------------------- */

    public void numberOfAGPairsInAString(String A) {
        // Complexity : Time : [ O(n) ]
        // Complexity : Space : [ O(1) ]
        char[] charArray = A.toCharArray();
        optimal(charArray);
    }

    public void maxPossibleElementConsideringNEdgeElements() {

        /* Observations :
         *
         * subtracting current element and removing target element provides new sum
         *  */

        int[] A = {5, -2, 3, 1, 2};
        int B = 3;
        int maxSum = 0;
        for (int i = 0; i < B; i++) {
            maxSum += A[i];
        }

        int removeIndex = B - 1;
        int reverseSum = maxSum;
        for (int j = A.length - 1; j > A.length - 1 - B; j--) {
            reverseSum = reverseSum - A[removeIndex] + A[j];
            if (reverseSum > maxSum) {
                maxSum = reverseSum;
            }
            removeIndex--;
        }

        System.out.println("Maximum possible edge sum " + maxSum);
    }

    public void leaderElements() {
//        Elements which have all the elements to right lower than them are leader elements
        int[] A = {16, 17, 4, 3, 5, 2};


        int n = A.length - 1;
        int[] returnArray = new int[n];
        int maxNumVisitedSoFar = A[n];
        returnArray[0] = maxNumVisitedSoFar;
        int index = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > maxNumVisitedSoFar) {
                returnArray[index] = A[i];
                maxNumVisitedSoFar = A[i];
                index++;
            }
        }
        System.out.println(index);

        System.out.println(Arrays.toString(returnArray));

        int[] result = new int[index];
        int tmp = 0;
        for (int i = index - 1; i >= 0; i--) {
            result[tmp] = returnArray[i];
            tmp++;
        }

        System.out.println(Arrays.toString(result));
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int count) {
        printHelper.print(message, count);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Remainder technique :  Instead of checking each time all possible scenarios,
         * somehow carry forward its position, aggregate of the needed value
         * Notebook_06012025 : Page 5
         * */
    }

    private void links() {
        /*
         * /academy/mentee-dashboard/class/348473/session?joinSession=1
         * /academy/mentee-dashboard/class/348473/assignment/problems
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
