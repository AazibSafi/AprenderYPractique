package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Kadane's Algorithm
 *  Maximum Product Subarray
 *  https://www.youtube.com/watch?v=QQVCKkImH_s
 *  https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray_KadaneAlgo {

    @Test
    public void test() {
        int[] array = new int[] {2,3,-2,4};
        Assert.assertEquals(6,maxProduct(array));

        array = new int[] {-2,0,-1};
        Assert.assertEquals(0,maxProduct(array));

        array = new int[] {-2,3,-4};
        Assert.assertEquals(24,maxProduct(array));
    }

    public int maxProduct(int[] nums) {
        if(nums.length == 0)    return 0;

        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++) {
            int x = nums[i];
            int tempMax = Math.max(x * max , Math.max(x * min , x));
            min = Math.min(x * max , Math.min(x * min , x));

            max = tempMax;
            result = Math.max(result, max);
        }

        return result;
    }

}
