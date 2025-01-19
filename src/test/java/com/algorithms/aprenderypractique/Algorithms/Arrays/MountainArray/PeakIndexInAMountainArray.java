package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/peak-index-in-a-mountain-array
 */
public class PeakIndexInAMountainArray extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, peakIndexInMountainArray(new int[]{0, 1, 0}));
        Assert.assertEquals(1, peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
        Assert.assertEquals(1, peakIndexInMountainArray(new int[]{0, 10, 5, 2}));
    }

/*
    Approach#2 - Binary Search
    Time: O(logn)
    Space: O(1)
    Intuition: Array is sorted ASC <= PEAK >= Array is sorted DESC
 */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length-1;

        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] < arr[mid+1])
                left = mid+1;
            else
                right = mid;
        }
        return left;
    }

/*
    Approach#1 - Linear Scan
    Time: O(n)
    Space: O(1)
 */
    public int peakIndexInMountainArray1(int[] arr) {
        int i = 0;
        while (arr[i] < arr[i + 1]) {
            i++;
        }
        return i;
    }

}
