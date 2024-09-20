package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;

/**
 *  https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {

/**
 * Intuition
     * Find the Middle of Linked List
     * Reversed the 2nd half of the list
     * Breaked the connect between 1st half and 2nd half
     * Merge the List nodes alternatively from both lists

    * Time: O(n)
    * Space: O(n)
 */
    public void reorderList(LinkedList head) {
        LinkedList mid = findMiddle(head);
        mid = reverseList(mid);
        mergeAlt(head, mid);
    }

    LinkedList findMiddle(LinkedList head) {
        LinkedList slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        LinkedList mid = slow.next;
        slow.next = null;
        return mid;
    }

    public LinkedList reverseList(LinkedList head) {
        LinkedList prev = null, next = null, curr = head;

        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    LinkedList mergeAlt(LinkedList list1, LinkedList list2) {
        LinkedList root = new LinkedList(-1);
        LinkedList merge = root;

        boolean flag = true;
        while(list1 != null && list2 != null) {
            if(flag) {
                merge.next = list1;
                list1 = list1.next;
            }
            else {
                merge.next = list2;
                list2 = list2.next;
            }
            merge = merge.next;
            flag = !flag;
        }

        merge.next = list1 == null ? list2 : list1;
        return root = root.next;   // Head of New LL will be root
    }
    
}
