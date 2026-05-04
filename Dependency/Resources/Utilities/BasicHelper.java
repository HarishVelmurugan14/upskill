package Resources.Utilities;

import java.util.ArrayList;

public class BasicHelper {
    public void swap(int a, int b, int[] nums) {
        int x = nums[a];
        nums[a] = nums[b];
        nums[b] = x;
    }

    public void swap(int a, int b, ArrayList<Integer> list) {
        int x = list.get(a);
        list.set(a, list.get(b));
        list.set(b, x);
    }

    public void reverse(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        reverse(arr, i, j);
    }

    public void reverse(int[] arr, int i, int j) {
        while (j > i) {
            swap(i, j, arr);
            i++;
            j--;
        }
    }

    public void reverse(ArrayList<Integer> list, int i, int j) {
        while (j > i) {
            swap(i, j, list);
            i++;
            j--;
        }
    }

}