package com.algorithms.aprenderypractique.Algorithms.Strings.PatternMatching;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/wildcard-matching/
 *  https://www.youtube.com/watch?v=dzMdbWulRII&ab_channel=faadcoder
 *
 *  Regex Problem: https://www.youtube.com/watch?v=HAA8mgxlov8&ab_channel=NeetCode
 *
 *  Microsoft Hiring Event Question
 */
public class WildcardMatching extends BaseTest {

    @Test
    public void test() {
        Assert.assertFalse( isMatch("aa","a") );
        Assert.assertTrue( isMatch("aabc","*") );
        Assert.assertFalse( isMatch("cb","?a") );
        Assert.assertTrue( isMatch("adceb","*a*b") );
        Assert.assertFalse( isMatch("acdcb","a*c?b") );
        Assert.assertTrue( isMatch("abcdef","a?c*f") );
        Assert.assertFalse( isMatch("aa","?") );
        Assert.assertFalse( isMatch("aab","c*a*b") );
        Assert.assertTrue( isMatch("","*") );
        Assert.assertTrue( isMatch("ab","****") );       // Edge Case
        Assert.assertTrue( isMatch("","****") );       // Edge Case
        Assert.assertTrue( isMatch("abcabczzzde","*abc???de*") );     // Edge Case

        String s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
        String p = "a*******b";
        Assert.assertFalse(isMatch(s,p));   // Time Limit Exceeded
    }

/*
    Time: O(m*n)
    Space: O(m*n)
 */
    public boolean isMatch(String s, String p) {
        Boolean[][] memoization = new Boolean[s.length()][p.length()];
        return isMatch(0, 0, s, p, memoization);
    }

    public boolean isMatch(int i, int j, String s, String p, Boolean[][] memo) {
        int m = s.length(), n = p.length();
        boolean ans = false;

        if (j == n)     return i == m;

        if(i == m)     return p.substring(j).chars().allMatch(ch -> ch == '*');

        if(memo[i][j] != null)      return memo[i][j];

        else if(p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))
            ans = isMatch(i+1, j+1, s, p, memo);

        else if(p.charAt(j) == '*')
            ans = isMatch(i, j+1, s, p, memo) || isMatch(i+1, j, s, p, memo);

        return memo[i][j] = ans;
    }

}
