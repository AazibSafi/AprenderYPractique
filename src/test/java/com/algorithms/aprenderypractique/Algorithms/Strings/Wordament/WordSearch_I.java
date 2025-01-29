package com.algorithms.aprenderypractique.Algorithms.Strings.Wordament;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  https://leetcode.com/problems/word-search
 *  https://www.youtube.com/watch?v=vYYNp0Jrdv0
 *
 *  Also Called Boggle word game - Scramble
 *
 *  Todo: https://leetcode.com/problems/robot-room-cleaner
 *
 */
public class WordSearch_I extends BaseTest {

    @Test
    public void solution() {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        assertTrue(exist(board, "ABCCED"));

        board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        assertTrue(exist(board, "SEE"));

        board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        assertTrue(exist(board, "eat"));

        board = new char[][]{{'A','B','E'},{'B','C','D'}};
        assertTrue(exist(board, "ABCDEB"));

//      Edge Case: The same letter cell may not be used more than once.
        board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        assertFalse(exist(board, "ABCB"));
    }

/*
    Time: O(M.N.3^L) -> 3^L is for 4 directions reduced to 3 directions of each word letter
    Space: O(L) -> maximum length of the recursion call stack

    where M.N is the number of cells in the board
    L is the length of the word to be matched.
*/
/*  Complexity can also be simplified as below
    Time: O(n*m)    -- Worst case where the whole board is a word
    Space: O(n*m)   -- because we are modifying the board to mark visited
 */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for(int row=0; row<m; row++) {
            for(int col=0; col<n; col++) {
                if(board[row][col] == word.charAt(0) &&
                        searchWord(board, row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean searchWord(char[][] board, int row, int col, String word, int wordIndex) {
        if(word.length() == wordIndex)  return true;

        if(!isValidBoundary(board,row,col) || board[row][col] != word.charAt(wordIndex))
            return false;

        char temp = board[row][col];
        board[row][col] = ' ';      // Marking as Visited

        for (int[] adjCell : adjacent) {
            int adjRow = row + adjCell[0];
            int adjCol = col + adjCell[1];

            if (searchWord(board, adjRow, adjCol, word, wordIndex + 1)) {
                board[row][col] = temp;     // Not necessary for WORD_SEARCH_I, bcz after returning true.. the program will completed and exit. // This is necessary for WORD_SEARCH_II
                return true;
            }
        }

        board[row][col] = temp;     // un-visit
        return false;
    }

//  using 2d Array to mark visited
    public boolean exist2(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int row=0; row<m; row++) {
            for(int col=0; col<n; col++) {
                if(board[row][col] == word.charAt(0) &&
                        searchWord2(board, row, col, word, 0, visited)) {
                        return true;
                }
            }
        }
        return false;
    }

    boolean searchWord2(char[][] board, int row, int col, String word, int wordIndex, boolean[][] visited) {
        if(word.length() == wordIndex)  return true;

        if(isValidBoundary(board,row,col) && !visited[row][col] && board[row][col] == word.charAt(wordIndex)) {
            visited[row][col] = true;

            for (int[] adjCell : adjacent) {
                int adjRow = row + adjCell[0];
                int adjCol = col + adjCell[1];

                if (searchWord2(board, adjRow, adjCol, word, wordIndex + 1, visited)) {
                    return true;
                }
            }

            visited[row][col] = false;  // un-visit
        }

        return false;
    }

    boolean isValidBoundary(char[][] board, int row, int col) {
        return row>=0 && col>=0 && row<board.length && col<board[0].length;
    }

    int[][] adjacent = new int[][] {
            {-1,0},     // UP
            {1,0},      // DOWN
            {0,-1},     // LEFT
            {0,1}       // RIGHT
    };

}
