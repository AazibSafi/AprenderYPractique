package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/one-edit-distance
 *
 *      Similar
 *      @see EditDistance
 */
public class OneEditDistance extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(isOneEditDistance("ab", "acb"));
        Assert.assertFalse(isOneEditDistance("", ""));
    }

/*
    Time:  O(min(M.N))
    Space: O(N) -> Strings are immutable create substring costs.
    Approach: One Pass Algorithm
*/
    public boolean isOneEditDistance(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        if(Math.abs(sLen - tLen) > 1)
            return false;

        int i=0;
        // find the index of first mismatch
        while(i<sLen && i<tLen && s.charAt(i) == t.charAt(i)) {
            i++;
        }

        if(i==sLen && i== tLen) return false;

        if(sLen < tLen)     // Insert
            return s.substring(i).equals(t.substring(i+1));

        if(sLen > tLen)     // Delete
            return s.substring(i+1).equals(t.substring(i));

        // if(sLen == tLen) // Replace
        return s.substring(i+1).equals(t.substring(i+1));
    }

}
