package com.algorithms.aprenderypractique.algorithm.strings.AlienDictionary;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/verifying-an-alien-dictionary/
 *  https://www.youtube.com/watch?v=qSbJZWENtX4
 */
public class VerifyAlienDictionary extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        Assert.assertTrue(isAlienSorted(new String[]{"ubg", "kwh"}, "qcipyamwvdjtesbghlorufnkzx"));
        Assert.assertFalse(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"az", "abc"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMapping = new int[26];
        int orderPriority=0;
        for(char c : order.toCharArray())
            orderMapping[c-'a'] = orderPriority++;

        for(int w=1; w<words.length; w++) {
            if(compare(words[w-1], words[w], orderMapping) > 0) {
                return false;
            }
        }
        return true;
    }

/**
 * Reference
 * @see String compareTo()
 */
    int compare(String word1, String word2, int[] orderMapping) {
        int len1 = word1.length();
        int len2 = word2.length();
        int minLen = Math.min(len1, len2);

        for(int i=0; i<minLen; i++) {
            int c1 = orderMapping[word1.charAt(i) - 'a'];
            int c2 = orderMapping[word2.charAt(i) - 'a'];
            if (c1 != c2)
                return c1 - c2;     // when there is a mismatch return the diff
        }

/*
  At this point no mismatch is found
  Either both words are equal in size -- difference is 0

  Or one of the word is smaller in size and the small word is the prefix of the other word. Since all the chars were matched above.
  In this case:
    if 1st word length is small - return Negative int
    if 2nd word length is small - return Positive int
 */
        return len1 - len2;
    }

}
