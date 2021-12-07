package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*
 * Sliding Window Algorithm
 * Longest Substring Without Repeating Characters
 * https://www.youtube.com/watch?v=4i6-9IzQHwo&ab_channel=MichaelMuinos
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring_SlidingWindow extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("PWWKEW"));     //  wke
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));   //  abc
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));      //  b
        Assert.assertEquals(0, lengthOfLongestSubstring(""));
    }

    public int lengthOfLongestSubstring(String str) {
        Set<Character> set = new HashSet<>();

        int left = 0,  max = 0;
        for(int right=0; right<str.length(); right++) {
            char c = str.charAt(right);

            while(set.contains(c)) {
                set.remove(str.charAt(left));
                left++;
            }

            max = Math.max(max, right - left + 1);
            set.add(c);
        }
        return max;
    }

}
