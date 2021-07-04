package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *
 */
public class LowestCommonAncestor_BST extends BaseTest {

    @Test
    public void solution() {

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left,p,q);

        else if(p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right,p,q);

        else
            return root;
    }

}
