package com.algorithms.aprenderypractique.Algorithms.Strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Longest Palindrome Substring
 *
 *  HARD Problem
 *
 *  Other Solutions of this problem are O(n^3) or O(n^2)
 *  But this Manacher's Algorithm is O(N) is Linear
 *
 *  https://hackernoon.com/manachers-algorithm-explained-longest-palindromic-substring-22cb27a5e96f
 */
public class LongestPalindromeSubstring_ManachersAlgorithm extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("BABCBAB", longestPalindrome("BABCBAB"));
        Assert.assertEquals("caac", longestPalindrome("acaac"));
        Assert.assertEquals("abba", longestPalindrome("abacdfgdcabba"));
        Assert.assertEquals("abacdedcaba", longestPalindrome("abacdedcaba"));
        Assert.assertEquals("a", longestPalindrome("a"));
        Assert.assertEquals("a", longestPalindrome("ac"));
    }

/*
    Manacher's Algorithm
        Time Complexity: O(N)
        Space Complexity: O(N)
 */
    public String longestPalindrome(String str) {
        String LPS = CommonHelper.modifyString(str,"#");

        int[] P = new int[LPS.length()];

        int C = 0;      // stores the center of the longest palindromic substring until now
        int R = 0;      // stores the right boundary of the longest palindromic substring until now
        int iMirror;
        int maxLenIndex = 0;        // stores the centre of Max Palindrome String Length

        for(int i=0; i< LPS.length(); i++) {

            iMirror = 2*C - i;       // get mirror index of i

            if(R > i) {
                P[i] = Math.min(R-i, P[iMirror]);
            }

//  expand at i towards left and right
            int left = i - 1 - P[i];
            int right = i + 1 + P[i];
            while( left>=0 && right<LPS.length() &&
                    LPS.charAt(left) == LPS.charAt(right) ) {

                P[i]++;
                left = i - 1 - P[i];
                right = i + 1 + P[i];

            }

            if( i+P[i] > R ) {
                C = i;         // the new center is i
                R = i + P[i];

                if(P[i] > P[maxLenIndex]) {
                    maxLenIndex = i;        // Storing centre index
                }
            }

        }

/*
    maxLenIndex centre index of Longest Palindromic Substring
    P[maxLenIndex] is the Half Length of Longest Palindromic Substring
 */
        return LPS.substring(maxLenIndex-P[maxLenIndex], maxLenIndex+P[maxLenIndex])
                .replace("#","");
    }

}
