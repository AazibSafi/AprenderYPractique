package com.algorithms.aprenderypractique.algorithm.arrays.Intervals.MeetingRoom;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  https://leetcode.com/problems/meeting-rooms
 *  https://leetcode.ca/2016-08-08-252-Meeting-Rooms
 */
public class MeetingRooms extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(canAttendMeetings(new int[][]{{7,10},{2,4}}));
        Assert.assertFalse(canAttendMeetings(new int[][]{{7,10},{4,6},{2,5},{11,9}}));
        Assert.assertFalse(canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}}));
        Assert.assertFalse(canAttendMeetings(new int[][]{{1,5},{1,2},{15,20}}));
    }

// Time: O(nLogN)
    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        if(n == 0 || n == 1) return true;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // O(nlogN)

        for(int i=1; i<n; i++) {    // O(n)
            if(intervals[i-1][1] >= intervals[i][0])
                return false;
        }
        return true;
    }

}
