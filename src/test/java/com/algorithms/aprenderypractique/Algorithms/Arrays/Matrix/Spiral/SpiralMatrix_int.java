package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix.Spiral;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/spiral-matrix/
 *  Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 *  Same as;    Here i have used int[] as return type
 * @see SpiralMatrix
 */
public class SpiralMatrix_int extends BaseTest {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Assert.assertArrayEquals(new int[]{1,2,3,6,9,8,7,4,5}, spiralOrder(matrix));
    }

    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length, n= matrix[0].length;
        int[] result = new int[m*n];
        int x=0;

        int top = 0;    int down = m-1;   int left = 0;   int right = n-1;
        int dir = 0;

        while(left <= right && top <= down) {
            if(dir == 0) {
                for(int i=left; i<=right; i++) {
                    result[x++] = matrix[top][i];
                }
                top++;
            }
            if(dir == 1) {
                for(int i=top; i<=down; i++) {
                    result[x++] = matrix[i][right];
                }
                right--;
            }
            if(dir == 2) {
                for(int i=right; i>=left; i--) {
                    result[x++] = matrix[down][i];
                }
                down--;
            }
            if(dir == 3) {
                for(int i=down; i>=top; i--) {
                    result[x++] = matrix[i][left];
                }
                left++;
            }

            dir = (dir + 1) % 4;
        }
        return result;
    }

}
