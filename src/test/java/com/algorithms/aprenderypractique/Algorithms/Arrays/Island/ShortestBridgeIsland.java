package com.algorithms.aprenderypractique.Algorithms.Arrays.Island;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://leetcode.com/problems/shortest-bridge/
 *  https://www.youtube.com/watch?v=B64JXbZsTOE&t=193s&ab_channel=code_report
 *
 *  Flood Fill Technique
 *
 *  Note: Exactly Only Two islands exist in the Grid - No less - no more
 *
 *  Note: This is Brute Force Approach
 */
public class ShortestBridgeIsland  extends BaseTest {

    @Test
    public void test() {
        int[][] grid = {
                {0, 1},
                {1, 0}
            };
        Assert.assertEquals(1,shortestBridge(grid));

        grid = new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 1}
        };
        Assert.assertEquals(2,shortestBridge(grid));

        grid = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
        };
        Assert.assertEquals(1,shortestBridge(grid));

        grid = new int[][] {
                    {1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1},
                };
        Assert.assertEquals(7,shortestBridge(grid));
    }

/*
    loop through the grid
        find first 1
            if first Set is empty
                flood fill the island to A set if it is not -1
                store their coordinates into to a set
                mark the element as -1
            else
                flood fill the island to B set if it is not -1
                store their coordinates into to a set
                mark the element as -1
     Calculate Min Distance between A and B

     Time: O(n*m + A*B)
 */
    int shortestBridge(int[][] grid) {
        Set<Integer[]> A = new HashSet<>();
        Set<Integer[]> B = new HashSet<>();

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j] == 1) {
                    if(A.isEmpty()) {
                        floodFill(grid,i,j,A);
                    }
                    else if(B.isEmpty()) {
                        floodFill(grid,i,j,B);
                    }
                }
            }
        }

        return findMinDistance(A,B);
    }

    void floodFill(int[][] grid, int i, int j, Set<Integer[]> set) {
        if(withInBoundry(grid,i,j) && grid[i][j] == 1) {
            grid[i][j] = -1;
            set.add(new Integer[]{i,j});

            for (int[] dir : directions) {
                floodFill(grid, i+dir[0], j+dir[1], set);
            }
        }
    }

    int findMinDistance(Set<Integer[]> A, Set<Integer[]> B) {
        int minDistance = Integer.MAX_VALUE-1;
        for(Integer[] cordA : A) {
            for(Integer[] cordB : B) {
                minDistance = Math.min(minDistance, distanceBtwPoints(cordA, cordB));
            }
        }
        return minDistance;
    }

//  Manhattan Distance
    int distanceBtwPoints(Integer[] cordA, Integer[] cordB) {
        return Math.abs(cordA[0] - cordB[0]) + Math.abs(cordA[1] - cordB[1]) - 1;
        // subtracting 1 because one of the two points is already present in the grid
        // We need to get the distance excluding the two points itself
    }

    boolean withInBoundry(int[][] grid, int i, int j) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length;
    }

    int[][] directions = new int[][] {
            {-1,0},     // UP
            {1,0},      // DOWN
            {0,-1},     // LEFT
            {0,1}       // RIGHT
    };

}
