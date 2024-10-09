package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/palindromic-substrings
 *  Same Logic as: https://leetcode.com/problems/longest-palindromic-substring/
 */
public class PalindromicSubstrings extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, countSubstrings("abc"));
        Assert.assertEquals(6, countSubstrings("aaa"));
        Assert.assertEquals(7, countSubstrings("ABCBA"));
        Assert.assertEquals(1000, countSubstrings("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijkl"));
    }

/*
    Time: O(N^2)
    Space: O(1)
*/
    public int countSubstrings(String s) {
        int count = 0;
        for(int i=0; i<s.length(); i++) {
            count += expand(s, i, i);
            count += expand(s, i, i+1);
        }
        return count;
    }

    int expand(String s, int i, int j) {
        int count=0;
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
            i--; j++; count++;
        }
        return count;
    }

/*
    Time: O(n^3)
    Not Optimal
*/
    public int countSubstrings2(String s) {
        int count=0;
        for(int i=0; i<s.length(); i++) {
            for(int j=i; j<s.length(); j++) {
                if(isPalindrome(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    boolean isPalindrome(String s, int i, int j) {
        while(i<j) {
            if(s.charAt(i++)!=s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
