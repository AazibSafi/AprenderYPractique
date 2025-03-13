package com.algorithms.aprenderypractique.Algorithms.Strings;

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
    public void testOneEditDistance() {
        // Test cases for insertion
        Assert.assertTrue(isOneEditDistance("a", "ab"));       // Insert 'b' at the end
        Assert.assertTrue(isOneEditDistance("ab", "acb"));     // Insert 'c' in the middle
        Assert.assertTrue(isOneEditDistance("ab", "abc"));     // Insert 'c' at the end

        // Test cases for deletion
        Assert.assertTrue(isOneEditDistance("abc", "ab"));     // Delete 'c' at the end
        Assert.assertTrue(isOneEditDistance("abc", "ac"));     // Delete 'b' in the middle
        Assert.assertTrue(isOneEditDistance("abc", "bc"));     // Delete 'a' at the beginning

        // Test cases for replacement
        Assert.assertTrue(isOneEditDistance("abc", "abd"));    // Replace 'c' with 'd'
        Assert.assertTrue(isOneEditDistance("abc", "axc"));    // Replace 'b' with 'x'
        Assert.assertTrue(isOneEditDistance("abc", "xbc"));    // Replace 'a' with 'x'

        // Test cases for no edit distance
        Assert.assertFalse(isOneEditDistance("abc", "abc"));   // No edit needed
        Assert.assertFalse(isOneEditDistance("", ""));         // Both strings are empty

        // Test cases for more than one edit distance
        Assert.assertFalse(isOneEditDistance("abc", "def"));   // More than one edit
        Assert.assertFalse(isOneEditDistance("abc", "abde"));  // More than one edit
        Assert.assertFalse(isOneEditDistance("abc", "a"));     // More than one edit

        // Test cases for edge cases
        Assert.assertTrue(isOneEditDistance("", "a"));        // Insert 'a' into empty string
        Assert.assertTrue(isOneEditDistance("a", ""));        // Delete 'a' from string
        Assert.assertFalse(isOneEditDistance("a", "a"));      // No edit needed
        Assert.assertTrue(isOneEditDistance("a", "aa"));      // More than one edit
        Assert.assertTrue(isOneEditDistance("aa", "a"));     // More than one edit
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
