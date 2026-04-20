package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Harish Velmurugan
 * @last-modified 27-01-2025
 * @since 27-01-2025
 */
public class d12_BitWiseManipulations2 {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d12_BitWiseManipulations2 bitManipulationTwo1 = new d12_BitWiseManipulations2();
        bitManipulationTwo1.tripleTrouble();
        bitManipulationTwo1.twoUniqueElements();
        int[] A = {1, 0, 0, 0, 1, 0, 1};
        bitManipulationTwo1.numberOfSubArraysWithOr0(A);
        bitManipulationTwo1.numberOfSubArraysWithOr1(A);
        bitManipulationTwo1.findMinXor();
        bitManipulationTwo1.strangeEqualityWithFormula(5);
        bitManipulationTwo1.sumAfterBitwiseOROperatorOnAllSubArraysOfAnArray();
        bitManipulationTwo1.missingTwoNumbersInRangeNPlus2();


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void tripleTrouble() {
        // Int array where every element is occurring 3 times except 1 number. Identify that. Without extra space

        int x = tripleTrouble_approach3();
        System.out.println(x);
        tripleTrouble_approach4();
    }

    private int tripleTrouble_approach3() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(N) ]

        int[] A = {4, 4, 4, 2, 2, 2, 5, 7, 7, 7};

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i : A) {
            if (hashMap.containsKey(i)) {
                hashMap.put(i, hashMap.get(i) + 1);
            } else {
                hashMap.put(i, 1);
            }
        }

        for (int i : hashMap.keySet()) {
            if (hashMap.get(i) == 1) {
                return i;
            }
        }
        return -1;
    }

    private void tripleTrouble_approach4() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        int[] A = {4, 4, 4, 2, 7, 7, 7};
        int n = A.length;

        int result = 0;
        /*
         * instead of 32 we can find max of array and iterate up to  the bit which the max number has
         *
         * */


        for (int i = 0; i < 32; i++) {
            int currentBitSetCount = 0;
            for (int j = 0; j < n; j++) {
                if (checkIfIthBitIsSet(A[j], i)) {
                    currentBitSetCount++;
                }
            }
            if (currentBitSetCount % 3 == 1) {
                result = setIthBit(result, i);
            }
        }
        System.out.println("AP 4 :" + result);
    }

    /* Section : ------------------------------- [ Problems ] ------------------------------- */


    public void twoUniqueElements() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]

        // all element occurs twice except 2
        int[] A = {4, 4, 5, 5, 6, 6, 3, 1, 7, 7};

        int xorAll = 0;
        for (int i : A) {
            xorAll ^= i;
        }
        System.out.println(xorAll);

        int num1 = 0;
        int num2 = 0;
        int magicIndex = 0;
        for (int i = 0; i < 32; i++) {
            if (checkIfIthBitIsSet(xorAll, i)) {
                magicIndex = i;
            }
        }
        System.out.println("Magic " + magicIndex);

        for (int i = 0; i < A.length; i++) {
            if (checkIfIthBitIsSet(A[i], magicIndex)) {
                num1 ^= A[i];
            } else {
                num2 ^= A[i];
            }
        }
        System.out.println(num1 + " - " + num2);
    }

    private int numberOfSubArraysWithOr0(int[] A) {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]


        int result = 0;
        int countZero = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                countZero++;
            } else {
                result = result + (countZero * (countZero + 1)) / 2;
                countZero = 0;
            }
        }
        result = result + (countZero * (countZero + 1)) / 2;

        System.out.println(result);
        return result;
    }

    public int numberOfSubArraysWithOr1(int[] A) {
        int n = A.length;
        int totalSubArrayCount = (n * (n + 1)) / 2;
        int subArrayWithNo1 = numberOfSubArraysWithOr0(A);
        return totalSubArrayCount - subArrayWithNo1;
    }

    private void sumAfterBitwiseOROperatorOnAllSubArraysOfAnArray_brute() {
        int[] A = {1, 2, 3, 4, 5};
        int n = A.length;

        int sum = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int currentORSum = 0;
                for (int i = start; i <= end; i++) {
                    currentORSum |= A[i];
                }
                sum += currentORSum;
                System.out.println("start - " + start + " end - " + end + " cur " + currentORSum + " sum " + sum);
            }
        }
    }

    public void findMinXor() {
        int x = findMinXor_Brute();
        int y = findMinXor_Optimal();
        System.out.println(x + " - " + y);
    }

    public int findMinXor_Brute() {
//        int[] A = {0, 2, 5, 7};
        int[] A = {0, 4, 7, 9};
        int n = A.length;
        int minXor = A[0] ^ A[1];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentXor = A[i] ^ A[j];
                if (currentXor < minXor) {
                    minXor = currentXor;
                }
            }
        }
        return minXor;
    }

    public int findMinXor_Optimal() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        int[] A = {0, 4, 7, 9};
        Arrays.sort(A);
        int minXor = A[0] ^ A[1];
        int start = 1;
        int end = 2;
        int n = A.length;
        while (end < n) {
            int currentXor = A[start] ^ A[end];
            if (currentXor < minXor) {
                minXor = currentXor;
                if (minXor == 0) {
                    break;
                }
            }
            start++;
            end++;
        }
        return minXor;
    }


    @SuppressWarnings("ReassignedVariable")
    public void sumAfterBitwiseOROperatorOnAllSubArraysOfAnArray() {
        // Complexity : Time : [ O(27N) ]
        // Complexity : Space : [ O(1) ]
//        int[] A = {1, 2, 3, 4, 5};
        int[] A = {7, 8, 9, 10};
        int n = A.length;
        int MOD = 1_000_000_007;

        int totalNumberOfSubArrays = (n * (n + 1)) / 2;

        long answer = 0;
        for (int i = 0; i < 32; i++) {
            int numOfZeroCount = 0;
            int numberOfIgnorableSubArrays = 0;
            for (int j = 0; j < n; j++) {
                if (checkIfIthBitIsSet(A[j], i)) {
                    if (numOfZeroCount > 0) {
                        numberOfIgnorableSubArrays += (numOfZeroCount * (numOfZeroCount + 1)) / 2;
                    }
                    numOfZeroCount = 0;
                } else {
                    numOfZeroCount++;
                }
            }
            numberOfIgnorableSubArrays += (numOfZeroCount * (numOfZeroCount + 1)) / 2;
            long valid = totalNumberOfSubArrays - numberOfIgnorableSubArrays;
//            answer += (valid * (1L << i));
            answer = (answer + valid * (1L << i) % MOD) % MOD;
        }
        System.out.println("Answer : " + answer);
    }

    public void strangeEqualityWithFormula(int A) {
        int x = strangeEqualityWithFormula_brute(A);
        int y = strangeEqualityWithFormula_optimal(A);
    }

    private int strangeEqualityWithFormula_brute(int A) {
        int X = 0;
        int Y = 0;

        for (int i = A - 1; i > 0; i--) {
            if ((i ^ A) == i + A) {
                X = i;
                break;
            }
        }
        for (int i = A + 1; i < Integer.MAX_VALUE; i++) {
            if ((i ^ A) == i + A) {
                Y = i;
                break;
            }
        }

        System.out.println(X ^ Y);
        return X ^ Y;
    }

    private int strangeEqualityWithFormula_optimal(int A) {
        // Complexity : Time : [ O(logN) ]
        // Complexity : Space : [ O(1) ]
        int X = A;

        int numberOfBits = numberOfBits(A);
        int Y = 1 << numberOfBits;
        System.out.println(Y);

        for (int i = 0; i < numberOfBits; i++) {
            if (checkIfIthBitIsSet(A, i)) {
                X = unSetIthBit(X, i);
            } else {
                X = setIthBit(X, i);
            }
        }
        System.out.println(X);
        System.out.println(X ^ Y);

        return X ^ Y;
    }

    //Problem 7
    public void missingTwoNumbersInRangeNPlus2() {
        int[] A = {5, 1, 3, 6};
//        int[] A = {3, 2, 4};
        int[] res1 = missingTwoNumbersInRangeNPlus2_Brute(A);
        int[] res2 = missingTwoNumbersInRangeNPlus2_XORapporoach(A);
    }

    private int[] missingTwoNumbersInRangeNPlus2_Brute(int[] A) {
        // Complexity : Time : [ O(N^2 + 2N) => O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int[] B = new int[2];
        int counter = 0;
        int n = A.length;
        for (int i = 0; i < n + 2; i++) {
            boolean isPresent = false;
            for (int j = 0; j < n; j++) {
                if (A[j] == i + 1) {
                    isPresent = true;
                }
            }
            if (!isPresent) {
                B[counter] = i + 1;
                counter++;
            }
        }
        print("", B);

        return B;
    }

    private int[] missingTwoNumbersInRangeNPlus2_XORapporoach(int[] A) {
        // Complexity : Time : [ O(N + 2 + N + N + 2 + N) => O(4N + 4) => O(N)]
        // Complexity : Space : [ O(1) ]
        int n = A.length;
        int xorOfAllElementsInRangeUptoNPlus2 = 0;
        for (int i = 1; i <= n + 2; i++) {
            xorOfAllElementsInRangeUptoNPlus2 ^= i;
        }
//        System.out.println("All " + xorOfAllElementsInRangeUptoNPlus2);
        int xorOfElementsPresentInTheArray = 0;
        for (int i = 0; i < n; i++) {
            xorOfElementsPresentInTheArray ^= A[i];
        }
//        System.out.println("Array " + xorOfElementsPresentInTheArray);
        // all the duplicate elements will become zero and hence this result is similar to X ^ Y
        int xorResult = xorOfElementsPresentInTheArray ^ xorOfAllElementsInRangeUptoNPlus2;
//        System.out.println("All ^ Array " + xorResult);

        // If a bit is set in result X and Y should be different in that bit

        // Find Index which has a contrasting bit
        int targetIndex = 0;
        for (int i = 0; i < numberOfBits(xorResult); i++) {
            if (checkIfIthBitIsSet(xorResult, i)) {
                targetIndex = i;
                break;
            }
        }
//        System.out.println("Target " + targetIndex);
        int allElementsWhichHasTargetIndexSet = 0;


        for (int i = 0; i < n + 2; i++) {
            if (checkIfIthBitIsSet(i + 1, targetIndex)) {
                allElementsWhichHasTargetIndexSet ^= (i + 1);
            }
        }
        int allElementsWhichHasTargetIndexUnSet = allElementsWhichHasTargetIndexSet ^ xorOfAllElementsInRangeUptoNPlus2;
//        System.out.println(allElementsWhichHasTargetIndexSet + " - " + allElementsWhichHasTargetIndexUnSet);
        int arrayElementsWhichHasTargetIndexSet = 0;


        for (int i = 0; i < n; i++) {
            if (checkIfIthBitIsSet(A[i], targetIndex)) {
                arrayElementsWhichHasTargetIndexSet ^= A[i];
            }
        }
        int arrayElementsWhichHasTargetIndexUnSet = arrayElementsWhichHasTargetIndexSet ^ xorOfElementsPresentInTheArray;
//        System.out.println(arrayElementsWhichHasTargetIndexSet + " - " + arrayElementsWhichHasTargetIndexUnSet);

        int num1 = arrayElementsWhichHasTargetIndexSet ^ allElementsWhichHasTargetIndexSet;
        int num2 = arrayElementsWhichHasTargetIndexUnSet ^ allElementsWhichHasTargetIndexUnSet;

        int[] B = new int[2];
        B[0] = Math.min(num1, num2);
        B[1] = Math.max(num1, num2);

        print("", B);
        return B;
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */


    private boolean checkIfIthBitIsSet(int num, int pos) {
        return (num & (1 << pos)) == 1 << pos;
    }

    private int setIthBit(int num, int pos) {
        return num | (1 << pos);
    }

    private int unSetIthBit(int num, int pos) {
        return num & ~(1 << pos);
    }

    private int toggleIthBit(int num, int pos) {
        return num ^ (1 << pos);
    }

    private int numberOfBits(int num) {
        int x = (int) ((Math.log(num) / Math.log(2)) + 1);
        return x;
    }



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int a) {
        printHelper.print(message, a);
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
         * Notebook_06012025
         * */
    }

    private void links() {
        /*
         * /academy/mentee-dashboard/class/345231/session?navref=cl_dd
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
