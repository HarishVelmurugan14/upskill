package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Harish Velmurugan
 * @last-modified 19-03-2025
 * @since 19-03-2025
 */
@SuppressWarnings({"unused", "ClassEscapesDefinedScope"})
public class d31_Trees3_BST {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d31_Trees3_BST d31_trees3_bst = new d31_Trees3_BST();

        d31_trees3_bst.isValidBST(null); // Q1 // LC98 //##
        d31_trees3_bst.sortedArrayToBST(new int[]{1, 3, 5, 6, 7, 8}); // Q2 //##
        d31_trees3_bst.searchInABST(input(), 3); // Q4 //##
        d31_trees3_bst.maxDepth(null); //LC104 //##


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

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int maxDepth =0;
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        bfs(root, 0);
        return maxDepth+1;
    }

    public void bfs(TreeNode root, int level) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            maxDepth = Math.max(maxDepth, level+1);
            bfs(left, level + 1);
        }
        if(right != null){
            maxDepth = Math.max(maxDepth, level+1);
            bfs(right, level+1);
        }
    }

    public int searchInABST(TreeNode A, int B) {
        /* QUESTION :
        Given a Binary Search Tree A. Check whether there exists a node with value B in the BST.
        */

        if (A == null) {
            return 0;
        }

        if (A.val == B) return 1;
        if (A.val > B) {
            return searchInABST(A.left, B);
        } else {
            return searchInABST(A.right, B);
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode A, long min, long max) {
        // System.out.println(A + " : "+min + " : "+max + " : "+"IN");
        if (A == null) {
            return true;
        }
        // System.out.println(A.val + " : "+ min + " : "+ max);
        if (A.val >= min && A.val <= max) {
            return (isValidBST(A.left, min, (long) (A.val) - 1) && isValidBST(A.right, (long) (A.val) + 1, max));
        }
        return false;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return convert(nums, 0, nums.length - 1);
    }

    public TreeNode convert(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = convert(nums, l, mid - 1);
        root.right = convert(nums, mid + 1, r);
        return root;
    }

//    public int isValidBST(TreeNode A) {
//
//    }
//
//    public int isValidBST(TreeNode A, int min, int max) {
//
//        if (A.val < min) {
//            isValidBST(A.left, min, A.val);
//        } else {
//            isValidBST(A.right, A.val, max);
//        }
//
//    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    private void print(String message) {
        printHelper.print(message, "");
    }


    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void definitions() {
        /**/
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void links() {
        /**/
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */
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
