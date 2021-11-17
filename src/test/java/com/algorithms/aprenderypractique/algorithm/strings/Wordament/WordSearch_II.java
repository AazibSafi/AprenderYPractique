package com.algorithms.aprenderypractique.algorithm.strings.Wordament;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  https://leetcode.com/problems/word-search-ii/
 *
 *  @see WordSearch_I
 *  Extension of Above Solution
 *  InEfficient Solution
 */
public class WordSearch_II extends BaseTest {

    @Test
    public void solution() {
        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("eat","oath"),findWords(board, new String[]{"oath","pea","eat","rain"})));

        board = new char[][]{{'a','b'},{'c','d'}};
        Assert.assertTrue(CommonHelper.isEquals(Collections.emptyList(),findWords(board, new String[]{"abcb"})));

        board = new char[][]{{'a','l','i','l'},{'g','e','g','a'},{'k','k','i','l'}};
        Assert.assertTrue(CommonHelper.isEquals(Collections.singletonList("agile"),findWords(board, new String[]{"agile", "lily"})));
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> output = new ArrayList<>();
        for(String word : words) {
            if (exist(board, word)) {
                output.add(word);
            }
        }
        return output;
    }

/**
 * @see WordSearch_I
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
                board[row][col] = temp;     // Additional Line here from WordSearch 1
                return true;
            }
        }

        board[row][col] = temp;     // un-visit
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
