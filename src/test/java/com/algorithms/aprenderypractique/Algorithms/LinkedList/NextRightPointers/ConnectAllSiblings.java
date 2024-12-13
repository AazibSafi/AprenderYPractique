package com.algorithms.aprenderypractique.Algorithms.LinkedList.NextRightPointers;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;

/**
 *      https://www.educative.io/connect-all-siblings
 */
public class ConnectAllSiblings {

/*
    Time : O(n)
    Space : O(n)
*/
    void populate_sibling_pointers(BinaryTree root) {
        BinaryTree curr = root, prev = root;

        while(curr != null) {
            if(curr.left != null) {
                prev.next = curr.left;
                prev = prev.next;
            }

            if(curr.right != null) {
                prev.next = curr.right;
                prev = prev.next;
            }

            curr = curr.next;
        }
    }

}
