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
            if (new WordSearch_I().exist(board, word)) {
                output.add(word);
            }
        }
        return output;
    }

}
