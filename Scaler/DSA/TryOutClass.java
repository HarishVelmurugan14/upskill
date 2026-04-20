package DSA;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;

public class TryOutClass {
    PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {
        TryOutClass tryOutClass = new TryOutClass();
//        String s2 = "zebra";
//        System.out.println(s2.substring(0, 1));
//        System.out.println(s2.substring(1, s2.length()));


        tryOutClass.buildCombination("zebra", 3);
        for(String s : tryOutClass.comb){
            System.out.println(s);
        }

    }
    ArrayList<String> comb = new ArrayList<>();

    public void buildCombination(String word, int num) {
        if(num == 1){
            comb.add(word);
            return;
        }
        int n = word.length();
        for (int i = 1; i <= n - num + 1; i++) {
            buildCombination(word.substring(0, i), 1);
            buildCombination(word.substring(i, n), num - 1);
        }
    }
}

