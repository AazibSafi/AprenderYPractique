package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MeetingRoom;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/meeting-rooms-ii
 *  https://leetcode.ca/2016-08-09-253-Meeting-Rooms-II
 *
 */
public class MeetingRoomsII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, minMeetingRooms(new int[][]{{7,10},{2,4}}));
        Assert.assertEquals(2, minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        Assert.assertEquals(1, minMeetingRooms(new int[][]{{13,15},{1,13}}));
    }

/*
    Time: O(N + T) --> O(N)
    (T) is the fixed size of the delta array
    Space: O(T) --> Constant O(1)

    Line Sweep Algorithm
 */
    public int minMeetingRooms(int[][] intervals) {
        int[] arr = new int[10000000];      // 10^6

        for(int[] interval : intervals) {
            ++arr[interval[0]];
            --arr[interval[1]];
        }

        int sum=0, max=0;
        for(int x : arr) {
            sum += x;
            max = Math.max(max, sum);
        }
        return max;
    }

}


