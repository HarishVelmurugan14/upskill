package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author Harish Velmurugan
 * @last-modified 24-05-2025
 * @since 24-05-2025
 */
public class d51_DP_Knapsack {

    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d51_DP_Knapsack d51DpKnapsack = new d51_DP_Knapsack();
        System.out.println(d51DpKnapsack.maxValueForKnapsack(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50));
        System.out.println(d51DpKnapsack.unboundedKnapsack(10, new int[]{6, 7}, new int[]{5, 5}));
        System.out.println(d51DpKnapsack.unboundedKnapsackMapApproach(10, new int[]{6, 7}, new int[]{5, 5}));
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int sendingAlienSignal(int A) {
        int MOD = 1000000007;
        if (A == 1) return 2;
        if (A == 2) return 3;

        long a = 2, b = 3, c = 0;

        for (int i = 3; i <= A; i++) {
            c = (a + b) % MOD;
            a = b;
            b = c;
        }

        return (int) c;
    }

    public int fractionalKnapsack(int[] A, int[] B, int C) {
        // A: values, B: weights, C: capacity

        int N = A.length;
        List<double[]> items = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            items.add(new double[]{(double) A[i], (double) B[i], (double) A[i] / B[i]});
        }

        Collections.sort(items, new Comparator<double[]>() {
            @Override
            public int compare(double[] item1, double[] item2) {
                if (item2[2] > item1[2]) {
                    return 1;
                } else if (item2[2] < item1[2]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        double totalValue = 0.0;
        double remainingCapacity = (double) C;

        for (double[] item : items) {
            if (remainingCapacity <= 0) {
                break;
            }

            double itemValue = item[0];
            double itemWeight = item[1];

            if (itemWeight <= remainingCapacity) {
                totalValue += itemValue;
                remainingCapacity -= itemWeight;
            } else {
                double fraction = remainingCapacity / itemWeight;
                totalValue += (fraction * itemValue);
                remainingCapacity = 0;
            }
        }

        // --- MODIFICATION STARTS HERE ---
        // Add a small epsilon to counter floating-point inaccuracies before flooring
        // A common epsilon value is 1e-9 or 1e-10
        double epsilon = 1e-9;
        return (int) Math.floor(totalValue * 100 + epsilon);
    }

    public int unboundedKnapsackMapApproach(int A, int[] B, int[] C) {
        HashMap<String, Integer> memory = new HashMap<>();
        return unboundedKnapsackMapApproach(A, B.length - 1, B, C, memory);
    }

    public String getKey(int index, int capacity) {
        return index + "," + capacity;
    }

    public int unboundedKnapsackMapApproach(int capacity, int index, int[] values, int[] weights, HashMap<String, Integer> memory) {
        if (index == 0) {
            if (capacity >= weights[0]) {
                return values[0];
            } else {
                return 0;
            }
        }
        if (memory.containsKey(getKey(index, capacity))) {
            return memory.get(getKey(index, capacity));
        }

        // Option 1 : Not Select
        int ans = unboundedKnapsackMapApproach(capacity, index - 1, values, weights, memory);
        // Option 2 : Select
        if (capacity >= weights[index]) {
            ans = Math.max(ans, values[index] + unboundedKnapsackMapApproach(capacity - weights[index], index, values, weights, memory));
        }
        memory.put(getKey(index, capacity), ans);
        return memory.get(getKey(index, capacity));
    }

    public int unboundedKnapsack(int A, int[] B, int[] C) {
        //NOTE: HK1007
        int[][] memory = new int[B.length + 1][A + 1];
        for (int i = 0; i <= B.length; i++) {
            for (int j = 0; j <= A; j++) {
                memory[i][j] = -1;
            }
        }
        return unboundedKnapsack(A, B.length - 1, B, C, memory);
    }

    public int unboundedKnapsack(int capacity, int index, int[] values, int[] weights, int[][] memory) {
        if (index == 0) {
            if (capacity >= weights[0]) {
                return values[0];
            } else {
                return 0;
            }
        }
        if (memory[index][capacity] != -1) {
            return memory[index][capacity];
        }

        // Option 1 : Not Select
        int ans = unboundedKnapsack(capacity, index - 1, values, weights, memory);
        // Option 2 : Select
        if (capacity >= weights[index]) {
            ans = Math.max(ans, values[index] + unboundedKnapsack(capacity - weights[index], index, values, weights, memory));
        }
        memory[index][capacity] = ans;
        return memory[index][capacity];
    }


    public int maxValueForKnapsack(int[] A, int[] B, int C) {
        //NOTE: HK1006
        int[][] memory = new int[A.length + 1][C + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= C; j++) {
                memory[i][j] = -1;
            }
        }
        return maxValueForKnapsack(A, B, C, A.length - 1, memory);
    }

    public int maxValueForKnapsack(int[] values, int[] weights, int capacity, int index, int[][] memory) {
        if (index == 0) {
            if (weights[0] <= capacity) {
                return values[0];
            } else {
                return 0;
            }
        }
        if (memory[index][capacity] != -1) {
            return memory[index][capacity];
        }

        int ans = Integer.MIN_VALUE;
        //Option 1 : Select
        if (capacity >= weights[index]) {
            ans = values[index] + maxValueForKnapsack(values, weights, capacity - weights[index], index - 1, memory);
        }
        //Option 2 : NS
        ans = Math.max(ans, maxValueForKnapsack(values, weights, capacity, index - 1, memory));
        memory[index][capacity] = ans;
        return memory[index][capacity];
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
