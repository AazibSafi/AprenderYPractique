package com.algorithms.aprenderypractique.Algorithms.Arrays.MaxConsecutiveOnes;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/max-consecutive-ones/
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 */
public class MaxConsecutiveOnes extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
        Assert.assertEquals(2, findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
        Assert.assertEquals(0, findMaxConsecutiveOnes(new int[]{0,0,0}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = Integer.MIN_VALUE;
        int count=0;

        for(int x : nums) {
            if(x == 1) {
                count++;
            }
            else if(x == 0) {
                max = Math.max(max, count);
                count=0;
            }
        }
        max = Math.max(max, count);
        return max;
    }

}
