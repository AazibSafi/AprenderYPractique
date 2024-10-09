package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *    https://leetcode.com/problems/longest-palindromic-substring/
 *  For Efficient Algorithm
 *  @see LongestPalindromeSubstring
 */
public class LongestPalindromeSubstring_Length extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(7, longestPalindrome("BABCBAB"));
        Assert.assertEquals(9, longestPalindrome("abacabacabb"));   //   bacabacab
        Assert.assertEquals(3, longestPalindrome("babad"));         //   bab
        Assert.assertEquals(2, longestPalindrome("cbbd"));          //   bb
        Assert.assertEquals(5, longestPalindrome("acacacb"));       //   acaca or cacac
    }

/*
    Time: O(N^2)
    Space: O(1)
*/
    public int longestPalindrome(String str) {
        int max = 0;
        for(int i=0; i<str.length(); i++) {
            max = Math.max(max, expand(str, i, i));
            max = Math.max(max, expand(str, i, i+1));
        }
        return max;
    }

    int expand(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
            i--; j++;
        }
        return j-i-1;
    }

}
