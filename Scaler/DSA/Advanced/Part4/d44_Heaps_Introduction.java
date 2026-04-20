package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Harish Velmurugan
 * @last-modified 25-05-2025
 * @since 25-05-2025
 */
public class d44_Heaps_Introduction {

    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d44_Heaps_Introduction d44HeapsIntroduction = new d44_Heaps_Introduction();
    }


    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */
    public void buildMinHeap(int[] A){
        MinHeapManual.buildMinHeap(A);
    }

    public int[] solveQueries(int[][] A) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int[] row: A){
            int P = row[0];
            int Q = row[1];
            if(P == 1){
                if(minHeap.size() > 0){
                    list.add(minHeap.poll());
                } else {
                    list.add(-1);
                }
            }else{
                minHeap.add(Q);
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0; i< res.length; i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public int connectingRopesPQ(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int j : ropes) {
            minHeap.add(j);
        }
        int totalCost = 0;
        while (minHeap.size() > 1){
            int x = minHeap.poll();
            int y = minHeap.poll();
            int cost = x + y;
            totalCost += cost;
            minHeap.add(cost);
        }
        return totalCost;
    }

    public int connectingRopesManual(int[] A) {
        if (A == null || A.length <= 1) return 0;
        MinHeapManual.buildMinHeap(A);

        int totalCost = 0;
        int heapSize = A.length;

        while (heapSize > 1) {
            int first = MinHeapManual.extractMin(A, heapSize);
            heapSize = heapSize - 1;

            int second = MinHeapManual.extractMin(A, heapSize);
            heapSize = heapSize - 1;

            int cost = first + second;
            totalCost = totalCost + cost;

            A[heapSize] = cost; // place at the next open slot
            heapSize = heapSize + 1;

            MinHeapManual.heapify(A, heapSize, 0);
            ;
        }

        return totalCost;
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

class MinHeapManual {
    private void rules(){
        // Binary Tree in the hide
        // all levels should have 2 except last
        // all child should be larger for min heap
        // left = 2i+1
        // right = 2i+2
        // parent = (i-1)/2
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Heapify the subtree rooted at index i
    public static void heapify(int[] heap, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < n && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(heap, i, smallest);
            heapify(heap, n, smallest);
        }
    }

    // Build a min heap from array
    public static void buildMinHeap(int[] heap) {
        int n = heap.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(heap, n, i);
        }
    }

    // Extract the minimum element (root) from heap
    public static int extractMin(int[] heap, int heapSize) {
        if (heapSize <= 0) return Integer.MIN_VALUE;

        int min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapify(heap, heapSize - 1, 0);
        return min;
    }
}
