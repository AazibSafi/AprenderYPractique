package com.algorithms.aprenderypractique.algorithm.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  Given a binary Tree, get the average at each level of the tree
 *
 *      4
 *     / \
 *    7   9
 *   / \   \
 *  10  2   6
 *      \
 *       6
 *      /
 *     2
 *
 *  Output:
 *  [4, 8, 6, 6, 2]
 */
public class AverageOfBinaryTreeLevels extends BaseTest {

    @Test
    public void test() {
        BinaryTree tree = new BinaryTree(4);
        tree.left = new BinaryTree(7);
        tree.right = new BinaryTree(9);
        tree.left.left = new BinaryTree(10);
        tree.left.right = new BinaryTree(2);
        tree.right.right = new BinaryTree(6);
        tree.left.right.right = new BinaryTree(6);
        tree.left.right.right.left = new BinaryTree(2);
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(4, 8, 6, 6, 2), findAverage(tree)));
    }

    public List<Integer> findAverage(BinaryTree tree) {
        if(tree == null) return null;

        List<Integer> result = new ArrayList<>();
        Queue<BinaryTree> queue = new LinkedList<>();
        int sum;
        int n;

        queue.add(tree);

        while(!queue.isEmpty()) {
            sum = 0;
            n = queue.size();

            for(int i=0; i<n; i++) {
                BinaryTree node = queue.poll();
                sum += node.val;

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }

            result.add(sum/n);
        }
        return result;
    }

}
