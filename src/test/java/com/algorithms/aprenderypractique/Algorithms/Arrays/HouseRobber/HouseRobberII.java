package com.algorithms.aprenderypractique.Algorithms.Arrays.HouseRobber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/house-robber-ii
 *      https://algo.monster/liteproblems/213
 */
public class HouseRobberII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, rob(new int[]{2,3,2}));
        Assert.assertEquals(4, rob(new int[]{1,2,3,1}));
        Assert.assertEquals(3, rob(new int[]{1,2,3}));
    }

/*
    Time: O(N)
    Space: O(1)
*/
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        return Math.max(solve(nums, 0, n-1), solve(nums, 1, n));
    }

    int solve(int[] nums, int start, int end) {
        int rob = 0, notRob = 0;

        for(int i=start; i<end; i++) {
            int currRob = notRob + nums[i];
            notRob = Math.max(rob, notRob);
            rob = currRob;
        }

        return Math.max(rob, notRob);
    }

}
