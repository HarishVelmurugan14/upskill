package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Harish Velmurugan
 * @last-modified 10-03-2025
 * @since 10-03-2025
 */
@SuppressWarnings({"UnusedReturnValue", "ClassEscapesDefinedScope"})
public class d29_Trees1_StructureAndTraversal {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Call Stack
        d29_Trees1_StructureAndTraversal d29_trees1_structureAndTraversal = new d29_Trees1_StructureAndTraversal();

        System.out.println(d29_trees1_structureAndTraversal.getSize(basicInput(), 0));
        d29_trees1_structureAndTraversal.inorderTraversal(basicInput()); // Q1
        d29_trees1_structureAndTraversal.preOrderTraversal(basicInput()); // Q2
        d29_trees1_structureAndTraversal.hasPathSum(hasPathSumInput(), 22, 0); // Q3
        d29_trees1_structureAndTraversal.equalTreePartition(equalTreePartitionInput()); // Q4
        d29_trees1_structureAndTraversal.postOrderTraversal(basicInput()); // AQ1
        d29_trees1_structureAndTraversal.sumBinaryTreeOrNotMain(sumBinaryTreeInput()); // AQ2
    }

    /* Section : ----------------------------------- [ Inputs ] ------------------------------------ */

    public static TreeNode basicInput() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.right = node2;
        node2.left = node3;
        return root;
    }

    public static TreeNode hasPathSumInput() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        return root;
    }

    public static TreeNode equalTreePartitionInput() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        return root;
    }

    public static TreeNode sumBinaryTreeInput() {
        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(3);
        return root;
    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public int sumBinaryTreeOrNotMain(TreeNode A) {
        return (sumBinaryTreeOrNot(A) == -1) ? 0 : 1;
    }

    public int sumBinaryTreeOrNot(TreeNode A) {
        if (A == null) {
            return 0;
        }

        if (A.left == null && A.right == null) {
            return A.val;
        }

        int leftSum = sumBinaryTreeOrNot(A.left);
        int rightSum = sumBinaryTreeOrNot(A.right);
        if (leftSum == -1 || rightSum == -1) return -1;

        if (leftSum + rightSum == A.val) {
            return leftSum + rightSum + A.val;
        } else {
            return -1;
        }
    }

    public int equalTreePartition(TreeNode A) {
        HashMap<Long, Integer> map = new HashMap<>();
        long totalSum = sumOfSubTree(A, map);

        map.put(totalSum, map.get(totalSum) - 1);
        long halfSum = totalSum / 2;

        if (totalSum % 2 == 1) {
            return 0;
        } else {
            return map.containsKey(halfSum) ? 1 : 0;
        }
    }

    public long sumOfSubTree(TreeNode A, HashMap<Long, Integer> map) {
        if (A == null) {
            return 0;
        }
        long leftSum = sumOfSubTree(A.left, map);
        long rightSum = sumOfSubTree(A.right, map);
        long sum = leftSum + rightSum + A.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }


    public int hasPathSum(TreeNode A, int B, String met) {
        if (A == null) {
            return 0;
        }
        if (A.left == null && A.right == null && B == A.val) {
            return 1;
        }


        int lst = hasPathSum(A.left, B - A.val, "LST");
        int rst = hasPathSum(A.right, B - A.val, "RST");

        System.out.println((lst == 1 || rst == 1) ? 1 : 0);
        return (lst == 1 || rst == 1) ? 1 : 0;
    }

    public boolean hasPathSum(TreeNode node, int targetSum, int currentSum) {

        /*
         * HAS PATH SUM - Root to Leaf
         *
         * GOAL: check if any root-to-leaf path exists
         *       where sum of nodes equals targetSum
         *
         * STRATEGY: preorder traversal accumulating sum top-down
         *   at each node add its value to currentSum
         *   at leaf check if currentSum matches targetSum
         *
         * TRAVERSAL RULES:
         *   null node              → return false (empty path)
         *   leaf node              → return currentSum == targetSum
         *   internal node          → check left || right (short-circuit)
         *
         * EXAMPLE: targetSum = 10
         *        5
         *       / \
         *      4   2
         *     / \ / \
         *    1  6 9  11
         *
         *   5→4→1 = 10 ✅ found! right side never checked (short-circuit)
         *   5→4→6 = 15 ❌
         *   5→2→9 = 16 ❌
         *   5→2→11= 18 ❌
         *
         * WHY LEAF NOT NULL:
         *   path ends at leaf (no children), not at null
         *   checking at null causes double counting
         *
         * WHY BOOLEAN NOT INT:
         *   answer is yes/no — boolean is self explanatory
         *   int 0/1 adds unnecessary ambiguity
         *
         * WHY || NOT if(x!=1):
         *   || short-circuits naturally — if left true, right never checked
         *   same behaviour, cleaner code
         *
         * Time: O(N)  Space: O(H)  ← H = height of tree (recursive stack)
         */

        // step 1 — empty node, nothing to check
        if (node == null) return false;

        // step 2 — add current node value to running sum
        currentSum += node.val;

        // step 3 — reached a leaf! check if sum matches
        if (node.left == null && node.right == null) {
            return currentSum == targetSum;
        }

        // step 4 — not a leaf, keep going left or right
        return hasPathSum(node.left, targetSum, currentSum) ||
                hasPathSum(node.right, targetSum, currentSum);
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public ArrayList<Integer> inorderTraversal(TreeNode A) {
        /*
         * INORDER TRAVERSAL - Left → Root → Right
         *
         * GOAL: collect all node values in inorder sequence
         *       into a result list
         *
         * STRATEGY: recursively visit left subtree first, then root, then right
         *   res list accumulates values in traversal order
         *   helper isolates recursion from public API
         *
         * TRAVERSAL RULES:
         *   null node    → base case, return
         *   node.left    → recurse left subtree
         *   root         → add node.val to res
         *   node.right   → recurse right subtree
         *
         * EXAMPLE:
         *        5
         *       / \
         *      4   2
         *     / \ / \
         *    1  6 9  11
         *
         *   visit 1 → visit 4 → visit 6 → visit 5 → visit 9 → visit 2 → visit 11
         *   result = [1, 4, 6, 5, 9, 2, 11]
         *
         * APPROACHES:
         *   helper method  → single shared list passed through recursion     ✅ efficient   O(N) time O(H) space
         *   addAll         → new list created at each node, merged upward    ⚠️ overhead   O(N²) time O(N) space
         *
         * NOTE: inorder of a BST always gives sorted ascending order
         *
         * Time: O(N)  Space: O(H)  ← H = height of tree (recursive stack)
         */

        return inorderTraversal(A, new ArrayList<>(), "IN");
    }

    public ArrayList<Integer> inorderTraversal(TreeNode A, ArrayList<Integer> res, String met) {
        System.out.println((A != null ? A.val : null) + " - " + met);
        if (A == null) {
            return res;
        }
        res = inorderTraversal(A.left, res, "LST");
        res.add(A.val);
        print("", res);
        res = inorderTraversal(A.right, res, "RST");
        print("", res);
        return res;
    }

    public ArrayList<Integer> preOrderTraversal(TreeNode A) {
        /*
         * PREORDER TRAVERSAL - Root → Left → Right
         *
         * GOAL: collect all node values in preorder sequence
         *       into a result list
         *
         * STRATEGY: recursively visit root first, then left subtree, then right
         *   res list accumulates values in traversal order
         *   helper isolates recursion from public API
         *
         * TRAVERSAL RULES:
         *   null node    → base case, return
         *   root         → add node.val to res
         *   node.left    → recurse left subtree
         *   node.right   → recurse right subtree
         *
         * EXAMPLE:
         *        5
         *       / \
         *      4   2
         *     / \ / \
         *    1  6 9  11
         *
         *   visit 5 → visit 4 → visit 1 → visit 6 → visit 2 → visit 9 → visit 11
         *   result = [5, 4, 1, 6, 2, 9, 11]
         *
         * APPROACHES:
         *   helper method  → single shared list passed through recursion     ✅ efficient   O(N) time O(H) space
         *   addAll         → new list created at each node, merged upward    ⚠️ overhead   O(N²) time O(N) space
         *
         * Time: O(N)  Space: O(H)  ← H = height of tree (recursive stack)
         */
        ArrayList<Integer> res = new ArrayList<>();
        preOrderTraversal(A, res);
        return res;
    }

    public ArrayList<Integer> preOrderTraversal(TreeNode A, ArrayList<Integer> res) {
        if (A == null) {
            return res;
        }
        res.add(A.val);
        print("", res);
        res = preOrderTraversal(A.left, res);
        res = preOrderTraversal(A.right, res);
        print("", res);
        return res;
    }

    public ArrayList<Integer> postOrderTraversal(TreeNode A) {
        /*
         * POSTORDER TRAVERSAL - Left → Right → Root
         *
         * GOAL: collect all node values in postorder sequence
         *       into a result list
         *
         * STRATEGY: recursively visit left subtree, then right subtree, then root
         *   res list accumulates values in traversal order
         *   helper isolates recursion from public API
         *
         * TRAVERSAL RULES:
         *   null node    → base case, return
         *   node.left    → recurse left subtree
         *   node.right   → recurse right subtree
         *   root         → add node.val to res
         *
         * EXAMPLE:
         *        5
         *       / \
         *      4   2
         *     / \ / \
         *    1  6 9  11
         *
         *   visit 1 → visit 6 → visit 4 → visit 9 → visit 11 → visit 2 → visit 5
         *   result = [1, 6, 4, 9, 11, 2, 5]
         *
         * APPROACHES:
         *   helper method  → single shared list passed through recursion     ✅ efficient   O(N) time O(H) space
         *   addAll         → new list created at each node, merged upward    ⚠️ overhead   O(N²) time O(N) space
         *
         * NOTE: postorder commonly used for deletion — children deleted before parent
         *
         * Time: O(N)  Space: O(H)  ← H = height of tree (recursive stack)
         */
        return postOrderTraversal(A, new ArrayList<>());
    }

    public ArrayList<Integer> postOrderTraversal(TreeNode A, ArrayList<Integer> res) {
        if (A == null) {
            return res;
        }
        res = postOrderTraversal(A.left, res);
        res = postOrderTraversal(A.right, res);
        res.add(A.val);
        return res;
    }

    public int getSize(TreeNode A, int i) {
        if (A == null) {
            return i;
        }
        i++;
        i = getSize(A.left, i);
        i = getSize(A.right, i);
        return i;
    }

    private void print(String message, int[] arr) {
        printHelper.print(message, arr);
    }

    private void print(String message, ArrayList<Integer> arr) {
        printHelper.print(message, arr);
    }

    private void print(String message, HashMap arr) {
        printHelper.print(message, arr);
    }

    private void definitions() {
        /*
         * Notebook_06012025: Page No : 144
         * */
    }

    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void links() {
        /*
         * /academy/mentee-dashboard/class/345259/assignment/problems?navref=cl_tb_br
         * */
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
