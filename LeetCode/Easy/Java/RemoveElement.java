package Easy.Java;

public class RemoveElement {
    @SuppressWarnings("ReassignedVariable")
    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int currentNum : nums) {
            if (currentNum != val) {
                nums[k] = currentNum;
                k++;
            }
        }
        return k;
    }
}
