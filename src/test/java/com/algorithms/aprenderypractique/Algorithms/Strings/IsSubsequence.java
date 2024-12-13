package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/is-subsequence
 *      https://www.youtube.com/watch?v=99RVfqklbCE&ab_channel=NeetCode
 */
public class IsSubsequence extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(isSubsequence("abc", "ahbgdc"));
        Assert.assertFalse(isSubsequence("axc", "ahbgdc"));
        Assert.assertTrue(isSubsequence("b", "abc"));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        if(m > n)   return false;

        int i = 0, j = 0;
        while(i<m && j<n) {
            if(s.charAt(i) == t.charAt(j))
                i++;
            j++;
        }
        return i == m;
    }
}
