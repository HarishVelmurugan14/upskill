package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Harish Velmurugan
 * @last-modified 12-03-2025
 * @since 12-03-2025
 */
@SuppressWarnings({"ClassEscapesDefinedScope", "UnusedReturnValue"})
public class d30_Trees2_ViewsAndTypes {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d30_Trees2_ViewsAndTypes d30_trees2_viewsAndTypes = new d30_Trees2_ViewsAndTypes();

        d30_trees2_viewsAndTypes.levelOrderTraversal(input()); // Q1 //##
        d30_trees2_viewsAndTypes.buildTreeWithInOrderAndPreOrder(null, null); //Q2 //##
        d30_trees2_viewsAndTypes.buildTreeWithInOrderAndPostOrder(null, null);
        int x = d30_trees2_viewsAndTypes.isBalanced(isBalancedInput()); // Q3 //##
        System.out.println(x);
        d30_trees2_viewsAndTypes.leftViewOfABinaryTree(input()); // Q4 //##

    }


    public static TreeNode input() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
//        root.left.left = new TreeNode(65);
        root.left.right = new TreeNode(68);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }


    public static TreeNode leftViewInput() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        return root;
    }

    public static TreeNode isBalancedInput() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right.right = new TreeNode(5);
        return root;
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public TreeNode buildTreeWithInOrderAndPostOrder(int[] A, int[] B) {
        int[] post = {6, 3, 2, 1};
        int[] in = {6, 1, 3, 2};
        int N = post.length;
        TreeNode x = constructTreeWithInOrderAndPostOrder(post, in, 0, N - 1, 0, N - 1);
        levelOrderTraversal(x);
        return x;
    }

    public TreeNode constructTreeWithInOrderAndPostOrder(int[] post, int[] in, int post_s, int post_e, int in_s, int in_e) {
        if (in_s > in_e) {
            return null;
        }

        int rootVal = post[post_e];
        TreeNode root = new TreeNode(rootVal);
        int index = -1;
        for (int i = in_s; i <= in_e; i++) {
            if (in[i] == rootVal) {
                index = i;
                break;
            }
        }
        // LST RANGE
        // IN : in_s , index-1
        // POST : post_s , r - 1
        // RST RANGE
        // IN : index + 1, in_e
        // PRE : r, post_e-1
        // number of elements in LST of: index - 1 - in_s + 1 = r - post_s - 1 + 1;
        // r = index - in_s + post_s;

        int r = index - in_s + post_s;
        root.left = constructTreeWithInOrderAndPostOrder(post, in, post_s, r - 1, in_s, index - 1);
        root.right = constructTreeWithInOrderAndPostOrder(post, in, r, post_e - 1, index + 1, in_e);

        return root;
    }


    public TreeNode buildTreeWithInOrderAndPreOrder(int[] A, int[] B) {
        int[] pre = {1, 9, 11, 8, 4, 20, 15, 7, 18, 12};
        int[] in = {11, 9, 4, 8, 1, 15, 20, 18, 7, 12};
        int N = pre.length;
        TreeNode x = constructTreeWithInOrderAndPreOrder(pre, in, 0, N - 1, 0, N - 1);
        levelOrderTraversal(x);
        return x;
    }

    public TreeNode constructTreeWithInOrderAndPreOrder(int[] pre, int[] in, int pre_s, int pre_e, int in_s, int in_e) {
        if (pre_s > pre_e) {
            return null;
        }
        int rootVal = pre[pre_s];
        TreeNode root = new TreeNode(rootVal);
        int index = -1;
        for (int i = in_s; i <= in_e; i++) {
            if (in[i] == rootVal) {
                index = i;
                break;
            }
        }
        // LST RANGE
        // IN : in_s , index-1
        // PRE : pre_s+1, r
        // RST RANGE
        // IN : index+1, in_e
        // PRE : r+1, pre_e
        // number of elements in LST of: index - 1 - in_s + 1 = r - pre_s - 1 + 1;
        // r = index - in_s + pre_s;

        int r = index - in_s + pre_s;
        root.left = constructTreeWithInOrderAndPreOrder(pre, in, pre_s + 1, r, in_s, index - 1);
        root.right = constructTreeWithInOrderAndPreOrder(pre, in, r + 1, pre_e, index + 1, in_e);

        return root;
    }


    public int isBalanced(TreeNode A) {
        if (A == null) {
            return -1;
        }
        int leftHeight = isBalanced(A.left);
        int rightHeight = isBalanced(A.right);
//        System.out.println(A.val + " - " + leftHeight + " - " + rightHeight);
        if (leftHeight == -2 || rightHeight == -2) {
            return -2;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -2;
        }
//        System.out.println(A.val + " - " + (Math.max(leftHeight, rightHeight) +1));
        return Math.max(leftHeight, rightHeight) + 1;

    }

    public ArrayList<Integer> leftViewOfABinaryTree(TreeNode A) {
        if (A == null) return null;

        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        result.add(A.val);

        while (!queue.isEmpty()) {
            int sizeOfThisLevel = queue.size();
            boolean isLeftMostElementAdded = false;

            for (int i = 0; i < sizeOfThisLevel; i++) {
                TreeNode temp = queue.poll();
                TreeNode left = temp.left;
                TreeNode right = temp.right;
                if (left != null) {

                    if (!isLeftMostElementAdded) {
                        result.add(left.val);
                        isLeftMostElementAdded = true;
                    }
                    queue.add(left);
                }
                if (right != null) {
                    if (!isLeftMostElementAdded) {
                        result.add(right.val);
                        isLeftMostElementAdded = true;
                    }
                    queue.add(right);
                }
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (A == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<Integer> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(levelNodes);
        }

        System.out.println(result.toString());

        return result;
    }


    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    private void print(String message, LinkedList<Integer> lis) {
        printHelper.print(message, lis);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

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
