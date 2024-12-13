package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/delete-operation-for-two-strings
 *
 *      Similar
 *      @see EditDistance
 *      @see OneEditDistance
 *
 *      Prerequisit
 *      @see LongestCommonSubSequence
 *
 *      Todo: https://leetcode.com/problems/make-three-strings-equal
 */
public class DeleteOperationForTwoStrings extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2, minDistance("sea", "eat"));
        Assert.assertEquals(4, minDistance("leetcode", "etco"));
    }

/*
    Time: O(M.N)
    Space: O(min(M.N))
    Problem Converted to longestCommonSubsequence
*/
    public int minDistance(String word1, String word2) {
        int lcs = new LongestCommonSubSequence().longestCommonSubsequence(word1, word2);
        return word1.length() + word2.length() - 2*lcs;
    }

}
