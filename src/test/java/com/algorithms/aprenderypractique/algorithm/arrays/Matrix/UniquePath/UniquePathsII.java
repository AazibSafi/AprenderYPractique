package com.algorithms.aprenderypractique.algorithm.arrays.Matrix.UniquePath;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/unique-paths-ii/
 *
 *  Todo: https://leetcode.com/problems/dungeon-game
 */
public class UniquePathsII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        Assert.assertEquals(1, uniquePathsWithObstacles(new int[][]{{0,1},{0,0}}));
        Assert.assertEquals(0, uniquePathsWithObstacles(new int[][]{{1,0}}));
    }

/*
    Time: O(N*M)
    Space: O(N*M) + O(N*M) = O(N*M) -- Recursive Stack + 2D Array
 */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] mem = new int[m][n];
        for(int[] row : mem)
            Arrays.fill(row, -1);
        return paths(obstacleGrid, m-1, n-1, mem);
    }

    public int paths(int[][] obstacleGrid, int m, int n, int[][] mem) {
        if(m<0 || n<0 || obstacleGrid[m][n] == 1) return 0;
        if(m==0 && n==0) return 1;
        if(mem[m][n] != -1) return mem[m][n];

        return mem[m][n] = paths(obstacleGrid, m-1, n, mem) + paths(obstacleGrid, m, n-1, mem);
    }


/*
    Time: O(N*M)
    Space: O(N*M) -- if we take new 2D Array for output
    Space: O(1) -- if we use the same input 2D Array
 */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0) return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)  return 0;

        obstacleGrid[0][0] = 1;

        for(int row=1; row<m; row++) {      // Fill first row with 1 until it finds obstacle
            obstacleGrid[row][0] = (obstacleGrid[row][0] == 1) ? 0 : obstacleGrid[row-1][0];
        }
        for(int col=1; col<n; col++) {      // Fill first col with 1 until it finds obstacle
            obstacleGrid[0][col] = (obstacleGrid[0][col] == 1) ? 0 : obstacleGrid[0][col-1];
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j] == 1)      // if we have obstacle
                    obstacleGrid[i][j] = 0;
                else
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }

        return obstacleGrid[m-1][n-1];
    }

}
