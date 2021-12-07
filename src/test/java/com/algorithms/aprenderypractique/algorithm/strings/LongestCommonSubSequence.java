package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Longest Common SubSequence
 *  Longest Repeating Subsequence
 *  https://www.youtube.com/watch?v=NnD96abizww
 *
 *  https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubSequence extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4, findLCS("abcdaf","acbcf"));      //  abcf
        Assert.assertEquals(3, findLCS("ABCDGH","AEDFHR"));     //  ADH
        Assert.assertEquals(4, findLCS("AGGTAB","GXTXAYB"));    //  GTAB
        Assert.assertEquals(3, findLCS("abcde","ace"));         //  ace
        Assert.assertEquals(3, findLCS("abc","abc"));           //  abc
        Assert.assertEquals(0, findLCS("abc","def"));
    }

/*
    To get the subsequence string
    Store the ith and jth index instead of maxLength
    Traversed back from the stored ith, jth index of the dp table

Formula:
    if( iCHAR == jCHAR )
        diagonal + 1
    else
        Max of Left and Upper Value
 */
    public int findLCS(String str, String ptr) {
        int n = str.length(), m = ptr.length();

        int[][] dp = new int[n][m];
        int left, upper, maxLength=0;

        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {

                if(str.charAt(i-1) == ptr.charAt(j-1)) {        // -1 bcz actual string starts from 0 index
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    left = dp[i][j-1];
                    upper = dp[i-1][j];
                    dp[i][j] = Math.max(left, upper);
                }

                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }

//  Old - Working But Depreciated
    public int findLCS_2(String str, String ptr) {
        int[][] dp = new int[str.length()][ptr.length()];
        int left, upper, maxLength=0;

        for(int i=0;i<str.length();i++) {
            for(int j=0;j<ptr.length();j++) {

                if(str.charAt(i) == ptr.charAt(j)) {
                    dp[i][j] = i-1>=0 && j-1>=0 ? dp[i-1][j-1]+1 : 1;
                }
                else {
                    left = j-1>=0 ? dp[i][j-1] : 0;
                    upper = i-1>=0 ? dp[i-1][j]: 0;
                    dp[i][j] = Math.max(left,upper);
                }

                maxLength = Math.max(maxLength,dp[i][j]);
            }
        }

        return maxLength;
    }



}
