package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

public class LinkedList extends BaseTest {

    @Test
    public void test() {
        Node root = initialize(5);
        root = reverseList(root);

        printLinkedList(root);
    }

    Node initialize(int n) {
        Node root = new Node(1,null);
        Node head = root;
        for(int i=2;i<=n;i++) {
            root.next = new Node(i,null);
            root = root.next;
        }
        return head;
    }

    Node reverseList(Node root) {
        if(root==null || root.next==null) {
            return root;
        }
        Node head = reverseList(root.next);
        root.next.next = root;
        root.next = null;
        return head;
    }

    void printLinkedList(Node root) {
        while(root!=null) {
            System.out.println(root.x);
            root = root.next;
        }
    }

}
