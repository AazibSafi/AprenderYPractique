package com.algorithms.aprenderypractique.algorithm.arrays.Matrix;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/battleships-in-a-board
 *      https://leetcode.com/problems/battleships-in-a-board/submissions/1458733898/?envType=problem-list-v2&envId=7p55wqm
 *      https://www.youtube.com/watch?v=74NuFEdzs6A
 */
public class BattleshipsInABoard extends BaseTest {

    @Test
    public void test() {
        char[][] board = new char[][] {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        Assert.assertEquals(2, countBattleships(board));

        board = new char[][]{{'.'}};
        Assert.assertEquals(0, countBattleships(board));
    }

/*
    Time: O(M * N)
    Space: O(1)
    One-Pass Constant-Space solution. Input board is not modified
*/
    public int countBattleships(char[][] board) {
        int rows = board.length, cols = board[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == 'X'
                        && (i==rows-1 || board[i+1][j] == '.')
                        && (j==cols-1 || board[i][j+1] == '.'))
                    count++;
            }
        }
        return count;
    }
}
