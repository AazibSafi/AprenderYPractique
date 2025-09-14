package com.algorithms.aprenderypractique.InterviewDB.Pinterest.Engagement;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/discuss/post/5542874/pinterest-screening-interview-mle-july-2-4xtf
 *
 * @see com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MeetingRoom.MeetingRooms
 *
 * Pinterest Problem
 */
public class EngagementIntervals extends BaseTest {

    @Test
    public void test() {}

    // This is O(N log N) due to sorting, where N = number of intervals.
    public static List<String> engagementIntervals(int[][] intervals) {
        // Sort by start time if not equal
        Arrays.sort(intervals, (e1, e2) -> e1[0] == e2[0] ? e1[1]-e2[1] : e1[0]-e2[0]);

        List<int[]> events = new ArrayList<>();
        for(int[] interval : intervals) {
            events.add(new int[]{interval[0], 1});  // start -> engagement count goes up.
            events.add(new int[]{interval[1], -1}); // end -> (engagement count goes down.
        }

        List<String> result = new ArrayList<>();
        int active = 0;             // how many intervals are alive at this moment.
        Integer prevTime = null;

        // Sweep line
        for(int[] event : events) {
            int time = event[0];
            int delta = event[1];   // whether this event adds or removes from the active set.

            if(prevTime != null && prevTime != time && active > 0) {
                result.add("[" + prevTime + "," + time + "] -> " + active);
            }

            active += delta;
            prevTime = time;
        }

        return result;
    }

}
