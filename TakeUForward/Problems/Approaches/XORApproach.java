package Problems.Approaches;

public class XORApproach {

    public static void main(String[] args) {
        XORApproach xorApproach = new XORApproach();
        xorApproach.execute();
    }

    public void definitions() {
        /*
         * XOR of two same numbers is always 0 i.e. a ^ a = 0. ←Property 1.
         * XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.  ←Property 2
         */
    }

    public void execute() {
        int number = 4;
        int sameNumberXor = number ^ number;
        int differentNumberXor = number ^ 7;
        int numberWithZero = number ^ 0;

        for (int i = 0; i < 21; i++) {
            System.out.println(i + " - " + (number ^ i) + " - " + (3 ^ i));
        }

        System.out.println("SameNumber : " + sameNumberXor + " Different Number : " + differentNumberXor + " With Zero : " + numberWithZero);
    }
}
