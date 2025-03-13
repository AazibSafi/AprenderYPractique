package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MaxNoOfEvents;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *      https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended
 *      https://www.youtube.com/watch?v=dTVB1W7-BvY&t=255s
 *      https://www.youtube.com/watch?v=JrSeHtfhxog
 *
 *      Asked at Snowflake
 */
public class MaximumNumberOfEventsThatCanBeAttended extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, maxEvents(new int[][]{{1,2},{2,3},{3,4}}));
        Assert.assertEquals(4, maxEvents(new int[][]{{1,2},{2,3},{3,4},{1,2}}));
        Assert.assertEquals(4, maxEvents(new int[][]{{1,4}, {4,4}, {2,2}, {3,4}, {1,1}}));
    }

/*
    Time: O(NlogN)
    Space: O(N)
*/
    public int maxEvents(int[][] events) {
        // Sort by startDay
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        // Priority queue to keep track of the end days of events
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = events.length;
        int eventIdx = 0, eventsAttended = 0;

        for(int day=1; day<=1e5; day++) {
            // Remove all events from the queue that end before the current day\
            while(!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            // Add all events to queue that start on the current day
            while(eventIdx<n && events[eventIdx][0] == day) {
                pq.offer(events[eventIdx++][1]);
            }

            // Attend the event that ends the earliest (the top of the priority queue)
            if(!pq.isEmpty()) {
                pq.poll();
                eventsAttended++;
            }
        }

        return eventsAttended;
    }

}
