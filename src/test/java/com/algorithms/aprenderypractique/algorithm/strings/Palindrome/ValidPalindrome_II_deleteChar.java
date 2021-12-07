package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/valid-palindrome-ii/
 *  https://www.youtube.com/watch?v=L_74qbyPHXE&ab_channel=KevinNaughtonJr.
 *
 *  Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 */
public class ValidPalindrome_II_deleteChar extends BaseTest {

    @Test
    public void test() {
        String str = "aba";
        Assert.assertTrue(validPalindrome(str));

        str = "abca";
        Assert.assertTrue(validPalindrome(str));

        str = "abcffba";
        Assert.assertTrue(validPalindrome(str));

        str = "abcfba";
        Assert.assertTrue(validPalindrome(str));

        str = "abc";
        Assert.assertFalse(validPalindrome(str));

        str = "abcfeba";
        Assert.assertFalse(validPalindrome(str));
    }

//  Time: O(N)
    public boolean validPalindrome(String s) {
        int n = s.length();

        for(int i=0, j=n-1; i<j; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s,i, j-1) || isPalindrome(s,i+1, j);
            }
        }
        return true;
    }

    boolean isPalindrome(String s, int i, int j) {
        while(i<j) {
            if(s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

}
