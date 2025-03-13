package com.algorithms.aprenderypractique.Algorithms.Arrays.MedianStream;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *      https://leetcode.com/problems/find-median-from-data-stream
 *
 *      TODO:
 *      https://leetcode.com/problems/sliding-window-median
 *      https://leetcode.com/problems/ipo
 */
public class FindMedianFromDataStream extends BaseTest {
    @Test
    public void test() { }
}

class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

/*
    Time: O(log n)
    Space: O(n)
 */
    public void addNum(int num) {
        minHeap.offer(num);                 // O(log n)
        maxHeap.offer(minHeap.poll());      // O(log n)

        if(maxHeap.size() > minHeap.size()+1) {
            minHeap.offer(maxHeap.poll());
        }
    }

// Time: O(1)
    public double findMedian() {
        if(maxHeap.size() > minHeap.size())
            return maxHeap.peek();
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

}
