package com.algorithms.aprenderypractique.algorithm.arrays.Matrix.Spiral;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *  https://leetcode.com/problems/spiral-matrix-ii/
 *  https://www.youtube.com/watch?v=1ZGJzvkcLsA
 *
 *  Here i have implemented m*n version instead of n^2
 */
public class SpiralMatrix_II extends BaseTest {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1,2,3},{8,9,4},{7,6,5}};
        Assert.assertArrayEquals(matrix, spiralOrder(3,3));

        matrix = new int[][]{{1}};
        Assert.assertArrayEquals(matrix, spiralOrder(1,1));
    }

    public int[][] spiralOrder(int m, int n) {
        int[][] A = new int[m][n];
        int x = 1;

        int top = 0;    int down = m-1;   int left = 0;   int right = n-1;
        int dir = 0;

        while(left <= right && top <= down) {
            if(dir == 0) {
                for(int i=left; i<=right; i++) {
                    A[top][i] = x++;
                }
                top++;
            }
            if(dir == 1) {
                for(int i=top; i<=down; i++) {
                    A[i][right] = x++;
                }
                right--;
            }
            if(dir == 2) {
                for(int i=right; i>=left; i--) {
                    A[down][i] = x++;
                }
                down--;
            }
            if(dir == 3) {
                for(int i=down; i>=top; i--) {
                    A[i][left] = x++;
                }
                left++;
            }

            dir = (dir+1)%4;
        }
        return A;
    }

}
