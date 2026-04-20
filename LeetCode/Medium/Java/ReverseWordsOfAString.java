package Medium.Java;

import java.util.Arrays;

public class ReverseWordsOfAString {

    @SuppressWarnings("ReassignedVariable")
    public static String reverseWords(String s) {

        String[] str = s.split(" ");
        Arrays.stream(str).forEach(System.out::println);
        s = "";
        for (int i=str.length-1; i>-1;i--){
            if(!str[i].isEmpty()) {
                s = s + str[i];
                if (i != 0) {
                    s = s + " ";
                }
            }
        }
        System.out.println(s.trim());
        return s.trim();
    }
}
