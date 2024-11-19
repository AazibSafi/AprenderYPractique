package com.algorithms.aprenderypractique.algorithm.Games.JumpGame;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/jump-game-vi
 *      https://leetcode.com/problems/jump-game-vi/solutions/1261213/java-journey-from-brute-force-to-most-optimized-dp-sliding-window-algorithm/
 *
 *      Todo: Understand Optimal solutions
 */
public class JumpGameVI extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(7, maxResult(new int[]{1,-1,-2,4,-7,3}, 2));
        Assert.assertEquals(17, maxResult(new int[]{10,-5,-2,4,0,3}, 3));
        Assert.assertEquals(0, maxResult(new int[]{1,-5,-20,4,-1,3,-6,-3}, 2));
    }

/*
    Time: O(K*n)
     As at each node we have k next nodes to be processed. So k,k,k.....n times gives k*n.
    BruteForce Approach - TLE
 */
    public int maxResult(int[] nums, int k) {
        int standingPosition = 0;
        int score = nums[0];
        int n = nums.length;

        while(standingPosition < n-1) {
            int max = Integer.MIN_VALUE, maxInd = 0;

            for(int j=standingPosition+1; j<=Math.min(n-1, standingPosition+k); j++) {
                if(nums[j] > max) {
                    max = nums[j];
                    maxInd = j;
                }
            }
            score += max;
            standingPosition = maxInd;
        }

        return score;
    }

}
