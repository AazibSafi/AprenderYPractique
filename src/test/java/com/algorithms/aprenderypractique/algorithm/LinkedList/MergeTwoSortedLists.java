package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;

/**
 *      https://leetcode.com/problems/merge-two-sorted-lists
 */
public class MergeTwoSortedLists {

    public LinkedList mergeTwoLists(LinkedList list1, LinkedList list2) {
        LinkedList ans = new LinkedList(-1);
        LinkedList merge = ans;

        while(list1!=null && list2!=null) {
            if(list1.val < list2.val) {
                merge.next = list1;
                list1 = list1.next;
            }
            else {
                merge.next = list2;
                list2 = list2.next;
            }
            merge = merge.next;
        }

        merge.next = list1 == null ? list2 : list1;
        return ans.next;
    }

/*
    Time: O(n+m)
    Space: O(n+m)
*/
    public LinkedList mergeTwoLists2(LinkedList list1, LinkedList list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next , list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1 , list2.next);
            return list2;
        }
    }
}
