package com.algorithms.aprenderypractique.Algorithms.Strings.CandyCrush;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii
 *
 *      Similar:
 *      @see CandyCrush1D
 */
public class RemoveAllAdjacentDuplicatesInStringII extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("abcd", removeDuplicates("abcd",2));
        Assert.assertEquals("aa", removeDuplicates("deeedbbcccbdaa",3));
        Assert.assertEquals("ps", removeDuplicates("pbbcggttciiippooaais",2));
        Assert.assertEquals("c", removeDuplicates("aaabbbc",3));
        Assert.assertEquals("cd", removeDuplicates("aabbbacd",3));
        Assert.assertEquals("", removeDuplicates("aabbccddeeedcba",3));
        Assert.assertEquals("acd", removeDuplicates("aaabbbacd",3));
    }

/*
    Approach - Memoise Count
    Time: O(n)
    Space: O(n)
*/
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];

        for(int i=0; i<sb.length(); i++) {
            if(i > 0 && sb.charAt(i) == sb.charAt(i-1)) {
                count[i] = count[i-1] + 1;
                if(count[i] == k) {
                    sb.delete(i-k+1, i+1);
                    i = i - k;
                }
            }
            else
                count[i] = 1;
        }
        return sb.toString();
    }

}
