package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;

/**
 *  https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * @see LinkedListCycle
 */
public class LinkedListCycleII {

/*
    Time: O(n)
    Space: O(1)
*/
    public LinkedList detectCycle(LinkedList head) {
        LinkedList slow=head, fast=head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                slow = head;

                while(slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }
}
