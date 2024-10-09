package com.algorithms.aprenderypractique.algorithm.arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  https://leetcode.com/problems/meeting-scheduler
 *  https://algo.monster/liteproblems/1229
 *  https://hsiyinl.medium.com/meeting-scheduler-leetcode-1229-bce46775243e
 *
 * @see IntersectionsIntervals
 */
public class MeetingScheduler extends BaseTest {

    @Test
    public void test() {
        int[][] slot1 = new int[][]{{10,50},{60,120},{140,210}};
        int[][] slot2 = new int[][]{{0,15},{60,70}};
        Assert.assertArrayEquals(new int[]{60,68}, minAvailableDuration(slot1, slot2, 8));

        slot1 = new int[][]{{10,50},{60,120},{140,210}};
        slot2 = new int[][]{{0,15},{60,70}};
        Assert.assertArrayEquals(new int[]{}, minAvailableDuration(slot1, slot2, 12));
    }

/*
    Time: O(m log m + n log n + m + n) --> O(m log m + n log n)
    Space: O(m+n) --> because of sorting
 */
    public int[] minAvailableDuration(int[][] slot1, int[][] slot2, int duration) {
        Arrays.sort(slot1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slot2, Comparator.comparingInt(a -> a[0]));

        int i=0, j=0;
        while(i<slot1.length && j<slot2.length) {
            int s1 = slot1[i][0], e1 = slot1[i][1];
            int s2 = slot2[j][0], e2 = slot2[j][1];

            if(e2 >= s1 && s1 >= s2) {
                int start = Math.max(s1, s2);
                int end = Math.min(e1, e2);
                if(end - start >= duration) {
                    return new int[]{start, start+duration};
                }
            }
            if(e1 < e2)     i++;
            else            j++;
        }
        return new int[]{};
    }

}
