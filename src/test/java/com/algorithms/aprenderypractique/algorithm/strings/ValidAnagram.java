package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-anagram/
 * https://www.youtube.com/watch?v=ikO4qKG_IWc&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 */
public class ValidAnagram extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(isAnagram("anagram", "nagaram"));
        Assert.assertFalse(isAnagram("rat", "car"));
    }

    public boolean isAnagram(String s, String t) {
        int[] letters = new int[26];

        for(char c : s.toCharArray()) {
            letters[c - 'a']++;
        }
        for(char c : t.toCharArray()) {
            letters[c - 'a']--;
        }

        for(int letter :  letters) {
            if(letter != 0)
                return false;
        }
        return true;
    }
}
