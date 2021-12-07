package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/range-sum-of-bst/
 *  Code: https://www.youtube.com/watch?v=7lvGUiOAZeE
 */
public class RangeSumOfBST extends BaseTest {

    @Test
    public void test() { }

/*
    Time: O(logn)
    Worst Case: O(n)
 */
    public int rangeSumBST(BinaryTree root, int low, int high) {
        if(root == null)    return 0;

        if(root.val > high)     return rangeSumBST(root.left, low, high);
        if(root.val < low)      return rangeSumBST(root.right, low, high);

//  low <= root.val <= high
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

/*
    This is for Binary Tree.
    Kind of Brute force
    Not efficient in case of BST
 */
    public int rangeSumBinaryTree(BinaryTree root, int low, int high) {
        if(root == null)    return 0;

        int sum = 0;
        if(root.val >= low && root.val <= high)     sum += root.val;
        sum += rangeSumBST(root.left, low, high);
        sum += rangeSumBST(root.right, low, high);

        return sum;
    }

}
