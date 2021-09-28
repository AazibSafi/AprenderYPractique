package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *  https://leetcode.com/problems/merge-intervals/
 *  https://www.youtube.com/watch?v=qKczfGUrFY4&t=564s
 *
 *  Merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class MergeOverlapIntervels extends BaseTest {

    @Test
    public void test() {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        Assert.assertArrayEquals(new int[][]{{1,6},{8,10},{15,18}}, merge(intervals));

        intervals = new int[][]{{1,4},{4,5}};
        Assert.assertArrayEquals(new int[][]{{1,5}}, merge(intervals));
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)   return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));

        List<int[]> output = new ArrayList<>();
        int[] currentInterval = intervals[0];
        output.add(currentInterval);

        for(int[] interval : intervals) {
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if(currentEnd >= nextStart) {
                currentInterval[1] = Math.max(currentEnd,nextEnd);
            }
            else {
                currentInterval = interval;
                output.add(currentInterval);
            }
        }

        return output.toArray(new int[output.size()][]);
    }

}
