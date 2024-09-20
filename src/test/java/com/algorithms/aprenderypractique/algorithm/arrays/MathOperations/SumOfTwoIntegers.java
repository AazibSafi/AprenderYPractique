package com.algorithms.aprenderypractique.algorithm.arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *    https://leetcode.com/problems/sum-of-two-integers
 *    https://leetcode.com/problems/sum-of-two-integers/solutions/1564265/java-1-liner-0-ms-explained/?envType=problem-list-v2&envId=xi4ci4ig
 *    https://www.youtube.com/watch?v=gVUrDV4tZfY
 */
public class SumOfTwoIntegers extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, getSum(1,2));
        Assert.assertEquals(5, getSum(2,3));
    }

/*
    Time: O(1) bcz of Constraint defined (-1000 <= a, b <= 1000)
    Space: O(1)
*/
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }
    public int getSum2(int a, int b) {
        return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
    }

}
