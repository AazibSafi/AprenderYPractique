package com.algorithms.aprenderypractique.algorithm.tree.CountNodes;

import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;

/**
 *  https://leetcode.com/problems/count-complete-tree-nodes
 *  https://leetcode.com/problems/count-complete-tree-nodes/solutions/2815375/python-c-java-rust-logn-logn-with-proof-bonus-complete-list-of-solutions-explained/
 *
 *  Todo:
 *      https://leetcode.com/problems/count-the-number-of-good-nodes
 *      https://leetcode.com/problems/count-nodes-with-the-highest-score
 *      https://leetcode.com/problems/count-number-of-possible-root-nodes
 */
public class CountCompleteTreeNodes {
    
/*
    Time: O(logN*logN)
    Space: O(logN)
*/
    public int countNodes(BinaryTree root) {
        if(root == null) return 0;

        int lh = 1, rh = 1;

        BinaryTree left = root, right = root;
        while((left = left.left) != null) lh++;     // Left Height
        while((right = right.right) != null) rh++;  // Right Height

        if(lh == rh)  return (1 << lh) - 1;         // 2^h - 1

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /*
        Time: O(n)
        Space: O(n)
    */
    public int countNodes2(BinaryTree root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
}
