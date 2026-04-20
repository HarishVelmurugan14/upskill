
import Resources.Utilities.BasicHelper;

public class Recursions {
    public int sumOfFirstNNumbers(int sum, int i, int n) {

        if (i <= n) {
            sum = sum + i;
            System.out.println(i + "--" + sum);
            sum = sumOfFirstNNumbers(sum, i + 1, n);
        }
        return sum;
    }

    public long sumFirstN(long n) {
        if (n == 0) {
            return 0;
        }
        return n + sumFirstN(n - 1);
    }

    public long sumFirstNUsingFormula(long n) {
        return (n * (n + 1)) / 2;
    }

    public long factorial(long n) {
        if (n == 2) {
            return 2;
        }
        return n * factorial(n - 1);
    }

    public int[] reverseArray(int n, int[] nums) {
        int nCount = n;
        for (int i = 0; i < nCount / 2; i++) {
            n--;
            BasicHelper basicHelper = new BasicHelper();
            basicHelper.swap(i, n, nums);
        }
        return nums;
    }

    public boolean isPalindrome(String str) {

        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int length = str.length();
        if (length < 2) {
            return true;
        }
        char[] charArray = str.toCharArray();
        if (charArray[0] == charArray[length - 1]) {
            return isPalindrome(str.substring(1, length - 1));
        }
        return false;
    }

    public boolean isPalindromeNormal(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        StringBuilder reversedString = new StringBuilder();
        reversedString.append(s);
        reversedString.reverse();
        return s.equals(reversedString.toString());
    }

    public int[] generateFibonacciNumbers(int n) {
        int a = 0;
        int b = 1;
        int[] result = new int[n];
        result[0] = a;
        if (n == 1) return result;
        result[1] = b;
        for (int i = 2; i < n; i++) {
            result[i] = a + b;
            a = b;
            b = result[i];
        }
        return result;
    }

    public int generateFibonacciValue(int n) {
        int a = 0;
        int b = 1;
        int x = 0;
        if (n == 1) return a;
        if (n == 2) return b;
        for (int i = 2; i <= n; i++) {
            x = a + b;
            a = b;
            b = x;
        }
        return x;
    }

    public int fibonacciInRecursion(int n) {

        if (n <= 1) {
            return 1;
        }
        int first = fibonacciInRecursion(n - 1);
        int last = fibonacciInRecursion(n - 2);
        return first + last;
    }

}
