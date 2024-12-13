package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix.SeachInMatrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/search-a-2d-matrix
 *
 *      Four Solutions of this problem;
 *      1- Linear Scan the whole 2D array | Time: O(m * n) --> Brute Force
 *      2- Binary search on each row | Time: O(m * log n)
 *      3- Start from Upper right corner |  Time: O(m + n)
 *      4- Leverage the fact that the matrix is sorted both row-wise and column-wise. (The first integer of each row is greater than the last integer of the previous row)
 *          Treat 2-D matrix as 1-D array and apply binary search | Time: O(log(m * n))
 */
public class Search2DMatrix extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
        Assert.assertFalse(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13));
        Assert.assertTrue(searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
        Assert.assertFalse(searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 20));
    }

/*
    Time: O(log(m * n))
    Space: O(1)
    Most Efficient
    https://leetcode.com/problems/search-a-2d-matrix/solutions/3874071/100-binary-search-video-simple-solution
 */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n -1;

        while(left <= right) {
            int mid = left + (right - left)/2;  // To avoid overflow of Integer from :: (left+right)/2
            int row = mid/n;
            int col = mid%n;

            if(matrix[row][col] == target)
                return true;

            if(matrix[row][col] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }

/*
    Time: O(m + n)
    Space: O(1)
    Logic: https://www.educative.io/search-matrix
 */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int i=0, j=n-1;

        while(i<m && j>=0) {
            if(target == matrix[i][j])
                return true;

            if(target < matrix[i][j])   j--;    // Go left
            else if(target > matrix[i][j]) i++; // Go down
        }
        return false;
    }

}
