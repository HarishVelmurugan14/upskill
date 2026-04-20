package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author Harish Velmurugan
 * @last-modified 26-05-2025
 * @since 26-05-2025
 */
public class d45_Heaps_SortAndGreedy {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d45_Heaps_SortAndGreedy d45HeapsSortAndGreedy = new d45_Heaps_SortAndGreedy();
        d45HeapsSortAndGreedy.expectedDeliveryTime(new int[]{1, 2, 5, 4, 3});
        d45HeapsSortAndGreedy.maxJobs(new int[]{1, 5, 7, 1}, new int[]{7, 8, 8, 8});
        d45HeapsSortAndGreedy.medianRunningArray();

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */
    public void medianRunningArray(){
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        System.out.println(m.findMedian());
        m.addNum(2);
        System.out.println(m.findMedian());
        m.addNum(5);
        System.out.println(m.findMedian());
        m.addNum(4);
        System.out.println(m.findMedian());
        m.addNum(3);
        System.out.println(m.findMedian());
    }


    public int[] expectedDeliveryTime(int[] deliveryTimes) {
        int n = deliveryTimes.length;
        PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
        int[] res = new int[n];
        leftMaxHeap.add(deliveryTimes[0]);
        res[0] = deliveryTimes[0];

        for (int i = 1; i < n; i++) {
            int maxOfLeftHalf = leftMaxHeap.peek();
            if (maxOfLeftHalf < deliveryTimes[i]) {
                rightMinHeap.add(deliveryTimes[i]);
            } else {
                leftMaxHeap.add(deliveryTimes[i]);
            }
            if (leftMaxHeap.size() - rightMinHeap.size() > 1) {
                rightMinHeap.add(leftMaxHeap.poll());
            } else if (rightMinHeap.size() - leftMaxHeap.size() > 1) {
                leftMaxHeap.add(rightMinHeap.poll());
            }
            int medianIndexNeeded;
            int median;
            if ((i + 1) % 2 == 0) {
                medianIndexNeeded = (i + 1) / 2 - 1;
            } else {
                medianIndexNeeded = (i + 1) / 2;
            }
            if (leftMaxHeap.size() >= medianIndexNeeded + 1) {
                median = leftMaxHeap.peek();
            } else {
                median = rightMinHeap.peek();
            }
            res[i] = median;
        }
        printHelper.print("", res);
        return res;
    }


    public int[] expectedDeliveryTime_tle(int[] deliveryTimes) {
        int n = deliveryTimes.length;
        int[] temp = new int[n];
        int[] res = new int[n];
        Arrays.fill(temp, -1);
        for (int i = 0; i < n; i++) {
            int firstNonZeroIndex = n - i - 1;
            temp[firstNonZeroIndex] = deliveryTimes[i];
            Arrays.sort(temp, firstNonZeroIndex, n);
            int medianIndexNeeded;
            if ((i + 1) % 2 == 0) {
                medianIndexNeeded = (i + 1) / 2 - 1;
            } else {
                medianIndexNeeded = (i + 1) / 2;
            }
            int median = temp[medianIndexNeeded + firstNonZeroIndex];
            res[i] = median;
        }
        printHelper.print("", res);
        return res;
    }


    public int maxJobs(int[] A, int[] B) {
        int n = A.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(A[i], B[i]);
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a.endTime, b.endTime));
//        for (Job j : jobs) {
//            System.out.println(j.startTime + " -> " + j.endTime);
//        }
        int prev = 0;
        int jobsCount = 0;
        for (Job j : jobs) {
            int start = j.startTime;
            int end = j.endTime;
            if (start >= prev) {
                prev = end;
                jobsCount++;
            }
        }
        System.out.println(jobsCount);
        return jobsCount;
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

class Job {
    int startTime;
    int endTime;

    public Job(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

class MedianFinder {
    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        int n = leftMaxHeap.size() + rightMinHeap.size();
        if(n == 0){
            leftMaxHeap.add(num);
            return;
        }

        int maxOfLeftHalf = leftMaxHeap.peek();
        if(maxOfLeftHalf > num){
            leftMaxHeap.add(num);
        } else {
            rightMinHeap.add(num);
        }

        if(leftMaxHeap.size() - rightMinHeap.size() > 1){
            rightMinHeap.add(leftMaxHeap.poll());
        } else if(rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.add(rightMinHeap.poll());
        }
    }

    public double findMedian() {
        int n = leftMaxHeap.size() + rightMinHeap.size();
        if(n % 2 != 0){
            return leftMaxHeap.peek();
        }
        return ((double)leftMaxHeap.peek() + (double)rightMinHeap.peek())/2.0;
    }
}
