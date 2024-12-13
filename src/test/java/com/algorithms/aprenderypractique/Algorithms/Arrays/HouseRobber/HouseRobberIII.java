package com.algorithms.aprenderypractique.Algorithms.Arrays.HouseRobber;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/house-robber-iii
 *      https://algo.monster/liteproblems/337
 */
public class HouseRobberIII extends BaseTest {

    @Test
    public void test() { }

/*
    Time: O(N)
    Space: O(H)
*/
    public int rob(BinaryTree root) {
        int[] result = robSubTree(root);
        return Math.max(result[0], result[1]);
    }

    /**
     * Performs a depth-first search to find the maximum amount of money
     * that can be robbed from the current subtree.
     *
     * @param root The current node of the binary tree.
     * @return An array containing two elements:
     *         [0] - The maximum amount when the current node is robbed.
     *         [1] - The maximum amount when the current node is not robbed.
     */
    public int[] robSubTree(BinaryTree root) {
        if(root == null) return new int[2];

        int[] left = robSubTree(root.left);
        int[] right = robSubTree(root.right);

        int rob = root.val + left[1] + right[1];    // Robbing the current node + Not Robbing its both children
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);     // Max(Rob , Not Rob) of children

        return new int[]{rob, notRob};
    }

}
