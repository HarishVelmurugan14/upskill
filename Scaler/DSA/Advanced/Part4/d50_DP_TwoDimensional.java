package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 21-05-2025
 * @since 21-05-2025
 */
public class d50_DP_TwoDimensional {

    private final PrintHelper printHelper = new PrintHelper();
    public int MOD = 1_000_000_007;

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d50_DP_TwoDimensional d50DpTwoDimensional = new d50_DP_TwoDimensional();

        int[][] x = new int[][]{{1, 2, 3, 4}, {2, 3, 4, 5}};
        int[][] y = new int[][]{{28}, {10}};
        d50DpTwoDimensional.maxSum(x);
        d50DpTwoDimensional.maxSum(y);
        System.out.println(d50DpTwoDimensional.nDigitNumbers(2, 2));
        System.out.println(d50DpTwoDimensional.numBSTrees(3));
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */
    public int numBSTrees(int A) {
        //NOTE : HK1005 Catalan Number
        // Time Complexity : #number of States * Work done in each state
        // A* (Iteration of A) => O[A^2]
        int[] memory = new int[A + 1];
        Arrays.fill(memory, -1);
        return numBSTrees(A, memory);
    }

    public int numBSTrees(int A, int[] memory) {
        if (A == 0 || A == 1) return 1;
        if(memory[A] != -1){
            return memory[A];
        }

        int count = 0;
        for (int i = 0; i < A; i++) {
            count += (numBSTrees(i, memory) * numBSTrees(A - (i + 1), memory));
        }
        memory[A] = count;
        return memory[A];
    }

    public int nDigitNumbers(int A, int B) {
        // NOTE : HK1004
        // Complexity : Time : [ O(A*B) ]
        int[][] memory = new int[A + 1][B + 1];

        for (int i = 0; i <= A; i++) {
            for (int j = 0; j <= B; j++) {
                memory[i][j] = -1;
            }
        }
        return nDigitNumbers(A, B, memory);
    }

    public int nDigitNumbers(int pos, int sum, int[][] memory) {
        if (pos == 1) {
            if (sum >= 1 && sum <= 9) {
                return 1;
            } else {
                return 0;
            }
        }
        if (sum <= 0) return 0;

        if (memory[pos][sum] != -1) {
            return memory[pos][sum];
        }

        int count = 0;
        for (int i = 0; i <= 9; i++) {
            if (sum >= i) {
                count = (count + nDigitNumbers(pos - 1, sum - i, memory)) % MOD;
            }
        }
        memory[pos][sum] = count;
        return memory[pos][sum];
    }


    public int maxSum(int[][] A) {
        // NOTE : HK1003
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [  ]
        int[] memory = new int[A[0].length];
        Arrays.fill(memory, -1);
        int x = maxSum(A, A[0].length - 1, memory);
        System.out.println(x);
        return x;
    }

    public int maxSum(int[][] A, int col, int[] memory) {
        if (col < 0) return 0;
        if (col == 0) return Math.max(A[0][0], A[1][0]);

        if (memory[col] != -1) {
            return memory[col];
        }

        //Option 1 : Consider top element and skip prev column
        int option1Res = A[0][col] + maxSum(A, col - 2, memory);
        //Option 2 : Consider bottom element and skip prev column
        int option2Res = A[1][col] + maxSum(A, col - 2, memory);
        //Option 1 : Skip current column
        int option3Res = maxSum(A, col - 1, memory);

        memory[col] = Math.max(Math.max(option1Res, option2Res), option3Res);

        return memory[col];
    }


    public int uniquePathsWithObstacles(int[][] A) {
        return path(A, A.length - 1, A[0].length - 1);
    }

    public int path(int[][] A, int n, int m) {
        if (A[n][m] == 1) {
            return 0;
        } else if (n == 0 && m == 0) {
            return 1;
        }

        return (n > 0 ? path(A, n - 1, m) : 0) + (m > 0 ? path(A, n, m - 1) : 0);
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
