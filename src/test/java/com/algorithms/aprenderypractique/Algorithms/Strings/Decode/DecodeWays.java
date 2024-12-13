package com.algorithms.aprenderypractique.Algorithms.Strings.Decode;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/decode-ways
 */
public class DecodeWays extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, numDecodings("12"));
        Assert.assertEquals(3, numDecodings("226"));
        Assert.assertEquals(0, numDecodings("06"));
        Assert.assertEquals(1, numDecodings("10"));
        Assert.assertEquals(1836311903, numDecodings("111111111111111111111111111111111111111111111")); // Covered by Memoization
    }

/*
   Time: O(N)
   Space: O(1)

   Two pointer approach - Efficient
*/
    public int numDecodings(String s) {
        if(s==null || s.isEmpty() || s.charAt(0) == '0') return 0;

        int n = s.length();
        int dp1 = 1, dp2 = 0, curr;

        for(int i=n-1; i>=0; i--) {
            char c = s.charAt(i);
            curr = c=='0' ? 0 : dp1;

            if(i+1<n && (c=='1' || (c=='2' && s.charAt(i+1)<='6')))
                curr += dp2;

            dp2 = dp1;
            dp1 = curr;
        }
        return dp1;
    }

/*
    Time: O(N)
    Space: O(N)     // 1D Array
 */
    public int numDecodings_DpArray(String s) {
        if(s==null || s.isEmpty() || s.charAt(0) == '0') return 0;

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for(int i=n-1; i>=0; i--) {
            char c = s.charAt(i);
            if(c != '0') {
                dp[i] = dp[i + 1];
                if (i + 1 < n && (c == '1' || (c == '2' && s.charAt(i+1) <= '6')))
                    dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

/*
    Time: O(N)
    Space: O(N)     // Recursive

    Memoization gives O(N) Time, otherwise it could be exponential 2^n
 */
    public int numDecodings_Recursive(String s) {
        if(s.charAt(0) == '0') return 0;
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return decode(s, 0, mem);
    }

    int decode(String s, int i, int[] mem) {
        int n = s.length();
        if(i == n) return 1;
        if(s.charAt(i) == '0') return 0;

        if(mem[i] != -1) return mem[i];

        int res = decode(s, i+1, mem);

        if(i+1<n && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i+1) <= '6')))
            res += decode(s, i+2, mem);

        return mem[i] = res;
    }

}
