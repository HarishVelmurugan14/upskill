package DSA.Advanced.Part0;

import Formula.MathsFormulas;
import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 09-01-2025
 * @since 08-01-2025
 */
@SuppressWarnings("UnusedReturnValue")
public class d04_Arrays_SubArraysWithCarryForward {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs
//        int[] array = {1, 2, 6, 1, 3, 4, 6, 4, 6, 3};
//        int[] array = {2};
//        int[] array = {1, 2, 1, 4, 3, 1};
        int[] array = {1, 2, 3};


        // Call Stack
        d04_Arrays_SubArraysWithCarryForward subArraysWithCarryForward = new d04_Arrays_SubArraysWithCarryForward();

        subArraysWithCarryForward.printASubArray(array, 0, array.length - 1);
        subArraysWithCarryForward.printAllSubArrays(array);
        subArraysWithCarryForward.numberOfPossibleSubArrays(21);

//        Problems
        subArraysWithCarryForward.smallestSubArrayWithBothMinAndMaxElements(array); // Q2
        subArraysWithCarryForward.subArrayInAGivenRange(array,1,3); // Q4
        subArraysWithCarryForward.storeAllSubArraysInA2DMatrix(array); // Q5
        subArraysWithCarryForward.maxProfitOnOnlyOneTransaction(); // AQ3
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */


    public int[] subArrayInAGivenRange(int[] A, int B, int C) {
        int[] returnArray = new int[C-B+1];
        int index = 0;
        for(int i = B; i <= C; i++){
            returnArray[index] = A[i];
            index++;
        }
        return returnArray;
    }

    public void bruteForce(int[] array) {
        // Complexity : Time : [ O(N^3) ]
        // Complexity : Space : [ O(1) ]
        int n = array.length;
        int[] minMaxArray = minAndMaxElements(array);
        int minElement = minMaxArray[0];
        int maxElement = minMaxArray[1];
        System.out.println("Max " + maxElement + " Min " + minElement);
        int minSuccessLength = n;

        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                boolean minElementPresent = false;
                boolean maxElementPresent = false;
                printASubArray(array, start, end);
                for (int i = start; i <= end; i++) {
                    if (array[i] == minElement) {
                        minElementPresent = true;
                    }
                    if (array[i] == maxElement) {
                        maxElementPresent = true;
                    }
                }
                if (minElementPresent && maxElementPresent) {
                    int subArrayLength = end - start + 1;
                    if (subArrayLength < minSuccessLength) {
                        minSuccessLength = subArrayLength;
                        System.out.println("Successful Sub array : Start Index : " + start +
                                " End : " + end + " Length : "+minSuccessLength);
                    }
                }
            }
        }

        print("Brute : Minimum Success Length is ", minSuccessLength);
    }

    public void optimal(int[] array) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]

        /* Observations :
         *
         * smallest sub array always contains min and max at the edges
         * only one min and max in result answer
         *  */

        int n = array.length;
        int[] minMaxArray = minAndMaxElements(array);
        int minElement = minMaxArray[0];
        int maxElement = minMaxArray[1];

        int latestMinPosition = -1;
        int latestMaxPosition = -1;
        int minSuccessLength = n;

        for (int i = 0; i < n; i++) {

            if (array[i] == minElement) {
                latestMinPosition = i;
                int currentLength = latestMinPosition - latestMaxPosition + 1;
                if (latestMaxPosition != -1 && currentLength < minSuccessLength) {
                    minSuccessLength = currentLength;
                }
            }

            if (array[i] == maxElement) {
                latestMaxPosition = i;
                int currentLength = latestMaxPosition - latestMinPosition + 1;
                if (latestMinPosition != -1 && currentLength < minSuccessLength) {
                    minSuccessLength = currentLength;
                }
            }
        }

        print("Optimal : Minimum Success Length is ", minSuccessLength);
    }
    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public void smallestSubArrayWithBothMinAndMaxElements(int[] array) {
        bruteForce(array);
        optimal(array);
    }

    public void storeAllSubArraysInA2DMatrix(int[] A) {
        // Complexity : Time : [ O(N^3) ]
        // Complexity : Space : [ O(1) ]
        int n = A.length;
        int totalNumberOfSubArrays = (n * (n + 1)) / 2;
        int[][] allSubArrays = new int[totalNumberOfSubArrays][];
        int totalSubArrayIndex = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int[] subArray = new int[end - start + 1];
                int subArrayIndex = 0;
                for (int i = start; i <= end; i++) {
                    subArray[subArrayIndex] = A[i];
                    subArrayIndex++;
                }
                allSubArrays[totalSubArrayIndex] = subArray;
                totalSubArrayIndex++;
            }
        }
        print("All SubArrays are ", allSubArrays);
    }

    public void maxProfitOnOnlyOneTransaction() {
        int[] A = {1, 4, 5, 2, 4};

        int n = A.length;


        //Brute
        int maxProfit = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                System.out.println("Start : " + start + " End : " + end);

                int currentProfit = A[end] - A[start];
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }
        }


        System.out.println("Max profit : " + maxProfit);


        // Optimal
        /* Observations :
         *
         * All we need is the maximum possible elements to the right of each element
         * */

        maxProfit = 0;
        if (n > 0) {
            int maxNumVisited = A[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                int currentProfit = maxNumVisited - A[i];
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
                if (A[i] > maxNumVisited) {
                    maxNumVisited = A[i];
                }
            }
        }


        System.out.println("Max profit : " + maxProfit);

    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public void printASubArray(int[] array, int start, int end) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        for (int i = start; i <= end; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    public void printAllSubArrays(int[] array) {
//        array = new int[]{4, 1, 2, 3, -1, 6, 9, 8, 12};
        // Complexity : Time : [ O(N^3) ]
        // Complexity : Space : [ O(1) ]
        for (int start = 0; start < array.length; start++) {
            for (int end = start; end < array.length; end++) {
                print("SubArray with start : " + start + " and end : " + end);
                printASubArray(array, start, end);
                print("");
            }
        }
        numberOfPossibleSubArrays(array);
    }

    public void numberOfPossibleSubArrays(int[] array) {
        int n = array.length;
        numberOfPossibleSubArrays(n);
    }

    public void numberOfPossibleSubArrays(int n) {
        int count = (n * (n + 1)) / 2;;
        print("Total Number of SubArray's possible : " + count);
    }

    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, Object value) {
        printHelper.print(message, value);
    }

    private void print(String message, int[][] twoDArray) {
        printHelper.print(message, twoDArray);
    }

    private void print(String message) {
        printHelper.print(message, "");
    }

    private int[] minAndMaxElements(int[] array) {
        int[] minMaxArray = new int[2];
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        Arrays.sort(newArray);
        minMaxArray[0] = newArray[0];
        minMaxArray[1] = newArray[newArray.length - 1];
        return minMaxArray;
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
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
