package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.Two_Three_Sum;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/two-sum-ii-input-array-is-sorted
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

/*
    Approach#2: using sorted property. Two Pointers
    Time: O(n)
    Space: O(1)
 */
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left <= right) {
            int sum = nums[left] + nums[right];

            if(sum == target)   return new int[]{left+1, right+1};

            if(sum > target)    right--;
            else                left++;
        }

        return new int[]{}; // No solution found
    }


/*
    Approach#1: Using Set [Not Expected Solution -> Since array is already sorted]
    Time: O(n)
    Space: O(n)
 */
    public int[] twoSum1(int[] nums, int target) {
        int[] res = new TwoSum().twoSum(nums, target);
        return new int[]{res[0]+1, res[1]+1};   // bcz given array is 1-indexed
    }

}
