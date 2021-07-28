package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=226517205173943
 *
 *  You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous subarrays that fulfill the following conditions:
 * The value at index i must be the maximum element in the contiguous subarrays, and
 * These contiguous subarrays must either start from or end on index i.
 *
 * Output
 * An array where each index i contains an integer denoting the maximum number of contiguous subarrays of arr[i]
 */
public class ContiguousSubarrays extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{3, 4, 1, 6, 2};
        Assert.assertArrayEquals(new int[]{1, 3, 1, 5, 1},countSubArrays(arr));

        arr = new int[]{2, 4, 7, 1, 5, 3};
        Assert.assertArrayEquals(new int[]{1, 2, 6, 1, 3, 1},countSubArrays(arr));
    }

/*
    Time: O(n^2)
 */
    int[] countSubArrays(int[] arr) {
        int[] R = new int[arr.length];

        for(int i=0; i<arr.length; i++) {
            int count = 1;

            for(int left=i-1; left>=0 && arr[left] <= arr[i]; left--) {
                count++;
            }

            for(int right=i+1; right<arr.length && arr[right] <= arr[i]; right++) {
                count++;
            }

            R[i] = count;
        }

        return R;
    }

}
