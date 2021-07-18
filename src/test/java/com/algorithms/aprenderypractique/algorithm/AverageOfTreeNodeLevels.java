package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Test;

import java.util.ArrayList;
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
public class AverageOfTreeNodeLevels extends BaseTest {

    @Test
    public void test() {
        TreeNode tree = new TreeNode(4);
        tree.left = new TreeNode(7);
        tree.right = new TreeNode(9);
        tree.left.left = new TreeNode(10);
        tree.left.right = new TreeNode(2);
        tree.right.right = new TreeNode(6);
        tree.left.right.right = new TreeNode(6);
        tree.left.right.right.left = new TreeNode(2);

        CommonHelper.printList( findAverage(tree) );
    }

    public List<Integer> findAverage(TreeNode tree) {
        if(tree == null) return null;

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int sum;
        int n;

        queue.add(tree);

        while(!queue.isEmpty()) {
            sum = 0;
            n = queue.size();

            for(int i=0;i<n;i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }

            result.add(sum/n);
        }
        return result;
    }

}
