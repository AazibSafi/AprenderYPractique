package com.algorithms.aprenderypractique.Microsoft;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  Calculate the minimum Letters to delete to make the frequency of each letter as even.
 */
public class Task1 extends BaseTest {

    @Test
    public void test() {
        String S = "acbcbba";
        Assert.assertEquals(1, solution(S));

        S = "axxaxa";
        Assert.assertEquals(2, solution(S));

        S = "aaaa";
        Assert.assertEquals(0, solution(S));
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
