package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
 *  https://www.educative.io/m/find-low-high-index
 */
public class Low_HighIndex extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 3, 3};
        Assert.assertArrayEquals(new int[]{2, 4}, findLowHighIndex(arr,3));

        arr = new int[]{1, 2, 5, 5, 5, 5, 5, 5, 5, 5, 20};
        Assert.assertArrayEquals(new int[]{10, 10}, findLowHighIndex(arr,20));

        arr = new int[]{1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6};
        Assert.assertArrayEquals(new int[]{11, 14}, findLowHighIndex(arr,4) );

        arr = new int[]{1, 2, 3, 4};
        Assert.assertArrayEquals(new int[]{-1, -1}, findLowHighIndex(arr,5) );

        arr = new int[]{5, 7, 7, 8, 8, 10};
        Assert.assertArrayEquals(new int[]{3, 4}, findLowHighIndex(arr,8) );

        arr = new int[]{5, 7, 7, 8, 8, 10};
        Assert.assertArrayEquals(new int[]{-1, -1}, findLowHighIndex(arr,6) );

        Assert.assertArrayEquals(new int[]{-1, -1}, findLowHighIndex(new int[]{},0) );
        Assert.assertArrayEquals(new int[]{-1, -1}, findLowHighIndex(new int[]{1},0) );
    }

/*
    Time: O(logN) + O(logN) = O(logN)
 */
    public int[] findLowHighIndex(int[] arr, int key) {
        if(arr.length == 0) return new int[]{-1, -1};
        int lowIndex = findLowIndex(arr, key);      // O(logN)
        int highIndex = findHighIndex(arr, key);    // O(logN)
        return new int[]{lowIndex, highIndex};
    }

    public int findLowIndex(int[] arr, int key) {
        int low = 0, high = arr.length-1, idx = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(key <= arr[mid])
                high = mid - 1;
            else
                low = mid + 1;     // if key is greater than middle

            if(key == arr[mid]) idx = mid;
        }

        return idx;
    }

//      Only diff is EQUAL CHECK in both low and high functions
    public int findHighIndex(int[] arr, int key) {
        int low = 0, high = arr.length-1, idx = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(key < arr[mid])
                high = mid - 1;
            else
                low = mid + 1;     // if key is greater or equal than middle

            if(key == arr[mid]) idx = mid;
        }

        return idx;
    }

}
