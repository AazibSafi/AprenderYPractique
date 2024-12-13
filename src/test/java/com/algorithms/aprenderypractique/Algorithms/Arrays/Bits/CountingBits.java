package com.algorithms.aprenderypractique.Algorithms.Arrays.Bits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/counting-bits/
 */
public class CountingBits extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{0,1,1}, countBits(2));
        Assert.assertArrayEquals(new int[]{0,1,1,2,1,2}, countBits(5));
    }

/*
    Time: O(N)
    Space: O(1)
 */
    public int[] countBits(int n) {
        int[] output = new int[n+1];
        output[0] = 0;

        for(int i=1; i<=n; i++) {
            output[i] = output[i/2] + i%2;
        }
        return output;
    }
}

/*  Logic
    result[i] = result[i/2] + i%2;

    If 'x' is even, set the x-th element of the array to
    the (x / 2)-th element

    If x is odd, set the x-th element of the array to
    the (x / 2)-th element + 1
 */