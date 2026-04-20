package Easy.Java;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    @SuppressWarnings("ReassignedVariable")
    public static int removeDuplicates(int[] nums) {

        int prevNumber = nums[0]-1;
        int nextPosition = 0;
        for (int i=0; i< nums.length; i++){

            if(nums[i] != prevNumber){
                if(nextPosition < i){
                    nums[nextPosition] = nums[i];
                }
                prevNumber = nums[i];
                nextPosition ++;
            }
        }

        System.out.println(Arrays.toString(Arrays.stream(nums).toArray()));
        return nextPosition;
    }
}
