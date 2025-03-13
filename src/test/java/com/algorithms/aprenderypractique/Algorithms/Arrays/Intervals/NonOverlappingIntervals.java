package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  https://leetcode.com/problems/non-overlapping-intervals
 *  https://leetcode.com/problems/non-overlapping-intervals/solutions/5817029/video-sorting-solution/?envType=problem-list-v2&envId=xi4ci4ig
 */
public class NonOverlappingIntervals extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
        Assert.assertEquals(2, eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}));
        Assert.assertEquals(0, eraseOverlapIntervals(new int[][]{{1,2},{2,3}}));
    }

/*
    Time: O(nLongN)
    Space: O(1)
 */
    public int eraseOverlapIntervals(int[][] intervals) {
        int remove = 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));   // O(nlogN)
        int prevEnd = intervals[0][1];

        for(int i=1; i<intervals.length; i++) {   // O(n)
            if(prevEnd > intervals[i][0])
                remove++;
            else
                prevEnd = intervals[i][1];
        }

        return remove;
    }

}
