package com.algorithms.aprenderypractique.algorithm.arrays.Digits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/remove-k-digits/
 *      https://www.youtube.com/watch?v=3QJzHqNAEXs&t=228s
 */
public class RemoveKDigits extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("1219", removeKdigits("1432219", 3));
        Assert.assertEquals("200", removeKdigits("10200", 1));
        Assert.assertEquals("0", removeKdigits("10", 2));
    }

    public String removeKdigits(String num, int k) {
        return "";
    }

}
