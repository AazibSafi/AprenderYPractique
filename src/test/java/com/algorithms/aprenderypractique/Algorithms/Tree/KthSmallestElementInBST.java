package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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

/*
    inorder traversal of a BST gives us the elements in sorted order
    Time: O(K)
    Space: O(K)
*/
    public int kthSmallest_rec(BinaryTree root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, k, list);
        return list.get(k-1);
    }

    void inorder(BinaryTree root, int k, List<Integer> list) {
        if(root == null || list.size() >= k) return;
        inorder(root.left, k, list);
        list.add(root.val);
        inorder(root.right, k, list);
    }

}
