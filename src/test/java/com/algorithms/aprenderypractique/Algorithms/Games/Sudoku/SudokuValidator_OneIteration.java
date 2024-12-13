package com.algorithms.aprenderypractique.Algorithms.Games.Sudoku;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://leetcode.com/problems/valid-sudoku/
 *  https://www.youtube.com/watch?v=rJ9NFK9s_mI&ab_channel=PrakashShukla
 *
 *  Single Iteration Solution
 */
public class SudokuValidator_OneIteration extends BaseTest {

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
        Assert.assertTrue( isValidSudoku(board) );

        board = new char[][]{  {'8','3','.','.','7','.','.','.','.'},
                                {'6','.','.','1','9','5','.','.','.'},
                                {'.','9','8','.','.','.','.','6','.'},
                                {'8','.','.','.','6','.','.','.','3'},
                                {'4','.','.','8','.','3','.','.','1'},
                                {'7','.','.','.','2','.','.','.','6'},
                                {'.','6','.','.','.','.','2','8','.'},
                                {'.','.','.','4','1','9','.','.','5'},
                                {'.','.','.','.','8','.','.','7','9'}};
        Assert.assertFalse( isValidSudoku(board) );

        board = new char[][]{{'.','.','.','.','5','.','.','1','.'},
                            {'.','4','.','3','.','.','.','.','.'},
                            {'.','.','.','.','.','3','.','.','1'},
                            {'8','.','.','.','.','.','.','2','.'},
                            {'.','.','2','.','7','.','.','.','.'},
                            {'.','1','5','.','.','.','.','.','.'},
                            {'.','.','.','.','.','2','.','.','.'},
                            {'.','2','.','9','.','.','.','.','.'},
                            {'.','.','4','.','.','.','.','.','.'}};
        Assert.assertFalse( isValidSudoku(board) );
    }

/*
    Time: O(n*n) -- O(n^2)  -- iterating all elements in the board
    Space: O(n^2) -- Storing each element in the Set
 */
    public boolean isValidSudoku(char[][] board) {
        int size = board.length;
        Set<String> set = new HashSet<>();

        for(int row=0; row<size; row++) {
            for(int col=0; col<size; col++) {

                char cell = board[row][col];

                if(cell != '.') {
                    if(!set.add("Row:"+row + ",cell:"+cell) ||
                        !set.add("Col:"+col + ",cell:"+cell) ||
                        !set.add("Box:"+((row/3)*3 + col/3) + ",cell:"+cell))
                        return false;
                }

            }
        }

        return true;
    }

}
