package com.algorithms.aprenderypractique.algorithm.arrays.UglyNumber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 *      https://leetcode.com/problems/ugly-number
 *      https://leetcode.com/problems/ugly-number/submissions/1468765155
 */
public class UglyNumber extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isUgly(6));
        Assert.assertTrue(isUgly(1));
        Assert.assertTrue(isUgly(20));
        Assert.assertFalse(isUgly(14));
    }

/*
    Time: O(log(N))
    Space: O(1)
*/
    public boolean isUgly(int n) {
        if(n <= 0)  return false;

        for(int factor : List.of(2,3,5)) {
            while(n % factor == 0) {
                n /= factor;
            }
        }
        return n==1;
    }

}
