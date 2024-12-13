package com.algorithms.aprenderypractique.Algorithms.Strings.PatternMatching;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/regular-expression-matching
 *   https://www.youtube.com/watch?v=HAA8mgxlov8&ab_channel=NeetCode
 *
 * @see WildcardMatching
 *  Similar problem but difference is matching regex
 */
public class RegularExpressionMatching extends BaseTest {

    @Test
    public void test() {
        Assert.assertFalse( isMatch("aa","a") );
        Assert.assertTrue( isMatch("aa","a*") );
        Assert.assertTrue( isMatch("ab",".*") );
        Assert.assertTrue( isMatch("aab","c*a*b") );
        Assert.assertTrue( isMatch("ab",".*b") );
        Assert.assertFalse( isMatch("cb",".a") );
        Assert.assertFalse( isMatch("acdcb","a*c.b") );
        Assert.assertFalse( isMatch("abcdef","a.c*f") );
        Assert.assertFalse( isMatch("aa",".") );
        Assert.assertFalse( isMatch("","*") );  // It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
    }

/*
    Time: O(m*n)
    Space: O(m*n)
 */
    public boolean isMatch(String s, String p) {
        Boolean[][] memoization = new Boolean[s.length()+1][p.length()+1];
        return isMatch(0, 0, s, p, memoization);
    }

    public boolean isMatch(int i, int j, String s, String p, Boolean[][] memo) {
        int m = s.length(), n = p.length();

        if (j == n)     return i == m;

        if(memo[i][j] != null)      return memo[i][j];

        boolean firstMatch = i<m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if(j+1<n && p.charAt(j+1) == '*')
            memo[i][j] = isMatch(i, j+2, s, p, memo) || (firstMatch && isMatch(i+1, j, s, p, memo));
        else
            memo[i][j] = firstMatch && isMatch(i+1, j+1, s, p, memo);

        return memo[i][j];
    }

}
