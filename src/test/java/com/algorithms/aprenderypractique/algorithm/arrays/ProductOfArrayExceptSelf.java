package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/product-of-array-except-self/
 *  https://www.youtube.com/watch?v=bNvIQI2wAjk
 */
public class ProductOfArrayExceptSelf  extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 4};
        Assert.assertArrayEquals(new int[]{24, 12, 8, 6}, productExceptSelf(array));

        array = new int[]{-1, 1, 0, -3, 3};
        Assert.assertArrayEquals(new int[]{0, 0, 9, 0, 0}, productExceptSelf(array));
    }

//    Time: O(n)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        if(n==0)    return new int[0];

        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] output = new int[n];

        prefix[0] = nums[0];
        for(int i=1; i<n; i++) {
            prefix[i] = nums[i] * prefix[i-1];
        }

        suffix[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--) {
            suffix[i] = nums[i] * suffix[i+1];
        }

        for(int i=0; i<n; i++) {
            int pre = (i-1 < 0) ? 1 : prefix[i-1];
            int suf = (i+1 >= n) ? 1 : suffix[i+1];
            output[i] = pre * suf;
        }

        return output;
    }

}
