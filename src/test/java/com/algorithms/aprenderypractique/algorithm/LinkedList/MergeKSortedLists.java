package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;

/**
 *      https://leetcode.com/problems/merge-k-sorted-lists
 *      https://www.youtube.com/watch?v=q5a5OiGbT6Q
 */
public class MergeKSortedLists {

/*
    Time: O(N log k)
    Space: O(log k)
 */
    public LinkedList mergeKLists(LinkedList[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private LinkedList mergeKListsHelper(LinkedList[] lists, int start, int end) {
        if (start == end)
            return lists[start];

        int mid = (start + end) / 2;
        LinkedList left = mergeKListsHelper(lists, start, mid);
        LinkedList right = mergeKListsHelper(lists, mid + 1, end);
        return merge(left, right);
    }

    private LinkedList merge(LinkedList l1, LinkedList l2) {
        LinkedList root = new LinkedList(0);
        LinkedList merge = root;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                merge.next = l1;
                l1 = l1.next;
            }
            else {
                merge.next = l2;
                l2 = l2.next;
            }
            merge = merge.next;
        }

        merge.next = (l1 != null) ? l1 : l2;
        return root.next;
    }

}
