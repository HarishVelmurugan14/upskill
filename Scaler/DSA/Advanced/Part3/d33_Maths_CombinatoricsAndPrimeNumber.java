package DSA.Advanced.Part3;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harish Velmurugan
 * @last-modified 24-03-2025
 * @since 24-03-2025
 */
public class d33_Maths_CombinatoricsAndPrimeNumber {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d33_Maths_CombinatoricsAndPrimeNumber d33_maths_combinatoricsAndPrimeNumber = new d33_Maths_CombinatoricsAndPrimeNumber();
        d33_maths_combinatoricsAndPrimeNumber.isPrimeNumber(13);
        d33_maths_combinatoricsAndPrimeNumber.allPrimes(10);


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int[] allPrimes(int A) {
        int[] primeArray = new int[A + 1];
        List<Integer> list = new ArrayList<>();
        primeArray[0] = 1;
        primeArray[1] = 1;
        for (int i = 2; i <= A; i++) {
            if (primeArray[i] != 1 && isPrimeNumber(i)) {
                primeArray[i] = 0;
                list.add(i);
            }
            for (int j = 2 * i; j <= A; j = j + i) {
                primeArray[i] = 1;
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int x : list) {
            res[index] = x;
            index++;
        }
        return res;
    }

    public boolean isPrimeNumber(int A) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(A); i++) {
            if (A % i == 0) {
                count++; // First factor
                if (i != A / i) {
                    // Pair factor
                    count++;
                }
            }
        }
        if (count == 2) {
            System.out.println(A + " YES");
            return true;
        }
        return false;
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
