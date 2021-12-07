package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/palindrome-permutation/
 *  https://www.youtube.com/watch?v=Pp5hdsNdqOU
 */
public class Palindrome_Permutation extends BaseTest {

    @Test
    public void test() {
        Assert.assertFalse(canPermutePalindrome("code"));
        Assert.assertTrue(canPermutePalindrome("aab"));
        Assert.assertTrue(canPermutePalindrome("carerac"));
    }

    public boolean canPermutePalindrome(String s) {
        int[] freq = new int[128];
        for(char c : s.toCharArray()) {
            freq[c-'a']++;
        }

        int count=0;
        for(char c : s.toCharArray()) {
            if(freq[c-'a'] < 2) {
                count++;
            }
        }

        return count < 2;
    }

}
