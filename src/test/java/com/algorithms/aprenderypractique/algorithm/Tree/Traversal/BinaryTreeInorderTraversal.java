package com.algorithms.aprenderypractique.algorithm.Tree.Traversal;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *      https://leetcode.com/problems/binary-tree-inorder-traversal
 *      https://www.youtube.com/watch?v=80Zug6D1_r4&ab_channel=takeUforward
 */
public class BinaryTreeInorderTraversal extends BaseTest {
/*
    Approach#3: Threaded Binary Tree - Morris Traversal
    Time: O(n)
    Space: O(1)
*/
    public List<Integer> inorderTraversal(BinaryTree root) {
        List<Integer> inorder = new ArrayList<>();

        while(root != null) {
            if(root.left == null) {
                inorder.add(root.val);
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
                    prev.right = root;  // Mark Thread
                    root = root.left;
                }
                else {
                    inorder.add(root.val);  // Add the current node to result
                    prev.right = null;  // Restore the tree
                    root = root.right;  // Move to the right subtree
                }
            }
        }
        return inorder;
    }

/*
    Todo: Approach#2: Iterating method using Stack
    Time: O(n)
    Space: O(n)
*/

/*
    Approach#1 - Recursive
    Time: O(n)
    Space: O(n)
*/
    public List<Integer> inorderTraversal1(BinaryTree root) {
        List<Integer> output = new ArrayList<>();
        traverse(root, output);
        return output;
    }
    public void traverse(BinaryTree root, List<Integer> output) {
        if(root == null)    return;
        traverse(root.left, output);
        output.add(root.val);
        traverse(root.right, output);
    }
}
