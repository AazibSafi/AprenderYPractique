package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;

/**
 *  https://leetcode.com/problems/invert-binary-tree
 */
public class InvertBinaryTree {
    public BinaryTree invertTree(BinaryTree root) {
        if(root == null) return null;

        BinaryTree temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }
}