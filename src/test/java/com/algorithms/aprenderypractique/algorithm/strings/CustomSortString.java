package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/custom-sort-string/
 *  https://www.youtube.com/watch?v=edluuHln06g
 */
public class CustomSortString extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("cbad", customSortString("cba", "abcd"));
        Assert.assertEquals("cbad", customSortString("cbafg", "abcd"));
    }

    public String customSortString(String order, String s) {
        StringBuilder output = new StringBuilder();

        int[] freq = new int[26];
        for(int i=0; i<s.length(); i++) freq[s.charAt(i)-'a']++;

        for(char orderChar : order.toCharArray()) {
            while(freq[orderChar-'a'] > 0) {
                output.append(orderChar);
                freq[orderChar-'a']--;
            }
        }
        for(int i=0; i<freq.length; i++) {
            while(freq[i] > 0) {
                output.append((char) (i+'a'));
                freq[i]--;
            }
        }
        return output.toString();
    }

}
