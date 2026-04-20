package Problems.DailyChallenge;

public class ReversePrefixOfWord {
    public static void main(String[] args) {
        String response = reversePrefix("xyxzxe", 'a');
        System.out.println(response);
    }

    public static String reversePrefix(String word, char ch) {
        StringBuilder stringToBeReversed = new StringBuilder();
        StringBuilder stringToBeRetained = new StringBuilder();
        int pos = -1;
        int length = word.length();
        if (length > 1) {
            char[] wordArray = word.toCharArray();
            for (int i = 0; i < length; i++) {
                stringToBeReversed.append(wordArray[i]);
                if (wordArray[i] == ch) {
                    pos = i;
                    break;
                }
            }
            if (pos != -1) {
                for (int i = pos+1; i < length; i++) {
                    stringToBeRetained.append(wordArray[i]);
                }
                stringToBeReversed.reverse();
                System.out.println("1-> "+ stringToBeReversed);
                System.out.println("2->"+stringToBeRetained);
                stringToBeReversed.append(stringToBeRetained);
            }
        }

        return pos == -1 ? word : stringToBeReversed.toString();
    }
}
