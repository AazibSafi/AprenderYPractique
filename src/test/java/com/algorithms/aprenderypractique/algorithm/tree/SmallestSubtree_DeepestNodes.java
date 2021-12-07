package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 *  https://www.youtube.com/watch?v=uod_6_NNWrk
 */
public class SmallestSubtree_DeepestNodes extends BaseTest {

    @Test
    public void test() {

    }

    BinaryTree resultNode;
    int maxDepth=0;
    public BinaryTree subtreeWithAllDeepest(BinaryTree root) {
        postOrder(root,0);
        return resultNode;
    }

    int postOrder(BinaryTree root, int depth) {
        if(root == null)        return depth;

        int left = postOrder(root.left, depth+1);
        int right = postOrder(root.right, depth+1);

        if(left == right) {
            if(left >= maxDepth) {      // condition not merge due to code readability
                maxDepth = left;
                resultNode = root;
            }
        }

        return Math.max(left, right);
    }

}
