package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Arrays;

/**
 *      https://leetcode.com/problems/maximum-number-of-points-with-cost
 *      https://www.youtube.com/watch?v=IuAlYxhrx-0
 */
public class MaximumNumberOfPointsWithCost extends BaseTest {

/*
    Time: O(m * n)
    Space: O(n)
 */
    public long maxPoints(int[][] points) {
        int ROW = points.length, COL = points[0].length;

        long[] prevRow = new long[COL];
        for(int i=0; i<COL; i++) {
            prevRow[i] = points[0][i];
        }

        for(int i=1; i<ROW; i++) {

            long[] currRow = new long[COL];

            long left = 0;
            for(int j=0; j<COL; j++) {
                left = Math.max(left-1, prevRow[j]);
                currRow[j] = left + points[i][j];
            }

            long right = 0;
            for(int j=COL-1; j>=0; j--) {
                right = Math.max(right-1, prevRow[j]);
                currRow[j] = Math.max(currRow[j], right + points[i][j]);
            }

            prevRow = currRow;
        }
        return Arrays.stream(prevRow).max().getAsLong();
    }

}
