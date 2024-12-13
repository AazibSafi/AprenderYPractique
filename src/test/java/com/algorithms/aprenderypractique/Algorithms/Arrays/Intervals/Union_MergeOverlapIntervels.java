package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *  https://leetcode.com/problems/merge-intervals/
 *  https://www.youtube.com/watch?v=qKczfGUrFY4&t=564s
 *
 *  https://leetcode.com/discuss/interview-question/338948/Facebook-or-Onsite-or-Schedule-of-Tasks
 *
 *  Merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *  In Other Words: Union of Interval
 */
public class Union_MergeOverlapIntervels extends BaseTest {

    @Test
    public void test() {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        Assert.assertArrayEquals(new int[][]{{1,6},{8,10},{15,18}}, merge(intervals));

        intervals = new int[][]{{1,4},{4,5}};
        Assert.assertArrayEquals(new int[][]{{1,5}}, merge(intervals));

        intervals = new int[][]{{1,3}};
        Assert.assertArrayEquals(new int[][]{{1,3}}, merge(intervals));
    }

/*
    Time: O(nLogn)
    Space: O(Logn) or O(n)  => Sorting itself takes O(Logn) space depends on sorting implementation
 */
    public int[][] merge2(int[][] intervals) {
        if(intervals.length <= 1)   return intervals;

//  if the input intervals are not already sorted
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0])); // O(nlogn)

        List<int[]> output = new ArrayList<>();
        int[] currentInterval = intervals[0];
        output.add(currentInterval);

        for(int[] interval : intervals) {               // O(n)
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if(currentEnd >= nextStart) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            }
            else {
                currentInterval = interval;
                output.add(currentInterval);
            }
        }

        return output.toArray(new int[output.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)   return intervals;

//  if the input intervals are not already sorted
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));

        List<int[]> output = new ArrayList<>();
        int[] prevInterval = intervals[0];

        for(int[] interval : intervals) {
            int prevStart = prevInterval[0];
            int prevEnd = prevInterval[1];
            int currStart = interval[0];
            int currEnd = interval[1];

            if(prevEnd >= currStart) {
                prevInterval[0] = Math.min(prevStart, currStart);   // Since the array is already sort by startTime, this will always give prevStart as min. Therefore this statement is not required.
                prevInterval[1] = Math.max(prevEnd, currEnd);
            }
            else {
                output.add(prevInterval);
                prevInterval = interval;
            }
        }

        output.add(prevInterval);
        return output.toArray(new int[output.size()][]);
    }

}
