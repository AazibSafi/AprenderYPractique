package com.algorithms.aprenderypractique.Algorithms.Tree;


import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;

/**
 *  https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *  https://www.youtube.com/watch?v=6cA_NDtpyz8&ab_channel=MichaelMuinos
 */
public class MaximumPathSum {

    Integer max = Integer.MIN_VALUE;

    public int maxPathSum(BinaryTree root) {
        postOrder(root);
        return max;
    }

    int postOrder(BinaryTree root) {
        if(root == null)        return 0;
        int left = Math.max(postOrder(root.left), 0);     // To avoid negative numbers
        int right = Math.max(postOrder(root.right) , 0);

         int pathSum = left + right + root.val;
         max = Math.max(pathSum, max);

         return Math.max(left , right) + root.val;
    }

}
