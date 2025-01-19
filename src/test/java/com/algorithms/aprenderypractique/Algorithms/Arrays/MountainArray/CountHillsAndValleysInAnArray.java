package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/count-hills-and-valleys-in-an-array
 */
public class CountHillsAndValleysInAnArray extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, countHillValley(new int[]{2,4,1,1,6,5}));
        Assert.assertEquals(0, countHillValley(new int[]{6,6,5,5,4,1}));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public int countHillValley(int[] nums) {
        int count = 0;
        int left = nums[0];
        for(int i=1; i<nums.length-1; i++) {
            boolean isHill = left < nums[i] && nums[i] > nums[i+1];
            boolean isValley = left > nums[i] && nums[i] < nums[i+1];

            if(isHill || isValley) {
                count++;
                left = nums[i];
            }
        }
        return count;
    }

}
