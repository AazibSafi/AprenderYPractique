package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class SumOfLeftLeaves extends BaseTest {

    public class BinaryTree {
        int val;
        BinaryTree left;
        BinaryTree right;
        BinaryTree() {}
        BinaryTree(int val) { this.val = val; }
        BinaryTree(int val, BinaryTree left, BinaryTree right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void solution() {

    }

    public int sumOfLeftLeavesRec(BinaryTree root) {
        if(root == null) return 0;

        int sum = 0;

        if(root.left != null) {
            if(isLeaf(root.left)) sum += root.left.val;
            else sum += sumOfLeftLeavesRec(root.left);
        }

        sum += sumOfLeftLeavesRec(root.right);

        return sum;
    }

    public int sumOfLeftLeaves(BinaryTree root) {
        if(root == null)    return 0;

        int sum = 0;
        Queue <BinaryTree>queue = new LinkedList();
        queue.add(root);

        while(!queue.isEmpty()) {
            BinaryTree node = queue.poll();

            if(node.left != null) {
                if(isLeaf(node.left)) sum += node.left.val;
                else queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
        }

        return sum;
    }

    public boolean isLeaf(BinaryTree node) {
        return node.left == null && node.right == null;
    }

}
