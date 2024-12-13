package com.algorithms.aprenderypractique.Algorithms.Arrays.RotatedSortedArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
 *
 *      https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solutions/5758737/java-beats-100-easy-and-simple-approach/?envType=problem-list-v2&envId=xi4ci4ig
 */
public class FindMinimumInRotatedSortedArray extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, findMin(new int[]{3,4,5,1,2}));
        Assert.assertEquals(0, findMin(new int[]{4,5,6,7,0,1,2}));
        Assert.assertEquals(11, findMin(new int[]{11,13,15,17}));
        Assert.assertEquals(1, findMin(new int[]{1}));
    }

    public int findMin(int[] nums) {
        int left=0, right=nums.length-1;
        int min = Integer.MAX_VALUE;

        while(left <= right) {
// If the array is already sorted
            if(nums[left] <= nums[right]) {
                min = Math.min(min, nums[left]);
                return min;
            }

            int mid = (left+right)/2;

// Check which half is sorted
            if(nums[left] <= nums[mid]) {
                min = Math.min(min, nums[left]);
                left = mid+1;       // Move to the right half
            }
            else {  // Right half is sorted
                min = Math.min(min, nums[mid]);
                right = mid-1;      // Move to the left half
            }
        }

        return min;
    }

}
