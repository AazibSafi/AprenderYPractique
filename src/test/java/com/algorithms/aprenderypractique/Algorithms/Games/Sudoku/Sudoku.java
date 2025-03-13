package com.algorithms.aprenderypractique.Algorithms.Games.Sudoku;

import org.junit.Assert;

/**
 *  https://www.youtube.com/watch?v=H5i2ugoZLbg&ab_channel=EricProgramming
 *  https://leetcode.com/problems/sudoku-solver
 *
 *  Sudoku Puzzle of size n*n
 *
 *  Sudoku Puzzle Board must be a Perfect Square
 */
public class Sudoku {

    static char[][] GRID;
    static int gridSize;
    static int subGridSize;
    static char emptyCell;

    public static boolean solveSudoku(char[][] board, char emptyCellChar) {
        GRID = board;
        gridSize = board.length;                    //  i.e;    9*9 --- 16*16
        subGridSize = perfectSquare(gridSize);      //  i.e;    3*3 ---  4*4
        emptyCell = emptyCellChar;

        return solve(0, 0);
    }

    static boolean solve(int row, int col) {
//  Start the next row
        if(col == gridSize) {
            row += 1;
            col = 0;
        }

// Finish the search after the last row
        if(row == gridSize)    return true;

//  Search the next cell
        if(GRID[row][col] != emptyCell) {
            return solve(row, col+1);
        }

//  DFS all valid options
        for(char i='1'; i<=(gridSize+'0'); i++) {
            if(!isRepeatedInGroup(row, col, i)) {
                GRID[row][col] = i;
                if(solve(row, col + 1)) {
                    return true;
                }
                GRID[row][col] = emptyCell;
            }
        }
        return false;
    }

    static boolean isRepeatedInGroup(int row, int col, int ch) {
        return alreadyExistInRowOrCol(row, col, ch) ||
                alreadyExistInSubGridSquare(row, col, ch);
    }

    static boolean alreadyExistInRowOrCol(int row, int col, int ch) {
        for(int i=0; i<gridSize; i++) {
            if(GRID[row][i] == ch || GRID[i][col] == ch)
                return true;
        }
        return false;
    }

    static boolean alreadyExistInSubGridSquare(int row, int col, int ch) {
        int rowBox = row / subGridSize;
        int colBox = col / subGridSize;
        int subGridRowStart = rowBox * subGridSize;
        int subGridColStart = colBox * subGridSize;
        int subGridRowEnd = subGridRowStart + subGridSize;
        int subGridColEnd = subGridColStart + subGridSize;

        for(int subGridRowIndex=subGridRowStart; subGridRowIndex<subGridRowEnd; subGridRowIndex++) {
            for(int subGridColIndex=subGridColStart; subGridColIndex<subGridColEnd; subGridColIndex++) {
                if(GRID[subGridRowIndex][subGridColIndex] == ch)
                    return true;
            }
        }

        return false;
    }

    static int perfectSquare(double x) {
        double sqrt = Math.sqrt(x);
        Assert.assertEquals("Grid size must be a Perfect Square", 0, (sqrt - Math.floor(sqrt)), 0.0);
        return (int) sqrt;
    }

}
