package com.algorithms.aprenderypractique.algorithm.tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *  https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *  https://www.youtube.com/watch?v=5LUXSvjmGCw
 *
 *  Note: In-Order Traversal of BST gives us the sorted array
 */
public class KthSmallestElementInBST extends BaseTest {

    @Test
    public void test() {
        BinaryTree tree = new BinaryTree(3);
        tree.left = new BinaryTree(1);
        tree.right = new BinaryTree(4);
        tree.left.right = new BinaryTree(2);
        Assert.assertEquals(1, kthSmallest(tree, 1));

        tree = new BinaryTree(5);
        tree.left = new BinaryTree(3);
        tree.right = new BinaryTree(6);
        tree.left.left = new BinaryTree(2);
        tree.left.right = new BinaryTree(4);
        tree.left.left.left = new BinaryTree(1);
        Assert.assertEquals(3, kthSmallest(tree, 3));
    }

//  In-Order Traversal - Iterative
    int kthSmallest(BinaryTree root, int k) {
        int[] res = new int[k];
        int index = 0;

        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree curr = root;

        while((!stack.isEmpty() || curr != null) && index != k) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            else {
                curr = stack.pop();
                res[index++] = curr.val;
                curr = curr.right;
            }
        }

        return res[k-1];
    }

//  In-Order Traversal - Recursive
    public int kthSmallest2(BinaryTree root, int k) {
        List<Integer> list = new LinkedList<>();
        getSortedTree_DFS(root, list);
        return list.get(k-1);   // 1 Based Index
    }

    void getSortedTree_DFS(BinaryTree root, List<Integer> list) {
        if(root == null)    return;
        getSortedTree_DFS(root.left, list);
        list.add(root.val);
        getSortedTree_DFS(root.right, list);
    }

}
