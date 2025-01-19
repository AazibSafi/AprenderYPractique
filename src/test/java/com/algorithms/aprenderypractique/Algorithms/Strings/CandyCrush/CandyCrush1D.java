package com.algorithms.aprenderypractique.Algorithms.Strings.CandyCrush;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/discuss/interview-question/380650/Bloomberg-or-Phone-Screen-or-Candy-Crush-1D
 *
 *      Similar
 *      @see RemoveAllAdjacentDuplicatesInStringII
 */
public class CandyCrush1D extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("c", greedyCandyCrush("aaabbbc"));
        Assert.assertEquals("cd", greedyCandyCrush("aabbbacd"));
        Assert.assertEquals("", greedyCandyCrush("aabbccddeeedcba"));
        Assert.assertEquals("acd", greedyCandyCrush("aaabbbacd"));
    }

    public String greedyCandyCrush(String s) {
        return new RemoveAllAdjacentDuplicatesInStringII().removeDuplicates(s, 3);
    }

}
