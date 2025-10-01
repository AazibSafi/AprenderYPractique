package com.algorithms.aprenderypractique.InterviewDB.Pinterest.Engagement;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/discuss/post/5542874/pinterest-screening-interview-mle-july-2-4xtf
 * @see com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MeetingRoom.MeetingRooms
 *
 * Pinterest.md Problem
 */
public class EngagementIntervals_Pins extends BaseTest {

    @Test
    public void test() {}

    // This is O(N log N) due to sorting, where N = number of intervals.
    public static List<int[]> engagementIntervals(int[][] pins) {
        List<int[]> sessions = new ArrayList<>();
        for(int[] pin : pins) {
            sessions.add(new int[]{pin[0], 1});
            sessions.add(new int[]{pin[1], -1});
        }

        sessions.sort((p1, p2) -> p1[0]==p2[0] ? p1[1]-p2[1] : p1[0]-p2[0]);

        List<int[]> result = new ArrayList<>();
        int active = 0;
        Integer prevTime = null;

        for(int[] session : sessions) {
            int time = session[0];
            int delta = session[1];

            if(prevTime != null && prevTime != time && active > 0) {
                if(!result.isEmpty() && result.getLast()[2] == active)
                    result.getLast()[1] = time;
                else
                    result.add(new int[]{prevTime, time, active});
            }

            active += delta;
            prevTime = time;

        }

        return result;
    }

}
