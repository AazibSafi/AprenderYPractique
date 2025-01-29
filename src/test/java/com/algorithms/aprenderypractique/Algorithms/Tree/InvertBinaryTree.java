package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;

/**
 *  https://leetcode.com/problems/invert-binary-tree
 */
public class InvertBinaryTree {
/*
    Time: O(n) -> number of Nodes in the tree
    Space: O(h) -> height of the tree;
           O(n) -> worst case
 */
    public BinaryTree invertTree(BinaryTree root) {
        if(root == null) return null;

        BinaryTree temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }
}
