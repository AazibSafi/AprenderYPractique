package com.algorithms.aprenderypractique.Algorithms.Strings.Anagram;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/valid-anagram/
 * https://www.youtube.com/watch?v=ikO4qKG_IWc&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 *
 *  We can also solve this problem by using HashMap instead of an array but that will cost us an space.
 */
public class ValidAnagram extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(isAnagram("anagram", "nagaram"));
        Assert.assertFalse(isAnagram("rat", "car"));
    }

/*
    Time: O(n+m) where n and m is the length of s and t respectively
    Space: O(26) --> O(1) Constant
 */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] letters = new int[26];

        for(char c : s.toCharArray()) {
            letters[c-'a']++;
        }

        for(char c : t.toCharArray()) {
            letters[c-'a']--;
        }

        return IntStream.of(letters).allMatch(c -> c==0);
    }

}
