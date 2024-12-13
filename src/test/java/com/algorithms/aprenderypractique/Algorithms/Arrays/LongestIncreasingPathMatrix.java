package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 *  https://www.youtube.com/watch?v=uLjO2LUlLN4
 */
public class LongestIncreasingPathMatrix extends BaseTest {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        Assert.assertEquals(4, longestIncreasingPath(matrix));

        matrix = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        Assert.assertEquals(4, longestIncreasingPath(matrix));

        matrix = new int[][]{{1}};
        Assert.assertEquals(1, longestIncreasingPath(matrix));

        matrix = new int[][]{{7,7,5},{2,4,6},{8,2,0}};
        Assert.assertEquals(4, longestIncreasingPath(matrix));
    }

/*
    Time: O(N*M)
    Space: O(N*M)   bcz of Cache
 */
    int[][] cache;
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length, max=0;
        cache = new int[n][m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int longestPath = rec(matrix, i, j);
                max = Math.max(max, longestPath);
            }
        }
        return max;
    }

    int rec(int[][] matrix, int row, int col) {
        if(cache[row][col] != 0)      return cache[row][col];

        int max = 0;
        for(int[] dir : directions) {
            int x = dir[0]+row , y = dir[1]+col;
            if(isWithInBoundry(matrix, x, y) && matrix[x][y] > matrix[row][col]) {
                int longestPath = rec(matrix, x, y);
                max = Math.max(max, longestPath);
            }
        }

        cache[row][col] = max + 1;
        return cache[row][col];
    }

    boolean isWithInBoundry(int[][] matrix, int i, int j) {
        return i>=0 && i<matrix.length && j>=0 && j<matrix[0].length;
    }

    int[][] directions = new int[][] {
            {-1,0},     // UP
            {1,0},      // DOWN
            {0,-1},     // LEFT
            {0,1}       // RIGHT
    };

}
