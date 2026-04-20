package Problems.Approaches;

import Resources.Utilities.PrintHelper;

public class SlidingWindow {

    PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        slidingWindow.definitions();
        slidingWindow.execute();
    }

    public void execute() {
        int m = flexibleSlidingWindow_BruteForce(new int[]{5, 6, 3, 1, 4, 2, 1, 1, 1}, 10);
        int m0 = flexibleSlidingWindow_BruteForce(new int[]{1, -3, 4, 2, -1, 3}, 4);
        int m1 = flexibleSlidingWindow_Optimal(new int[]{5, 6, 3, 1, 4, 2, 1, 1, 1}, 10);
        int m2 = flexibleSlidingWindow_Optimal(new int[]{1, -3, 4, 2, -1, 3}, 4);
        printHelper.print("Max possible books : ", m);
        printHelper.print("Max possible books : ", m0);
        printHelper.print("Max possible books 1: ", m1);
        printHelper.print("Max possible books 1: ", m2);
    }

    public void definitions() {
        /*
         * Constant Window : If the window size to be considered is given
         *                   possible traversal for 0 -> N-1 elements in K window is 0 -> N-K
         *                   Select the first window and calculate
         *                   move window by 1 index subtract previous and add new element
         *
         * Flexible Window : Move right hand to the last follow left hand based on need (double while loop)
         *
         *                   Remember sub array identification 0->n-1 ; 1->n-1; 2->n-1 .. n-1 -> n-1
         *                   traversal should go for O(N) for once 0 -> N-1 (some use case can have less
         *                                which is identified during runtime)
         *                   Upon violation reduce left and proceed
         *                   start = end for all traversal
         *                   keep start same move last until possible. repeat cycle
         * */
    }


    public int flexibleSlidingWindow_BruteForce(int[] arr, int k) {
        int n = arr.length;
        int maxCount = 0;
//        for (int left = 0; left < n - maxCount + 1; left++) { // Current use case
        for (int left = 0; left < n; left++) {
            int currentCount = 0;
            int currentSum = 0;
            int right = left;
            while (right < n) {
                currentSum = currentSum + arr[right];
                if (currentSum == k) {
                    currentCount++;
                } else {
                    break;
                }
                right++;
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    public int flexibleSlidingWindow_Optimal(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int maxBooks = 0;
        int currentSum = arr[0];

        while (right < arr.length) {
            // adjust left end of window until condition meets
            while (currentSum > k && left <= right) {
                currentSum = currentSum - arr[left];
                left++;
            }

            // base criteria to check
            if (currentSum == k) {
                maxBooks = Math.max(maxBooks, right - left + 1);
            }
            // move right end of window
            right++;

            // avoid out of index
            if (right < arr.length) {
                currentSum = currentSum + arr[right];
            }
        }
        return maxBooks;
    }

}
