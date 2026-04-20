package Easy.Java;

public class MergeSortedArray {
    @SuppressWarnings({"ReassignedVariable"})
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int firstArrayMaxIndex = m-1;
        int secondArrayMaxIndex = n-1;
        for(int i = m+n-1;i>=0;i--){
            if(secondArrayMaxIndex < 0){
                nums1[i] = nums1[firstArrayMaxIndex];
                firstArrayMaxIndex--;
            } else if (firstArrayMaxIndex < 0) {
                nums1[i] = nums2[secondArrayMaxIndex];
                secondArrayMaxIndex--;
            } else if(nums2[secondArrayMaxIndex] >= nums1[firstArrayMaxIndex]) {
                nums1[i] = nums2[secondArrayMaxIndex];
                secondArrayMaxIndex--;
            } else{
                System.out.println("four");
                nums1[i] = nums1[firstArrayMaxIndex];
                firstArrayMaxIndex--;
            }
        }
    }
}
