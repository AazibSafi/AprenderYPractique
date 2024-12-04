package com.algorithms.aprenderypractique.algorithm.Tree.Traversal;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *      https://leetcode.com/problems/binary-tree-postorder-traversal
 */
public class BinaryTreePostorderTraversal extends BaseTest {
    public List<Integer> postorderTraversal3(BinaryTree root) {
        List<Integer> result = new ArrayList<>();

        // If the root is null, return an empty list
        if (root == null) {
            return result;
        }

        // Create a dummy node to simplify edge cases
        BinaryTree dummyNode = new BinaryTree(-1);
        BinaryTree predecessor = null;
        dummyNode.left = root;
        root = dummyNode;

        // Traverse the tree
        while (root != null) {
            // If the current node has a left child
            if (root.left != null) {
                predecessor = root.left;

                // Find the rightmost node in the left subtree or the thread to the current node
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // Create a thread if it doesn't exist
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    // Process the nodes in the left subtree
                    BinaryTree node = predecessor;
                    reverseSubtreeLinks(root.left, predecessor);

                    // Add nodes from right to left
                    while (node != root.left) {
                        result.add(node.val);
                        node = node.right;
                    }
                    result.add(node.val); // Add root.left value
                    reverseSubtreeLinks(predecessor, root.left);
                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                // Move to the right child if there's no left child
                root = root.right;
            }
        }

        return result;
    }

    private void reverseSubtreeLinks(BinaryTree startNode, BinaryTree endNode) {
        if (startNode == endNode) {
            return; // If the start and end nodes are the same, no need to reverse
        }

        BinaryTree prev = null;
        BinaryTree current = startNode;
        BinaryTree next = null;

        // Reverse the direction of the pointers in the subtree
        while (current != endNode) {
            next = current.right;
            current.right = prev;
            prev = current;
            current = next;
        }
        // Reverse the last node
        current.right = prev;
    }
    
/*
    Approach#2 - Two Stack Postorder Traversal (Iterative)
    Time: O(n)
    Space: O(n)
*/
    public List<Integer> postorderTraversal2(BinaryTree root) {
        List<Integer> output = new ArrayList<>();

        if(root == null)    return output;

        Stack<BinaryTree> treeStack = new Stack<>();  // Stack to manage the Tree Traversal
        Stack<BinaryTree> orderStack = new Stack<>(); // Stack to manage the Order Path

        treeStack.push(root);

        while(!treeStack.isEmpty()) {
            root = treeStack.peek();

            if(!orderStack.isEmpty() && orderStack.peek() == root) {
                output.add(root.val);
                treeStack.pop();
                orderStack.pop();
            }
            else {
                orderStack.push(root);
                if(root.right != null)
                    treeStack.push(root.right);

                if(root.left != null)
                    treeStack.push(root.left);
            }
        }

        return output;
    }

/*
    Approach#1 - Recursive
    Time: O(n)
    Space: O(n)
*/
    public List<Integer> postorderTraversal1(BinaryTree root) {
        List<Integer> output = new ArrayList<>();
        traverse(root, output);
        return output;
    }

    public void traverse(BinaryTree root, List<Integer> output) {
        if(root == null)    return;
        traverse(root.left, output);
        traverse(root.right, output);
        output.add(root.val);
    }

}
