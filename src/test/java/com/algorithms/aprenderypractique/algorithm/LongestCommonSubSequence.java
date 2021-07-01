package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Longest Common SubSequence
 *  Longest Repeating Subsequence
 *  https://www.youtube.com/watch?v=NnD96abizww
 */
public class LongestCommonSubSequence extends BaseTest {

    @Test
    public void test() {
        String str = "abcdaf";
        String ptr = "acbcf";
        int maxLCSLength = findLCS(str,ptr);
        System.out.println(maxLCSLength);
        Assert.assertEquals(4,maxLCSLength);
    }

/*
    To get the subsequence string
    Store the ith and jth index instead of maxLength
    Traversed back from the stored ith, jth index of the dp table
 */
    public int findLCS(String str, String ptr) {
        int[][] dp = new int[str.length()][ptr.length()];
        int left,upper, maxLength=0;
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
