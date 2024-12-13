package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  Longest Common SubSequence
 *  Longest Repeating Subsequence
 *  https://www.youtube.com/watch?v=NnD96abizww
 *
 *  https://leetcode.com/problems/longest-common-subsequence
 *
 *  @Similar
 */
public class LongestCommonSubSequence extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4, longestCommonSubsequence("abcdaf","acbcf"));      //  abcf
        Assert.assertEquals(3, longestCommonSubsequence("abcdgh","aedfhr"));     //  ADH
        Assert.assertEquals(4, longestCommonSubsequence("aggtab","gxtxayb"));    //  GTAB
        Assert.assertEquals(3, longestCommonSubsequence("abcde","ace"));         //  ace
        Assert.assertEquals(3, longestCommonSubsequence("abc","abc"));           //  abc
        Assert.assertEquals(0, longestCommonSubsequence("abc","def"));
        Assert.assertEquals(5, longestCommonSubsequence("abcba","abcbcba"));
    }

/*
    Time: O(M.N)
    Space: O(min(M.N))
    Approach -> DP with 1D Array
*/
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] dp = new int[n+1];

        for(int i=1; i<m+1; i++) {
            int prev = dp[0];
            for(int j=1; j<n+1; j++) {
                int temp = dp[j];
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    dp[j] = 1 + prev;        // 1 + diagonal [Prev Row Value]
                else
                    dp[j] = Math.max(dp[j-1], dp[j]);    // Max of left and Curr Value
                prev = temp;
            }
        }
        return dp[n];
    }

/*
    Time: O(M.N)
    Space: O(M.N)
    Approach -> DP with 2D Array
*/
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];        // 1 + North West diagonal
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);    // Max of Left and Upper Value
            }
        }
        return dp[m][n];
    }

/*
    Time: O(M.N)
    Space: O(M.N)
    Approach -> Recursion with Memoization
*/
    public int longestCommonSubsequence1(String text1, String text2) {
        int[][] memo = new int[text1.length()][text2.length()];
        for(int[] memoRow : memo) {
            Arrays.fill(memoRow, -1);
        }
        return solve(text1, text2, 0, 0, memo);
    }

    public int solve(String text1, String text2, int i, int j, int[][] memo) {
        if(i == text1.length() || j == text2.length())  return 0;

        if(memo[i][j] != -1)    return memo[i][j];

        if(text1.charAt(i) == text2.charAt(j))
            memo[i][j] = 1 + solve(text1, text2, i+1, j+1, memo);
        else
            memo[i][j] = Math.max(solve(text1, text2, i, j+1, memo), solve(text1, text2, i+1, j, memo));

        return memo[i][j];
    }

}
