package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix.Spiral;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/spiral-matrix/
 *  Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class SpiralMatrix extends BaseTest {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(1,2,3,6,9,8,7,4,5), spiralOrder(matrix)));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n= matrix[0].length;
        List<Integer> result = new ArrayList<>();

        int top = 0;    int down = m-1;   int left = 0;   int right = n-1;
        int dir = 0;

        while(left <= right && top <= down) {
            if(dir == 0) {
                for(int i=left; i<=right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;
            }
            if(dir == 1) {
                for(int i=top; i<=down; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
            }
            if(dir == 2) {
                for(int i=right; i>=left; i--) {
                    result.add(matrix[down][i]);
                }
                down--;
            }
            if(dir == 3) {
                for(int i=down; i>=top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }

            dir = (dir + 1) % 4;
        }
        return result;
    }

}
