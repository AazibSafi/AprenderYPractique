package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/squares-of-a-sorted-array/
 *  https://www.youtube.com/watch?v=4eWKHLSRHPY
 */
public class SquareOfSortedArray extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{-4,-1,0,3,10};
        Assert.assertArrayEquals(new int[]{0,1,9,16,100}, sortedSquares(nums));

        nums = new int[]{-7,-3,2,3,11};
        Assert.assertArrayEquals(new int[]{4,9,9,49,121}, sortedSquares(nums));
    }

/*
      Time: O(N)
      Space: O(N)
 */
    public int[] sortedSquares(int[] nums) {
        int[] output = new int[nums.length];
        int left = 0;
        int right = nums.length-1;

        for(int i=output.length-1; i>=0; i--) {
            if( Math.abs(nums[left]) > nums[right] ) {
                output[i] = nums[left] * nums[left];
                left++;
            }
            else {
                output[i] = nums[right] * nums[right];
                right--;
            }
        }
        return output;
    }

}
