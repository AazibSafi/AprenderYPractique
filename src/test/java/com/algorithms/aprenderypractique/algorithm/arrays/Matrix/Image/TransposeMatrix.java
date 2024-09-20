package com.algorithms.aprenderypractique.algorithm.arrays.Matrix.Image;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/transpose-matrix
 */
public class TransposeMatrix extends BaseTest {

    @Test
    public void solution() {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
        int[][] Tmatrix = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };
        Assert.assertArrayEquals(Tmatrix, transpose(matrix));

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
        };
        int[][] Tmatrix1 = {
                {1, 4},
                {2, 5},
                {3, 6}
        };
        Assert.assertArrayEquals(Tmatrix1, transpose(matrix1));
    }

/**
 * Time: O(n*m)
 * Space: O(n*m)
 *
 * Matrix doesn't need to be square (same size of row and col)
 */
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] transposeMatrix = new int[columns][rows];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                transposeMatrix[j][i] = matrix[i][j];
            }
        }
        return transposeMatrix;
    }

}
