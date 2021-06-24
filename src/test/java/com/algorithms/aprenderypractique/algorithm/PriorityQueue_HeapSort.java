package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueue_HeapSort extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {4, 10, 3, 5, 1};
        heapSort(array);
    }

    public void heapSort(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : nums) {
            maxHeap.add(i); // O(N)
        }
        System.out.println("Result: "+maxHeap);
    }

}
