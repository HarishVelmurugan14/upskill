package Topics;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringTemplateMigration")
public class BackTracking {

    public List<String> generateParenthesis(int n) {
        // Complexity : Time : [ O(2^2N)  ]
        // Complexity : Space : [ O(N) ]
        List<String> res = new ArrayList<>();
        backTracking(n, "", 0, 0, res);
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

    public List<List<Integer>> generateSubsets(int[] nums) {
        // Complexity : Time : [ O(2^N)  ]
        // Complexity : Space : [ O(N) ]
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums.length, nums, 0, new ArrayList<Integer>(), res);
        System.out.println(res.toString());
        return res;
    }

    private List<List<Integer>> dfs(int N, int[] nums, int index, List<Integer> currentList, List<List<Integer>> all) {
        if (index >= N) {
            System.out.println(currentList.toString());
            all.add(new ArrayList<>(currentList)); // VERY IMPORTANT
            return all;
        }

        currentList.add(nums[index]);
        dfs(N, nums, index + 1, currentList, all);
        currentList.remove(Integer.valueOf(nums[index]));
        dfs(N, nums, index + 1, currentList, all);

        return all;
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

    public List<List<Integer>> generatePermutations_old(int[] nums) {
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
}
