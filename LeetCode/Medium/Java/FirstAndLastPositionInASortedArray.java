package Medium.Java;

public class FirstAndLastPositionInASortedArray {
    @SuppressWarnings("ReassignedVariable")
    public static int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int[] returnNums={-1,-1};
        while(left<=right){
            int mid = left + ((right - left) / 2);
            if(nums[mid] == target){
                left = mid;
                right = mid;
                returnNums[0] = mid;
                returnNums[1] = mid;
                while(left >= 0 || right < nums.length){
                    left--;
                    right++;
                    if(left >=0){
                        if(nums[left]==target) {
                            returnNums[0] = left;
                        }else {
                            left = -1;
                        }
                    }
                    if(right < nums.length){
                        if(nums[right]==target){
                            returnNums[1] = right;
                        } else {
                            right = nums.length;
                        }
                    }
                }
                return returnNums;
            }else if(nums[mid] > target){
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }
        return returnNums;
    }
}
