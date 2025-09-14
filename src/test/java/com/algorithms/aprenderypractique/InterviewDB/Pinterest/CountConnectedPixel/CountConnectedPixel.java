package com.algorithms.aprenderypractique.InterviewDB.Pinterest.CountConnectedPixel;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  Pinterest Problem
 */
public class CountConnectedPixel extends BaseTest {

    @Test
    public void test() {
        Pixel[][] grid = new Pixel[5][5];

        // Example grid setup
        grid[0][0] = new Pixel(0,0);
        grid[0][1] = new Pixel(0,1);
        grid[2][2] = new Pixel(2,2);
        grid[3][2] = new Pixel(3,2);
        grid[4][4] = new Pixel(4,4);

        int result = countConnectedPixel(grid);
        System.out.println("Number of connected objects = " + result);
    }

    int[][] adjacent = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    /*
        Time: O(m * n)
        Space: O(m * n)
     */
    public int countConnectedPixel(Pixel[][] grid) {
        int count = 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if (grid[i][j] != null && !visited[i][j]) {
                    travelConnectedObjects(grid, i, j, visited);
                    count++;
                }
            }
        }
         return count;
    }

    // DFS
    void travelConnectedObjects(Pixel[][] grid, int i, int j, boolean[][] visited) {
        if(!isValid(grid, i, j)) return;
        if(visited[i][j]) return;

        visited[i][j] = true;

        for(int[] adj : adjacent) {
            int row = i + adj[0], col = j + adj[1];
            if (isSameObject(grid, grid[i][j], grid[row][col])) {
                travelConnectedObjects(grid, row, col, visited);
            }
        }
    }

    boolean isValid(Pixel[][] grid, int i, int j) {
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length;
    }

    // Mock API - in real interview this is provided
    public boolean isSameObject(Pixel[][] grid, Pixel p1, Pixel p2) {
        return true; // Assume implementation
    }

    class Pixel {
        int x, y;

        // Constructor for Pixel
        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
