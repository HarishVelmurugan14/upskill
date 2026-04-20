package javaLearn.classConcept.Arrays;

import java.util.Scanner;

@SuppressWarnings("ALL")
public class MinimumElement {
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int count = scanner.nextInt();
        System.out.println(findMin(readIntegers(count)));
    }

    public static int[] readIntegers(int inputNumbers) {
        int[] array = new int[inputNumbers];
        for (int i = 0; i < inputNumbers; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int findMin(int[] array) {
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

}
