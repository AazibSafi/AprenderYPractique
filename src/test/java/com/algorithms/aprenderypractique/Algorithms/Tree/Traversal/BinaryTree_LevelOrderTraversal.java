package com.algorithms.aprenderypractique.Algorithms.Tree.Traversal;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import com.algorithms.aprenderypractique.BaseTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
    https://leetcode.com/problems/binary-tree-level-order-traversal
    https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
 */
public class BinaryTree_LevelOrderTraversal extends BaseTest {

/*
    Breath First Search
    Time: O(n)
    Space; O(n)
 */
    public List<List<Integer>> levelOrder(BinaryTree root) {
        List<List<Integer>> output = new ArrayList<>();

        if(root == null)        return output;

        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int currSize = queue.size();
            List<Integer> list = new ArrayList<>();

            for(int i=0; i<currSize; i++) {
                BinaryTree node = queue.poll();
                list.add(node.val);

                if(node.left != null)       queue.add(node.left);
                if(node.right != null)      queue.add(node.right);
            }

            output.add(list);
        }

        return output;
    }

}
