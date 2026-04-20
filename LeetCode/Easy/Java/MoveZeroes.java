package Easy.Java;

import java.util.Arrays;

public class MoveZeroes {

    @SuppressWarnings("ReassignedVariable")
    public static void moveZeroes(int[] nums) {

        for(int i=0,j=0;j<nums.length;j++) {
            if(nums[j]==0) {
                if(nums[i] != 0 ) {
                    i = j;
                }
            } else if(i>0 || nums[i] ==0 ) {
                nums[i] = nums[j];
                nums[j] = 0;
                i++;
            }
            System.out.println(j  + " - " + i);
            print(nums);
        }
        Arrays.stream(nums).forEach(System.out::print);
    }

    public static void print(int[] nums){
        System.out.println();
        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
    }
}
