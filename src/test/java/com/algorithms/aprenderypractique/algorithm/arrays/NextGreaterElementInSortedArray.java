package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 *  Find the target in a sorted array
 *  if target not found, return the next greater element than target.
 */
public class NextGreaterElementInSortedArray extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, upperBound(new int[]{1, 2, 4, 7},3));
        Assert.assertEquals(1, upperBound(new int[]{1, 3, 4, 7},2));
        Assert.assertEquals(3, upperBound(new int[]{1, 2, 4, 5, 7},5));
        Assert.assertEquals(4, upperBound(new int[]{1, 2, 4, 5, 7},6));
    }

    public int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while(left < right) {
            int mid = left + (right-left)/2;

            if(target > arr[mid])   left = mid + 1;
            else right = mid;
        }

        return left;
    }

}
