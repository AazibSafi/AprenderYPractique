package com.algorithms.aprenderypractique.algorithm.arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/rotate-image/
 *  https://www.youtube.com/watch?v=J-Ihez5cwCM&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 *
 * https://dev.to/rohithv07/leetcode-48-rotate-image-4kmo
 * https://yuminlee2.medium.com/leetcode-48-rotate-image-973d0e85ffc6
 */
public class RotateImage extends BaseTest {

    @Test
    public void solution() {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int[][] Tmatrix = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        rotate(matrix);
        Assert.assertArrayEquals(Tmatrix, matrix);

        int[][] matrix1 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        int[][] Tmatrix1 = {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        };
        rotate(matrix1);
        Assert.assertArrayEquals(Tmatrix1, matrix1);
    }

/**
 * Time: O(n^2)
 * Space: O(1)
 */
    public int[][] rotate(int[][] matrix) {
        transpose(matrix);
        reverse(matrix);
        return matrix;
    }

    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    public void reverse(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n/2; j++) {
                swap(matrix, i, j, i, n-1-j);   // Flip Horizontally
            }
        }
    }

    public void swap(int[][] matrix, int i, int j, int a, int b) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[a][b];
        matrix[a][b] = temp;
    }

}
