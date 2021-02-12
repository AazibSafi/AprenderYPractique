package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

public class RotateImage extends BaseTest {

    @Test
    public void test() throws Exception {
        int[][] arr = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        arr = rotateImage(arr,90);
        printImage(arr);
    }

    int[][] rotateImage(int[][] arr, int degree) throws Exception {
        if(degree%90 != 0) {
            throw new Exception("Incorrect Rotation Degree. Degrees Must be multiple of 90");
        }
        int rotations = degree/90;
        for(int i=0; i<rotations; i++) {
            arr = transformMatrix(arr);
        }
        return arr;
    }

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

    void printImage(int[][] arr) {
        int n = arr.length;
        for (int[] row : arr) {
            for (int j=0; j<n; j++) {
                System.out.print(row[j]);
            }
            System.out.println();
        }
    }

}
