package com.algorithms.aprenderypractique.Algorithms.Arrays.RotatedSortedArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/search-in-rotated-sorted-array/
 *  https://www.youtube.com/watch?v=oTfPJKGEHcc&ab_channel=TECHDOSE
 *  https://www.youtube.com/watch?v=U8XENwh8Oy8&ab_channel=NeetCode
 *
 *  with distinct values
 */
public class SearchRotatedSortedArray extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4, search(new int[]{4,5,6,7,0,1,2}, 0));
        Assert.assertEquals(7, search(new int[]{7,8,0,1,2,3,4,5,6}, 5));
        Assert.assertEquals(-1, search(new int[]{4,5,6,7,0,1,2}, 3));
    }

/*
  Time: O(logN)

     In Rotated Sorted Array, atleast one of the two half is already sorted.
 */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        int mid;
        while(left <= right) {
            mid = (left+right)/2;

            if(nums[mid] == target)     return mid;

//  If left half is sorted
            if(nums[mid] >= nums[left]) {
                if(target >= nums[left] && target <= nums[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }
            else {  //  If right half is sorted
                if(target >= nums[mid] && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
        }

        return -1;
    }

}
