package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/copy-list-with-random-pointer
 *      https://www.educative.io/copy-linked-list-with-arbitrary-pointer
 */
public class CopyListWithRandomPointer {

/*
    Time: O(n)
    Space: O(n)
*/
    Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return clone(head, map);
    }

    Node clone(Node head, Map<Node, Node> map) {
        if(head == null) return null;
        if(map.containsKey(head))
            return map.get(head);

        Node copy = new Node(head.val);
        map.put(head, copy);

        copy.next = clone(head.next, map);
        copy.random = clone(head.random, map);
        return copy;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}


