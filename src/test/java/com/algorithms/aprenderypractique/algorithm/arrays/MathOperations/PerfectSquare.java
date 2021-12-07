package com.algorithms.aprenderypractique.algorithm.arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class PerfectSquare extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isPerfectSquare(16));
        Assert.assertFalse(isPerfectSquare(14));
    }

/**
    Time: O(logN)
    Using Binary Search Technique
    @see SquareRoot
 */
    public boolean isPerfectSquare(int num) {
        if(num < 2)    return true;

        long start = 1;
        long end = num;

        while(start < end) {
            long mid = (start + end) / 2;
            long square = mid * mid;

            if(square == num)     return true;

            else if(square > num)      end = mid;
            else if(square < num)      start = mid + 1;
        }

        return false;
    }

}
