package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *    https://leetcode.com/problems/longest-palindromic-substring/
 *
 *  For Efficient Algorithm
 *  @see LongestPalindromeSubstring_ManachersAlgorithm
 */
public class LongestPalindromeSubstring extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("BABCBAB", longestPalindrome("BABCBAB"));
        Assert.assertEquals("bacabacab", longestPalindrome("abacabacabb"));
        Assert.assertEquals("bab", longestPalindrome("babad"));
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
        Assert.assertEquals("acaca", longestPalindrome("acacacb"));       //    or cacac
    }

/*
    Time: O(N^2)
    Space: O(1)
*/
    public String longestPalindrome(String s) {
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = expand(s, i, i), s2 = expand(s, i, i + 1);
            if (s1.length() > max.length()) max = s1;
            if (s2.length() > max.length()) max = s2;
        }
        return max;
    }

    String expand(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
            i--; j++;
        }
        return s.substring(i+1,j);
    }

}
