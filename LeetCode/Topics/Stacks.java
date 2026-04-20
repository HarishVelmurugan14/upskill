package Topics;

import java.util.Arrays;
import java.util.Stack;

public class Stacks {
    public String robotWithString(String s) {
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        char minChar = 'a';
        for (char x : s.toCharArray()) {
            freq[x - 'a']--;
            while (minChar <= 'z' && freq[minChar - 'a'] == 0) {
                minChar++;
            }

            stack.push(x);
            while (!stack.isEmpty() && stack.peek() <= minChar) {
                result.append(stack.pop());
            }
        }

        return result.toString();
    }
}
