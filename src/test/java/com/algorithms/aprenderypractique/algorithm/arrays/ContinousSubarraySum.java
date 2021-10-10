package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/continuous-subarray-sum/
 *  https://www.youtube.com/watch?v=G_N0H7tFLTY&ab_channel=WorkWithGoogler
 *
 *  https://dev.to/sphoorthi/leetcode-continuous-subarray-sum-459o
 *  http://buttercola.blogspot.com/2018/10/leetcode-523-continuous-subarray-sum.html
 *
 *  Given an integer array nums and an integer k, return true if nums has a continuous subarray
 *  of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 *      a % k == c &&
 *      b % k == c
 *
 *      Then (a - b) % k == 0.
 *      why? Because a = m * k + c, b = n * k + c, then a - b = (m - n) * k, which modular b is 0.
 */
public class ContinousSubarraySum extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{23,2,4,6,7};
        Assert.assertTrue(checkSubarraySum(arr,6));

        arr = new int[]{23,2,6,4,7};
        Assert.assertTrue(checkSubarraySum(arr,6));

        arr = new int[]{23,2,4,6,6};      // Edge Case: Where mod 0 (35%7) comes only once in the map (To fulfill the math formula described)
        Assert.assertTrue(checkSubarraySum(arr,7));

        arr = new int[]{23,2,6,4,7};
        Assert.assertFalse(checkSubarraySum(arr,13));

        arr = new int[]{1,0};
        Assert.assertFalse(checkSubarraySum(arr,2));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int minLength = 2;

        if(nums == null || nums.length < minLength)   return false;

        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);      // Edge Case

        int sum=0;

        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            int mod = sum%k;

            if(map.containsKey(mod) && (i-map.get(mod) >= minLength) ) {
                    return true;
            }

            if(!map.containsKey(mod))
                map.put(mod,i);
        }

        return false;
    }

    @Test
    public void testArray() {
        int[] arr = new int[]{23,2,4,6,7};
        Assert.assertArrayEquals(new int[]{2,4},getSubarraySum(arr,6));

        arr = new int[]{23,2,6,4,7};
        Assert.assertArrayEquals(new int[]{2,6,4},getSubarraySum(arr,6));

        arr = new int[]{23,2,4,6,6};      // Edge Case: Where mod 0 (35%7) comes only once in the map (To fulfill the math formula described)
        Assert.assertArrayEquals(new int[]{23,2,4,6},getSubarraySum(arr,7));

        arr = new int[]{23,2,6,4,7};
        Assert.assertArrayEquals(new int[]{},getSubarraySum(arr,13));

        arr = new int[]{1,0};
        Assert.assertArrayEquals(new int[]{},getSubarraySum(arr,2));
    }

    public int[] getSubarraySum(int[] nums, int k) {
        int minLength = 2;

        if(nums == null || nums.length < minLength)
            return new int[0];

        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);      // Edge Case

        int sum=0;

        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            int mod = sum%k;

            if(map.containsKey(mod) && (i-map.get(mod) >= minLength) ) {
                return Arrays.copyOfRange(nums, map.get(mod)+1, i+1);
            }

            if(!map.containsKey(mod))
                map.put(mod,i);
        }

        return new int[0];
    }

}
