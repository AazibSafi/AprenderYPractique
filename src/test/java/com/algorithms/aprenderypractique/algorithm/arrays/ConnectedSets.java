package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
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

        int[][] matrix1 = {
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        };

        int[][] matrix2 = {
                {1, 0, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

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

        System.out.println(findNoOfConnectSets(matrix));
        System.out.println(findNoOfConnectSets(matrix1));
        System.out.println(findNoOfConnectSets(matrix2));
        System.out.println(findNoOfConnectSets(matrix3));
    }

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

    public void traverseNeighbor(int[][] matrix, int row, int col) {

        if(row<0 || col<0 || row>=matrix.length || col>=matrix[0].length
            || matrix[row][col] != 1) {
            return;
        }

        matrix[row][col] = -1; // Visited Cell

        traverseNeighbor(matrix, row - 1, col - 1);
        traverseNeighbor(matrix, row - 1, col);
        traverseNeighbor(matrix, row - 1, col + 1);
        traverseNeighbor(matrix, row, col - 1);
        traverseNeighbor(matrix, row, col);
        traverseNeighbor(matrix, row, col + 1);
        traverseNeighbor(matrix, row+1, col - 1);
        traverseNeighbor(matrix, row+1, col);
        traverseNeighbor(matrix, row+1, col + 1);
    }

}
