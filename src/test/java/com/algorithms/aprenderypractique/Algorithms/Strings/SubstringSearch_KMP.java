package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/*
    https://www.youtube.com/watch?v=GTJr8OvyEVQ&t=465s&ab_channel=TusharRoy-CodingMadeSimple

    Pattern Matching - Substring Search
    KMP Algorithm
 */
public class SubstringSearch_KMP extends BaseTest {

    @Test
    public void testIndex() {
        Assert.assertEquals(6, substringSearchIndex("abcdfgcdeyz","cde"));
        Assert.assertEquals(8, substringSearchIndex("abcdfgyzcde","cde"));
        Assert.assertEquals(-1, substringSearchIndex("abcdfgcd","cde"));
        Assert.assertEquals(4, substringSearchIndex("abcdcdcdegyzcde","cdcde"));
        Assert.assertEquals(0, substringSearchIndex("AABAACAADAABAABA","AABA"));
        Assert.assertEquals(-1, substringSearchIndex("ABABABCABABABCABABABC","ABABAC"));
        Assert.assertEquals(6, substringSearchIndex("abxabcabcaby","abcaby"));
        Assert.assertEquals(-1, substringSearchIndex("abcdfgcd","cde"));
        Assert.assertEquals(-1, substringSearchIndex("ab","abcdef"));
    }

/*
    Time: O(N + M)
    Space: O(N)     --  Longest Proper Prefix
 */
    int substringSearchIndex(String str, String ptr) {
        int n = str.length(), m = ptr.length();
        int index = -1;

        if(n < m)   return index;

        int[] lps = generatePrefixArray(ptr);    // Time: O(M)  -- Longest Proper Prefix

        int i=0, j=0;

        while(i<n && j<m) {       // Time: O(N)
            if(str.charAt(i) == ptr.charAt(j)) {
                i++;    j++;
            }

            if (j == m) {
                index = i - m;      // storing the index of found substring - (Current index of String - length of Pattern)
                //j = lps[j - 1];
            }
            else if (i < n && str.charAt(i) != ptr.charAt(j)) {
                if(j != 0)     j = lps[j-1];
                else           i++;
            }
        }

        return index;
    }

    @Test
    public void test() {
        Assert.assertTrue(substringSearch("abcdfgcdeyz","cde"));
        Assert.assertTrue(substringSearch("abcdfgyzcde","cde"));
        Assert.assertTrue(substringSearch("abcdcdcdegyzcde","cdcde"));
        Assert.assertTrue(substringSearch("AABAACAADAABAABA","AABA"));
        Assert.assertFalse(substringSearch("ABABABCABABABCABABABC","ABABAC"));
        Assert.assertTrue(substringSearch("abxabcabcaby","abcaby"));
        Assert.assertFalse(substringSearch("abcdfgcd","cde"));
        Assert.assertFalse(substringSearch("abc","abcde"));
    }

/*
    Time: O(N + M)
    Space: O(N)     --  Longest Proper Prefix
 */
    boolean substringSearch(String str, String ptr) {
        int n = str.length(), m = ptr.length();

        if(n < m)     return false;

        int[] lps = generatePrefixArray(ptr);    // Time: O(M)  -- Longest Proper Prefix

        int i=0, j=0;
        while(i<n && j<m) {       // Time: O(N)
            if(str.charAt(i) == ptr.charAt(j)) {
                i++;    j++;
            }
            else {
                if(j != 0)     j = lps[j-1];
                else           i++;
            }
        }

        return j == m;
    }

    int[] generatePrefixArray(String ptr) {
        int[] prefix = new int[ptr.length()];
        for(int i=0, j=1; i<ptr.length() && j<ptr.length();) {
            if(ptr.charAt(j) == ptr.charAt(i)) {
                prefix[j] = i+1;
                i++;    j++;
            }
            else {
                if(i != 0)     i = prefix[i-1];
                else            j++;
            }
        }
        return prefix;
    }

}
