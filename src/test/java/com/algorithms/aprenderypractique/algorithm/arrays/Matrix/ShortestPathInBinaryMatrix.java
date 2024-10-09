package com.algorithms.aprenderypractique.algorithm.arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *          https://leetcode.com/problems/shortest-path-in-binary-matrix
 *          https://leetcode.com/problems/shortest-path-in-binary-matrix/solutions/2043319/why-use-bfs-search-every-possible-path-vs-search-a-possible-path
 */
public class ShortestPathInBinaryMatrix extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}));
        Assert.assertEquals(4, shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
        Assert.assertEquals(-1, shortestPathBinaryMatrix(new int[][]{{1,0,0},{1,1,0},{1,1,0}}));
        Assert.assertEquals(25, shortestPathBinaryMatrix(new int[][]{{0,1,1,1,1,1,1,1},{0,1,1,0,0,0,0,0},{0,1,0,1,1,1,1,0},{0,1,0,1,1,1,1,0},{0,1,1,0,0,1,1,0},{0,1,1,1,1,0,1,0},{0,0,0,0,0,1,1,0},{1,1,1,1,1,1,1,0}}));
    }

/*
    Time: O(n^2)
    Space: O(n^2)
*/
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(n==0 ||grid[0][0] == 1 || grid[n-1][n-1] == 1)      return -1;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        int path = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            path++;

            for(int i=0; i<size; i++) {
                int[] pos = queue.poll();

                if(pos[0] == n-1 && pos[1] == n-1)  return path;

                for(int[] move : directions) {
                    int x = pos[0]+move[0];
                    int y = pos[1]+move[1];

                    if(isSafe(grid, x, y) && !visited[x][y] && grid[x][y] == 0) {
                        queue.offer(new int[]{x,y});
                        visited[x][y] = true;
                    }
                }
            }
        }
        return -1;
    }

    boolean isSafe(int[][] grid, int i, int j) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length;
    }

    int[][] directions = new int[][] {
            {-1,0},     // North
            {1,0},      // South
            {0,-1},     // West
            {0,1},      // East
            {-1,-1},    // NW
            {-1,1},     // NE
            {1,-1},     // SW
            {1,1}       // SE
    };

}
