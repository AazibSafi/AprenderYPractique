package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *  https://leetcode.com/problems/palindrome-number
 */
public class PalindromeNumber extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isPalindrome(121));
        Assert.assertTrue(isPalindrome(362263));
        Assert.assertFalse(isPalindrome(-121));
        Assert.assertFalse(isPalindrome(100901));
        Assert.assertFalse(isPalindrome(10));
    }

//      Time: O(n/2)
//  Check only half of the digits of given number to prevent OVERFLOW
    public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return (x==rev || x==rev/10);
    }

//      Time: O(n)
    public boolean isPalindrome2(int x) {
        if(x<0) return false;
        if(x==0) return true;

        int revX=0;

        while(x != 0) {
            int remainder = x % 10;
            x = x / 10;

            revX = (revX * 10) + remainder;
        }

        return x == revX;
    }

}
