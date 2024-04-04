package com.algorithms.aprenderypractique.algorithm.arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see RotateImage
 * https://dev.to/rohithv07/leetcode-48-rotate-image-4kmo
 * https://yuminlee2.medium.com/leetcode-48-rotate-image-973d0e85ffc6
 */
public class RotateImage_ByDegree extends BaseTest {

    @Test
    public void test() throws Exception {
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
        Assert.assertArrayEquals(Tmatrix, rotateImage(matrix, 90));

        int[][] matrix2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        int[][] Tmatrix2 = {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        };
        Assert.assertArrayEquals(Tmatrix2, rotateImage(matrix2, 90));
    }

/** Solutions 1: Optimized Solution
 *  Time: O(r*n^2)
 *  Space: O(1)
 */
    int[][] rotateImage(int[][] arr, int degree) throws Exception {
        if(degree%90 != 0) {
            throw new Exception("Incorrect Rotation Degree. Degrees Must be multiple of 90");
        }

        int rotations = degree/90;
        for(int r=0; r<rotations; r++) {
            new RotateImage().rotate(arr);
        }
        return arr;
    }

/** Solutions 2:
 *  Time: O(r*n^2)
 *  Space: O(n^2)
 */
    int[][] rotateImage2(int[][] arr, int degree) throws Exception {
        if(degree%90 != 0) {
            throw new Exception("Incorrect Rotation Degree. Degrees Must be multiple of 90");
        }

        int rotations = degree/90;
        for(int r=0; r<rotations; r++) {
            arr = transformMatrix(arr);
        }
        return arr;
    }

/**
 *  Time: O(n^2)
 *  Space: O(n^2)
 */
    int[][] transformMatrix(int[][] arr) {
        int n = arr.length;
        int[][] matrix = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                matrix[j][n-i-1] = arr[i][j];
            }
        }
        return matrix;
    }

}
