package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 29-01-2025
 * @since 29-01-2025
 */
public class d13_Recursions1 {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d13_Recursions1 recursionOne2 = new d13_Recursions1();

        int sum = recursionOne2.sumOfNNaturalNumbers(5);
        recursionOne2.print("Sum : ", sum);

        // Factorial Of N numbers
        int factorial = recursionOne2.factorial(5);
        recursionOne2.print("Factorial : ", factorial);

        recursionOne2.printAllNumbersInIncreasingOrder(5);
        recursionOne2.printAllNumbersInDecreasingOrder(10);

        int fib = recursionOne2.printNthFibonacciNumber(7);
        recursionOne2.print("Fib : ", fib);
        recursionOne2.printNNumbersFirstInDescendingThenInAscendingOrder(3);

        recursionOne2.findSumOfDigitsOfANumber(46);

        String strAfterSkip = recursionOne2.skipACharacter("", "baaccvgdaa", 'a');
        recursionOne2.print(strAfterSkip);

        strAfterSkip = recursionOne2.skipAString( "baaccvgdapplea", "apple");
        recursionOne2.print(strAfterSkip);

    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    private int sumOfNNaturalNumbers(int N) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        if (N == 0) {
            return 0;
        }
        return sumOfNNaturalNumbers(N - 1) + N;
    }


    public int factorial(int N) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(N) ]
        if (N == 0) { // 0 and 1 both has factorial as 1
            return 1;
        }
        return factorial(N - 1) * N;
    }

    public void printAllNumbersInIncreasingOrder(int N) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(N) ]
        if (N == 0) {
            return;
        }
        printAllNumbersInIncreasingOrder(N - 1);
        System.out.print(N);
    }

    public void printAllNumbersInDecreasingOrder(int N) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(N) ]
        if (N == 0) {
            return;
        }
        System.out.print(N + " ");
        printAllNumbersInDecreasingOrder(N - 1);
    }

    public int printNthFibonacciNumber(int N) {
        // Complexity : Time : [ O(Refer Notes) ]
        // Complexity : Space : [ O(Refer Notes) ]
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        }
        return printNthFibonacciNumber(N - 1) + printNthFibonacciNumber(N - 2);
    }

    public void printNNumbersFirstInDescendingThenInAscendingOrder(int A) {

        if (A == 0) {
            return;
        }
        System.out.print(A + " ");

        printNNumbersFirstInDescendingThenInAscendingOrder(A - 1);
        System.out.print(A + " ");
    }

    public int findSumOfDigitsOfANumber(int N) {
        if (N == 0) {
            return 0;
        }
        return N % 10 + findSumOfDigitsOfANumber(N / 10);
    }



    /* Section : ------------------------------- [ Additional Problems ] ------------------------------- */

    public String skipACharacter(String p, String up, char target) {
        if (up.isEmpty()) {
            return p;
        }
        char current = up.charAt(0);
        if (current == target) {
            p = skipACharacter(p, up.substring(1), target);
        } else {
            p = skipACharacter(p + current, up.substring(1), target);
        }
        return p;
    }

    public String skipAString(String up, String target) {
        if (up.isEmpty()) {
            return "";
        }
        if (up.startsWith(target)) {
            return skipAString(up.substring(target.length()), target);
        } else {
            return up.charAt(0) + skipAString(up.substring(1), target);
        }
    }

    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    private void print(String message, int a) {
        printHelper.print(message, a);
    }

    private void print(String message, int[] a) {
        printHelper.print(message, a);
    }

    private void print(String message, int[][] a) {
        printHelper.print(message, a);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Time complexit : (# total number of function calls * Time comp per function)
         * Space comp : # max active function call in stack * Space comp per function
         * Notebook_06012025 : Page 57
         * */
    }

    private void links() {
        /*
         *
         * academy/mentee-dashboard/class/345217/session?joinSession=1
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
