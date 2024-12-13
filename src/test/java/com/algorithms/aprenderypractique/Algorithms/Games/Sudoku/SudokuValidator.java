package com.algorithms.aprenderypractique.Algorithms.Games.Sudoku;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://leetcode.com/problems/valid-sudoku/
 *  https://www.youtube.com/watch?v=rJ9NFK9s_mI&ab_channel=PrakashShukla
 */
public class SudokuValidator extends BaseTest {

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

        board = new char[][]{   {'8','3','.','.','7','.','.','.','.'},
                                {'6','.','.','1','9','5','.','.','.'},
                                {'.','9','8','.','.','.','.','6','.'},
                                {'8','.','.','.','6','.','.','.','3'},
                                {'4','.','.','8','.','3','.','.','1'},
                                {'7','.','.','.','2','.','.','.','6'},
                                {'.','6','.','.','.','.','2','8','.'},
                                {'.','.','.','4','1','9','.','.','5'},
                                {'.','.','.','.','8','.','.','7','9'}};
        Assert.assertFalse( isValidSudoku(board) );

        board = new char[][]{   {'.','.','.','.','5','.','.','1','.'},
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
    Time: O(n^2 + n^2)
    Space: O(n)
 */
    public boolean isValidSudoku(char[][] board) {
        return isRow_ColValid(board) && isGridSquareValid(board);
    }

/*
    Time: O(n*n) --- O(n^2)
    Space: O(n + n)     --- two HashSet
 */
    boolean isRow_ColValid(char[][] board) {
        Set<Character> rowSet;
        Set<Character> colSet;

        for (int fixed_row_or_col = 0; fixed_row_or_col < board.length; fixed_row_or_col++) {
            rowSet = new HashSet<>();
            colSet = new HashSet<>();

            for (int i = 0; i < board.length; i++) {

//  Checking duplicate elements in a Row
                char cell1 = board[fixed_row_or_col][i];
                if (rowSet.contains(cell1))         return false;
                else if(cell1 != '.')               rowSet.add(cell1);

//  Checking duplicate elements in a Column
                char cell2 = board[i][fixed_row_or_col];
                if (colSet.contains(cell2))         return false;
                else if(cell2 != '.')               colSet.add(cell2);
            }

        }

        return true;
    }

/*
    Time: O(n*n) --- O(n^2)
    Space: O(n)     --- One HashSet
 */
    boolean isGridSquareValid(char[][] board) {
        int m = (int) Math.sqrt(board.length);      // Note: Grid size must be a Perfect Square

        for(int gridSquareX = 0; gridSquareX < m; gridSquareX++) {
            for(int gridSquareY = 0; gridSquareY < m; gridSquareY++) {

                int gridSquareRowStart = gridSquareX * m;
                int gridSquareColStart = gridSquareY * m;
                int gridSquareRowEnd = gridSquareRowStart + m;
                int gridSquareColEnd = gridSquareColStart + m;

                Set<Character> set = new HashSet<>();

                for(int row = gridSquareRowStart; row < gridSquareRowEnd; row++) {
                    for(int col = gridSquareColStart; col < gridSquareColEnd; col++) {
                        char cell = board[row][col];
                        if(set.contains(cell))          return false;
                        else if(cell != '.')            set.add(cell);
                    }
                }
            }
        }

        return true;
    }

}
