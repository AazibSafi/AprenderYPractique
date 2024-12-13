package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/odd-even-linked-list/
 *  https://www.youtube.com/watch?v=YE9ggKeHeK0&t=194s
 */
public class OddEvenLL  extends BaseTest {

    @Test
    public void test() {    }

    public LinkedList oddEvenList(LinkedList head) {
        if(head == null || head.next == null)
            return head;

        LinkedList odd = head;
        LinkedList even = head.next;
        LinkedList evenHead = head.next;

        while( even != null && even.next != null ) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

}
