
import Sorting.AllSorting;
import Sorting.SortingsYouNeed;
import Resources.Utilities.PrintHelper;

import java.util.Arrays;

public class DriverClass {
    PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {
        DriverClass driverClass = new DriverClass();
//        driverClass.patternMethods();
//        driverClass.recursionMethods();
//        driverClass.hashingMethods();
//        driverClass.allSortingMethods();
//        driverClass.usefulSortingMethods();

    }

    public void usefulSortingMethods() {
        SortingsYouNeed sortingsYouNeed = new SortingsYouNeed();
        sortingsYouNeed.definitions();
    }

    public void allSortingMethods() {
        AllSorting sorting = new AllSorting();
        int[] arr = sorting.selectionSort(new int[]{9, 3, 6, 2, 0});
        printHelper.print("Test", arr);
        sorting.bubbleSort(new int[]{9, 3, 6, 2, 0}, 5);
        sorting.insertionSort(new int[]{7, 2, 3, 4, 5, 6}, 6);
        sorting.mergeSortPseudocodeExample(); // Image reference
        sorting.mergeTwoArraysInAscendingOrder(new int[]{2, 8, 3, 5, 1, 6, 4, 7}, 4, 5, 7);
        sorting.mergeSort(new int[]{5, 2, 8, 3, 6, 1, 7, 4}, 0, 7);
        sorting.mergeSort(new int[]{49, 92, 15, 102, 72, 88, 13, 14, 103, 134, 45, 104, 139, 43, 119, 80, 84, 76, 80,
                144, 125, 39, 5, 118, 64, 137, 12, 50, 122, 58, 140, 58, 6, 49, 67, 9, 55, 14, 94, 109, 76, 114, 115,
                130, 120, 83, 121, 58, 6, 101, 52, 56, 63, 70, 26, 55, 64, 84, 65, 52, 112, 116, 100}, 0, 62);
        sorting.mergeTwoArraysInAscendingOrder(new int[]{58, 122, 58, 140}, 0, 1, 3);
    }

    public void hashingMethods() {
        Hashing hashing = new Hashing();
        printHelper.print("Test", hashing.countFrequency(7, 20, new int[]{10, 7, 9, 8, 14, 20, 6}));
        printHelper.print("Test", hashing.getFrequencies(new int[]{19, 17, 16}));
    }

    public void recursionMethods() {
        Recursions recursions = new Recursions();
        recursions.sumOfFirstNNumbers(0, 1, 5);
        recursions.sumFirstN(5);
        recursions.sumFirstNUsingFormula(5);
        recursions.factorial(5);
        System.out.println(Arrays.toString(recursions.reverseArray(8, new int[]{3, 1, 1, 7, 4, 2, 6, 11})));
        System.out.println(recursions.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(recursions.isPalindromeNormal("A man, a plan, a canal: Panama"));
        System.out.println(recursions.isPalindromeNormal("dad"));
        System.out.println(recursions.isPalindromeNormal("dab , bad"));
        printHelper.print("Test", recursions.generateFibonacciNumbers(8));
        System.out.println(recursions.generateFibonacciValue(4));
        System.out.println(recursions.fibonacciInRecursion(2));
    }


    public void patternMethods() {
        Pattern pattern = new Pattern();
        pattern.createRectangleStarPattern(3);
        pattern.createRightAngledTrianglePattern(3);
        pattern.createRightAngledNumberPyramid(3);
        pattern.createRightAngledNumberPyramid2(3);
        pattern.pattern5(3);
        pattern.pattern6(3);
        pattern.pattern8(3);
        pattern.pattern11(3);
        pattern.pattern12(3);
        pattern.pattern13(3);
        pattern.pattern14(3);
    }
}
