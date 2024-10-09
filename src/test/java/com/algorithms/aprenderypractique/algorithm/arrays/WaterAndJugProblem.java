package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/water-and-jug-problem
 *      https://leetcode.com/problems/water-and-jug-problem/solutions/3078684/simple-java-code-logic-behind-using-gcd
 *
 *      https://leetcode.com/discuss/interview-question/4773975/Google
 *
 *      Todo: Look for optimize solution
 */
public class WaterAndJugProblem extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(canMeasureWater(3, 5, 4));
        Assert.assertTrue(canMeasureWater(1, 2, 3));
        Assert.assertTrue(canMeasureWater(1, 4, 4));
        Assert.assertTrue(canMeasureWater(1, 2, 3));
        Assert.assertFalse(canMeasureWater(2, 2, 8));
        Assert.assertFalse(canMeasureWater(2, 6, 5));
    }

/*
    Time: O(log(min(a,b))
    Space: O(1)
*/
    public boolean canMeasureWater(int x, int y, int target) {
        if (x + y < target)     return false;
        if(x == target || y == target || x+y == target)     return true;
        return target % gcd(x, y) == 0;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int gcd_Rec(int a, int b) {
        return b==0 ? a : gcd_Rec(b, a%b);
    }

}
