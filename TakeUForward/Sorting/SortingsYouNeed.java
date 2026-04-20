package Sorting;

import RobertSedgewick.algorithms.com.dsa.week3.sorting.InsertionSort;
import RobertSedgewick.algorithms.com.dsa.week3.sorting.OptimizedQuickSort;

public class SortingsYouNeed {

    public void definitions() {
        // For data set having less than 1L record follow
        // NOTE : Insertion sort :  Quadratic (O(N^2))
        // For large datasets
        // NOTE : QuickSort(3wayPartition) - handles equal cases : O(NlogN)

        Integer[] a = {3, 9, 1, 8, 2, 3, 7, 6, 3, 0, 2, 4};
        new InsertionSort().sort(a);
        new OptimizedQuickSort().sort(a);

    }
}
