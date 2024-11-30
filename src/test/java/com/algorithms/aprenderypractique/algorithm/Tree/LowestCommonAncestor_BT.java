package com.algorithms.aprenderypractique.algorithm.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

/**
 *  https://www.youtube.com/watch?v=xuvw11Ucqs8&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=16
 *  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree
 */
public class LowestCommonAncestor_BT extends BaseTest {

    @Test
    public void solution() {}
/*
    Time: O(N)
    Space: O(N) || O(logN)
*/
    public BinaryTree lowestCommonAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {
        if(root == null || root == p || root == q) return root;

        BinaryTree left = lowestCommonAncestor(root.left, p, q);
        BinaryTree right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) return root;
        return left == null ? right : left;
    }
    
    
/*  Another Solution
        Time: O(N)
        Space: O(N)
 */
    BinaryTree result;

    public BinaryTree lowestCommonAncestor2(BinaryTree root, BinaryTree p, BinaryTree q) {
        findAncestor(root, p, q);
        return result;
    }

    public boolean findAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {
        if(root == null)        return false;

        boolean left = findAncestor(root.left, p, q);
        boolean right = findAncestor(root.right, p, q);
        boolean curr = root.val == p.val || root.val == q.val;

        if((left && right) || (left && curr) || (right && curr))
            result = root;

        return left || right || curr;
    }

}
