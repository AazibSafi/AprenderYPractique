package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

public class CountryMap extends BaseTest {

    @Test
    public void test() {

        int[][] B = new int[2][2];
        int[][] C = {{3}};

        int[][] A = {
                {5, 4, 5},
                {5, 5, 5},
                {5, 2, 4},
                {2, 2, 2},
                {3, 3, 4},
                {1, 4, 4},
                {4, 1, 3},
        };

        int[][] D = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12},
                {13, 14, 15},
                {16, 17, 18},
                {19, 20, 21},
        };

        int[][] E = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        };

        System.out.println("Countries: " + solution(A));
        System.out.println("Countries: " + solution(B));
        System.out.println("Countries: " + solution(C));
        System.out.println("Countries: " + solution(D));
        System.out.println("Countries: " + solution(E));
    }

    public int solution(int[][] A) {

        int noOfCountry = 0;

        if (A != null) {
            for (int row = 0; row < A.length; row++) {
                for (int col = 0; col < A[row].length; col++) {
                    if (A[row][col] != -1) {
                        traverse(A, A[row][col], row, col);
                        noOfCountry++;
                    }
                }
            }
        }
        return noOfCountry;
    }

    public void traverse(int[][] A, int elm, int row, int col) {

        if (row < 0 || col < 0 || row >= A.length || col >= A[row].length
                || A[row][col] != elm) {
            return;
        }

        A[row][col] = -1;

        traverse(A, elm, row - 1, col);  // North
        traverse(A, elm, row, col - 1);  // West
        traverse(A, elm, row, col + 1);  // East
        traverse(A, elm, row + 1, col);  // South
    }

}
