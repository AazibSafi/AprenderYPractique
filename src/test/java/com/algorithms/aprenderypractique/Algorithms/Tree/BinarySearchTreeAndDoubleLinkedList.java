package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import com.algorithms.aprenderypractique.BaseTest;

/**
 *      https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list
 *      https://codercareer.blogspot.com/2011/09/interview-question-no-1-binary-search.html
 */
public class BinarySearchTreeAndDoubleLinkedList extends BaseTest {

/*
    Time: O(n)
    Space: O(n)
*/
    BinaryTree first = null, last = null;

    public BinaryTree treeToDoublyList(BinaryTree root) {
        if(root == null)    return null;

        helper(root);

        // Making it Circular
        last.right = first;
        first.left = last;

        return first;
    }

    void helper(BinaryTree root) {
        if(root == null)    return;

        helper(root.left);

        if(last == null) {  // For the first time
            first = root;   // Saving the head of the result
            last = root;
        }
        else {
            last.right = root;
            root.left = last;
            last = root;
        }

        helper(root.right);
    }

}