package com.algorithms.aprenderypractique.algorithm.arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *      https://leetcode.ca/2016-09-21-296-Best-Meeting-Point
 *      https://algo.monster/liteproblems/296
 *      https://prepfortech.io/leetcode-solutions/best-meeting-point
 *
 *      Logic Explained: https://www.youtube.com/watch?v=KfH51O3l2EM
 *
 *      Distance formula: https://miro.medium.com/v2/resize:fit:1400/format:webp/1*CCZt1t82V15ITnGzQH6qOg.png
 *
 *      Similar TODO: https://leetcode.ca/2016-10-12-317-Shortest-Distance-from-All-Buildings
 */
public class BestMeetingPoint extends BaseTest {

    @Test
    public void test() {
        int[][] grid = new int[][] {
                {1,0,0,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        Assert.assertEquals(6, minTotalDistance(grid));

        grid = new int[][] {
                {1,0,0},
                {0,1,0},
                {0,0,1}
        };
        Assert.assertEquals(4, minTotalDistance(grid));

        grid = new int[][] {{1,1}};
        Assert.assertEquals(1, minTotalDistance(grid));
    }

/*
    Time: O(mn * log(mn))
    Space: O(m + n)
*/
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // get all row and column indices of 1's (houses) in the grid
        List<Integer> houseXCord = new ArrayList<>();
        List<Integer> houseYCord = new ArrayList<>();
        for (int x = 0; x < grid.length; x++) {             // O(m*n)
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 1) {      // House Found
                    houseXCord.add(x);
                    houseYCord.add(y);
                }
            }
        }

        // Collections.sort(houseXCord);     // Rows are already sorted from above loop
        Collections.sort(houseYCord);       // O(log(mn))

        // Find the median of the coordinates, which will be our meeting point
        int medianRow = houseXCord.get(houseXCord.size() / 2);
        int medianCol = houseYCord.get(houseYCord.size() / 2);

        // calculate the sum of distances from median point to all other points
        int sum = 0;
        for (int i = 0; i < houseXCord.size(); i++) {
            sum += distance(houseXCord.get(i), houseYCord.get(i), medianRow, medianCol);
        }
        return sum;
    }

    // Manhattan distances
    int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
