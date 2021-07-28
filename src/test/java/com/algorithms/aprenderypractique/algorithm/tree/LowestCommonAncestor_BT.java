package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  https://www.youtube.com/watch?v=xuvw11Ucqs8&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=16
 *  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/submissions/
 */
public class LowestCommonAncestor_BT extends BaseTest {

    @Test
    public void solution() {
    /*
        TreeNode [3,5,1,6,2,0,8,null,null,7,4]
        p=5
        q=1

        Time: O(N)
        Space: O(N)
     */
    }

    class BinaryTree {
        int val;
        BinaryTree left;
        BinaryTree right;
        BinaryTree(int x) { val = x; }
    }

    BinaryTree result;

    public BinaryTree lowestCommonAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {
        findAncestor(root,p,q);
        return result;
    }

    public boolean findAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {
        if(root == null) return false;

        boolean left = findAncestor(root.left,p,q);
        boolean right = findAncestor(root.right,p,q);
        boolean curr = root == p || root == q;

        if((left && right) || (left && curr) || (right && curr))
            result = root;

        return left || right || curr;
    }

}
