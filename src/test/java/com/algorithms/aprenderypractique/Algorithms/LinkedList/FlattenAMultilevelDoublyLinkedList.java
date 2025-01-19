package com.algorithms.aprenderypractique.Algorithms.LinkedList;

/**
 *      https://leetcode.com/problems/add-two-numbers
 *      https://www.educative.io/add-two-integers
 */
public class FlattenAMultilevelDoublyLinkedList {
/*
    Approach#1: DFS by Recursion
    Time: O(n)
    Space: O(n)
*/
    public Node flatten(Node head) {
        if(head == null)    return head;
        Node root = new Node();  //(0, null, head, null);
        flattenDFS(root, head);

        // Detach the pseudo head from the real head
        root.next.prev = null;
        return root.next;
    }

    // Return the tail of the flatten list
    public Node flattenDFS(Node prev, Node curr) {
        if(curr == null) return prev;
        curr.prev = prev;
        prev.next = curr;

        Node tempNext = curr.next;

        Node tail = flattenDFS(curr, curr.child);
        curr.child = null;                  // Edge Case: Don't forget to remove all child pointers.
        return flattenDFS(tail, tempNext);
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
