package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/find-peak-element
 */
public class FindPeakElement extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, findPeakElement(new int[]{1,2,3,1}));
        Assert.assertEquals(5, findPeakElement(new int[]{1,2,1,3,5,6,4}));
        Assert.assertEquals(0, findPeakElement(new int[]{9,2,1,8,5,6,4}));
        Assert.assertEquals(1, findPeakElement(new int[]{10,20,15,30,16,2,8,6}));
        Assert.assertEquals(1, findPeakElement(new int[]{21,30,14}));
        Assert.assertEquals(2, findPeakElement(new int[]{7,16,32}));
    }

/*
    Approach#4 - Iterative Binary Search
    Time: O(logn)
    Space: O(1)
*/
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = (left + right) / 2;

            if(nums[mid] > nums[mid+1])
                right = mid;        // Peak must be in left half (including mid)
            else
                left = mid + 1;      // Peak must be in right half (excluding mid)
        }
        return left;
    }

/*
    Approach#3 - Recursive Binary Search
    Time: O(logn)
    Space: O(logn)
*/
    public int findPeakElement3(int[] nums) {
        return findPeakElement3(nums, 0, nums.length-1);
    }

    public int findPeakElement3(int[] nums, int left, int right) {
        if(left == right) return left;
        int mid = (left + right) / 2;

        if(nums[mid] > nums[mid+1])
            return findPeakElement3(nums, left, mid);        // Peak must be in left half (including mid)

        return findPeakElement3(nums, mid+1, right);     // Peak must be in right half (excluding mid)
    }

/*
    Approach#2 - Linear Scan - Early Return
    Find the first peak of the array
    Time: O(n)
    Space: O(1)
*/
    public int findPeakElement2(int[] nums) {
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] > nums[i+1])
                return i;
        }
        return nums.length-1;
    }

/*
    Approach#1 - Linear Scan
    Find the max number of the array, that is the peak element
    Time: O(n)
    Space: O(1)
*/
    public int findPeakElement1(int[] nums) {
        int iMax = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > nums[iMax])
                iMax = i;
        }
        return iMax;
    }

}
