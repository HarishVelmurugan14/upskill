package Sorting;

import Resources.Utilities.PrintHelper;

public class AllSorting {

    //*************************************SELECTION SORT*****************************************
    public int[] selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < minValue) {
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            int x = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = x;
        }
        return arr;
    }

    //*************************************SELECTION SORT*****************************************

    //*************************************BUBBLE SORT*****************************************

    public void bubbleSort(int[] arr, int n) {
        for (int i = n - 2; i >= 0; i--) {
            int didSwap = 0;
            for (int j = 0; j <= i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int x = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                    didSwap = 1; // Check if already sorted ?
                }
            }
            if (didSwap == 0) {
                break;
            }
        }

        PrintHelper printHelper = new PrintHelper();
        printHelper.print("Test",arr);
    }

    public void bubbleSortRecursion(int[] arr, int n) {

    }


    //*************************************BUBBLE SORT*****************************************

    //*************************************INSERTION SORT*****************************************

    public void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                int x = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = x;
                j--;
            }
        }
        PrintHelper printHelper = new PrintHelper();
        printHelper.print("Test", arr);
    }

    //*************************************INSERTION SORT*****************************************

    //*************************************MERGE SORT*****************************************

    public void mergeSortPseudocodeExample() {
//            Refer Resources/MergeSortMyOwnPseudocodeImplementation.png
    }

    public void mergeSort(int[] arr, int start, int end) {

        //BaseCase
        if (start >= end) {
            return;
        }
        //RecursiveCase
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        mergeTwoArraysInAscendingOrder(arr, start, mid, end);
    }

    public void mergeTwoArraysInAscendingOrder(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int[] tempArray = new int[(end - start) + 1];

        for (int currentPos = 0; currentPos <= end - start; currentPos++) {
            if (i > mid) {
                tempArray[currentPos] = arr[j];
                j++;
            } else if (j > end) {
                tempArray[currentPos] = arr[i];
                i++;
            } else if (arr[i] <= arr[j]) {
                tempArray[currentPos] = arr[i];
                i++;
            } else if (arr[i] > arr[j]) {
                tempArray[currentPos] = arr[j];
                j++;
            }
        }

        for (int k = 0; k <= end - start; k++) {
            arr[k + start] = tempArray[k];
        }
    }
    //*************************************MERGE SORT*****************************************
}
