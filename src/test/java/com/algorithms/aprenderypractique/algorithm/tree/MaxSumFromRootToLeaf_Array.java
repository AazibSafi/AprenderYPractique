package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *     https://youtu.be/HzeK7g8cD0Y?list=PLqM7alHXFySESatj68JKWHRVhoJ1BxtLW&t=160
 *
 *          3
 *       /     \
 *      4      7
 *     / \    / \
 *    6  20  9  11
 *
 *    Output: Sum of [3 4 20] = 27
 *
 *
 *  https://d18l82el6cdm1i.cloudfront.net/uploads/xlck8z42EM-greedy-search-path-example.gif
 *          7
 *       /     \
 *      3      12
 *     / \    / \
 *    99  8  5   6
 *
 *    Output: Sum of [7 3 99] = 109
 *
 *    2^k nodes at each k level of tree
 *
 *    (2^k+1)-1 total Nodes in the Tree where k is the total depth/height of the Tree
 */
public class MaxSumFromRootToLeaf_Array extends BaseTest {

    @Test
    public void test() {
        int[] tree = new int[]{3, 4, 7, 6, 20, 9, 11};
        Assert.assertEquals(27,findMaxSumFromRootToLeaf(tree,0));

        tree = new int[]{7, 3, 12, 99, 8, 5, 6};
        Assert.assertEquals(109,findMaxSumFromRootToLeaf(tree,0));
    }

    public int findMaxSumFromRootToLeaf(int[] T, int root) {
        if(root >= T.length)  return 0;

        int leftChild = 2*root+1;
        int rightChild = 2*root+2;

        return T[root] + Math.max(
                            findMaxSumFromRootToLeaf(T, leftChild),
                            findMaxSumFromRootToLeaf(T,rightChild)
                        );
    }

/*
    Same logic and implementation as above.
    just open up the code for more readable
 */
    public int findMaxSumFromRootToLeaf2(int[] T, int root) {
        int leftChild = 2*root+1;
        int rightChild = 2*root+2;

        int leftSum = 0, rightSum = 0;

        if(leftChild < T.length) {
            leftSum = findMaxSumFromRootToLeaf(T, leftChild);
        }
        if(rightChild < T.length) {
            rightSum = findMaxSumFromRootToLeaf(T,rightChild);
        }

        return T[root] + Math.max(leftSum,rightSum);
    }

}
