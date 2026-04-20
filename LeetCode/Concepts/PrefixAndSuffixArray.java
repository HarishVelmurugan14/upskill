package Concepts;

@SuppressWarnings("ALL")
public class PrefixAndSuffixArray {
//    public static void main(String[] args) {
//        int[] nums = {1,2,3,4};
//        int[] ans =  PrefixSumArray(nums);
//        ans =  PrefixSubtractArray(nums);
//        ans =  PrefixDivideArray(nums);
//        //[1, 3, 6, 10, 15]
//        //[1, 2, 6, 24, 120]
//        //[1, -1, -4, -8, -13]
//        //[1, 0, 0, 0, 0]
//        ans = SuffixSumArray(nums);
//        ans =  PrefixProductArray(nums);
//        System.out.println(Arrays.toString(ans));
//        ans = SuffixProductArray(nums);
//        System.out.println(Arrays.toString(ans));
//    }



    public static  int[] SuffixSumArray(int[] nums){
        int[] ans = new int[nums.length];
        ans[nums.length-1] = nums[nums.length-1];
        for(int i= nums.length-2; i>=0; i--){
            ans[i] = ans[i+1]+nums[i];
        }
        return ans;
    }
    public static  int[] SuffixProductArray(int[] nums){
        int[] ans = new int[nums.length];
        ans[nums.length-1] = nums[nums.length-1];
        for(int i= nums.length-2; i>=0; i--){
            ans[i] = ans[i+1]*nums[i];
        }
        return ans;
    }











    public static  int[] PrefixSumArray(int[] nums){
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            ans[i] = ans[i-1]+nums[i];
        }
        return ans;
    }

    public static  int[] PrefixSubtractArray(int[] nums){
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            ans[i] = ans[i-1]-nums[i];
        }
        return ans;
    }
    public static  int[] PrefixProductArray(int[] nums){
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            ans[i] = ans[i-1]*nums[i];
        }
        return ans;
    }

    public static  int[] PrefixDivideArray(int[] nums){
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            ans[i] = ans[i-1]/nums[i];
        }
        return ans;
    }

}
