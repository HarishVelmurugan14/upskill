package Medium.Java;

public class SumOfTwoIntegers {
    public static int getSum(int a, int b) {
        for(short i=0; i<Math.abs(b); i++){
            if(b > 0) {
                a++;
            }else{
                a--;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(getSum(0, -11));
    }
}
