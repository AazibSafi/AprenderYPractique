package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MaxNoOfEvents;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *      https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended
 *      https://www.youtube.com/watch?v=dTVB1W7-BvY&t=255s
 *      https://www.youtube.com/watch?v=JrSeHtfhxog
 *
 *      Asked at Snowflake
 *
 *      Similar
 *      https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii
 *      https://leetcode.com/problems/maximum-earnings-from-taxi
 */
public class MaximumNumberOfEventsThatCanBeAttended extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, maxEvents(new int[][]{{1,2},{2,3},{3,4}}));
        Assert.assertEquals(4, maxEvents(new int[][]{{1,2},{2,3},{3,4},{1,2}}));
        Assert.assertEquals(4, maxEvents(new int[][]{{1,4}, {4,4}, {2,2}, {3,4}, {1,1}}));
    }

    public int maxEvents(int[][] events) {
        int n = events.length;

        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));    // Sort events based on the starting day

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // Priority queue to keep track of the end days of events

        int eventsAttended = 0;

        for(int day=0, eventsIdx=0; !pq.isEmpty() || eventsIdx<n; day++) {
            if(pq.isEmpty()) {
                day = events[eventsIdx][0]; // Move to the next available day
            }

// Add all events that start on or before the current day to the priority queue
            while(eventsIdx<n && events[eventsIdx][0] <= day) {
                pq.offer(events[eventsIdx][1]);
                eventsIdx++;
            }

// Attend the event that ends the earliest (the top of the priority queue)
            pq.poll();
            eventsAttended++;

// Remove all events from the priority queue that end before the current day
            while(!pq.isEmpty() && pq.peek() <= day) {
                pq.poll();
            }
        }

        return eventsAttended;
    }

}
