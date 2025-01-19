package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/find-a-peak-element-ii
 *      https://leetcode.com/problems/find-a-peak-element-ii/solutions/1744470/runtime-0-ms-faster-than-100-00-of-java-online-submissions
 */
public class FindPeakElementII extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{0,1}, findPeakGrid(new int[][]{{1,4},{3,2}}));
        Assert.assertArrayEquals(new int[]{1,1}, findPeakGrid(new int[][]{{10,20,15},{21,30,14},{7,16,32}}));
        Assert.assertArrayEquals(new int[]{1,0}, findPeakGrid(new int[][]{{70,50,40,30,20},{100,1,2,3,4}}));
    }

/*
    Approach#2 - Binary Search
    Time: O(n*log(m))
    Space: O(1)
*/
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int topRow = 0, bottomRow = m-1;

        while(topRow < bottomRow) {                             // O(logm)
            int midRow = (topRow + bottomRow) / 2;

            // Finding the column number which has max element in midRow
            int currPeak = findIndexOfMaxElement(mat[midRow]);  // O(n)

            // Note: Since it is max element, no need to compare left and right neighbour,
            // We need to compare it with top and bottom neighbour
            boolean topNeighbor = midRow-1<0 || (mat[midRow][currPeak] > mat[midRow-1][currPeak]);
            boolean bottomNeighbor = midRow+1>=m || (mat[midRow][currPeak] > mat[midRow+1][currPeak]);
            if(topNeighbor && bottomNeighbor)
                return new int[]{midRow, currPeak};

            if(mat[midRow][currPeak] < mat[midRow+1][currPeak])
                topRow = midRow+1;
            else
                bottomRow = midRow;
        }

        // Here topRow == bottomRow, so this would be the peek
        return new int[]{topRow, findIndexOfMaxElement(mat[topRow])};
    }

/*
    Approach#1 - Iterate whole Matrix
    Time: O(m*n)
    Space: O(1)
*/
    public int[] findPeakGrid1(int[][] mat) {
        int m = mat.length;
        for(int row=0; row<m; row++) {              // O(m)
            int peakIndex = findIndexOfMaxElement(mat[row]);      // O(n)
            boolean topNeighbor = row-1<0 || (mat[row][peakIndex] > mat[row-1][peakIndex]);
            boolean bottomNeighbor = row+1>=m || (mat[row][peakIndex] > mat[row+1][peakIndex]);

            if(topNeighbor && bottomNeighbor)
                return new int[]{row, peakIndex};
        }
        return new int[]{-1,-1};
    }

    //  Finding the maximum element position in 1-D array
    public int findIndexOfMaxElement(int[] nums) {
        int iMax = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > nums[iMax])
                iMax = i;
        }
        return iMax;
    }

}
