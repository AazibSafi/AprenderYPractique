package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.LinkedList;
import com.algorithms.aprenderypractique.BaseTest;

/**
 *      https://leetcode.com/problems/split-linked-list-in-parts
 *      https://www.youtube.com/watch?v=K5M8ALXVkR0
 *
 *      https://leetcode.com/discuss/interview-question/689328/bloomberg-phone-shuffle-cards
 */
public class SplitLLinParts extends BaseTest {
    /*
        Time: O(n + n) -> O(n)
        pace: O(1)
    */
    public LinkedList[] splitListToParts(LinkedList head, int k) {
        LinkedList[] ans = new LinkedList[k];
        int totalLen = getLen(head);    // get total size of linked list
        int partitionSize = totalLen/k;
        int distribution = totalLen%k;

        LinkedList prev = null;

        for(int i=0; i<k; i++) {
            ans[i] = head;

            // calculate size of i-th part
            int currSize = partitionSize;
            if(distribution > 0) {
                currSize++;
                distribution--;
            }

            // traverse to end of new part
            for(int j=0; j<currSize; j++) {
                prev = head;
                head = head.next;
            }

            // cut off the rest of linked list
            if(prev != null)    prev.next = null;
        }
        return ans;
    }

    public int getLen(LinkedList head) {
        int len = 0;
        LinkedList root = head;
        while(root != null) {
            root = root.next;
            len++;
        }
        return len;
    }
}
