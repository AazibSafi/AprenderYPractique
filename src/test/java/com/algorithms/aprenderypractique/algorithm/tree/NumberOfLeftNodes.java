package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=495004218121393
 *  find the number of left nodes in a tree
 *
 *             8
 *            / \
 *          3    10
 *         / \     \
 *        1   6     14
 *           / \    /
 *          4   7  13
 */
public class NumberOfLeftNodes extends BaseTest {
    public String serialize(BinaryTree root) {
        StringBuilder serialize = new StringBuilder();
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            BinaryTree node = queue.poll();

            if(node != null) {
                serialize.append(node.val + ',');
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            else {
                serialize.append("null,");
            }
        }
        return serialize.toString();
    }
    @Test
    public void test() {
        BinaryTree root = new BinaryTree(8);
        root.left = new BinaryTree(3);
        root.right = new BinaryTree(10);
        root.left.left = new BinaryTree(1);
        root.left.right = new BinaryTree(6);
        root.right.right = new BinaryTree(14);
        root.left.right.left = new BinaryTree(4);
        root.left.right.right = new BinaryTree(7);
        root.right.right.left = new BinaryTree(13);
        Assert.assertEquals(4, visibleNodes(root));


        BinaryTree root_2 = new BinaryTree(10);
        root_2.left = new BinaryTree(8);
        root_2.right = new BinaryTree(15);
        root_2.left.left = new BinaryTree(4);
        root_2.left.left.right = new BinaryTree(5);
        root_2.left.left.right.right = new BinaryTree(6);
        root_2.right.left = new BinaryTree(14);
        root_2.right.right = new BinaryTree(16);
        Assert.assertEquals(3, visibleNodes(root_2));
    }

    public int visibleNodes(BinaryTree root) {
        if(root == null)    return 0;

        int count = 0;

        if(root.left != null) {
            count += visibleNodes(root.left);
            count++;       // Count the current left node
        }

        if(root.right != null) {
            count += visibleNodes(root.right);
        }

        return count;
    }

}
