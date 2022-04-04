package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
    https://leetcode.com/problems/sum-of-subarray-ranges/

    Asked in Amazon Similar Question
    @see Amazon2
 **/
public class SubArrayRanges extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3};
        Assert.assertEquals(4, subArrayRanges(nums));

        nums = new int[]{1,3,3};
        Assert.assertEquals(4, subArrayRanges(nums));

        nums = new int[]{4,-2,-3,4,1};
        Assert.assertEquals(59, subArrayRanges(nums));

        nums = new int[]{3, 3, 2, 3};
        Assert.assertEquals(5, subArrayRanges(nums));
    }

    public long subArrayRanges(int[] nums) {
        long ans = 0;
        for(int i=0; i<nums.length; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int j=i; j<nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += (max - min);
            }
        }
        return ans;
    }

}
