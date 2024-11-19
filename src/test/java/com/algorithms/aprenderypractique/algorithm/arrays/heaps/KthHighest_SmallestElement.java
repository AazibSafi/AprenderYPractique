package com.algorithms.aprenderypractique.algorithm.arrays.heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *  https://leetcode.com/problems/kth-largest-element-in-an-array
 *
 *  Note: Some confusion in Time Complexity
 *  Asked in Facebook Final Round
 *
 *  TODO: Learn for Quick Select approach for more efficient solution
 *
 *  Problem Type also known as Order statistic of an unsorted array
 */
public class KthHighest_SmallestElement extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {2, 8, 5, 3, 9, 1};
        Assert.assertEquals(8, findKthLargest(array, 2));
        Assert.assertEquals(2, findKthSmallest(array, 2));

        array = new int[] {3, 2, 1, 5, 6, 4};
        Assert.assertEquals(5, findKthLargest(array, 2));
        Assert.assertEquals(2, findKthSmallest(array, 2));

        array = new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6};
        Assert.assertEquals(4, findKthLargest(array, 4));
        Assert.assertEquals(3, findKthSmallest(array, 4));
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
