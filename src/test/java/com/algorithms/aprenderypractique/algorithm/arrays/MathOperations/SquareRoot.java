package com.algorithms.aprenderypractique.algorithm.arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/sqrtx/
 *      https://www.youtube.com/watch?v=VYtEKhxKd1Q&ab_channel=TerribleWhiteboard
 */
public class SquareRoot extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, mySqrt(4));
        Assert.assertEquals(2, mySqrt(8));
        Assert.assertEquals(3, mySqrt(15));
        Assert.assertEquals(4, mySqrt(16));
        Assert.assertEquals(4, mySqrt(23));
        Assert.assertEquals(0, mySqrt(0));
        Assert.assertEquals(1, mySqrt(1));
        Assert.assertEquals(46339, mySqrt(2147395599));
    }

/*
    Time: O(logN)
    Using Binary Search Technique
 */
    public int mySqrt(int x) {
        if(x < 2)    return x;

        long start = 1;
        long end = x;

        while(start < end) {
            long mid = (start + end) / 2;
            long square = mid * mid;

            if(square == x)     return (int) mid;     // in case if x is the Perfect Square

            else if(square > x)      end = mid;
            else if(square < x)      start = mid + 1;
        }

        return (int) start-1;
    }

}
