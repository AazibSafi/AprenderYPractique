package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/merge-sorted-array/
 *  https://www.youtube.com/watch?v=P1Ic85RarKY&ab_channel=NeetCode
 */
public class MergeSortedArray extends BaseTest {

    @Test
    public void test() {
        int[] num1 = new int[]{1,2,3,0,0,0}, num2 = new int[]{2,5,6};
        merge(num1, 3, num2, 3);
        Assert.assertArrayEquals(new int[]{1,2,2,3,5,6}, num1);

        num1 = new int[]{1}; num2 = new int[]{};
        merge(num1, 1, num2, 0);
        Assert.assertArrayEquals(new int[]{1}, num1);

        num1 = new int[]{0}; num2 = new int[]{1};
        merge(num1, 0, num2, 1);
        Assert.assertArrayEquals(new int[]{1}, num1);
    }

//  Time: O(m + n)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m+n;

        while(n>0) {
            if(m>0 && nums1[m-1] > nums2[n-1])
                nums1[--idx] = nums1[--m];
            else
                nums1[--idx] = nums2[--n];
        }
    }

}
