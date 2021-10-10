package com.algorithms.aprenderypractique.algorithm.arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/insert-interval/
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

/*
    We can insert the newInterval at any place in the intervals
    Then this will become Merge Interval Problem
 */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] insertedInterval = new int[intervals.length+1][];

        insertedInterval[0] = newInterval;
        for(int i=1; i<insertedInterval.length; i++) {
            insertedInterval[i] = intervals[i-1];
        }

        return new MergeOverlapIntervels().merge(insertedInterval);
    }

}
