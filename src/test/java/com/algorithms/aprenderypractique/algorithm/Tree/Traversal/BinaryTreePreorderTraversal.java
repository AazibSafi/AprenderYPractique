package com.algorithms.aprenderypractique.algorithm.Tree.Traversal;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 *      https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreorderTraversal extends BaseTest {
/*
    Approach#3: Threaded Binary Tree - Morris Traversal
    Time: O(n)
    Space: O(1)
*/
    public List<Integer> preorderTraversal(BinaryTree root) {
        List<Integer> preorder = new ArrayList<>();

        while(root != null) {
            if(root.left == null) {
                preorder.add(root.val);
                root = root.right;
            }
            else {
                BinaryTree prev = root.left;

                // Find the rightmost node in the left subtree, or the node that already points to root
                while(prev.right != null && prev.right != root) {
                    prev = prev.right;
                }

                if(prev.right == null) {
                    // Establish a temporary thread back to the current root node
                    prev.right = root;      // Mark Thread
                    preorder.add(root.val);  // Add the current node to result
                    root = root.left;
                }
                else {
                    prev.right = null;      // Restore the tree
                    root = root.right;      // Move to the right subtree
                }
            }
        }
        return preorder;
    }

/*
    Approach#1 - Recursive
    Time: O(n)
    Space: O(n)
*/
    public List<Integer> preorderTraversal1(BinaryTree root) {
        List<Integer> output = new ArrayList<>();
        traverse(root, output);
        return output;
    }

    public void traverse(BinaryTree root, List<Integer> output) {
        if(root == null)    return;
        output.add(root.val);
        traverse(root.left, output);
        traverse(root.right, output);
    }

}
