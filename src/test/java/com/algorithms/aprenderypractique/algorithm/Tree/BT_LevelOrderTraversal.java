package com.algorithms.aprenderypractique.algorithm.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
    https://leetcode.com/problems/binary-tree-level-order-traversal/

    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]
 */
public class BT_LevelOrderTraversal extends BaseTest {

    @Test
    public void test() { }

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
