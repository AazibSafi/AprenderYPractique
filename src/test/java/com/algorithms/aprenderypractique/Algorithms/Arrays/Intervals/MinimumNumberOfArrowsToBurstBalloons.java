package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons
 *
 */
public class MinimumNumberOfArrowsToBurstBalloons extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
        Assert.assertEquals(4, findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}}));
        Assert.assertEquals(2, findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
    }

/*
    Time: O(NlogN)
    Space: O(N) -> Based on sorting
*/
    public int findMinArrowShots(int[][] points) {
        // Sort the balloons by the end position of the arrow
        Arrays.sort(points, (o1, o2) -> {
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an
            // integer overflow for very large or small values.
            if (o1[1] == o2[1]) return 0;
            if (o1[1] < o2[1]) return -1;
            return 1;
        });

        int arrows = 1;
        int lastBurstBallon_End = points[0][1];

        for(int[] point : points) {
            // if start of current balloon is greater than end of last burst balloon
            if(point[0] > lastBurstBallon_End) {
                arrows++;   // One more arrow is needed to burst current balloon
                lastBurstBallon_End = point[1]; // track the last burst balloon end coordinate
            }
        }

        return arrows;
    }

}
