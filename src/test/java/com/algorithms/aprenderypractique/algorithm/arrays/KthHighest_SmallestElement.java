package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthHighest_SmallestElement extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {2,8,5,3,9,1};
        System.out.println("Kth Highest Element: " + findKthLargest(array, 2));
        //System.out.println("Kth Smallest Element: " + findKthSmallest(array, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int i : nums) {
            minHeap.add(i);

            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        System.out.println(minHeap); //To print all k Largest elements
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
        System.out.println(maxHeap); //To print all k Largest elements
        return maxHeap.remove(); //To print kth Largest element
    }

}
