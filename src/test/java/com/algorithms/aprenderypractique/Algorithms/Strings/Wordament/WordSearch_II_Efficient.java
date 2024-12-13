package com.algorithms.aprenderypractique.Algorithms.Strings.Wordament;

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
 *  https://www.youtube.com/watch?v=asbcE9mZz_U
 *  https://www.youtube.com/watch?v=EmvsBM7o-5k
 *
 *  https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleHard/212.html
 *
 *  Using Trie
 *  Efficient Solution
 */
public class WordSearch_II_Efficient extends BaseTest {

    @Test
    public void solution() {
        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("eat","oath"),findWords(board, new String[]{"oath","pea","eat","rain"})));

        board = new char[][]{{'a','b'},{'c','d'}};
        Assert.assertTrue(CommonHelper.isEquals(Collections.emptyList(),findWords(board, new String[]{"abcb"})));

        board = new char[][]{{'a','l','i','l'},{'g','e','g','a'},{'k','k','i','l'}};
        Assert.assertTrue(CommonHelper.isEquals(Collections.singletonList("agile"),findWords(board, new String[]{"agile", "lily"})));
    }

//  Time: O(NM * NM)
    public List<String> findWords(char[][] board, String[] words) {
        return new ArrayList<>();
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
