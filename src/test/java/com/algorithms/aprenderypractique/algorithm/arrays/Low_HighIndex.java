package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.educative.io/m/find-low-high-index
 */
public class Low_HighIndex extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1,2,5,5,5,5,5,5,5,5,20};
        Assert.assertArrayEquals(new int[]{10,10}, findLowHighIndex(arr,20));

        arr = new int[]{1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6};
        Assert.assertArrayEquals(new int[]{11,14}, findLowHighIndex(arr,4) );

        arr = new int[]{1, 2, 3, 4};
        Assert.assertArrayEquals(new int[]{-1,-1}, findLowHighIndex(arr,5) );
    }

    public int[] findLowHighIndex(int[] arr, int key) {
        int lowIndex = findLowIndex(arr,key);
        int highIndex = findHighIndex(arr,key);
        return new int[]{lowIndex,highIndex};
    }

    public int findLowIndex(int[] arr, int key) {

        int low = 0, high = arr.length-1;
        int middle;

        while(low<=high) {

            //middle = low + (high-low)/2;
            middle = (low+high)/2;

            if(key <= arr[middle]) {
                high = middle-1;
            }
            else {
                low = middle+1;     // if key is less than middle
            }

        }

        return (low < arr.length && arr[low] == key) ? low : -1;
    }
/*
    Only diff is Equal check in both low and high functions
 */
    public int findHighIndex(int[] arr, int key) {

        int low = 0, high = arr.length-1;
        int middle;

        while(low<=high) {

            middle = low + (high-low)/2;

            if(key < arr[middle]) {
                high = middle-1;
            }
            else {
                low = middle+1;     // if key is less or equal than middle
            }

        }

        return (high < arr.length && arr[high] == key) ? high : -1;
    }

}
