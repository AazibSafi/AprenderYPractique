package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.educative.io/find-all-palindrome-substrings
 *
 *  Same Logic as: https://leetcode.com/problems/palindromic-substrings
 *  It requires the substring with >= 2 length
 */
public class PalindromicSubstrings_2Chars extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(7, countSubstrings("aabbbaa"));
        Assert.assertEquals(6, countSubstrings("xxyyxxy"));
    }

/*
    Time: O(N^2)
    Space: O(1)
*/
    public int countSubstrings(String s) {
        int count = 0;
        for(int i=0; i<s.length(); i++) {
            count += extend(s, i-1, i+1);   // aba
            count += extend(s, i, i+1);        // aa
        }
        return count;
    }

    public int extend(String s, int i, int j) {
        int count = 0;
        while(i>=0 && j<s.length() && s.charAt(i--) == s.charAt(j++)) {
            count++;
        }
        return count;
    }

}
