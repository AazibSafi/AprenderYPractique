package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;

/**
 *  Detection of Loop in a Linked List
 *  Floyd's Cycle Finding Algorithm
 *  https://leetcode.com/problems/linked-list-cycle
 *  https://www.youtube.com/watch?v=Aup0kOWoMVg
 */
public class LinkedListCycle {

    /*
        Take 2 pointers A, B
        increase the pointer A by one
        increase pointer B by Two
        at some point, both pointers will be at same Node.

        if the pointers reach to same Node, there is a loop     (TRUE)
        if any pointer reaches to null, No loop exist           (FALSE)
     */

    public boolean hasCycle(LinkedList head) {
        LinkedList slow=head, fast=head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}
