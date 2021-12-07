package com.algorithms.aprenderypractique.algorithm.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/split-linked-list-in-parts/
 *      https://www.youtube.com/watch?v=K5M8ALXVkR0
 */
public class SplitLLinParts extends BaseTest {

    @Test
    public void test() {    }

    public LinkedList[] splitListToParts(LinkedList head, int k) {
        LinkedList[] ans = new LinkedList[k];

        int totalLength = getSizeOfLinkedList(head);
        int partitionSize = totalLength / k;
        int distribution = totalLength % k;

        LinkedList prev = null;
        int l = 0;
        while(head != null && l < k) {
            ans[l++] = head;

            for(int i=0; i<partitionSize+(distribution>0?1:0); i++) {
                prev = head;
                head = head.next;
            }

            prev.next = null;
            distribution--;
        }

        return ans;
    }

    int getSizeOfLinkedList(LinkedList head) {
        LinkedList temp = head;
        int len = 0;
        while(temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

}
