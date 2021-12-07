package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.youtube.com/watch?v=rWbmjLhXjTs
 *
 *          2
 *       /     \
 *      7       5
 *     / \       \
 *    2   6       9
 *       / \     /
 *      5  11   4
 *
 *   Output: sum of [2 7 6 11]
 */
public class MaxSumFromRootToLeaf_Tree extends BaseTest {

    @Test
    public void test() {
        BinaryTree tree = new BinaryTree(2);
        tree.left = new BinaryTree(7);
        tree.right = new BinaryTree(5);
        tree.left.left = new BinaryTree(2);
        tree.left.right = new BinaryTree(6);
        tree.left.right.left = new BinaryTree(5);
        tree.left.right.right = new BinaryTree(11);
        tree.right.right = new BinaryTree(9);
        tree.right.right.left = new BinaryTree(4);

        Assert.assertEquals(26,findMaxSumFromRootToLeaf(tree));
    }

    public int findMaxSumFromRootToLeaf(BinaryTree root) {
        if(root == null) return 0;

        return root.val + Math.max(
                            findMaxSumFromRootToLeaf(root.left),
                            findMaxSumFromRootToLeaf(root.right)
                        );
    }

}
