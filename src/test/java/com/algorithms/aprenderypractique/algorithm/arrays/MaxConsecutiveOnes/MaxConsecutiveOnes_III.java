package com.algorithms.aprenderypractique.algorithm.arrays.MaxConsecutiveOnes;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * https://www.youtube.com/watch?v=97oTiOCuxho
 *
 * II
 * https://www.youtube.com/watch?v=OaMbeeSSv7Y
 * Given a binary array nums, return the maximum number of consecutive 1’s in the array if you can flip at most one 0
 */
public class MaxConsecutiveOnes_III extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(6, findMaxConsecutiveOnes(new int[]{1,1,0,0,1,1,1,1,1} , 1));
        Assert.assertEquals(4, findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1} , 1));
        Assert.assertEquals(4, findMaxConsecutiveOnes(new int[]{1,0,1,1,0} , 1));
        Assert.assertEquals(6, findMaxConsecutiveOnes(new int[]{1,1,1,1,1,1} , 1));     // Edge Case: flip at most one 0 MEANS not flipping any bit is also allowed
        Assert.assertEquals(1, findMaxConsecutiveOnes(new int[]{0,0,0} , 1));

        Assert.assertEquals(6, findMaxConsecutiveOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0} , 2));
        Assert.assertEquals(10, findMaxConsecutiveOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1} , 3));
    }

//  Sliding Window Technique
    public int findMaxConsecutiveOnes(int[] nums, int k) {
        if(nums.length == 1)        return 1;

        int maxOnes = 0, zeroCount = 0;
        int left = 0;

        for(int right = 0; right<nums.length; right++) {
            if(nums[right] == 0)
                zeroCount++;

            while(zeroCount > k) {
                if(nums[left] == 0)     zeroCount--;
                left++;
            }

            maxOnes = Math.max(maxOnes , right-left+1);
        }
        return maxOnes;
    }

}
