package Medium.Java;

public class DivideTwoIntegers {
    public static int divideOld(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int returnValue = -1;
        int dividendCopy = dividend;
        int divisorCopy = divisor;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while(dividend >= 0 || returnValue ==-1){
            dividend = dividend - divisor;
            System.out.println(returnValue);
            returnValue++;
        }
        if(dividendCopy < 0 || divisorCopy < 0){
            if(dividendCopy < 0 && divisorCopy < 0){
                System.out.println("Yes");
                return returnValue;
            }
            return returnValue;
        }
        return returnValue;
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //Cornor case when -2^31 is divided by -1 will give 2^31 which doesnt exist so overflow

        boolean negative = dividend < 0 ^ divisor < 0; //Logical XOR will help in deciding if the results is negative only if any one of them is negative

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int quotient = 0, subQuot = 0;

        while (dividend - divisor >= 0) {
            System.out.println("1st Loop Dividend = " + dividend + " -- Divisor = "+ divisor + " QO = "+ quotient + " SQO = " + subQuot);
            for (subQuot = 0; dividend - (divisor << subQuot << 1) >= 0; subQuot++);
            System.out.println("After 1st Loop Dividend = " + dividend + " -- Divisor = "+ divisor + " QO = "+ quotient + " SQO = " + subQuot);
            quotient += 1 << subQuot; //Add to the quotient
            dividend -= divisor << subQuot; //Substract from dividend to start over with the remaining
        }
        return negative ? -quotient : quotient;
    }
}
