package com.algorithms.aprenderypractique.Algorithms.Strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *      Sliding Window Algorithm
 *      Longest Substring Without Repeating Characters
 *      https://www.youtube.com/watch?v=4i6-9IzQHwo&ab_channel=MichaelMuinos
 *      https://leetcode.com/problems/longest-substring-without-repeating-characters
 */
public class LongestSubstringWithoutRepeatingCharacters extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("PWWKEW"));     //  wke
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));   //  abc
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));      //  b
        Assert.assertEquals(0, lengthOfLongestSubstring(""));
        Assert.assertEquals(5, lengthOfLongestSubstring("1a b@"));   // Constraints: str consists of English letters, digits, symbols and spaces.
    }

/*
    Approach: Sliding Window
    Time: O(n + n) -> O(n) --> Each character is processed at most twice (once added and once removed from the HashSet)
    Space: O(n) --> In the worst case, the HashSet needs to store all the unique characters of the string
 */
    public int lengthOfLongestSubstring(String str) {
        Set<Character> set = new HashSet<>();

        int left = 0,  max = 0;
        for(int right=0; right<str.length(); right++) {
            while(set.contains(str.charAt(right))) {
                set.remove(str.charAt(left++));
            }

            set.add(str.charAt(right));
            max = Math.max(max, right - left + 1);  // Length of substring(left, right+1)
        }
        return max;
    }

}
