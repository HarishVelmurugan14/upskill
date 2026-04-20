package com.datastructures;

public class InorderTraversal {
    TreeNode root;

    InorderTraversal() {
        root = null;
    }

    int traverseInorder(TreeNode TreeNode) {
        if (TreeNode == null)
            return 333;
        System.out.println( "Old -> "+ TreeNode.value );
        System.out.println("LEFT - > "+traverseInorder(TreeNode.left));
        System.out.print(TreeNode.value + " ");
        System.out.println("RIGHT - > "+ traverseInorder(TreeNode.right));
        return 400;
    }

    void traverseInorder() {
        traverseInorder(root);
    }

    public static void main(String args[]) {
        InorderTraversal pt = new InorderTraversal();
        pt.root = new TreeNode(35);
        pt.root.left = new TreeNode(25);
        pt.root.right = new TreeNode(45);
        pt.root.left.left = new TreeNode(20);
        pt.root.left.right = new TreeNode(30);
        pt.root.left.left.left = new TreeNode(10);
        pt.root.left.left.right = new TreeNode(23);
        pt.root.right.left = new TreeNode(40);
        pt.root.right.right = new TreeNode(55);
        pt.root.right.right.left = new TreeNode(50);
        pt.root.right.right.right = new TreeNode(66);
        System.out.println();
        System.out.println("The Inorder traversal of given binary tree is - ");
        pt.traverseInorder();
        System.out.println();
    }
}

 class TreeNode {
    public int value;
    public TreeNode left, right;
    public TreeNode(int element)
    {
        value = element;
        left = right = null;
    }
}
