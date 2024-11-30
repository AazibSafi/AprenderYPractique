package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/delete-operation-for-two-strings
 *
 *      Similar
 *      @see EditDistance
 *      @see OneEditDistance
 */
public class DeleteOperationForTwoStrings extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2, minDistance("sea", "eat"));
        Assert.assertEquals(4, minDistance("leetcode", "etco"));
    }

    public int minDistance(String word1, String word2) {
        return 0;
    }

}
