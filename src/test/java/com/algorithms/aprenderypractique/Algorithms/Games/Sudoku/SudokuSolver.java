package com.algorithms.aprenderypractique.Algorithms.Games.Sudoku;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/sudoku-solver
 *  https://www.youtube.com/watch?v=H5i2ugoZLbg&ab_channel=EricProgramming
 */
public class SudokuSolver extends BaseTest {

    @Test
    public void test() {
        char[][] board = new char[][]{  {'5','3','.','.','7','.','.','.','.'},
                                        {'6','.','.','1','9','5','.','.','.'},
                                        {'.','9','8','.','.','.','.','6','.'},
                                        {'8','.','.','.','6','.','.','.','3'},
                                        {'4','.','.','8','.','3','.','.','1'},
                                        {'7','.','.','.','2','.','.','.','6'},
                                        {'.','6','.','.','.','.','2','8','.'},
                                        {'.','.','.','4','1','9','.','.','5'},
                                        {'.','.','.','.','8','.','.','7','9'}};

        char[][] solvedBoard = new char[][]{{'5','3','4','6','7','8','9','1','2'},
                                            {'6','7','2','1','9','5','3','4','8'},
                                            {'1','9','8','3','4','2','5','6','7'},
                                            {'8','5','9','7','6','1','4','2','3'},
                                            {'4','2','6','8','5','3','7','9','1'},
                                            {'7','1','3','9','2','4','8','5','6'},
                                            {'9','6','1','5','3','7','2','8','4'},
                                            {'2','8','7','4','1','9','6','3','5'},
                                            {'3','4','5','2','8','6','1','7','9'}};

        Assert.assertTrue( solveSudoku(board) );
        Assert.assertArrayEquals(solvedBoard, board);
    }

    public boolean solveSudoku(char[][] board) {
        return Sudoku.solveSudoku(board, '.');
    }

}
