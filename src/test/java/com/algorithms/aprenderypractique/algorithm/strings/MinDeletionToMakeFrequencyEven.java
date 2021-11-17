package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  Calculate the minimum Letters to delete to make the frequency of each letter as even.
 */
public class MinDeletionToMakeFrequencyEven extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, solution("acbcbba"));
        Assert.assertEquals(2, solution("axxaxa"));
        Assert.assertEquals(0, solution("aaaa"));
    }

    public int solution(String S) {
        if(S==null || S.length()==0)      return 0;

        int minLettersToDelete = 0;

        Map<Character, Integer> frequency = new HashMap<>();

        for(Character c : S.toCharArray()) {
            frequency.put( c, frequency.getOrDefault(c, 0) + 1 );
        }

        for(Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            if(entry.getValue() % 2 == 1) {
                minLettersToDelete++;
            }
        }

        return minLettersToDelete;
    }

}
