package com.algorithms.aprenderypractique.Algorithms.LinkedList.NextRightPointers;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *      https://leetcode.com/problems/populating-next-right-pointers-in-each-node
 *      https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
 */
public class PopulatingNextRightPointersInEachNode {

/*
    Time : O(n)
    Space : O(n)

    Both set of Problems can be solved by this single method of BFS
 */
    public BinaryTree connect(BinaryTree root) {
        if(root == null)    return root;
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int n = queue.size();

            for(int i=0; i<n && !queue.isEmpty(); i++) {    // only process the breadth of current level
                BinaryTree node = queue.poll();

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);

                node.next = i==n-1 ? null : queue.peek();
            }
        }
        return root;
    }

/*
    Time : O(n)
    Space : O(n)
    This method only applies to first problem
 */
    public BinaryTree connect_Rec(BinaryTree root) {
        if(root == null) return root;

        if(root.left != null) {
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;        // Not needed to return, but for the sake of method signature
    }

}
