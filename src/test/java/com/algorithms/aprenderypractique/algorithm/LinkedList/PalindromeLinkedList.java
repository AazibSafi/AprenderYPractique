package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;

/**
 *  https://leetcode.com/problems/palindrome-linked-list
 *  Logic: https://www.youtube.com/watch?v=yOzXms1J6Nk
 *  Code: https://www.youtube.com/watch?v=wk4QsvwQwdQ
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(LinkedList head) {
        if(head == null)    return true;
        LinkedList middle = getMiddle(head);
        LinkedList reversedHead = reverseLL(middle);        // Reverse from middle to last
        return isLLPalindrome(head, reversedHead);
    }

    public boolean isLLPalindrome(LinkedList head, LinkedList reversedHead) {
        while(reversedHead != null) {
            if(head.val != reversedHead.val)        return false;
            head = head.next;
            reversedHead = reversedHead.next;
        }
        return true;
    }

    public LinkedList reverseLL(LinkedList head) {
        if(head == null || head.next == null)       return head;

        LinkedList root = reverseLL(head.next);
        head.next.next = head;
        head.next = null;
        return root;
    }

    public LinkedList getMiddle(LinkedList head) {
        LinkedList slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
