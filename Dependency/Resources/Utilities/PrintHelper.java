package Resources.Utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PrintHelper {

    public void print(String message, Object o) {
        System.out.println(message + o);
    }

    public void print(String message, int[] array) {
        System.out.println(message + Arrays.toString(array));
    }

    public void print(String message, int[] array, int start, int end) {

        for (int i = start; i < end + 1; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }

    public void print(String message, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }


    public <T> void print(String message, List<T> list) {
        System.out.print(message);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i == list.size() - 1) {
                break;
            }
            System.out.print(",");
        }
        System.out.println();
    }

    public void print(String code, HashMap hashMap) {
        System.out.println("HASHMAP PRINT : ");
        switch (code) {
            case "II":
                print_II(hashMap);
                break;
            case "IS":
                print_IS(hashMap);
                break;
            case "SI":
                print_SI(hashMap);
                break;
            case "SS":
                print_SS(hashMap);
                break;
            default:
                break;
        }
        System.out.println("----------------------");
    }

    public void print_II(HashMap<Integer, Integer> hashMap) {
        for (int object : hashMap.keySet()) {
            System.out.println(object + " - " + hashMap.get(object));
        }
    }

    public void print_IS(HashMap<Integer, String> hashMap) {
        for (int object : hashMap.keySet()) {
            System.out.println(object + " - " + hashMap.get(object));
        }
    }

    public void print_SS(HashMap<String, String> hashMap) {
        for (String object : hashMap.keySet()) {
            System.out.println(object + " - " + hashMap.get(object));
        }
    }

    public void print_SI(HashMap<String, Integer> hashMap) {
        for (String object : hashMap.keySet()) {
            System.out.println(object + " - " + hashMap.get(object));
        }
    }
}