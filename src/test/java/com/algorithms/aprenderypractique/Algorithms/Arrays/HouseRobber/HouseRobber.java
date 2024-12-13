package com.algorithms.aprenderypractique.Algorithms.Arrays.HouseRobber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/house-robber
 */
public class HouseRobber extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4, rob(new int[]{1,2,3,1}));
        Assert.assertEquals(12, rob(new int[]{2,7,9,3,1}));
        Assert.assertEquals(4, rob(new int[]{2,1,1,2}));
    }

/*
    Time: O(N)
    Space: O(1)
*/
    public int rob(int[] nums) {
        int rob = 0;    // Max money can get if rob current house
        int notRob = 0; // Max money can get if not rob current house

        for(int house : nums) {
            int currRob = notRob + house; // if rob current value, previous house must not be robbed
            notRob = Math.max(notRob, rob); // if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
            rob = currRob;
        }

        return Math.max(rob, notRob);
    }

}
