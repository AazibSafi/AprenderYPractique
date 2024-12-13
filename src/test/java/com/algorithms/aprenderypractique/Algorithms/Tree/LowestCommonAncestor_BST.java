package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 */
public class LowestCommonAncestor_BST extends BaseTest {

    @Test
    public void solution() {

    }

    public BinaryTree lowestCommonAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {
        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);

        if(p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);

        return root;
    }

}
