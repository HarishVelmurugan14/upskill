package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Harish Velmurugan
 * @last-modified 21-05-2025
 * @since 21-05-2025
 */
public class d49_DP_OneDimensional {

    private final PrintHelper printHelper = new PrintHelper();
    public int MOD = 1_000_000_007;

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public static void main(String[] args) {

        // Inputs


//        You are climbing a staircase and it takes A steps to reach the top.
//        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//                Return the number of distinct ways modulo 1000000007

        // Call Stack
        d49_DP_OneDimensional d49_DP_OneDimensional = new d49_DP_OneDimensional();
        System.out.println(d49_DP_OneDimensional.minimumSquares(13));
        System.out.println(d49_DP_OneDimensional.minimumSquares(5));

    }

    public int minimumSquares(int A) {
        int[] memory = new int[A+1];
        Arrays.fill(memory, -1);
        return minimumSquares(A, memory);
    }

    public int minimumSquares(int A, int[] memory) {
        if (A <= 0) {
            return 0;
        }
        int minCount = Integer.MAX_VALUE;
        for (int i = 1; i * i <= A; i++) {
            int nextNum = A - (i * i);
            if(memory[nextNum] != -1){
                return memory[nextNum];
            }
            memory[nextNum] = minimumSquares(nextNum, memory) + 1;
            // In case of 12 there are 3 set of possibilities for minimum square,
            // so only the minimum one need to be stored in dp not all
            minCount = Math.min(minCount, memory[nextNum]);
        }
        return minCount;
    }

    public void fibonacci_topdown() {
        //NOTE : HK1002
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        int A = 4;
        int[] memory = new int[A + 1];
        Arrays.fill(memory, -1);
        System.out.println(fib_topDown(userInput, memory));
    }

    public int fib_topDown(int A, int[] memory) {
        if (A == 1 || A == 2) {
            return 1;
        }
        if (memory[A] != -1) {
            return memory[A];
        }

        memory[A] = fib_topDown(A - 1, memory) + fib_topDown(A - 2, memory);
        return memory[A];
    }

    public int climbStairs_bottomUp(int A) {
        //NOTE : HK1001
        if (A == 1 || A == 2) return A;

        long prev2 = 1; // f(1)
        long prev1 = 2; // f(2)

        for (int i = 3; i <= A; i++) {
            long current = (prev1 + prev2) % MOD;
            prev2 = prev1;
            prev1 = current;
        }

        return (int) prev1;
    }


    public int climbStairs_topDown(int A) {
        int MOD = 1000000007;
        int[] memory = new int[A + 1];
        Arrays.fill(memory, -1);
        return climbStairs(A, MOD, memory);
    }

    public int climbStairs(int A, int MOD, int[] memory) {
        if (A == 1 || A == 2) {
            return A;
        }
        if (memory[A] != -1) {
            return memory[A];
        }
        memory[A] = (int) (((long) climbStairs(A - 1, MOD, memory) + (long) climbStairs(A - 2, MOD, memory)) % MOD);
        return memory[A];
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
