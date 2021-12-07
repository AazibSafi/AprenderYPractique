package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestor_BST extends BaseTest {

    @Test
    public void solution() {

    }

    public BinaryTree lowestCommonAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {

        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);

        else if(p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);

        else
            return root;
    }

}
