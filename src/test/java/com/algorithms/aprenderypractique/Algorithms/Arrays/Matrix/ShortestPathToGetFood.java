package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *      https://leetcode.com/problems/shortest-path-to-get-food
 */
public class ShortestPathToGetFood {

/*
    Time: O(m*n)
    Space: O(m*n)
*/
    // Possible moves: right, left, up, down
    int[][] adjacents = {{0,1},{0,-1},{1,0},{-1,0}};

    public int getFood(char[][] grid) {
        int[] startLoc = findStartLocation(grid);

        // Although the grid will always have exactly one starting location, so this condition will never gets true
        if(startLoc[0]==-1 || startLoc[1]==-1)
            return -1;

        // BFS queue for level-by-level traversal
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(startLoc);
        int steps = 0;

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            steps++;    // Each level is one step in the path

            // Process all cells at current level
            for(int i=0; i<levelSize; i++) {
                int[] cell = queue.poll();

                for(int[] adj : adjacents) {
                    int row = cell[0] + adj[0], col = cell[1] + adj[1];

                    if(isValid(grid, row, col)) {

                        if(grid[row][col] == '#') // Found Food
                            return steps;

                        grid[row][col] = 'X';   // Mark visited as Obstacle
                        queue.add(new int[]{ row, col });
                    }
                }
            }
        }
        return -1;
    }

    // Find starting position marked as '*'
    int[] findStartLocation(char[][] grid) {
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == '*')
                    return new int[]{i, j};
            }
        }
        return new int[]{-1,-1};
    }

    boolean isValid(char[][] grid, int i, int j) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length
                && grid[i][j] != 'X';
    }
}