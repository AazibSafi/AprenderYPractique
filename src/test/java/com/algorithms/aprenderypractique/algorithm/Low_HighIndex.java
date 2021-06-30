package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Test;

/**
 *  https://www.educative.io/m/find-low-high-index
 */
public class Low_HighIndex extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1,2,5,5,5,5,5,5,5,5,20};
        CommonHelper.printArray( findLowHighIndex(arr,20) );
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

            middle = low + (high-low)/2;

            if(key <= arr[middle]) {
                high = middle-1;
            }
            else {
                low = middle+1;     // if key is less than middle
            }

        }

        return arr[low] == key ? low : -1;
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

        return arr[high] == key ? high : -1;
    }

}
