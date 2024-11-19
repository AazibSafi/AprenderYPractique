package com.algorithms.aprenderypractique.algorithm.arrays.RotatedSortedArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/rotate-array
 *  Logic: https://www.youtube.com/watch?v=BHr381Guz3Y
 *  Code: https://www.youtube.com/watch?v=gmu0RA5_zxs
 */
public class RotateArray_Right extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, rotate(nums, 3));

        nums = new int[]{-1, -100, 3, 99};
        Assert.assertArrayEquals(new int[]{3, 99, -1, -100}, rotate(nums, 2));

        nums = new int[]{-1};       // Edge Case: if k is greater than the size of array
        Assert.assertArrayEquals(new int[]{-1}, rotate(nums, 2));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public int[] rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;                                  // Edge Case: if k is greater than the size of array
        reverseArray(nums, 0, n-1);      // Reverse full Array
        reverseArray(nums, 0, k-1);      // Reverse first K elements
        reverseArray(nums, k, n-1);           // Reverse rest of the elements from K+1 to N
        return nums;
    }

    void reverseArray(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++; end--;
        }
    }

}
