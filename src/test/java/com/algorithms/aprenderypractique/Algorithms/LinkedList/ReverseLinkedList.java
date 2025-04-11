package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Reversing a LinkedList
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(1)
 *      https://leetcode.com/problems/reverse-linked-list
 */
public class ReverseLinkedList extends BaseTest {

    @Test
    public void test() {
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(6);
        list.next.next.next = new LinkedList(3);

        LinkedList reversedList = reverseList(list);
        Assert.assertEquals(3, reversedList.val);
    }

    public LinkedList reverseList(LinkedList head) {
        LinkedList prev = null, next = null, curr = head;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public LinkedList reverseList_rec(LinkedList head) {
        if(head == null || head.next == null) return head;

        LinkedList node = reverseList(head.next);
        head.next.next = head;
        head.next = null;  // Edge case: mostly people forget this, causes memory leak
        return node;
    }

}
