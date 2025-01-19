package com.algorithms.aprenderypractique.Algorithms.Strings.Wordament;

import com.algorithms.aprenderypractique.Algorithms.Trie.Dictionary;
import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *  https://leetcode.com/problems/word-search-ii
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
        char[][] board = new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("eat","oath"), findWords(board, new String[]{"oath","pea","eat","rain"})));

        board = new char[][]{{'a','b'}, {'c','d'}};
        Assert.assertTrue(CommonHelper.isEquals(Collections.emptyList(), findWords(board, new String[]{"abcb"})));

        board = new char[][]{{'a','l','i','l'}, {'g','e','g','a'}, {'k','k','i','l'}};
        Assert.assertTrue(CommonHelper.isEquals(Collections.singletonList("agile"), findWords(board, new String[]{"agile", "lily"})));
    }

/*
    Time: O(M(4⋅3^(L−1))) => Tricky, it is based on worst assumption
    Space: O(N)

    M is the number of cells in the board
    L is the maximum length of words
    N is the total number of letters in the dictionary.
*/
    Dictionary dict;
    Set<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        dict = new Dictionary();
        result = new HashSet<>();

        for(String word : words) {
            dict.insert(word);
        }

        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int i=0 ; i< board.length; i++) {
            for(int j=0; j< board[0].length; j++) {
                dfs(board, i, j, visited, "");
            }
        }
        return result.stream().toList();
    }

    public void dfs(char[][] board, int i, int j, boolean[][] visited, String current) {
        if(!isSafe(board, i, j) || visited[i][j])   return;

        current += board[i][j];
        if(!dict.startsWith(current)) return;       // if no prefix matches

        if(dict.search(current)) {                  // if a complete word is found
            result.add(current);
        }

        visited[i][j] = true;
        dfs(board, i+1, j, visited, current);   // DOWN
        dfs(board, i-1, j, visited, current);   // UP
        dfs(board, i, j+1, visited, current);   // RIGHT
        dfs(board, i, j-1, visited, current);   // LEFT
        visited[i][j] = false;
    }

    boolean isSafe(char[][] board, int row, int col) {
        return row>=0 && col>=0 && row<board.length && col<board[0].length;
    }

}
