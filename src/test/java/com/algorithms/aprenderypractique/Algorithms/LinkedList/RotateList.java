package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;
import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static com.algorithms.aprenderypractique.CommonHelper.createLinkedList;
import static com.algorithms.aprenderypractique.CommonHelper.isEqual;

/**
 *      https://leetcode.com/problems/rotate-list
 *      https://leetcode.com/problems/rotate-list/solutions/5233749/java-easy-to-understand-solution-with-explanation-100-beats
 *
 *      https://www.youtube.com/watch?v=UcGtPs2LE_c
 */
public class RotateList extends BaseTest {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {4, 5, 1, 2, 3};
        Assert.assertTrue(isEqual(createLinkedList(expected), rotateRight(createLinkedList(arr), 2)));

        arr = new int[]{0, 1, 2};
        expected = new int[]{2, 0, 1};
        Assert.assertTrue(isEqual(createLinkedList(expected), rotateRight(createLinkedList(arr), 4)));

        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        expected = new int[]{5, 6, 7, 1, 2, 3, 4};
        Assert.assertTrue(isEqual(createLinkedList(expected), rotateRight(createLinkedList(arr), 3)));

        arr = new int[]{-1, -100, 3, 99};
        expected = new int[]{3, 99, -1, -100};
        Assert.assertTrue(isEqual(createLinkedList(expected), rotateRight(createLinkedList(arr), 2)));

        arr = new int[]{-1};
        expected = new int[]{-1};   // Edge Case: if k is greater than the size of array
        Assert.assertTrue(isEqual(createLinkedList(expected), rotateRight(createLinkedList(arr), 2)));
    }

/*
    Time: O(n)
    Space: O(1)

    Intuition
    We need to find the length of the list. After that, traverse the list with a pointer
    and set the length-k node as the new head. Finally, link the last node to the previous first node.
 */
    public LinkedList rotateRight(LinkedList head, int k) {
        // 1. Initial Checks
        if(head == null || head.next == null || k == 0) return head;

        // 2. Calculate Length
        int length = 1;
        LinkedList tail = head;
        while(tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 3. Make the List Circular
        tail.next = head;

        // 4. Calculate Effective Rotations
        k = k % length;
        int breakPoint = length - k - 1;

        // 5. Find New Head
        LinkedList traverse = head;
        while(breakPoint-- > 0) {
            traverse = traverse.next;
        }

        // 6. Break the Circular List
        LinkedList newHead = traverse.next;
        traverse.next = null;

        // 7. Return New Head
        return newHead;
    }


}
