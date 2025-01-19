package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 *      https://leetcode.com/problems/remove-letter-to-equalize-frequency
 */
public class RemoveLetterToEqualizeFrequency extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(equalFrequency("abcc"));
        Assert.assertTrue(equalFrequency("abc"));
        Assert.assertFalse(equalFrequency("aazz"));
        Assert.assertTrue(equalFrequency("cbbd"));
        Assert.assertTrue(equalFrequency("aabbccddd"));
        Assert.assertTrue(equalFrequency("aa"));
        Assert.assertTrue(equalFrequency("a"));
        Assert.assertFalse(equalFrequency("")); // At least one letter should be removed
    }

/*
    Time: O(n*26) = O(n)
    Space: O(26) = O(1)
*/
    public boolean equalFrequency(String word) {
        int[] freq = new int[26];
        for(char c : word.toCharArray()) {      // O(n)
            freq[c - 'a']++;
        }

        for(int i=0; i<26; i++) {  // O(26)
            if(freq[i] > 0) {
                freq[i]--;

                if(isAllEqual(freq))        // O(n)
                    return true;

                freq[i]++;
            }
        }

        return false;
    }

    // Check if counts of each elements are equal
    public boolean isAllEqual(int[] arr) {
        int count = 0;
        for(int x : arr) {
            if(x == 0)  continue;   // Letter not available in the input word
            else if(count == 0)  count = x;  // Set the first frequency
            else if(count != x)  return false;  // Frequency mismatch
        }
        return true;
    }

}
