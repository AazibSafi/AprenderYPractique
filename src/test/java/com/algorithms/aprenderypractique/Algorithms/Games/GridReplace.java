package com.algorithms.aprenderypractique.Algorithms.Games;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Given a 2D Array of numbers from 1-9, write a function that replaces any consecutive numbers of at least length 3, with 0s.
 *  Numbers may be consecutive both horizontally or vertically.
 *
 *  Sub problem of CandyCrash
 *      https://leetcode.ca/2017-11-22-723-Candy-Crush
 */
public class GridReplace extends BaseTest {

    @Test
    public void test() {
        int[][] grid = {
                {1, 1, 1, 2, 3},
                {4, 5, 6, 6, 6},
                {7, 8, 8, 9, 9},
                {7, 7, 7, 4, 2},
                {3, 3, 3, 2, 2}
            };
        int[][] output = {
                {0, 0, 0, 2, 3},
                {4, 5, 0, 0, 0},
                {7, 8, 8, 9, 9},
                {0, 0, 0, 4, 2},
                {0, 0, 0, 2, 2}
            };
        Assert.assertEquals(output, replaceConsecutiveWithZeros(grid));
    }

/*
    Time: O(rows×cols)
    Space: O(rows×cols)
 */
    public static int[][] replaceConsecutiveWithZeros(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Step 1: Create a marker grid to mark positions for replacement.
        boolean[][] toReplace = new boolean[rows][cols];

        // Step 2: Check for consecutive numbers horizontally.
        for (int i = 0; i < rows; i++) {
            int count = 1;
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] == grid[i][j - 1]) {
                    count++;
                } else {
                    if (count >= 3) {
                        // Mark the consecutive sequence for replacement.
                        for (int k = j - count; k < j; k++) {
                            toReplace[i][k] = true;
                        }
                    }
                    count = 1; // Reset count for a new sequence
                }
            }
            // Check at the end of the row for consecutive numbers.
            if (count >= 3) {
                for (int k = cols - count; k < cols; k++) {
                    toReplace[i][k] = true;
                }
            }
        }

        // Step 3: Check for consecutive numbers vertically.
        for (int j = 0; j < cols; j++) {
            int count = 1;
            for (int i = 1; i < rows; i++) {
                if (grid[i][j] == grid[i - 1][j]) {
                    count++;
                } else {
                    if (count >= 3) {
                        // Mark the consecutive sequence for replacement.
                        for (int k = i - count; k < i; k++) {
                            toReplace[k][j] = true;
                        }
                    }
                    count = 1; // Reset count for a new sequence
                }
            }
            // Check at the end of the column for consecutive numbers.
            if (count >= 3) {
                for (int k = rows - count; k < rows; k++) {
                    toReplace[k][j] = true;
                }
            }
        }

        // Step 4: Replace marked positions with 0s.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (toReplace[i][j]) {
                    grid[i][j] = 0;
                }
            }
        }
        return grid;
    }

}
