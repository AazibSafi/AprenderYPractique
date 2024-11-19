package com.algorithms.aprenderypractique.algorithm.arrays.RotatedSortedArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://takeuforward.org/arrays/left-rotate-an-array-by-d-places
 *  https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
 *
 */
public class RotateArray_Left extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(new int[]{4, 5, 6, 7, 1, 2, 3}, rotate(nums, 3));

        nums = new int[]{-1, -100, 3, 99};
        Assert.assertArrayEquals(new int[]{3, 99, -1, -100}, rotate(nums, 2));

        nums = new int[]{-1};       // Edge Case: if k is greater than the size of array
        Assert.assertArrayEquals(new int[]{-1}, rotate(nums, 2));

        nums = new int[]{1, 2, 3, 4, 5};
        Assert.assertArrayEquals(new int[]{5, 1, 2, 3, 4}, rotate(nums, 4));

        nums = new int[]{41, 73, 89, 7, 10, 1, 59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51};
        Assert.assertArrayEquals(new int[]{77, 97, 58, 1, 86, 58, 26, 10, 86, 51, 41, 73, 89, 7, 10, 1, 59, 58, 84, 77}, rotate(nums, 10));

        nums = new int[]{33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60, 87, 97};
        Assert.assertArrayEquals(new int[]{87, 97, 33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60}, rotate(nums, 13));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public int[] rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;                                    // Edge Case: if k is greater than the size of array
        reverseArray(nums, 0, k-1);        // Reverse first K elements
        reverseArray(nums, k, n-1);             // Reverse elements from K+1 to N
        reverseArray(nums, 0, n-1);       // Reverse full Array
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
