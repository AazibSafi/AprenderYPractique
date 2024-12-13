package com.algorithms.aprenderypractique.Algorithms.Greedy;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=836241573518034
 */
public class SlowSums extends BaseTest {

    @Test
    public void testFindMaxValue() {
        int[] arr = new int[]{4, 2, 1, 3};
        Assert.assertEquals(26, getTotalTime(arr));

        arr = new int[]{2, 3, 9, 8, 4};
        Assert.assertEquals(88, getTotalTime(arr));
    }

//  O(n) + O[n-1(logn+logn)]
//  O(nlogn)    --> Probably :p
    public int getTotalTime(int[] arr) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int totalTime = 0;

        for(int x : arr) maxHeap.add(x);        // O(n)

        while(maxHeap.size() > 1) {         // O(n-1)
            int penality = maxHeap.poll() + maxHeap.poll();     // O(logn) + O(logn)
            totalTime += penality;
            maxHeap.add(penality);
        }
        return totalTime;
    }

}
