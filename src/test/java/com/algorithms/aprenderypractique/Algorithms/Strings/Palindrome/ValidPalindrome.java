package com.algorithms.aprenderypractique.Algorithms.Strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome("race a car"));
        Assert.assertTrue(isPalindrome(" "));
        Assert.assertFalse(isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while(left < right) {
            if(!Character.isLetterOrDigit(s.charAt(left)))
                left++;

            else if(!Character.isLetterOrDigit(s.charAt(right)))
                right--;

            else if(lower(s.charAt(left++)) != lower(s.charAt(right--)))
                return false;
        }

        return true;
    }

    public char lower(char c) {
        return Character.toLowerCase(c);
    }

}
