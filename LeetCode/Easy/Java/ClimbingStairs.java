package Easy.Java;

@SuppressWarnings("ALL")
public class ClimbingStairs {

    public static int climbStairs(int n) {
        int one = 1; // Index Sheet
        int two = 0;
        int currentSteps = 0;
        for(int i=n-1; i>=0;i--){
            currentSteps = one + two;
            two = one;
            one = currentSteps;
        }
        System.out.println(currentSteps);

       return -1;
    }
}
