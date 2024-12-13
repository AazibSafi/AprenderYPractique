package com.algorithms.aprenderypractique.Algorithms.Strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.assertj.core.util.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Minimum Window Subsequence
 * Dynamic Programming
 * Find a subsequence that contains all the character from T string in the str String in the SAME ORDER
 *
 * https://leetcode.com/problems/minimum-window-subsequence/
 * https://anshika-bhargava0202.medium.com/leetcode-727-minimum-window-subsequence-21c40baff689
 */
public class MinimumWindowSubsequence extends BaseTest {

    @Test
    public void test() {
        String S = "abcdebdde" , T = "bde";
        Assert.assertEquals("bcde",minWindowSubsequence(S,T));

        S = "abcxxdxxeabxcdxe"; T = "abcde";
        Assert.assertEquals("abxcdxe",minWindowSubsequence(S,T));

        S = "deacbabcxxdxxe"; T = "abcde";      // character must be in the same order as in T
        Assert.assertEquals("abcxxdxxe",minWindowSubsequence(S,T));

        S = "aaa"; T = "bb";
        Assert.assertEquals("",minWindowSubsequence(S,T));

        S = "aa"; T = "bbbbb";
        Assert.assertEquals("",minWindowSubsequence(S,T));

        S = ""; T = "aa";
        Assert.assertEquals("",minWindowSubsequence(S,T));

        S = "aa"; T = "";
        Assert.assertEquals("",minWindowSubsequence(S,T));
    }

//  Time: O(s*t)
    public String minWindowSubsequence(String S, String T) {
        int m = S.length();
        int n = T.length();

        if(Strings.isNullOrEmpty(S) || Strings.isNullOrEmpty(T) || m<n)
            return "";

        int[][] dp = new int[m][n];

        Arrays.fill(dp[0], -1);     // first row elements are -1

//  STEP#1
//      fill the first column. if the character matches, store the current index or else copy the value from above table cell
        for(int i=0; i<m; i++) {
            if(S.charAt(i) == T.charAt(0)) {
                dp[i][0] = i;
            }
            else {
                if(i!=0)    dp[i][0] = dp[i-1][0];
            }
        }

//  STEP#2
//      fill the dp table. if character matches get the DIAGONAL value or else get the UPPER value
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(S.charAt(i) == T.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

//  STEP#3
        int[] minSubsequence = getMinLength(dp);     // {startIndex, length}

        return minSubsequence[0] == -1 ? "" :
                S.substring(minSubsequence[0], minSubsequence[0]+minSubsequence[1]);
    }

    int[] getMinLength(int[][] dp) {
        int minBeginIndex = -1, minLen = Integer.MAX_VALUE;

        for(int i=0; i<dp.length; i++) {
            int beginIndex = dp[i][dp[0].length-1];
            if(beginIndex != -1) {
                int currLen = i - beginIndex + 1;
                if(currLen < minLen) {
                    minLen = currLen;
                    minBeginIndex = beginIndex;
                }
            }
        }
        return new int[]{minBeginIndex, minLen};
    }

}
