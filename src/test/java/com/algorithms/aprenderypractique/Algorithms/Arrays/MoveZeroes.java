package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/move-zeroes/
 *  https://www.youtube.com/watch?v=1PEncepEIoE&ab_channel=KevinNaughtonJr.
 */
public class MoveZeroes extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
        Assert.assertArrayEquals(new int[]{1,3,12,0,0}, nums);

        nums = new int[]{0};
        moveZeroes(nums);
        Assert.assertArrayEquals(new int[]{0}, nums);
    }

    public void moveZeroes(int[] nums) {
        int idx = 0;

        for(int x : nums) {
            if(x != 0) {
                nums[idx++] = x;
            }
        }

        while(idx < nums.length) {
            nums[idx++] = 0;
        }
    }

}
