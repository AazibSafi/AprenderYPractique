package com.algorithms.aprenderypractique.algorithm.strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*
 * Sliding Window Algorithm
 * Longest Substring Without Repeating Characters
 * https://www.youtube.com/watch?v=4i6-9IzQHwo&ab_channel=MichaelMuinos
 * https://leetcode.com/problems/longest-substring-without-repeating-characters
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
    Time: O(n + n) -> O(n) --> Each character is processed at most twice (once added and once removed from the HashSet)
    Space: O(n) --> In the worst case, the HashSet needs to store all the unique characters of the string
 */
    public int lengthOfLongestSubstring(String str) {
        Set<Character> set = new HashSet<>();

        int i = 0,  max = 0;
        for(int j=0; j<str.length(); j++) {
            while(set.contains(str.charAt(j))) {
                set.remove(str.charAt(i++));
            }

            set.add(str.charAt(j));
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

}
