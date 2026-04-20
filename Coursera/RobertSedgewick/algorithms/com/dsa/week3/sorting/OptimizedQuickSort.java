package RobertSedgewick.algorithms.com.dsa.week3.sorting;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class OptimizedQuickSort {

    public void sort(Comparable[] a) {
        threeWayPartitionQuickSort(a, 0, a.length - 1);
    }

    private static void threeWayPartitionQuickSort(Comparable[] a, int lo, int hi) {
        // if high less than low end
        // NOTE : Only foward path || Duplicate key case is solved
        // Traverse from lo to hi with lo value as pivot. Swapping of pivot exist ;
        // if less swap with pivot increment low and pivot; if greater swap with high , decreement high and proceed in forward direction
        // If equal increement low
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable pivot = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(pivot);
            if (cmp < 0) {
                SortingBaseClass.swap(a, lt, i);
                lt++;
                i++;
            } else if (cmp > 0) {
                SortingBaseClass.swap(a, i, gt);
                gt--;
            } else {
                i++;
            }
            System.out.println(Arrays.toString(a));
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        threeWayPartitionQuickSort(a, lo, lt - 1);
        threeWayPartitionQuickSort(a, gt + 1, hi);
    }
}
