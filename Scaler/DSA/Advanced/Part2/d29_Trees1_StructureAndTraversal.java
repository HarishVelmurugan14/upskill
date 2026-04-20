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
        d29_trees1_structureAndTraversal.hasPathSum(hasPathSumInput(), 22, "Pre"); // Q3
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

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public ArrayList<Integer> inorderTraversal(TreeNode A) {
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
        return preOrderTraversal(A, new ArrayList<>());
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
