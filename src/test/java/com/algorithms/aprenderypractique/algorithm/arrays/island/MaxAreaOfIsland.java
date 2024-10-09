package com.algorithms.aprenderypractique.algorithm.arrays.island;

/**
 *  https://leetcode.com/problems/max-area-of-island/
 *  https://www.youtube.com/watch?v=JP39wU1UhRs&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=6
 */
public class MaxAreaOfIsland {

/*
    Time: O(m*n)
    Space: O(m*n)
    In the worst case, we will have a recursion depth of N*M in the case where we have all 1's in the grid.
 */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, getArea(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    int getArea(int[][] grid, int i, int j) {
        if(!isSafe(grid, i, j) || grid[i][j] != 1) return 0;

        grid[i][j] = 0; // visited

        return 1 + getArea(grid, i-1, j) + getArea(grid, i+1, j)
                + getArea(grid, i, j-1) + getArea(grid, i, j+1);
    }

    boolean isSafe(int[][] grid, int i, int j) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length;
    }
}
