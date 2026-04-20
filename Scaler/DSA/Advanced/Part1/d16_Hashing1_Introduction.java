package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Harish Velmurugan
 * @last-modified 08-02-2025
 * @since 08-02-2025
 */
public class d16_Hashing1_Introduction {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d16_Hashing1_Introduction d16_Hashing1_Introduction = new d16_Hashing1_Introduction();
        d16_Hashing1_Introduction.frequencyCounter(new int[]{6, 3, 3, 6, 7, 8, 7, 3, 7}, new int[]{10, 9, 8});
        d16_Hashing1_Introduction.commonElements();
        d16_Hashing1_Introduction.firstRepeatingElement();
        d16_Hashing1_Introduction.distinctElements();
        d16_Hashing1_Introduction.subArraySumAsZero();
        d16_Hashing1_Introduction.countSubArraySumAsZero();

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void distinctElements() {
        int[] A = {3, 3, 3, 9, 0, 1, 0};
        int N = A.length;
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            int x = A[i];
            if (!frequencyMap.containsKey(x)) {
                count++;
                frequencyMap.put(x, 1);
            }
        }
        System.out.println(count);
    }

    public void firstRepeatingElement() {
        int[] A = {10, 5, 3, 4, 3, 5, 6};
        int N = A.length;
        HashMap<Integer, Integer> frequencyMap_A = new HashMap<>();
        int minIndex = Integer.MAX_VALUE;
        int valueToReturn = -1;
        for (int i = 0; i < N; i++) {
            int x = A[i];
            if (frequencyMap_A.containsKey(x)) {
                int curMinIndex = frequencyMap_A.get(x);
                if (curMinIndex < minIndex) {
                    minIndex = curMinIndex;
                    valueToReturn = x;
                }
            } else {
                frequencyMap_A.put(x, i);
            }
        }
        System.out.println(valueToReturn);
    }


    public void subArraySumAsZero() {
        int[] A = {-1, 1};
        int N = A.length;
        HashMap<Long, Integer> prefixMap = new HashMap<>();

        Long sum = 0L;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            System.out.println(sum);
            if (sum == 0) {
                System.out.println(1);
            } else if (prefixMap.containsKey(sum)) {
                int targetIndex = prefixMap.get(sum);
                System.out.println((targetIndex + 1) + " - " + i);
                break;
            } else {
                prefixMap.put(sum, i);
            }
        }

    }


    public void countSubArraySumAsZero() {
        int[] A = {1, -1, -2, 2};
        int N = A.length;
        HashMap<Long, Integer> prefixMap = new HashMap<>();
        int count = 0;
        Long sum = 0L;
        boolean once = true;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            System.out.println(sum);
            if (once && sum == 0) {
                count++;
                once = false;
            }
            if (prefixMap.containsKey(sum)) {
                int prevElement = prefixMap.get(sum);
                prefixMap.put(sum, prevElement + 1);
                count += prevElement + 1;
            } else {
                prefixMap.put(sum, 1);
            }
        }
        System.out.println();
        System.out.println(count);
    }

    public void commonElements() {
        int[] A = {1, 2, 2, 1};
        int[] B = {2, 3, 1, 2};
        int N = A.length;
        int M = B.length;
        HashMap<Integer, Integer> frequencyMap_A = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = A[i];
            if (frequencyMap_A.containsKey(x)) {
                frequencyMap_A.put(x, frequencyMap_A.get(x) + 1);
            } else {
                frequencyMap_A.put(x, 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int x = B[i];
            if (frequencyMap_A.containsKey(x)) {
                list.add(x);
                int currFreq = frequencyMap_A.get(x);
                if (currFreq > 1) {
                    frequencyMap_A.put(x, currFreq - 1);
                } else {
                    frequencyMap_A.remove(x);
                }
            }
        }

        print("", list);
        int count = 0;
        int[] C = new int[list.size()];

        for (int x : list) {
            C[count] = x;
            count++;
        }
        print("", C);

    }


    public void frequencyCounter(int[] A, int[] B) {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        int N = A.length;
        int M = B.length;
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int x = A[i];
            if (frequencyMap.containsKey(x)) {
                frequencyMap.put(x, frequencyMap.get(x) + 1);
            } else {
                frequencyMap.put(x, 1);
            }
        }

        print("II", frequencyMap);
        int[] C = new int[M];

        for (int i = 0; i < M; i++) {
            int x = B[i];
            C[i] = frequencyMap.getOrDefault(x, 0);
        }

        print("", C);


    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int[] array) {
        printHelper.print(message, array);
    }

    private void print(String code, ArrayList<Integer> list) {
        printHelper.print(code, list);
    }

    private void print(String code, HashMap hashMap) {
        printHelper.print(code, hashMap);
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
