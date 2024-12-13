package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/sort-array-by-parity/
 */
public class SortArrayByParity extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{4,2,1,3}, sortArrayByParity(new int[]{3,1,2,4}));
        Assert.assertArrayEquals(new int[]{0}, sortArrayByParity(new int[]{0}));
    }

    public int[] sortArrayByParity(int[] nums) {
        int i=0, j=nums.length-1;

        while(i<j) {
            if(nums[i]%2 == 1 && nums[j]%2 == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++; j--;
            }
            else if(nums[i]%2 == 0) i++;
            else if(nums[j]%2 == 1) j--;
        }
        return nums;
    }

}
