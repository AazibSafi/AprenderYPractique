package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 *  Calculate the minimum Letters to delete to make the frequency of each letter unique.
 *
 *  https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 *  https://www.youtube.com/watch?v=xzsAFSFiVF8
 */
public class MinDeletionToMakeFrequencyUnique extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(0, minDeletions("aab"));
        Assert.assertEquals(2, minDeletions("aaabbbcc"));
        Assert.assertEquals(2, minDeletions("ceabaacb"));
        Assert.assertEquals(0, minDeletions("aaaaaabbbcccc"));
        Assert.assertEquals(1, minDeletions("accdcdadddbaadbc"));
    }

    public int minDeletions(String s) {
        if(s==null || s.length()==0)    return 0;

        int minDeletion = 0;

        int[] alphabets = CommonHelper.fillArrayFrequencies(s);
        alphabets = Arrays.stream(alphabets).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int maxAllowedFrequency = alphabets[0];

        for(int currFreq : alphabets) {
            if(currFreq > maxAllowedFrequency) {
                if(maxAllowedFrequency > 0)     minDeletion += currFreq - maxAllowedFrequency;
                else                            minDeletion += currFreq;
            }

            maxAllowedFrequency = Math.min(currFreq - 1, maxAllowedFrequency - 1);
        }
        return minDeletion;
    }

}
