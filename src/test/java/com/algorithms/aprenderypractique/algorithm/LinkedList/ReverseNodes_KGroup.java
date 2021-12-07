package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/reverse-nodes-in-k-group/
 *  https://www.youtube.com/watch?v=jTWEmztptCQ&ab_channel=CodingwithConner
 *
 *  Code Not working right now :(
 */
public class ReverseNodes_KGroup extends BaseTest {

    @Test
    public void test() {

    }

    public LinkedList reverseKGroup(LinkedList head, int k) {
        LinkedList root = new LinkedList(0, head);
        LinkedList curr = head;
        LinkedList prev = root;

        while(curr!=null) {
            LinkedList tail = curr;
            int ind = 0;

            while(curr != null && ind < k) {
                curr = curr.next;
                ind++;
            }

            if(ind != k) {
                prev.next = tail;
            }
            else {
                prev.next = reverseLL(tail,k);
            }
        }
        return root.next;
    }

/**
 * @see ReverseLinkedList
 */
    LinkedList reverseLL(LinkedList head, int k) {
        LinkedList prev = null;
        LinkedList current = head;
        LinkedList next = null;

        while(current != null && k-- > 0) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
        return head;
    }

}
