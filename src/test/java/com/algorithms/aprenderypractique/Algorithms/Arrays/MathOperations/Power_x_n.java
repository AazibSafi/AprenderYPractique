package com.algorithms.aprenderypractique.Algorithms.Arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/powx-n/
 *  https://www.youtube.com/watch?v=g9YQyYi4IQQ&ab_channel=NeetCode
 *
 *  Power must be an Integer - No Decimal values
 *
 *  Asked in Facebook Final Round
 */
public class Power_x_n extends BaseTest {

/*
COMPARING DOUBLE WITH PRECISION
    double epsilon = 0.000001d;
    assertThat(Math.abs(d1 - d2) < epsilon).isTrue();
 */
    @Test
    public void test() {
        double epsilon = 0.000001d;
        Assert.assertTrue(Math.abs(myPow(2.00000, 10) - 1024.00000) < epsilon);
        Assert.assertTrue(Math.abs(myPow(2.10000, 3) - 9.26100) < epsilon);
        Assert.assertTrue(Math.abs(myPow(2.00000, -2) - 0.25000) < epsilon);
        Assert.assertTrue(Math.abs(myPow(0, 0) - 1) < epsilon);
        Assert.assertTrue(Math.abs(myPow(1.00000, 2147483647) - 1.0) < epsilon);
        Assert.assertTrue(Math.abs(myPow(2.00000, -2147483648) - 0.00000) < epsilon);
    }

/*
    Time: O(logN)
    Using Binary Search Technique
 */
    public double myPow(double x, int p) {
        double result = power(x, Math.abs(p));
        return p < 0 ? 1/result : result;
    }

    public double power(double x, int p) {
        if(p==0) return 1;
        if(p==1) return x;

        double prod = power(x,p/2);
        return p%2==0 ? (prod * prod) : (x * prod * prod);
    }

}
