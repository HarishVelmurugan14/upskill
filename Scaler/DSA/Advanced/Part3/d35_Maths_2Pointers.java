package DSA.Advanced.Part3;

import Resources.Utilities.PrintHelper;
/**
 * @author Harish Velmurugan
 * @since 09-06-2025
 * @last-modified 09-06-2025
 */
public class d35_Maths_2Pointers {



    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d35_Maths_2Pointers d35Maths2Pointers = new d35_Maths_2Pointers();


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */


    public int[] contiguousSubArrayWithGivenSum(int[] A, int B) {
        int left = 0;
        int n = A.length;
        int currentSum = 0;
        for(int i=0; i<n;i++){
            currentSum += A[i];
            while(currentSum > B){
                currentSum = currentSum - A[left];
                left++;
            }
            if(currentSum == B){
                int[] res = new int[i-left+1];
                int idx = 0;
                for(int j = left; j <= i; j++){
                    res[idx] = A[j];
                    idx++;
                }
                return res;
            }
        }
        return new int[]{-1};
    }

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
