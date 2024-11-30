package com.algorithms.aprenderypractique.algorithm.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/validate-binary-search-tree
 */
public class ValidateBST extends BaseTest {

    @Test
    public void solution() { }

/*
    Time complexity: O(n)
    Space complexity: O(n)
*/
    public boolean isValidBST(BinaryTree root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(BinaryTree root, long minVal, long maxVal) {
        if (root == null) return true;      // Empty trees are valid BSTs.
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    
}
