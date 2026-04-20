package javaLearn.classConcept.Arrays;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class SortIntegers {

    //Reference Types vs Value Type
    //Taking copy of integer make them act separately however array keepse them in sync
    //each type new is used it is referenced and again using it will cause dereference and reference the array
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        int[] myArray = new int[5];
//        myArray = getArray(myArray);
//        printArray(myArray);
//        int[] myArray = {34, 45, 67, 32, 21};
        int[] myArray = getIntegers(5);
        sortNumbers(myArray);
    }

    public static int[] getIntegers(int capacity) {
        int[] myArray = new int[capacity];
        System.out.println("Enter 5 intgeres \r");
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = scanner.nextInt();
        }
        return myArray;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }

    public static int[] sortNumbers(int[] myArray) {

        //Take a copy and proceed
        int[] sortedArray = Arrays.copyOf(myArray,myArray.length);
        myArray[3] =100;
        printArray(myArray);
        System.out.println("\n");
        printArray(sortedArray);
        System.out.println("\n");
        int temp;
        boolean toIterate = true;
        while (toIterate) {
            toIterate = false;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (sortedArray[i] < sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                    toIterate = true;
                }
            }
        }
       // System.out.println("\n");
       // printArray(sortedArray);
       // System.out.println("\n");
       // printArray(myArray);
        return sortedArray;
    }
}
