package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthHighest_SmallestElement extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {2,8,5,3,9,1};
        Assert.assertEquals(8,findKthLargest(array, 2));
        Assert.assertEquals(2,findKthSmallest(array, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int i : nums) {
            minHeap.add(i);

            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return minHeap.remove(); //To get kth Largest element
    }

    // O(N) + O(K*LogN)
    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i : nums) {
            maxHeap.add(i); // O(N)

            if (maxHeap.size() > k) {
                maxHeap.remove();   // O(K*LogN)
            }
        }
        return maxHeap.remove(); //To print kth Largest element
    }

}
