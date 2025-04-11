package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/product-of-array-except-self
 *  https://www.youtube.com/watch?v=bNvIQI2wAjk
 *
 *  Code: https://thechanmoon.medium.com/leetcode-238-product-of-array-except-self-c-c-c-java-javascript-ruby-e7c2b53e4a27
 */
public class ProductOfArrayExceptSelf  extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 4};
        Assert.assertArrayEquals(new int[]{24, 12, 8, 6}, productExceptSelf(array));

        array = new int[]{-1, 1, 0, -3, 3};
        Assert.assertArrayEquals(new int[]{0, 0, 9, 0, 0}, productExceptSelf(array));
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public int[] productExceptSelf(int[] nums) {
        int[] product = new int[nums.length];
        int left = 1, right = 1;

        for(int i=0; i<nums.length; i++) {
            product[i] = left;
            left *= nums[i];
        }

        for(int i=nums.length-1; i>=0; i--) {
            product[i] = right * product[i];        //left * right
            right *= nums[i];
        }

        return product;
    }

/*
    Time: O(n)
    Space: O(n+n)
 */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];

        int prefix = 1;

        int[] suffix = new int[n];
        suffix[n-1] = nums[n-1];

        for(int i=n-2; i>=0; i--) {
            suffix[i] = nums[i]*suffix[i+1];
        }

        for(int i=0; i<n; i++) {
            int pref = i==0 ? 1 : prefix;
            int suff = i==n-1 ? 1 : suffix[i+1];
            result[i] = pref * suff;

            prefix *= nums[i];
        }

        return result;
    }

/*
    Time: O(n)
    Space: O(n+n+n)
 */
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];

        // prefix array
        prefix[0] = 1;
        for(int i=1; i<n; i++) {
            prefix[i] = prefix[i-1] * nums[i-1];
        }

        // suffix array
        suffix[n-1] = 1;
        for(int i=n-2; i>=0; i--) {
            suffix[i] = suffix[i+1] * nums[i+1];
        }

        // prefix * suffix
        for(int i=0; i<n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

}
