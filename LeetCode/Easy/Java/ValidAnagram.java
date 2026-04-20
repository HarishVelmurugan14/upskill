package Easy.Java;

import java.util.Arrays;
@SuppressWarnings("ALL")
public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("cart","ratc"));

    }

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a,b);
    }
}
