package com.algorithms.aprenderypractique.algorithm.arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/insert-interval/
 *
 *  Todo: Look for another solution where sorting is not required. Since the input is already sorted
 */
public class InsertInterval extends BaseTest {

    @Test
    public void test() {
        int[][] intervals = new int[][]{{1,3},{6,9}};
        Assert.assertArrayEquals(new int[][]{{1,5},{6,9}}, insert(intervals, new int[]{2,5}));

        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        Assert.assertArrayEquals(new int[][]{{1,2},{3,10},{12,16}}, insert(intervals, new int[]{4,8}));

        intervals = new int[][]{};
        Assert.assertArrayEquals(new int[][]{{5,7}}, insert(intervals, new int[]{5,7}));

        intervals = new int[][]{{1,5}};
        Assert.assertArrayEquals(new int[][]{{1,5}}, insert(intervals, new int[]{2,3}));

        intervals = new int[][]{{1,5}};
        Assert.assertArrayEquals(new int[][]{{1,7}}, insert(intervals, new int[]{2,7}));
    }

/**
    We can insert the newInterval at any place in the intervals
    Then this will become Merge Interval Problem
    @see Union_MergeOverlapIntervels
 **/
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n=intervals.length;
        int[][] temp = new int[n+1][];

        for(int i=0; i<n; i++) temp[i] = intervals[i];

        temp[n] = newInterval;
        return new Union_MergeOverlapIntervels().merge(temp);
    }

}
