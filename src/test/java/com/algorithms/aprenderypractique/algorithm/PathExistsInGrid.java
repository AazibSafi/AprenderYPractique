package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Given a grid, entry at the top left corner, exit at the bottom right corner.
 *  Find if there is a path from entry to exit or not. Return true or false.
 *
 *  Related
 *  https://nlogn.in/find-if-a-path-is-possible-for-reaching-bottom-right-from-the-top-left-in-a-nxm-grid/
 */
public class PathExistsInGrid extends BaseTest {

    @Test
    public void test() {
        int[][] grid = {
                {1,1,1,1,1},
                {0,0,0,0,1},
                {1,1,1,1,1},
                {1,0,0,0,0},
                {1,1,1,1,1},
        };
        Assert. assertTrue(isPathPossible(grid));

        grid = new int[][]{
                {1,1,1,1,1},
                {0,0,0,0,1},
                {1,1,1,1,1},
                {0,0,0,0,0},
                {1,1,1,1,1},
        };
        Assert. assertFalse(isPathPossible(grid));

        grid = new int[][] {
                {1,1,0},
                {0,1,0},
                {0,1,1},
        };
        Assert.assertTrue(isPathPossible(grid));

        grid = new int[][] {
                {0,1,0},
                {0,1,0},
                {0,1,1},
        };
        Assert.assertFalse(isPathPossible(grid));

        grid = new int[][] {
                {1,1},
                {0,1},
        };
        Assert.assertTrue(isPathPossible(grid));
    }

    int[][] directions = new int[][] {
                {-1,0},     // UP
                {1,0},      // DOWN
                {0,-1},     // LEFT
                {0,1}       // RIGHT
            };

    public boolean isPathPossible(int[][] grid) {
        // Starting from 1st position with the value 1
        return grid[0][0] == 1 && traverse(grid,0,0);
    }

    boolean traverse(int[][] grid, int i, int j) {
        if(withInBoundry(grid,i,j) && grid[i][j] == 1) {

            if(isLastElement(grid,i,j))    return true;

            grid[i][j] = -1;    // Mark Visited - To avoid re-visit in the recursion

            for (int[] dir : directions) {
                if( traverse(grid, i + dir[0], j + dir[1]) ) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean isLastElement(int[][] grid, int i, int j) {
        return i==grid.length-1 && j==grid[0].length-1;
    }

    boolean withInBoundry(int[][] grid, int i, int j) {
        return i>=0 && i<grid.length && j>=0 && j<grid[0].length;
    }

}
