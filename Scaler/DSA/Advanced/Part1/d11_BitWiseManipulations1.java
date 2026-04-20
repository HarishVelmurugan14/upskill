package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 25-01-2025
 * @since 25-01-2025
 */
public class d11_BitWiseManipulations1 {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d11_BitWiseManipulations1 bitWiseOperatorsOne3 = new d11_BitWiseManipulations1();
        bitWiseOperatorsOne3.oddOrEven();
        bitWiseOperatorsOne3.powerOfLeftShiftOperator();
        bitWiseOperatorsOne3.powerOfRightShiftOperator();
        bitWiseOperatorsOne3.computerScientist(4, 3, 2);

        bitWiseOperatorsOne3.arrayContainingOnlyOneDistintNumber(new int[]{1, 2, 3, 2, 1});

        bitWiseOperatorsOne3.findingGoodDaysForAlexCat();


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Problems ] ------------------------------- */

    private void oddOrEven() {
        int num = 11134421;

        if ((num & 1) == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }
    }

    private void powerOfLeftShiftOperator() {
        setTheIthBitInNumberN(12, 3);
        toggleBitIInNumberN(12, 3);
        unSetTheIthBitInNumberN(4, 3);
        checkIfIthBitIsSetOrUnset(12, 4);
    }

    private void powerOfRightShiftOperator() {
        countNumberOfSetBits(6);
    }

    public void setTheIthBitInNumberN(int N, int i) {
        System.out.println("After setting " + N + " at " + i + " bit " + (N | (1 << i)));
    }

    public void toggleBitIInNumberN(int N, int i) {
        // Property of XOR 0^1 = 1; 1^1=0; xo ring with 1 gives opposite of input
        System.out.println("After toggling " + N + " at " + i + " bit " + (N ^ (1 << i)));
    }

    public void unSetTheIthBitInNumberN(int N, int i) {
        System.out.println("After un setting " + N + " at " + i + " bit " + (N & (~(1 << i))));
    }

    public void checkIfIthBitIsSetOrUnset(int N, int i) {
        if ((N & (1 << i)) == (1 << i)) {
            System.out.println("The Ith " + i + " bit is set");
        } else {
            System.out.println("The Ith " + i + " bit is unSet");
        }
    }

    public void countNumberOfSetBits(int N) {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        int A = N;
        int count = 0;
        while (A != 0) {
            if ((A & 1) == 1) {
                count++;
            }
            A = A >> 1;
        }
        System.out.println("Number of set bits in N is " + count);
    }

    private void computerScientist(int A, int B, int C) {
        // set A 0'1 B 1's and C 0's
        // A 0's has no meaning
//        int result = (int) (Math.pow(2, 3) - 1);
        int result = (1 << B) - 1;
        System.out.println(result);
        System.out.println(result << C);
    }

    public int arrayContainingOnlyOneDistintNumber(int[] A) {
        int result = A[0];
        for (int i = 1; i < A.length; i++) {
            result = result ^ A[i];
        }
        return result;
    }

    public void findingGoodDaysForAlexCat(){
        int A = 5;
        countNumberOfSetBits(A);
    }

    public int findNthMagicNumber(int A) {
        int magicNumber = 0;
        int power = 1;

        while (A > 0) {
            if ((A & 1) == 1) {
                magicNumber += Math.pow(5, power);
            }
            power++;
            A >>= 1;
        }

        return magicNumber;
    }

    public int helpFromSam(int A) {
        int N = A;
        int count = 0;
        while (N != 0) {
            if ((N & 1) == 1) {
                count++;
            }
            N = N >> 1;
        }
        return count;
    }


    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
        *
        * Notebook_06012025
        * */
    }

    private void links() {
        /*
        * academy/mentee-dashboard/class/345219/session?navref=cl_dd
        * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
