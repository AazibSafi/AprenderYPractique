package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.Two_Three_Sum;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/two-sum-ii-input-array-is-sorted
 *
 *  https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/solutions/2128501/two-pointers-visual-explanation-java/
 *
 */
public class TwoSumII_Sorted extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{1,2}, twoSum(new int[]{2,7,11,15}, 9));
        Assert.assertArrayEquals(new int[]{1,3}, twoSum(new int[]{2,3,4}, 6));
        Assert.assertArrayEquals(new int[]{1,2}, twoSum(new int[]{-1,0}, -1));
    }

    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while ( nums[left] + nums[right] != target) {        // bcz there is exactly one solution
            if (nums[left] + nums[right] < target)
                left++;
            else
                right--;
        }

        return new int[] {left+1, right+1};         // bcz given array is 1-indexed
    }

}
