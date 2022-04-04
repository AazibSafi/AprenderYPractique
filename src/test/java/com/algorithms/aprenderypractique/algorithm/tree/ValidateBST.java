package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

/*
    https://leetcode.com/problems/validate-binary-search-tree/
    https://leetcode.com/problems/validate-binary-search-tree/solution/
 */
public class ValidateBST extends BaseTest {

    @Test
    public void solution() { }

    public boolean validate(BinaryTree root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }
        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        // The left and right subtree must also be valid.
        return validate(root.right, root.val, high) && validate(root.left, low, root.val);
    }

    public boolean isValidBST(BinaryTree root) {
        return validate(root, null, null);
    }

}
