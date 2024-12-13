package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

import static com.algorithms.aprenderypractique.CommonHelper.createLinkedList;
import static com.algorithms.aprenderypractique.CommonHelper.isEqual;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=623634548182866&c=207085731181336
 *  https://leetcode.com/discuss/interview-question/688086/fb-online-assessment-question
 */
public class ReverseOperations extends BaseTest {

    @Test
    public void test() {
        int[] arr = {1, 2, 8, 9, 12, 16};
        int[] expected = {1, 8, 2, 9, 16, 12};
        Assert.assertTrue(isEqual(createLinkedList(expected), reverse(createLinkedList(arr))));

        arr = new int[]{2, 18, 24, 3, 5, 7, 9, 6, 12};
        expected = new int[]{24, 18, 2, 3, 5, 7, 9, 12, 6};
        Assert.assertTrue(isEqual(createLinkedList(expected), reverse(createLinkedList(arr))));

        arr = new int[]{2, 18, 24, 14, 3, 5, 7, 9, 6, 12};
        expected = new int[]{14, 24, 18, 2, 3, 5, 7, 9, 12, 6};
        Assert.assertTrue(isEqual(createLinkedList(expected), reverse(createLinkedList(arr))));
    }

/*
    push the GraphNode in stack if it is even
    when odd node is found, start swapping the items from head to the start of stack till half size of stack.
 */
    LinkedList reverse(LinkedList head) {
        LinkedList orignalHead = head, tail = head;
        Stack<LinkedList> stack = new Stack<>();

        while(head != null && tail != null) {
            if(tail.val %2 == 0) {
                stack.push(tail);
                tail = tail.next;
            }
            else {
                if(!stack.isEmpty()) {
                    reverseList(head, stack);
                    stack.clear();
                }
                tail = tail.next;
                head = tail;
            }
        }
        if(!stack.isEmpty()) {
            reverseList(head, stack);
            stack.clear();
        }
        return orignalHead;
    }

    void reverseList(LinkedList head, Stack<LinkedList> stack) {
        int n = stack.size()/2;
        while(n-- > 0) {
            swap(head, stack.pop());
            head = head.next;
        }
    }

    void swap(LinkedList head, LinkedList node) {
        int temp = head.val;
        head.val = node.val;
        node.val = temp;
    }

}
