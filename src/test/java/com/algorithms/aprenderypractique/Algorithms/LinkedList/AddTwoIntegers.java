package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;

/**
 *      https://leetcode.com/problems/add-two-numbers
 *      https://www.educative.io/add-two-integers
 */
public class AddTwoIntegers {
/*
    Time: O(n)
    Space: O(n)
 */
    LinkedList addTwoNumbers(LinkedList list1, LinkedList list2) {
        LinkedList result = new LinkedList(-1);
        LinkedList add = result;
        int carry = 0, sum;

        while(list1 != null || list2 != null || carry > 0) {
            sum = carry;

            if(list1 != null) {
                sum += list1.val;
                list1 = list1.next;
            }
            if(list2 != null) {
                sum += list2.val;
                list2 = list2.next;
            }

            carry = sum/10;
            add.next = new LinkedList(sum%10);
            add = add.next;
        }

        return result.next;
    }

}
