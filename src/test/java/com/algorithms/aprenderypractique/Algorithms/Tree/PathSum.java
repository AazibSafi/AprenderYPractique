package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import com.algorithms.aprenderypractique.BaseTest;

/**
 *      https://leetcode.com/problems/path-sum
 */
public class PathSum extends BaseTest {

/*
    Time: O(n)
    Space: O(n)
*/
    public boolean hasPathSum(BinaryTree root, int targetSum) {
        if(root == null)
            return false;
                                        // is Leaf Node
        if(root.val == targetSum && root.left == null && root.right == null)
            return true;

        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }

}
