package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/edit-distance
 *      https://www.youtube.com/watch?v=XYi2-LPrwm4&ab_channel=NeetCode
 *
 *      Find the minimal number of edit operations to convert one string to another string.
 *
 *      Similar
 *      @see OneEditDistance
 *
 *      Todo: https://leetcode.com/problems/uncrossed-lines
 */
public class EditDistance extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(3, minDistance("horse", "ros"));
        Assert.assertEquals(5, minDistance("intention", "execution"));
        Assert.assertEquals(6, minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));
        Assert.assertEquals(1, minDistance("abd", "acd"));
        Assert.assertEquals(3, minDistance("park", "spake"));
    }

/*
    Approach#3: Bottom-Up DP with 2D Array
    Time:  O(M.N)
    Space: O(M.N)
*/
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for(int row=0; row<m; row++) {
            dp[row][n] = m-row;     // Last Column
        }
        for(int col=0; col<n; col++) {
            dp[m][col] = n-col;     // Last Row
        }

        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                }
                else {
                    int insert = dp[i][j+1];
                    int delete = dp[i+1][j];
                    int replace = dp[i+1][j+1];
                    dp[i][j] = 1 + min(insert, delete, replace);
                }
            }
        }
        return dp[0][0];
    }

/*
    Approach#2: Recursion with memoization
    Time:  O(M.N)
    Space: O(M.N)
*/
    public int minDistance2(String word1, String word2) {
        Integer[][] memo = new Integer[word1.length()][word2.length()];
        return countOperations(word1, word2, 0, 0, memo);
    }

    public int countOperations(String word1, String word2, int i, int j, Integer[][] memo) {
        int m = word1.length(), n = word2.length();

        if(i==m && j==n)    return 0;
        if(i==m)            return n-j;
        if(j==n)            return m-i;

        if(memo[i][j] != null) return memo[i][j];

        int operationsCounter = 0;

        if(word1.charAt(i) == word2.charAt(j)) {
            operationsCounter = countOperations(word1, word2, i+1, j+1, memo);
        }
        else {
            int insert = countOperations(word1, word2, i, j+1, memo);
            int delete = countOperations(word1, word2, i+1, j, memo);
            int replace = countOperations(word1, word2, i+1, j+1, memo);

            operationsCounter = 1 + min(insert, delete, replace);
        }
        return memo[i][j] = operationsCounter;
    }

/*
    Approach#1: Recursion
    Let M be the length of string word1 and N be the length of string word2. Let K=max(M,N).
    Time:  O(3^K) -> exponential
    Space: O(K) -> internal call stack
*/
    public int minDistance1(String word1, String word2) {
        return countOperations(word1, word2, 0, 0);
    }

    public int countOperations(String word1, String word2, int i, int j) {
        int m = word1.length(), n = word2.length();

        if(i==m && j==n)    return 0;
        if(i==m)            return n-j;
        if(j==n)            return m-i;

        int operationsCounter = 0;

        if(word1.charAt(i) == word2.charAt(j)) {
            operationsCounter = countOperations(word1, word2, i+1, j+1);
        }
        else {
            int insert = countOperations(word1, word2, i, j+1);
            int delete = countOperations(word1, word2, i+1, j);
            int replace = countOperations(word1, word2, i+1, j+1);

            operationsCounter = 1 + min(insert, delete, replace);
        }
        return operationsCounter;
    }

    public int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

}
