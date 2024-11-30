package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  https://leetcode.com/problems/minimum-time-difference
 *  https://leetcode.com/problems/minimum-time-difference/?envType=problem-list-v2&envId=7p55wqm
 *
 *  https://www.youtube.com/watch?v=6H2U69l1vfM
 */
public class MinimumTimeDifference extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, findMinDifference(List.of("23:59","00:00")));
        Assert.assertEquals(0, findMinDifference(List.of("00:00","23:59","00:00")));
        Assert.assertEquals(1, findMinDifference(List.of("12:12","12:13")));
        Assert.assertEquals(719, findMinDifference(List.of("12:12","00:13")));
    }
    int ret() {
        return 3;
    }

/*
    Time: O(NLogN)
    Space: O(N)
*/
    public int findMinDifference(List<String> timePoints) {
        int[] timestamp = new int[timePoints.size()];
        int idx = 0;

        for(String time : timePoints) {
            String[] split = time.split(":");
            timestamp[idx++] = Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
        }

        Arrays.sort(timestamp);

        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<timestamp.length-1; i++) {
            minDiff = Math.min(minDiff, timestamp[i+1] - timestamp[i]);
        }

        // Edge Case: Diff between last and first time for circle nature
        return Math.min(
                minDiff,
                24*60 - timestamp[timestamp.length-1] + timestamp[0]
        );
    }

}
