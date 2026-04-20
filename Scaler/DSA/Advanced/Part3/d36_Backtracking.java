package DSA.Advanced.Part3;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harish Velmurugan
 * @last-modified 02-04-2025
 * @since 02-04-2025
 */
@SuppressWarnings({"StringTemplateMigration", "StringConcatenationInLoop", "ConstantValue"})
public class d36_Backtracking {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d36_Backtracking d36_backtracking = new d36_Backtracking();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        d36_backtracking.generateParenthesis(3); // LC22

        d36_backtracking.generateSubsets_yahnit(new int[]{1, 2, 3}); // LC22
        System.out.println(d36_backtracking.generateSubSequence("", "abc"));
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        d36_backtracking.generateSubSequence_listVersion(new ArrayList<>(), list, res);
        System.out.println(res);

        d36_backtracking.generatePermutations_yahnit(new int[]{1,2,3});
        d36_backtracking.generatePermutations("", "abc");
        d36_backtracking.generatePermutations_listVersion(new ArrayList<>(), list);

        System.out.println(d36_backtracking.generateOneZeroSpecialPattern(3, 3));

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int generateOneZeroSpecialPattern(int A, int B) {
        return kthGrammar(A, B);
    }

    public int kthGrammar(int n, int k) {
        if(n == 1){
            return 0;
        }
        return generateOneZeroSpecialPattern_util("0", n - 1, k);
    }

    public int generateOneZeroSpecialPattern_util(String p, int upTimes, int B) {
        if (upTimes == 0) {
            System.out.println(p);
            return p.charAt(B-1) - '0';
        }
        StringBuilder mod = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char current = p.charAt(i);
            System.out.println(current);
            if (current == '0') {
                mod.append("01");
                // mod = mod + "01";
            } else {
                mod.append("10");
                // mod = mod + "10";
            }
        }
        System.out.println(mod.toString() + " - " + upTimes);
        upTimes--;
        return generateOneZeroSpecialPattern_util(mod.toString(), upTimes, B);
    }


    public List<String> generateParenthesis(int n) {
        // Complexity : Time : [ O(2^2N)  ]
        // Complexity : Space : [ O(N) ]
        List<String> res = new ArrayList<>();
        res = backTracking(n, "", 0, 0, res);
        return res;
    }

    public List<String> backTracking(int n, String currentPattern, int openParenthesisCount, int closedParenthesisCount, List<String> result) {
        if (openParenthesisCount == n && closedParenthesisCount == n) {
            result.add(currentPattern);
            return result;
        }

        if (openParenthesisCount < n) {
            backTracking(n, currentPattern + "(", openParenthesisCount + 1, closedParenthesisCount, result);
        }

        if (closedParenthesisCount < openParenthesisCount) {
            backTracking(n, currentPattern + ")", openParenthesisCount, closedParenthesisCount + 1, result);
        }
        return result;
    }

    public List<List<Integer>> generateSubsets_yahnit(int[] nums) {
        // Complexity : Time : [ O(2^N)  ]
        // Complexity : Space : [ O(N) ]
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums.length, nums, 0, new ArrayList<Integer>(), res);
        System.out.println(res.toString());
        return res;
    }

    private List<List<Integer>> dfs(int N, int[] nums, int index, List<Integer> currentList, List<List<Integer>> all) {
        if (index >= N) {
//            System.out.println(currentList.toString());
            all.add(new ArrayList<>(currentList)); // VERY IMPORTANT
            return all;
        }

        currentList.add(nums[index]);
        dfs(N, nums, index + 1, currentList, all);
        currentList.remove(Integer.valueOf(nums[index]));
        dfs(N, nums, index + 1, currentList, all);

        return all;
    }

    public ArrayList<ArrayList<Integer>> generateSubSequence_listVersion(ArrayList<Integer> p, ArrayList<Integer> up, ArrayList<ArrayList<Integer>> res) {
        if (up.isEmpty()) {
            res.add(p);
            return res;
        }
        ArrayList<Integer> newUp = new ArrayList<>(up.subList(1, up.size()));
        int num = up.get(0);
        ArrayList<Integer> newProcessed = new ArrayList<>(p);
        generateSubSequence_listVersion(newProcessed, newUp, res);
        newProcessed.add(num);
        generateSubSequence_listVersion(newProcessed, newUp, res);
        return res;
    }

    public ArrayList<String> generateSubSequence(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = generateSubSequence(p + ch, up.substring(1));
        ArrayList<String> right = generateSubSequence(p, up.substring(1));
        left.addAll(right);
        return left;
    }

    public ArrayList<String> generateSubSequenceAscii(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = generateSubSequenceAscii(p + ch, up.substring(1));
        ArrayList<String> middle = generateSubSequenceAscii(p, up.substring(1));
        ArrayList<String> right = generateSubSequenceAscii(p + (ch + 0), up.substring(1));
        left.addAll(right);
        left.addAll(middle);
        return left;
    }

    public List<List<Integer>> generatePermutations_yahnit(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, nums, new boolean[nums.length], new ArrayList<Integer>());
        return res;
    }

    private List<List<Integer>> backTrack(List<List<Integer>> res, int[] nums, boolean[] visited, List<Integer> currentList) {
        if (currentList.size() == nums.length) {
            res.add(new ArrayList<>(currentList));
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int x = nums[i];
                currentList.add(x);
                visited[i] = true;
                backTrack(res, nums, visited, currentList);
                visited[i] = false;
                currentList.remove(Integer.valueOf(x));
            }
        }
        return res;
    }

    public void generatePermutations(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String start = p.substring(0, i);
            String end = p.substring(i);
            generatePermutations(start + ch + end, up.substring(1));
        }
    }

    public void generatePermutations_listVersion(ArrayList<Integer> p, ArrayList<Integer> up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        int ch = up.get(0);
        ArrayList<Integer> newUp = new ArrayList<>(up.subList(1, up.size()));
        for (int i = 0; i <= p.size(); i++) {
            ArrayList<Integer> newProcessed = new ArrayList<>(p);
            newProcessed.add(i, ch);
            generatePermutations_listVersion(newProcessed, newUp);
        }
    }


    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
