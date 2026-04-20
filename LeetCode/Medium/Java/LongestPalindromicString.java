package Medium.Java;

@SuppressWarnings("ReassignedVariable")
public class LongestPalindromicString {


    int maxLen = 0;
    int lo = 0;
    public String longestPalindrome(String s) {
        char[] input = s.toCharArray();
        if(s.length() < 2) {
            return s;
        }

        for(int i = 0; i<input.length; i++) {
            expandPalindrome(input, i, i);
            expandPalindrome(input, i, i+1);
        }
        return s.substring(lo, lo+maxLen);
    }

    public void expandPalindrome(char[] s, int j, int k) {
        while(j >= 0 && k < s.length && s[j] == s[k]) {
            j--;
            k++;
        }
        if(maxLen < k - j - 1) {
            maxLen = k - j - 1;
            lo = j+1;
        }
    }


    public static String longestPalindromeMySolution(String s) {
        if(s.length()<2){
            return s;
        }
        int endCheckIndex = s.length();
        char[] charArray = s.toCharArray();
        String longestPalindrome = "";

        for(int i=0;i<endCheckIndex;i++){
            longestPalindrome = utility(charArray,i,i,longestPalindrome);
            longestPalindrome = utility(charArray,i,i+1,longestPalindrome);
            endCheckIndex = s.length() - (longestPalindrome.length() % 2 + longestPalindrome.length()/2);
        }
        return longestPalindrome;
    }

    public static String utility(char[] charArray, int leftIndex, int rightIndex, String longestPalindrome){
        String currentPalindrome = String.valueOf(charArray[leftIndex]);
        if(leftIndex != rightIndex ){
            if(charArray[leftIndex] != charArray[rightIndex]){
                return longestPalindrome;
            }
            currentPalindrome = currentPalindrome + currentPalindrome;
        }
        leftIndex--;
        rightIndex++;
        while(leftIndex >= 0 && rightIndex < charArray.length){
            if(charArray[leftIndex]==charArray[rightIndex]){
                currentPalindrome = charArray[leftIndex] + currentPalindrome + charArray[rightIndex];
                leftIndex--;
                rightIndex++;
            }else{
                leftIndex = -1;
            }
        }
        if(currentPalindrome.length() >=  longestPalindrome.length()){
            return currentPalindrome;
        }
        return longestPalindrome;
    }

//    public static void main(String[] args) {
//        System.out.println(longestPalindrome("ac"));
//    }
}
