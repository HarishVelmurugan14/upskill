package DSA.Advanced.Part3;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @since 16-04-2025
 * @last-modified 16-04-2025
 */
public class d40_MorrisInorder_LCA {



    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d40_MorrisInorder_LCA d40_morrisInorder_lca = new d40_MorrisInorder_LCA();
//        d40_morrisInorder_lca.getKthSmallestElement(basicInput(), 1);
        boolean x = d40_morrisInorder_lca.test(secondInput(), secondInput(), -1, 0, false);
        System.out.println(x);


    }

    public static TreeNode basicInput() {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);

        root.right = node3;
        root.left = node2;
        return root;
    }

    public static TreeNode input() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        return root;
    }

    public static TreeNode secondInput() {
        TreeNode root = new TreeNode(1000);
        root.left = new TreeNode(2000);
        root.left.left = new TreeNode(-3001);
        return root;
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

//    public int hasPathSum(TreeNode A, int B) {
//    }

    private boolean test(TreeNode actualRoot, TreeNode root, int target, int sum, boolean res) {
        if (root == null) {
            System.out.println(target + " - "+sum + "-"+(target == sum));
            return target == sum;
        }
        sum = sum + root.val;
        boolean temp = test(actualRoot, root.left, target, sum, res);
        if(temp && actualRoot == root){
            temp = false;
        }
        res = res || temp;

        System.out.println("ONE " + res);
        temp = test(actualRoot, root.right, target, sum, res);
        if(temp && actualRoot == root){
            temp = false;
        }
        res = res || temp;

        System.out.println("TWO " + res);
        return res;
    }



    private int index = -1;
    private int ans;
    public void getKthSmallestElement(TreeNode root, int k){
        if(root == null){
            return;
        }
        getKthSmallestElement(root.left, k);
        index++;
        if(k==index){
            ans = root.val;
        }
        System.out.println(root.val + " = "+index + "-"+k);

        getKthSmallestElement(root.right, k);

    }

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


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

}
