package com.algorithms.aprenderypractique.algorithm.Tree;

import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;

/**
 *  https://leetcode.com/problems/same-tree
 */
public class SameTree {
    /*
        Time complexity: O(n)
            n is the total number of nodes in the binary trees.

        Space complexity: O(h) orO(n)
            h is height of deeper tree. That is average. In the worst case, O(n) because of skewed tree.
    */
    public boolean isSameTree(BinaryTree p, BinaryTree q) {
        if (p == null || q == null) return p == q;

        return (p.val == q.val)
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }

}
