package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 25-05-2025
 * @since 25-05-2025
 */
public class d52_DP_KnapsackApplications {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d52_DP_KnapsackApplications d52DpKnapsackApplications = new d52_DP_KnapsackApplications();
        System.out.println(d52DpKnapsackApplications.maxProfitFromRodCutting(new int[]{3, 4, 1, 6, 2}));
        System.out.println(d52DpKnapsackApplications.coinchange2(new int[]{1, 2, 3}, 4));
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int knapsack01BottomUp(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < n; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }
        return dp[capacity];
    }

    public int coinchange2(int[] A, int B) {
        //NOTE : HK1010
        int[][] memory = new int[A.length][B + 1];
        for (int i = 0; i < A.length; i++) {
            Arrays.fill(memory[i], -1);
        }
        return coinchange2(A, B, 0, memory);
    }

    public int coinchange2(int[] coins, int remaining, int index, int[][] memory) {
        if (remaining == 0) return 1;
        if (remaining < 0 || index == coins.length) return 0;

        if (memory[index][remaining] != -1) {
            return memory[index][remaining];
        }

        // Option 1: Include current coin
        int include = coinchange2(coins, remaining - coins[index], index, memory);
        // Option 2: Exclude current coin
        int exclude = coinchange2(coins, remaining, index + 1, memory);

        memory[index][remaining] = include + exclude;
        return memory[index][remaining];
    }


    public int maxProfitFromRodCutting(int[] A) {
        //NOTE : HK1009
        // Complexity : Time : [ O(N^2) ]
        int[] memory = new int[A.length];
        Arrays.fill(memory, -1);
        return maxProfitFromRodCutting(A, A.length, memory);
    }

    public int maxProfitFromRodCutting(int[] A, int index, int[] memory) {
        if (index == 0) {
            return 0;
        }
        if (memory[index - 1] != -1) {
            return memory[index - 1];
        }

        int maxProfit = 0;
        for (int i = 1; i <= index; i++) {
            int currentProfit = A[i - 1] + maxProfitFromRodCutting(A, index - i, memory);
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        memory[index - 1] = maxProfit;
        return memory[index - 1];
    }

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


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
