package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 *  Find the target in a sorted array
 *  if target not found, return the index of next greater element than target.
 *
 *   Also termed as Next Greater or Element In SortedArray
 */
public class BinarySearch extends BaseTest {

    @Test
    public void testUpperBound() {
        Assert.assertEquals(2, upperBound(new int[]{1, 2, 4, 7},3));
        Assert.assertEquals(1, upperBound(new int[]{1, 3, 4, 7},2));
        Assert.assertEquals(3, upperBound(new int[]{1, 2, 4, 5, 7},5));
        Assert.assertEquals(4, upperBound(new int[]{1, 2, 4, 5, 7},6));
        Assert.assertEquals(4, upperBound(new int[]{1, 2, 4, 5, 7, 7},6));
        Assert.assertEquals(3, upperBound(new int[]{1, 2, 4, 6, 7},6));
    }

    @Test
    public void testLowerBound() {
        Assert.assertEquals(1, lowerBound(new int[]{1, 2, 4, 7},3));
        Assert.assertEquals(0, lowerBound(new int[]{1, 3, 4, 7},2));
        Assert.assertEquals(1, lowerBound(new int[]{1, 1, 3, 4, 7},2));
        Assert.assertEquals(3, lowerBound(new int[]{1, 2, 4, 5, 7},5));
        //Assert.assertEquals(3, lowerBound(new int[]{1, 2, 4, 5, 7},6));
        Assert.assertEquals(3, lowerBound(new int[]{1, 2, 4, 6, 7},6));
        Assert.assertEquals(0, lowerBound(new int[]{2, 4},3));
    }

//  Helper Methods
    public static int upperBound(List<Integer> list, int target) {
        int[] arr = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return upperBound(arr, target);
    }

/*
    Implementation#2
    Binary Search -> O(LogN)
 */
    public static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(target == arr[mid])  return mid;     // Target Found

            if(target >= arr[mid])
                left = mid + 1;
            else {
                ans = mid;              // Note: only this is a minimal change
                right = mid - 1;
            }
        }

        return ans;    // index of upper bound: smallest element and greater than target
    }

    //  Binary Search -> O(LogN)
    public static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(target == arr[mid])  return mid;     // Target Found

            if(target >= arr[mid]) {
                ans = mid;              // Note: only this is a minimal change
                left = mid + 1;
            }
            else
                right = mid - 1;
        }

        return ans;    // index of lower bound: greatest element and smaller than target
    }

/*
    Implementation#1
    Binary Search -> O(LogN)
 */
    public static int upperBound1(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(target == arr[mid])  return mid;

            if(target > arr[mid])
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;    // index of target or the next greater element than target
    }

    public static int lowerBound1(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(target == arr[mid])  return mid;

            if(target > arr[mid])
                left = mid + 1;
            else
                right = mid - 1;
        }

        return right;    // index of target or the next smaller element than target
    }

}
