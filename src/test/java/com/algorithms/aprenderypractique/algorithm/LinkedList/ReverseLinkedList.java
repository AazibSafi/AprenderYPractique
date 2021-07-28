package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Reversing a LinkedList
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(1)
 */
public class ReverseLinkedList extends BaseTest {

    @Test
    public void test() {
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(6);
        list.next.next.next = new LinkedList(3);

        LinkedList reversedList = reverseLL(list);

        Assert.assertEquals(3,reversedList.x);
    }

    LinkedList reverseLL(LinkedList head) {
        if(head == null || head.next == null) {
            return head;
        }
        else {
            LinkedList reversedList = reverseLL(head.next);
            head.next.next = head;
            head.next = null;
            return reversedList;
        }
    }

}
