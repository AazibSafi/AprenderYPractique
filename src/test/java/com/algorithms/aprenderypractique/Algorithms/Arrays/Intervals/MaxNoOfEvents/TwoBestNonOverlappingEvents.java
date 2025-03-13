package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MaxNoOfEvents;

import com.algorithms.aprenderypractique.BaseTest;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *      https://leetcode.com/problems/two-best-non-overlapping-events
 *
 *      @see MaximumEarningsFromTaxi
 *      @see MaximumNumberOfEventsThatCanBeAttendedII
 */
public class TwoBestNonOverlappingEvents extends BaseTest {

/*
    Approach#2: Min-heap

    Sorting: O(nlogn)
    For all N events Heap: O(n.logn)
    Overall Time: O(n * logn)

    Space: O(n)
*/
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        // min-heap - Pair<event EndTime, event Value>
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getFirst));

        int maxVal = 0, maxSum = 0;

        for(int[] event : events) {
            // Poll all valid events from queue and take their maximum.
            while(!pq.isEmpty() && pq.peek().getFirst() < event[0]) {
                int val = pq.poll().getSecond();
                maxVal = Math.max(val, maxVal);
            }

            maxSum = Math.max(maxSum, maxVal + event[2]);
            pq.offer(Pair.of(event[1], event[2]));
        }

        return maxSum;
    }

/*
    Approach#1: Top-down Dynamic Programming

        Sorting: O(nlogn)
        Binary Search: O(logn)
        Memoization Ensure iterate each event once: (n)
    Overall Time: O(n * logn)

        Memoization: O(n)
        Recursive stack: O(n)
    Overall Space: O(n)
*/
    public int maxTwoEvents1(int[][] events) {
        return new MaximumNumberOfEventsThatCanBeAttendedII()
                .maxValue(events, 2);
    }

}
