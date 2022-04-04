package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Given a 2–d matrix , which has only 1’s and 0’s in it. Find the total number of connected sets in that matrix.
 *
 **  Explanation:
 *  Connected set can be defined as group of cell(s) which has 1 mentioned on it and have at least one other cell in that set with which they share the neighbor relationship.
 *  A cell with 1 in it and no surrounding neighbor having 1 in it can be considered as a set with one cell in it.
 *  Neighbors can be defined as all the cells adjacent to the given cell in 8 possible directions ( i.e N , W , E , S , NE , NW , SE , SW direction ).
 *  A cell is not a neighbor of itself.
 *
 *  https://www.hackerrank.com/contests/amazon/challenges/connected-sets
 */
public class ConnectedSets extends BaseTest {

    @Test
    public void solution() {

        int[][] matrix = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
            };
        Assert.assertEquals(1,findNoOfConnectSets(matrix));

        int[][] matrix1 = {
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        };
        Assert.assertEquals(3,findNoOfConnectSets(matrix1));

        int[][] matrix2 = {
                {1, 0, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
        Assert.assertEquals(3,findNoOfConnectSets(matrix2));

        int[][] matrix3 = {
                {0, 0, 1, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Assert.assertEquals(9,findNoOfConnectSets(matrix3));
    }

    int[][] dir = {
            {-1,-1}, {-1,0}, {-1,1},    // NW, N, NE
            {0,-1}, {0,1},              // W, E
            {1,-1}, {1,0}, {1,1}        // SW, S, SE
    };

    public int findNoOfConnectSets(int[][] matrix) {
        int setCount = 0;

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] == 1) {
                    traverseNeighbor(matrix,i,j);
                    setCount++;
                }
            }
        }

        return setCount;
    }

    void traverseNeighbor(int[][] matrix, int i, int j) {
        if(withInBoundary(i,j, matrix.length, matrix[0].length) && matrix[i][j] == 1) {

            matrix[i][j] = -1;  // Visited Cell

            for (int[] cord : dir) {   //  Traverse All Given Directions and mark them visited
                traverseNeighbor(matrix, i + cord[0], j + cord[1]);
            }

        }
    }

    boolean withInBoundary(int i, int j, int row, int col) {
        return i>=0 && j>=0 && i<row && j<col;
    }

}
