package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.tree.datastructure.BinaryTree;
import org.junit.Test;

/**
 *
 */
public class LowestCommonAncestor_BST extends BaseTest {

    @Test
    public void solution() {

    }

    public BinaryTree lowestCommonAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {

        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left,p,q);

        else if(p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right,p,q);

        else
            return root;
    }

}
