package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *      https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination
 */
public class ShortestPathInAGridWithObstaclesElimination {

/*
    Time: O(N.K)
    Space: O(N.k)

    Let N be the number of cells in the grid, and K be the quota to eliminate obstacles.
*/

    // Possible moves: right, left, up, down
    int[][] adjacents = {{0,1},{0,-1},{1,0},{-1,0}};

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        // if we have sufficient quotas to eliminate the obstacles in the worst case,
        // then the shortest distance is the Manhattan distance.
        if(k >= m + n - 2) {
            return m + n - 2;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0,0});

        int[][] visited = new int[m][n];
        Arrays.stream(visited).forEach(i -> Arrays.fill(i, Integer.MAX_VALUE));
        visited[0][0] = 0;

        int dist = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                int[] cell = queue.poll();

                if(cell[0] == m-1 && cell[1] == n-1) {
                    return dist;
                }

                for (int[] adj : adjacents) {
                    int newX = cell[0] + adj[0];
                    int newY = cell[1] + adj[1];

                    // 1). continue if out of bound
                    if(!isValidBoundry(grid, newX, newY))   continue;

                    // 2). continue if out of obstacles elimination
                    int newK = cell[2] + grid[newX][newY];  // cell value 0 <-> 1
                    if(newK > k)    continue;

                    // 3). continue if we have more optimal result
                    if(visited[newX][newY] <= newK)     continue;

                    visited[newX][newY] = newK;
                    queue.add(new int[]{newX, newY, newK});
                }
            }
            dist++;
        }
        return -1;
    }

    boolean isValidBoundry(int[][] grid, int i, int j) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length;
    }

}