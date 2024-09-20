package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;

/**
 *  https://leetcode.com/problems/subtree-of-another-tree
 *
 * @see SameTree
 */
public class SubtreeOfAnotherTree {

    /*
    Time: O(N * M)
    Space: O(N)
*/
    public boolean isSubtree(BinaryTree root, BinaryTree subRoot) {
        if(root == null) return root == subRoot;
        return isSameTree(root, subRoot)
                || isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(BinaryTree p, BinaryTree q) {
        if (p == null || q == null) return p == q;
        return (p.val == q.val)
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }

}
