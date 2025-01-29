package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;

/**
 *      https://leetcode.com/problems/remove-nth-node-from-end-of-list
 *
 *      https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/1384813926/?envType=problem-list-v2&envId=xi4ci4ig
 *
 *      https://leetcode.com/problems/remove-nth-node-from-end-of-list/solutions/5418407/video-using-distance-between-two-pointers/?envType=problem-list-v2&envId=xi4ci4ig
 */
public class RemoveNthNodeFromEndOfList {
/*
    Time: O(N)
    Space: O(1)
 */
    public LinkedList removeNthFromEnd(LinkedList head, int n) {
        LinkedList fast = head, slow = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        if (fast == null) return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public LinkedList removeNthFromEnd2(LinkedList head, int n) {
        if(head == null) return null;

        LinkedList res = new LinkedList(0, head); // edge cases when removing the first node
        LinkedList distance = res;

        for(int i=0; i<n; i++) {
            head = head.next;
        }

        while(head!=null) {
            head = head.next;
            distance = distance.next;
        }

        distance.next = distance.next.next;
        return res.next;
    }
    
}
