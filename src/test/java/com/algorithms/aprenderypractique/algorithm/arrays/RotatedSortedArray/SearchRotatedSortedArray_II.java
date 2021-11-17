package com.algorithms.aprenderypractique.algorithm.arrays.RotatedSortedArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *  https://www.youtube.com/watch?v=YqV0ftqukWo&ab_channel=ProgrammingTutorials
 *
 * @see SearchRotatedSortedArray
 *
 *  Not necessarily distinct values
 */
public class SearchRotatedSortedArray_II extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(search(new int[]{2,5,6,0,0,1,2}, 0));
        Assert.assertFalse(search(new int[]{2,5,6,0,0,1,2}, 3));
        Assert.assertTrue(search(new int[]{1,3,1,1,1}, 3));
        Assert.assertTrue(search(new int[]{1,0,1,1,1}, 0));
        Assert.assertTrue(search(new int[]{5,1,3}, 3));
    }

//  Time: O(logN)
//  Logical condition not completely understood
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        int mid;
        while(left <= right) {
            mid = (left+right)/2;

            if(nums[mid] == target)     return true;

//  If left half is sorted
            if(nums[mid] > nums[left]) {
                if(target >= nums[left] && target < nums[mid])
                    right = mid;
                else
                    left = mid+1;
            }
            else if(nums[mid] < nums[left]) {  //  If right half is sorted
                if(target > nums[mid] && target < nums[left])
                    left = mid+1;
                else
                    right = mid;
            }
            else {
/*
    When we are not sure on which half the value is present,
    nums[start] == nums[mid] == nums[end]
    in case of array {1,3,1,1,1}
 */
                left++;
            }
        }

        return false;
    }

}
