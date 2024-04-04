package com.algorithms.aprenderypractique.algorithm.arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see TransposeMatrix
 *
 * Logic: https://www.youtube.com/watch?v=J-Ihez5cwCM&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 * Logic from Transpose part of the problem
 */
public class TransposeMatrix_Square extends BaseTest {

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
    }

/**
 * Time: O(n^2)
 * Space: O(1) in-place Space Logic
 *
 * Matrix must be square (same size of row and col)
 */
    public int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                swap(matrix, i, j);
            }
        }
        return matrix;  // No need to return the same matrix - just returning bcz of test cases to assert
    }

    public void swap(int[][] matrix, int row, int col) {
        int temp = matrix[row][col];
        matrix[row][col] = matrix[col][row];
        matrix[col][row] = temp;
    }

}
