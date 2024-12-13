package com.algorithms.aprenderypractique.Algorithms.Strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/shortest-palindrome/
 *
 *  Logic understanding:    https://www.youtube.com/watch?v=c4akpqTwE5g&ab_channel=IDeserve
 *  Code Understanding:     https://just4once.gitbooks.io/leetcode-notes/content/leetcode/string/214-shortest-palindrome.html
 */
public class ShortestPalindrome extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("aaacecaaa", shortestPalindrome("aacecaaa"));
        Assert.assertEquals("dcbabcd", shortestPalindrome("abcd"));
        Assert.assertEquals("abbaabba", shortestPalindrome("aabba"));
    }

    public String shortestPalindrome(String str) {
        String revStr = new StringBuilder(str).reverse().toString();
        int longestCommonPrefix = KMP_LPS(str + "#" + revStr);
        return revStr.substring(0, revStr.length() - longestCommonPrefix) + str;
    }

/**
 * @see com.algorithms.aprenderypractique.Algorithms.Strings.SubstringSearch_KMP
 */
    int KMP_LPS(String s) {
        int n = s.length();
        int[] lps = new int[n];

        int i=0, j=1;
        while(i<n && j<n) {
            if(s.charAt(i) == s.charAt(j)) {
                lps[j] = i+1;
                i++; j++;
            }
            else {
                if(i == 0)  j++;
                else        i = lps[i-1];
            }
        }
        return lps[n-1];
    }

}
