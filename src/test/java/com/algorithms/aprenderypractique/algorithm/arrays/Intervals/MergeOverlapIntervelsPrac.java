package com.algorithms.aprenderypractique.algorithm.arrays.Intervals;

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
public class MergeOverlapIntervelsPrac extends BaseTest {

    @Test
    public void test() {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        Assert.assertArrayEquals(new int[][]{{1,6},{8,10},{15,18}}, merge(intervals));

        intervals = new int[][]{{1,4},{4,5}};
        Assert.assertArrayEquals(new int[][]{{1,5}}, merge(intervals));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> merge = new ArrayList<>();
        merge.add(intervals[0]);

        int ind=0;

        for(int i=1; i<intervals.length; i++) {
            if(intervals[i][0] <= merge.get(ind)[1]) {
                merge.get(ind)[1] = Math.max(intervals[i][1] , merge.get(ind)[1]);
            }
            else {
                merge.add(intervals[i]);
                ind++;
            }
        }
        return merge.toArray(new int[merge.size()][]);
    }

}
