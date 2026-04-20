package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 05-02-2025
 * @since 05-02-2025
 */

public class d15_ModularArithmeticAndGCD {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d15_ModularArithmeticAndGCD modularArithmeticAndGCD2 = new d15_ModularArithmeticAndGCD();

        long x = modularArithmeticAndGCD2.power(-1, 1, 20);
        modularArithmeticAndGCD2.pairSumDivisibleByM();
        modularArithmeticAndGCD2.gcd(130, 50); // 50, 130 also works
        modularArithmeticAndGCD2.gcdOfAnArray();

    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */


    public long power(int A, int N, int C) {
//        power(71045970, 41535484, 64735492)
//        power(0, 0, 1)
//        power(-1, 1, 20)
        if (A == 0) {
            return 0;
        } else if (N == 0) {
            return 1;
        }
        long halfPower = power(A, N / 2, C) % C;
        halfPower = (halfPower * halfPower) % C;
        if ((N & 1) == 1) {
            halfPower = (halfPower * A) % C;
        }
        return (halfPower + C) % C;
    }


    public int gcd(int a, int b) {
//       a > b or it can be lesser b > a : always it will be re corrected
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private void gcdOfAnArray() {
        int[] A = {6, 12, 20};
        int N = A.length;
        int ans = A[0];
        for (int i = 1; i < N; i++) {
            ans = gcd(ans, A[i]);
        }
        System.out.println(ans);
    }

    private void pairSumDivisibleByM() {
        pairSumDivisibleByM_brute();
        pairSumDivisibleByM_optimal();
    }


    public void pairSumDivisibleByM_brute() {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int[] A = {2, 3, 4, 8, 6, 15, 10, 6, 1, 6};
        int M = 6;
        int count = 0;

        int N = A.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((A[i] + A[j]) % M == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);

    }

    public void pairSumDivisibleByM_optimal() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(M) ]
        int[] A = {2, 3, 4, 8, 6, 15, 10, 6, 1, 6};
//        int[] A = {2, 3, 4, 8, 6, 15, 10, 6, 1, -1};
        int M = 6;

        int N = A.length;
        int[] frequencyCounter = new int[M];
        int count = 0;

        for (int i = 0; i < N; i++) {
//            int rem = A[i] % M;
            int rem = (A[i] + M) % M; // Works for both negative and positive number
            int pair = 0;
            if (rem != 0) {
                pair = M - rem;
            } else {
                // if rem is 0 pair cant be M as % won't return M as pair hence taken only 0
            }
            count += frequencyCounter[pair];
            frequencyCounter[rem] += 1;
        }
        System.out.println(count);
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */






    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    private void print(String message, int[] array) {
        printHelper.print(message, array);
    }

    private void print(String message, int[][] matrix) {
        printHelper.print(message, matrix);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         *
         * */
    }

    private void links() {
        /*
         * academy/mentee-dashboard/class/345235/session?joinSession=1
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
